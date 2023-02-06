package com.financebros.portfoliodashboard.sequencer;

import com.financebros.portfoliodashboard.sequencer.services.SequencerServiceImp;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
public class SequencerApp {
    private KafkaTemplate<byte[], byte[]> kafkaTemplate;
    @Autowired
    SequencerApp(KafkaTemplate<byte[], byte[]> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
        Server server = ServerBuilder
                .forPort(8080)
                .addService(new SequencerServiceImp(this.kafkaTemplate)).build();
        try {
            log.info("Server is started!..");
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            server.awaitTermination();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
