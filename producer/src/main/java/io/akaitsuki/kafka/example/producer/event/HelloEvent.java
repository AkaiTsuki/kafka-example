package io.akaitsuki.kafka.example.producer.event;

/**
 * Created by Jiachi on 2/18/2017.
 */
public class HelloEvent {
    private String id;
    private String msg;
    private int attempts;

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

    public int getAttempts() {
        return attempts;
    }

    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }

    @Override
    public String toString() {
        return "HelloEvent{" +
                "id='" + id + '\'' +
                ", msg='" + msg + '\'' +
                ", attempts=" + attempts +
                '}';
    }
}
