package io.akaitsuki.kafka.example.common.event.customer;

/**
 * Created by jliu on 2/21/2017.
 */
public enum CustomerEventType {
    CREATE("create"),
    READ("read");

    CustomerEventType(String type) {
        this.type = type;
    }

    private final String type;

    public String getType() {
        return type;
    }
}
