package com.financebros.portfolio.nseextractor.core;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Properties;
import java.util.function.BiConsumer;
@Slf4j
public class KafkaListenerApp<K,V> {
    private final HashMap<String, BiConsumer<byte[], byte[]>> dictionary = new HashMap<>();
    private final KafkaConsumer<K, V> consumer;
    public KafkaListenerApp(String topic) {
        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:29092");
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.ByteArrayDeserializer");
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.ByteArrayDeserializer");
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "NE1");
        properties.setProperty(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
        consumer = new KafkaConsumer<K, V>(properties);
        consumer.subscribe(Arrays.asList(topic));
    }

    public void poll() {
        while(true) {
            ConsumerRecords<K, V> records = consumer.poll(Duration.ofMillis(100));
            for(ConsumerRecord<K, V> record : records) {
                log.info("Processing Offset: {}", record.offset());
                for(String key : dictionary.keySet()) {
                    dictionary.get(key).accept((byte[]) record.key(), (byte[]) record.value());
                }
            }
        }
    }

    public void subscribe(String key, BiConsumer<byte[], byte[]> biConsumer) {
        dictionary.put(key, biConsumer);
    }
}
