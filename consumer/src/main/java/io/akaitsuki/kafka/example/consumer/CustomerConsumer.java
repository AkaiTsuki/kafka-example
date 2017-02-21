package io.akaitsuki.kafka.example.consumer;

import io.akaitsuki.kafka.example.common.event.customer.CustomerRequestEvent;
import io.akaitsuki.kafka.example.consumer.domain.Customer;
import io.akaitsuki.kafka.example.consumer.repository.CustomerRepository;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by jliu on 2/21/2017.
 */
@Service
public class CustomerConsumer {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    private CustomerRepository customerRepository;

    @KafkaListener(id = "customer", topics = "customer", group = "customerGroup", containerFactory = "customerListenerContainerFactory")
    public void consume(ConsumerRecord<String, CustomerRequestEvent> record){

        CustomerRequestEvent event = record.value();
        System.out.println(event);

        Customer customer = new Customer();
        customer.setFirstName(event.getFirstName());
        customer.setLastName(event.getLastName());

        Customer savedCustomer = customerRepository.save(customer);
    }
}
