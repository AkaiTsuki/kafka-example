package io.akaitsuki.kafka.example.producer;

import io.akaitsuki.kafka.example.producer.event.HelloEvent;
import io.akaitsuki.kafka.example.producer.event.HelloResponseEvent;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

/**
 * Created by jliu on 2/20/2017.
 */
@Service
public class TestResponseConsumer {

    private static final Integer MAX_ATTEMPT = 3;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @KafkaListener(id = "response-consumer-1", topics = "test-response", group = "response1")
    public void consume(ConsumerRecord<String, HelloResponseEvent> record) {
        System.out.println("Receive raw event:" + record);
        HelloResponseEvent responseEvent = record.value();
        System.out.println("Event Body:" + responseEvent);

        if(responseEvent.getResponseMsg().equals("Fail") && responseEvent.getRequestEvent().getAttempts() < MAX_ATTEMPT) {
            retry(responseEvent.getRequestEvent());
        }
    }

    private void retry(HelloEvent event) {
        event.setAttempts(event.getAttempts() + 1);
        ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send("test", event);
        future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {

            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("fail to send event:" + event);
            }

            @Override
            public void onSuccess(SendResult<String, Object> result) {
                System.out.println("event send success:" + result.getProducerRecord().value());
            }
        });
    }
}
