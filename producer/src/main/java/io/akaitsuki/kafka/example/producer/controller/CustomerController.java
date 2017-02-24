package io.akaitsuki.kafka.example.producer.controller;

import io.akaitsuki.kafka.example.common.event.customer.CustomerRequestEvent;
import io.akaitsuki.kafka.example.producer.domain.Customer;
import io.akaitsuki.kafka.example.producer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.StringUtils;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * Created by jliu on 2/21/2017.
 */
@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping("")
    public CustomerRequestEvent enroll(@RequestBody CustomerRequestEvent event){

        if(StringUtils.isEmpty(event.getFirstName()) || StringUtils.isEmpty(event.getLastName())){
            throw new RuntimeException("Invalid Customer Request Event");
        }

        event.setAttempts(0);
        event.setId(UUID.randomUUID().toString());

        Customer customer = new Customer();
        customer.setLastName(event.getLastName());
        customer.setFirstName(event.getFirstName());
        customer = customerRepository.save(customer);

        event.setCustomerId(customer.getId());

        ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send("customer", event);
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
