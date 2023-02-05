package com.financebros.portfoliodashboard.sequencer.services;

import com.financebros.portfolio.message.*;
import com.financebros.portfoliodashboard.sequencer.SequencerApplication;
import com.google.protobuf.InvalidProtocolBufferException;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClockService  {
    private final KafkaPublisher kafkaPublisher;
    public void handleRequest(Request request, StreamObserver<Acknowledge> responseObserver) throws InvalidProtocolBufferException {
        if (request.getRequest().is(ClockRequest.class)) {
            publishClock(request.getRequest().unpack(ClockRequest.class), responseObserver);
        }
    }
    public void publishClock(ClockRequest request, StreamObserver<Acknowledge> responseObserver) {
        Acknowledge acknowledge;
        try {
            SequencerApplication.engineTime = request.getEngineTime();
            kafkaPublisher.publishMessage( "COMMON",
                    OnCore.newBuilder()
                    .setEngineTime(SequencerApplication.engineTime)
                    .setSid(SenderId.CLOCK)
                    .build().toByteArray());
            acknowledge = Acknowledge.newBuilder()
                    .setEngineTime(SequencerApplication.engineTime)
                    .setStatus(Status.OK)
                    .build();
        } catch(Exception e) {
            acknowledge = Acknowledge.newBuilder()
                    .setEngineTime(SequencerApplication.engineTime)
                    .setStatus(Status.ERROR)
                    .setErrorMsg(e.getMessage())
                    .build();
        }
        responseObserver.onNext(acknowledge);
        responseObserver.onCompleted();
    }
}