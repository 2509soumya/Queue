package demo.pubsub.queue;

import demo.pubsub.queue.impl.QueueSubscriber;
import demo.pubsub.queue.impl.Queue;

public class ConsumerRunnable implements Runnable{

    QueueSubscriber subscriber;
    Queue queue;

    public ConsumerRunnable(Queue queue,QueueSubscriber subscriber){
       this.subscriber=subscriber;
       this.queue=queue;
    }

    @Override
    public void run() {
        queue.subscribe(subscriber);
        while(true){

        }
    }
}
