package io.akaitsuki.kafka.example.common.event.customer;

import io.akaitsuki.kafka.example.common.event.BaseEvent;

/**
 * Created by jliu on 2/21/2017.
 */
public class CustomerRequestEvent extends BaseEvent {
    private String firstName;
    private String lastName;
    private CustomerEventType type;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "CustomerRequestEvent{" +
                "id" + id +  '\'' +
                "attempts" + attempts +  '\'' +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
