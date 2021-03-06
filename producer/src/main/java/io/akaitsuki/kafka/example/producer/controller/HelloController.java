package io.akaitsuki.kafka.example.producer.controller;

import io.akaitsuki.kafka.example.common.event.HelloRequestEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * Created by Jiachi on 2/18/2017.
 */
@RestController
@RequestMapping("/kafka")
public class HelloController {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @RequestMapping("/hello/{name}")
    public HelloRequestEvent hello(@PathVariable("name") String name){
        HelloRequestEvent event = new HelloRequestEvent();
        event.setId(UUID.randomUUID().toString());
        event.setMsg("Hello, "+ name);
        event.setAttempts(0);

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

        return event;
    }
}
