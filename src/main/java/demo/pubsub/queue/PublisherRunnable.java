package demo.pubsub.queue;

import demo.pubsub.queue.impl.Queue;
import demo.pubsub.queue.impl.QueuePublisher;
import demo.pubsub.queue.model.Message;
import lombok.SneakyThrows;

import java.util.Arrays;
import java.util.List;

public class PublisherRunnable  implements Runnable{

    Queue queue;
    QueuePublisher publisher;

    public PublisherRunnable(Queue queue){
        this.queue=queue;
        this.publisher=new QueuePublisher(queue);
    }

    @SneakyThrows
    public void run() {
        List<Message> messageList= Arrays.asList(new Message("abc", (long) (60*1000)),new Message("cdada", (long) (60*1000)),new Message("abcdef", (long) (60*1000)));

        int currindex=0;
        while(true && currindex<messageList.size()){
            Thread.sleep(5000l);
            publisher.publishMessage(messageList.get(currindex));
            currindex++;
        }
    }
}
