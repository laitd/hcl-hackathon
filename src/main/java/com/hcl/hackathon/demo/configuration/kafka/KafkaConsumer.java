package com.hcl.hackathon.demo.configuration.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {
    @KafkaListener(topics = "TEST-TOPIC", groupId = "test-group")
    public void listen(String message) {
        System.out.println("Received Message in group my-group: " + message);
    }
}
