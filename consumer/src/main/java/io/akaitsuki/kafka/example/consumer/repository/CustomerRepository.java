package io.akaitsuki.kafka.example.consumer.repository;

import io.akaitsuki.kafka.example.consumer.domain.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by jliu on 2/21/2017.
 */
@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {
    List<Customer> findByFirstName(String firstName);
}
