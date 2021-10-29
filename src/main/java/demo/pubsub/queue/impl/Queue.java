package demo.pubsub.queue.impl;

import demo.pubsub.queue.exceptions.InvalidFormatException;
import demo.pubsub.queue.model.Message;

import java.util.*;

public class Queue{

    class TTLModel implements Comparable<TTLModel>{
        long expirytime;
        int offsetid;
        TTLModel(long expirytime,int offsetid){
            this.expirytime=expirytime;
            this.offsetid=offsetid;
        }

        @Override
        public int compareTo(TTLModel o) {
            return (int)(this.expirytime-o.expirytime);
        }
    }

    private LinkedHashMap<Integer, Message> messageList;
    Map<String,List<QueueSubscriber>> regexToSubscriber;
    PriorityQueue<TTLModel> ttl_pq=new PriorityQueue<>();
    int currentoffset;

    private int capacity;

    public Queue(int size){
        this.capacity=size;
        this.currentoffset=0;
        this.messageList=new LinkedHashMap<>();
        this.regexToSubscriber=new HashMap<>();
    }

    public void publish(Message message) throws Exception {
       // Message message=validateMessage(messagetext);
        currentoffset++;
        //post the new message to queue and ttl q
        messageList.put(currentoffset,message);
        ttl_pq.offer(new TTLModel(System.currentTimeMillis()+message.getTtl(),currentoffset));

        String messageheader=message.getMessageHeader();
        for(String regex : regexToSubscriber.keySet()){
           if(messageheader.matches(regex)){
               for(QueueSubscriber subscriber : regexToSubscriber.get(regex)){
                   subscriber.consume(message);
                   subscriber.setCurrentOffset(currentoffset);
               }
           }
        }
        if(messageList.size()==capacity){
            messageList.remove(0);
        }
    }


    public Message validateMessage(String message) throws Exception {
        try{
            Message newmessage=new Message("bb",1l);
            newmessage.setMessageHeader(message);
            newmessage.setTtl(100l);
            return newmessage;
        }catch(Exception ex){
            throw new InvalidFormatException(ex);
        }
    }

    public void subscribe(QueueSubscriber subscriber){
        regexToSubscriber.putIfAbsent(subscriber.getMessageHeaderRegex(),new ArrayList<>());
        regexToSubscriber.get(subscriber.getMessageHeaderRegex()).add(subscriber);
        deliverMessagesToNewSubscriber(subscriber);

    }

    public void deliverMessagesToNewSubscriber(QueueSubscriber subscriber){
        evictTTLqueue();
          for(Map.Entry<Integer,Message> messageEntry : messageList.entrySet()){
              if(messageEntry.getValue().getMessageHeader().matches(subscriber.getMessageHeaderRegex())){
                  subscriber.consume(messageEntry.getValue());
                  subscriber.setCurrentOffset(messageEntry.getKey());
              }
          }
    }

    public void evictTTLqueue(){
        while(!ttl_pq.isEmpty() && ttl_pq.peek().expirytime<System.currentTimeMillis()){
            TTLModel model=ttl_pq.poll();
            messageList.remove(model.offsetid);
        }
    }


}
