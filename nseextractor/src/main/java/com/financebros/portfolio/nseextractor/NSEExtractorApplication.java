package com.financebros.portfolio.nseextractor;

import com.financebros.portfolio.nseextractor.core.KafkaListenerApp;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NSEExtractorApplication {
    public static long engineTime;
    public static void callback1(byte[] key, byte[] value) {
        log.info("CALLBACK1");
    }
    public static void callback2(byte[] key, byte[] value) {
        log.info("CALLBACK2");
    }
    public static void main(String[] args) {
        String topic = "COMMON";
        KafkaListenerApp kafkaListener = new KafkaListenerApp<byte[], byte[]>(topic);
        kafkaListener.poll();
    }
}