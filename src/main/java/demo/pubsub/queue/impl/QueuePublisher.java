package demo.pubsub.queue.impl;

import demo.pubsub.queue.impl.Queue;
import demo.pubsub.queue.interfaces.Publisher;
import demo.pubsub.queue.model.Message;

public class QueuePublisher implements Publisher {

    Queue genericq;

    public QueuePublisher(Queue queue){
        this.genericq=queue;
    }

    @Override
    public void publishMessage(Message message){
        try{
            genericq.publish(message);
        }catch (Exception ex){
            System.out.println("Queue threw exception: "+ex.getMessage());
        }
    }
}
