package io.akaitsuki.kafka.example.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;

/**
 * Created by Jiachi on 2/18/2017.
 */
public class TestConsumer {

    @KafkaListener(id = "foo", topics = "test", group = "group1")
    public void consume(ConsumerRecord<?, ?> record) {
        System.out.println(record);
    }

}
