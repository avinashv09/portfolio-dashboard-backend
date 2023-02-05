package com.financebros.portfoliodashboard.sequencer.services;

import com.financebros.portfolio.message.*;
import com.financebros.portfoliodashboard.sequencer.SequencerApplication;
import com.financebros.portfolio.message.Acknowledge;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class SequencerServiceImp extends SequencerServiceGrpc.SequencerServiceImplBase {
    private final ClockService clockService;
    @Override
    public void request(Request request, StreamObserver<Acknowledge> responseObserver) {
        log.info("Request received!!");
        try {
            switch(request.getSid()) {
                case CLOCK:
                    clockService.handleRequest(request, responseObserver);
                    break;
                default:
                    responseObserver.onNext(getAcknowledge(Status.ERROR, "The SID in the message is not configured on the sequencer"));
                    responseObserver.onCompleted();
            }
        } catch (Exception e) {
            responseObserver.onNext(getAcknowledge(Status.ERROR, e.getMessage()));
            responseObserver.onCompleted();
        }
    }

    private Acknowledge getAcknowledge(Status status, String errorMsg) {
        return Acknowledge.newBuilder()
                .setEngineTime(SequencerApplication.engineTime)
                .setStatus(status)
                .setErrorMsg(errorMsg)
                .build();
    }
}
