package io.akaitsuki.kafka.example.consumer.event;

/**
 * Created by jliu on 2/20/2017.
 */
public class HelloResponseEvent {
    private String id;
    private String requestId;
    private String responseMsg;
    private HelloRequestEvent requestEvent;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getResponseMsg() {
        return responseMsg;
    }

    public void setResponseMsg(String responseMsg) {
        this.responseMsg = responseMsg;
    }

    public HelloRequestEvent getRequestEvent() {
        return requestEvent;
    }

    public void setRequestEvent(HelloRequestEvent requestEvent) {
        this.requestEvent = requestEvent;
    }

    @Override
    public String toString() {
        return "HelloResponseEvent{" +
                "id='" + id + '\'' +
                ", requestId='" + requestId + '\'' +
                ", responseMsg='" + responseMsg + '\'' +
                ", requestEvent=" + requestEvent +
                '}';
    }
}
