package com.financebros.portfoliodashboard.sequencer.services;

import com.financebros.portfoliodashboard.sequencer.SequencerApplication;
import com.financebros.portfolio.Acknowledge;
import com.financebros.portfolio.ClockRequest;
import com.financebros.portfolio.Request;
import com.financebros.portfolio.Status;
import com.google.protobuf.InvalidProtocolBufferException;
import io.grpc.stub.StreamObserver;

public class ClockService  {
    public ClockService() {}
    public void handleRequest(Request request, StreamObserver<Acknowledge> responseObserver) throws InvalidProtocolBufferException {
        if (ClockRequest.class.equals(request.getRequest().getClass())) {
            publishClock(request.getRequest().unpack(ClockRequest.class), responseObserver);
        }
    }
    public void publishClock(ClockRequest request, StreamObserver<Acknowledge> responseObserver) {
        Acknowledge acknowledge;
        try {
            SequencerApplication.engineTime = request.getEngineTime();
            // TODO: Publish to Kafka
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