package demo.pubsub.queue.exceptions;

public class InvalidFormatException extends Exception{

    public InvalidFormatException(Exception ex) throws Exception {
        throw new Exception("Message format is not JSONType",ex);
    }
}
