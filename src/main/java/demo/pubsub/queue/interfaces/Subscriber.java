package demo.pubsub.queue.interfaces;

import demo.pubsub.queue.model.Message;

public interface Subscriber {
    void consume(Message message);
}
