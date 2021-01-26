package com.b5wang.javalab.springbootex.config;

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

/**
 * @EnableKafka is used to detect @KafkaListener annotation on spring managed beans.
 * */
@EnableKafka
@Configuration
public class KafkaConsumerConfig {

    public static final String GROUP_ID_1 = "msg-group-1";// topic-1

    public static final String GROUP_ID_2 = "msg-group-2";// topic-2

    @Value(value = "${kafka.bootstrapAddress}")
    private String bootstrapAddress;

    public Map<String, Object> getCommonFactoryConfig() {
        Map<String, Object> properties = new HashMap<String, Object>();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
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
        //properties.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID_1);
        return new DefaultKafkaConsumerFactory<String, String>(properties);
    }

    private ConsumerFactory<String, String> consumerFactory1() {
        Map<String, Object> properties = getCommonFactoryConfig();
        //properties.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID_2);
        return new DefaultKafkaConsumerFactory<String, String>(properties);
    }

    private ConsumerFactory<String, String> consumerFactory2() {
        Map<String, Object> properties = getCommonFactoryConfig();
        //properties.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID_2);
        return new DefaultKafkaConsumerFactory<String, String>(properties);
    }

    private ConsumerFactory<String, String> consumerFactory3() {
        Map<String, Object> properties = getCommonFactoryConfig();
        //properties.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID_2);
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
     * For listener-3
     * */
    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> kafkaListenerContainerFactory3() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<String, String>();
        factory.setConsumerFactory(consumerFactory3());
        factory.getContainerProperties().setPollTimeout(4000);
        return factory;
    }

}
