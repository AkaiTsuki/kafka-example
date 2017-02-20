package io.akaitsuki.kafka.example.consumer;

import io.akaitsuki.kafka.example.common.event.HelloRequestEvent;
import io.akaitsuki.kafka.example.common.event.HelloResponseEvent;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.UUID;

/**
 * Created by Jiachi on 2/18/2017.
 */
@Service
public class TestConsumer {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @KafkaListener(id = "foo", topics = "test", group = "group1")
    public void consume(ConsumerRecord<String, HelloRequestEvent> record) {
        System.out.println("Receive raw event:" + record);

        HelloRequestEvent requestEvent = record.value();
        System.out.println("Event Body:" + requestEvent);

        HelloResponseEvent event = new HelloResponseEvent();
        event.setRequestId(requestEvent.getId());
        event.setId(UUID.randomUUID().toString());

        if(requestEvent.getMsg().endsWith("-fail")){
            event.setResponseMsg("Fail");
            event.setRequestEvent(requestEvent);
        } else {
            event.setResponseMsg("Done");
        }


        ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send("test-response", event);
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
