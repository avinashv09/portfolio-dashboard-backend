package com.financebros.portfoliodashboard.sequencer;

import com.financebros.portfoliodashboard.sequencer.services.SequencerServiceImp;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class SequencerApplication {
    public static long engineTime;
    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = ServerBuilder
                .forPort(8080)
                .addService(new SequencerServiceImp()).build();
        server.start();
        server.awaitTermination();
    }
}
