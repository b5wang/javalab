package com.b5wang.javalab.springbootex.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @EnableKafka is used to detect @KafkaListener annotation on spring managed beans.
 * */
@Slf4j
@EnableKafka
@Configuration
public class KafkaConsumerConfig {

    public static final String GROUP_ID_1 = "msg-group-1";// topic-1, 1 partition, 1 consumer

    public static final String GROUP_ID_2 = "msg-group-2";// topic-2, 3 partitions, 3 consumers with the same groupId

    public static final String GROUP_ID_3_PREFIX = "topic-3-consumer-";// topic-3, 1 partitions, 3 consumers with the different groupId

    @Value(value = "${kafka.bootstrapAddress}")
    private String bootstrapAddress;

    public Map<String, Object> getCommonFactoryConfig() {
        Map<String, Object> properties = new HashMap<String, Object>();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
        properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "100");
        properties.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "15000");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
        properties.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 1);
        properties.put(ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG, 1000);
        return properties;
    }

    private ConsumerFactory<String, String> consumerFactory0() {
        Map<String, Object> properties = getCommonFactoryConfig();
        return new DefaultKafkaConsumerFactory<String, String>(properties);
    }

    private ConsumerFactory<String, String> consumerFactory1() {
        Map<String, Object> properties = getCommonFactoryConfig();
        return new DefaultKafkaConsumerFactory<String, String>(properties);
    }

    private ConsumerFactory<String, String> consumerFactory2() {
        Map<String, Object> properties = getCommonFactoryConfig();
        return new DefaultKafkaConsumerFactory<String, String>(properties);
    }

    private ConsumerFactory<String, String> consumerFactory3() {
        Map<String, Object> properties = getCommonFactoryConfig();
        return new DefaultKafkaConsumerFactory<String, String>(properties);
    }

    /**
     * For listener-0
     * */
    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> kafkaListenerContainerFactory0() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<String, String>();
        factory.setConsumerFactory(consumerFactory0());
        factory.getContainerProperties().setPollTimeout(4000);
        return factory;
    }

    /**
     * For listener-1
     * */
    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> kafkaListenerContainerFactory1() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<String, String>();
        factory.setConsumerFactory(consumerFactory1());
        factory.getContainerProperties().setPollTimeout(4000);
        return factory;
    }

    /**
     * For listener-2
     * */
    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> kafkaListenerContainerFactory2() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<String, String>();
        factory.setConsumerFactory(consumerFactory2());
        factory.getContainerProperties().setPollTimeout(4000);
        return factory;
    }

    /**
     * For listener-2
     * */
    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> kafkaListenerContainerFactory3() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<String, String>();
        factory.setConsumerFactory(consumerFactory3());
        factory.getContainerProperties().setPollTimeout(4000);
        return factory;
    }

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> kafkaListenerContainerFactory4() {
        String groupId = GROUP_ID_3_PREFIX + UUID.randomUUID().toString();
        log.info("--- generate topic-3 listeners groupId: {}",groupId);
        Map<String, Object> properties = getCommonFactoryConfig();
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        ConsumerFactory<String, String> consumerFactory = new DefaultKafkaConsumerFactory<String, String>(properties);

        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<String, String>();
        factory.setConsumerFactory(consumerFactory);
        factory.getContainerProperties().setPollTimeout(4000);
        return factory;
    }

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> kafkaListenerContainerFactory5() {
        String groupId = GROUP_ID_3_PREFIX + UUID.randomUUID().toString();
        log.info("--- generate topic-3 listeners groupId: {}",groupId);
        Map<String, Object> properties = getCommonFactoryConfig();
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        ConsumerFactory<String, String> consumerFactory = new DefaultKafkaConsumerFactory<String, String>(properties);

        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<String, String>();
        factory.setConsumerFactory(consumerFactory);
        factory.getContainerProperties().setPollTimeout(4000);
        return factory;
    }

}
