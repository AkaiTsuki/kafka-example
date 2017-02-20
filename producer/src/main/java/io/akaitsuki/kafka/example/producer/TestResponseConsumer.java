package io.akaitsuki.kafka.example.producer;

import io.akaitsuki.kafka.example.producer.event.HelloResponseEvent;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * Created by jliu on 2/20/2017.
 */
@Service
public class TestResponseConsumer {

    @KafkaListener(id = "response-consumer-1", topics = "test-response", group = "response1")
    public void consume(ConsumerRecord<String, HelloResponseEvent> record) {
        System.out.println("Receive raw event:" + record);
        HelloResponseEvent requestEvent = record.value();
        System.out.println("Event Body:" + requestEvent);
    }
}
