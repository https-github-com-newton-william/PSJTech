package org.psjtech.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaConsumerService {
    @KafkaListener(topics = "test-topic", groupId = "my-group")
    public void consumeMessage(String message) {
        log.info("📩 Received: " + message);
    }
}
