package com.financebros.portfoliodashboard.sequencer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.kafka.core.KafkaTemplate;

import java.io.IOException;

@SpringBootApplication
@Slf4j
public class SequencerApplication implements CommandLineRunner {
    public static long engineTime;
    @Autowired
    private ApplicationContext applicationContext;
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(SequencerApplication.class);
        springApplication.run(args);
    }

    @Override
    public void run(String... args) {
        log.info("Application started...");
        new SequencerApp(applicationContext.getBean(KafkaTemplate.class));
    }
}
