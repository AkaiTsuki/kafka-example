package io.akaitsuki.kafka.example.consumer.event;

/**
 * Created by Jiachi on 2/18/2017.
 */
public class HelloRequestEvent {
    private String id;
    private String msg;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "HelloRequestEvent{" +
                "id='" + id + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
