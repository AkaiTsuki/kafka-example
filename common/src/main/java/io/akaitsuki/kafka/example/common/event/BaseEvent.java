package io.akaitsuki.kafka.example.common.event;

/**
 * Created by jliu on 2/21/2017.
 */
public class BaseEvent {
    /**
     * unique id for current event
     */
    protected String id;

    /**
     * number of times already attempted
     */
    protected int attempts;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAttempts() {
        return attempts;
    }

    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }
}
