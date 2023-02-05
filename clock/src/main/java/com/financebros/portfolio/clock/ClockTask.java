package com.financebros.portfolio.clock;

import com.financebros.portfolio.*;
import com.google.protobuf.Any;
import io.grpc.ManagedChannel;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.TimerTask;

@Slf4j
public class ClockTask {
    public static long engineTime = new Date().getTime();
    public void run(ManagedChannel channel) {
        while(true) {
            task(channel);
        }
    }

    public void task(ManagedChannel channel) {
        SequencerServiceGrpc.SequencerServiceBlockingStub stub
                = SequencerServiceGrpc.newBlockingStub(channel);

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
    }
}