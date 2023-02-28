package com.gitbitex.kafka;

import com.alibaba.fastjson.JSON;
import com.gitbitex.AppProperties;
import com.gitbitex.matchingengine.command.MatchingEngineCommand;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;
import java.util.concurrent.Future;

@Slf4j
public class KafkaMessageProducer extends KafkaProducer<String, String> {
    private final AppProperties appProperties;

    public KafkaMessageProducer(Properties kafkaProperties, AppProperties appProperties) {
        super(kafkaProperties);
        this.appProperties = appProperties;
    }

    public Future<RecordMetadata> sendToMatchingEngine(String productId, MatchingEngineCommand orderMessage,
                                                       Callback callback) {
        String topic = appProperties.getMatchingEngineCommandTopic();
        ProducerRecord<String, String> record = new ProducerRecord<>(topic, productId, JSON.toJSONString(orderMessage));

        return super.send(record, (metadata, exception) -> {
            if (callback != null) {
                callback.onCompletion(metadata, exception);
            }
        });
    }

}
