package io.akaitsuki.kafka.example.consumer;

import io.akaitsuki.kafka.example.common.event.customer.CustomerRequestEvent;
import io.akaitsuki.kafka.example.consumer.domain.Customer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static org.elasticsearch.common.xcontent.XContentFactory.*;

/**
 * Created by jliu on 2/21/2017.
 */
@Service
public class CustomerConsumer {

    @Autowired
    private TransportClient transportClient;

    @KafkaListener(id = "customer", topics = "customer", group = "customerGroup", containerFactory = "customerListenerContainerFactory")
    public void consume(ConsumerRecord<String, CustomerRequestEvent> record) throws IOException {
        CustomerRequestEvent event = record.value();
        System.out.println(event);

        Customer customer = new Customer();
        customer.setId(event.getCustomerId());
        customer.setLastName(event.getLastName());
        customer.setFirstName(event.getFirstName());
        IndexResponse response = transportClient.prepareIndex("bookstore", "customer", customer.getId())
                .setSource(jsonBuilder()
                                .startObject()
                                .field("firstName", customer.getFirstName())
                                .field("lastName", customer.getLastName())
                                .endObject()
                )
                .get();

        System.out.println("Saved to Elastic Search: " + String.format("index=%s, type=%s, id=%s, status=%s", response.getIndex(), response.getType(), response.getId(), response.status()));
    }
}
