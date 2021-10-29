package demo.pubsub.queue.model;


public class Message {
    private String messageHeader;

    public Long getTtl() {
        return ttl;
    }

    public void setTtl(Long ttl) {
        this.ttl = ttl;
    }

    private Long ttl;
    private String attr1;
    private String attr2;

    public Message(String messageHeader,Long ttl) {
        this.messageHeader=messageHeader;
        this.ttl=ttl;
    }

    public String getMessageHeader() {
        return messageHeader;
    }

    public void setMessageHeader(String messageHeader) {
        this.messageHeader = messageHeader;
    }

    public String getAttr1() {
        return attr1;
    }

    public void setAttr1(String attr1) {
        this.attr1 = attr1;
    }

    public String getAttr2() {
        return attr2;
    }

    public void setAttr2(String attr2) {
        this.attr2 = attr2;
    }
}
