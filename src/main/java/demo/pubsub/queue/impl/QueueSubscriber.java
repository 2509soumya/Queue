package demo.pubsub.queue.impl;

import demo.pubsub.queue.interfaces.Subscriber;
import demo.pubsub.queue.model.Message;

import java.util.List;


public class QueueSubscriber implements Subscriber {

    private String subscriberId;

    private String messageHeaderRegex;

    private List<String> dependentOnSubscribers;

    private int currentOffset;

    public QueueSubscriber(String subscriberId, String messageHeaderRegex){
        this.subscriberId=subscriberId;
        this.messageHeaderRegex=messageHeaderRegex;
    }

    public String getMessageHeaderRegex() {
        return messageHeaderRegex;
    }

    public void setMessageHeaderRegex(String messageHeaderRegex) {
        this.messageHeaderRegex = messageHeaderRegex;
    }

    public List<String> getDependentOnSubscribers() {
        return dependentOnSubscribers;
    }

    public void setDependentOnSubscribers(List<String> dependentOnSubscribers) {
        this.dependentOnSubscribers = dependentOnSubscribers;
    }

    @Override
    public void consume(Message message) {
        System.out.println("Subscriber " + subscriberId + " consumed message with header : "+message.getMessageHeader());
    }

    public String getSubscriberId() {
        return subscriberId;
    }

    public void setSubscriberId(String subscriberId) {
        this.subscriberId = subscriberId;
    }

    public int getCurrentOffset() {
        return currentOffset;
    }

    public void setCurrentOffset(int currentOffset) {
        this.currentOffset = currentOffset;
    }
}
