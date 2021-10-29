package demo.pubsub.queue;

import demo.pubsub.queue.impl.Queue;
import demo.pubsub.queue.impl.QueuePublisher;
import demo.pubsub.queue.impl.QueueSubscriber;
import demo.pubsub.queue.model.Message;

public class Driver {

    public static void main(String[] args) {

       /* Queue queue=new Queue(5);
        QueuePublisher publisher=new QueuePublisher(queue);
        QueueSubscriber subscriber1=new QueueSubscriber("1","[a].*");
        QueueSubscriber subscriber2=new QueueSubscriber("2","[b].*");
        QueueSubscriber subscriber3=new QueueSubscriber("3","[c].*");
        queue.subscribe(subscriber1);
        queue.subscribe(subscriber2);
        queue.subscribe(subscriber3);
        publisher.publishMessage(new Message("abc", (long) (60*1000)));
        publisher.publishMessage(new Message("cdada", (long) (60*1000)));
        publisher.publishMessage(new Message("abcdef", (long) (60*1000)));
        QueueSubscriber subscriber4=new QueueSubscriber("4","[a].*");
        queue.subscribe(subscriber4);*/

        Queue queue=new Queue(5);
        PublisherRunnable pr=new PublisherRunnable(queue);
        ConsumerRunnable cr1=new ConsumerRunnable(queue,new QueueSubscriber("1","[a].*"));
        ConsumerRunnable cr2=new ConsumerRunnable(queue,new QueueSubscriber("2","[b].*"));
        ConsumerRunnable cr3=new ConsumerRunnable(queue,new QueueSubscriber("3","[c].*"));
        Thread t1=new Thread(pr);
        Thread t2=new Thread(cr1);
        Thread t3=new Thread(cr2);
        Thread t4=new Thread(cr3);
        t2.start();
        t3.start();
        t4.start();
        t1.start();
    }
}
