package com.financebros.portfolio.clock;

import com.financebros.portfolio.message.*;
import com.google.protobuf.Any;
import io.grpc.ManagedChannel;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ClockTask {
    public static long engineTime = new Date().getTime();
    public void run(ManagedChannel channel) throws InterruptedException {
        while(true) {
            task(channel);
            Thread.sleep(100);
            log.info("Sleeping");
        }
    }
    public void task(ManagedChannel channel) throws InterruptedException {
        SequencerServiceGrpc.SequencerServiceBlockingStub stub
                = SequencerServiceGrpc.newBlockingStub(channel);
        while(true) {
            Request request = Request.newBuilder()
                    .setSid(ClockApplication.sid)
                    .setEngineTime(engineTime)
                    .setRequest(Any.pack(ClockRequest.newBuilder().setEngineTime(engineTime).build()))
                    .build();
            Acknowledge acknowledge = stub.request(request);
            if(acknowledge.getStatus() == Status.OK) {
                engineTime = new Date().getTime();
                log.info("Acked! {}", engineTime);
                System.out.println("HEllow");
            } else {
                log.error(acknowledge.getErrorMsg());
            }
            Thread.sleep(100);
        }
    }
}