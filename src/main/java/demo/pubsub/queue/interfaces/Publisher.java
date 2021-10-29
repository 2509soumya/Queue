package demo.pubsub.queue.interfaces;

import demo.pubsub.queue.model.Message;

public interface Publisher {
    void publishMessage(Message message) throws Exception;
}
