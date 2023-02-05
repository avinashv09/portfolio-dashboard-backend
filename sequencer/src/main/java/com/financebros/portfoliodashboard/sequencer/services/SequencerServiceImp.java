package com.financebros.portfoliodashboard.sequencer.services;

import com.financebros.portfoliodashboard.sequencer.SequencerApplication;
import com.financebros.portfolio.Acknowledge;
import com.financebros.portfolio.Request;
import com.financebros.portfolio.SequencerServiceGrpc;
import com.financebros.portfolio.Status;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SequencerServiceImp extends SequencerServiceGrpc.SequencerServiceImplBase {
    private final ClockService clockService = new ClockService();
    @Override
    public void request(Request request, StreamObserver<Acknowledge> responseObserver) {
        log.info("Request received!!");
        try {
            switch(request.getSid()) {
                case CLOCK -> clockService.handleRequest(request, responseObserver);
            }
        } catch (Exception e) {
            Acknowledge acknowledge = Acknowledge.newBuilder()
                    .setEngineTime(SequencerApplication.engineTime)
                    .setStatus(Status.ERROR)
                    .setErrorMsg(e.getMessage())
                    .build();
            responseObserver.onNext(acknowledge);
            responseObserver.onCompleted();
        }
    }
}
