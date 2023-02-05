package com.financebros.portfolio.clock;

import com.financebros.portfolio.SenderId;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.Timer;

@SpringBootApplication
public class ClockApplication {
    public static String hostname = ClockConfig.getHostname();
    public static int port = ClockConfig.getPort();
    public static long tickValue = ClockConfig.getTickValue();
    public static SenderId sid = SenderId.CLOCK;
    public static ClockTask clockTask = new ClockTask();
    @PostConstruct
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(hostname, port)
                .usePlaintext()
                .build();
        clockTask.run(channel);
    }
}