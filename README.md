# kafka-example
Spring Boot Kafka Example

## How to Run
```
// Start up Kafka Server
bin/zookeeper-server-start.sh config/zookeeper.properties
bin/kafka-server-start.sh config/server.properties

// Create test topic - no replication, one partition
bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic test
bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic test-response

// Verify topic
bin/kafka-topics.sh --list --zookeeper localhost:2181

// build project
mvn clean install

// Run producer
cd producer
mvn spring-boot:run

// Run consumer
cd consumer
mvn spring-boot:run

// Fire event
use browser access following url:
http://localhost:8080/kafka/hello/your_name

// Verify event consumed
go to consumer shell, you should see event print out, e.g:
ConsumerRecord(topic = test, partition = 0, offset = 4, CreateTime = 1487443798782, checksum = 2776761203, serialized key size = -1, serialized value size = 15, key = null, value = Hello, your_name)

// Trigger event retry by append -fail suffix to your name
use browser access following url:
http://localhost:8080/kafka/hello/your_name-fail

// You should see a list of same events with different attempt value
```
