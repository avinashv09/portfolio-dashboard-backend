package com.financebros.portfoliodashboard.sequencer;

import com.financebros.portfoliodashboard.sequencer.services.SequencerServiceImp;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class SequencerApplication implements CommandLineRunner {
    public static long engineTime;

    @Override
    public void run(String... args) {
        Server server = ServerBuilder
                .forPort(8080)
                .addService(new SequencerServiceImp()).build();
        try {
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

    public static void main(String[] args) throws IOException, InterruptedException {
        SpringApplication springApplication = new SpringApplication(SequencerApplication.class);
        springApplication.run(args);
    }
}
