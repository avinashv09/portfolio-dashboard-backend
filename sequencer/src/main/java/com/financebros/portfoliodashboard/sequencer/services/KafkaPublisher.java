package com.financebros.portfoliodashboard.sequencer.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaPublisher {
    private final KafkaTemplate<byte[],byte[]> kafkaTemplate;
    public void publishMessage(String topic, byte[] data) {
        kafkaTemplate.send(topic, data);
    }
}
