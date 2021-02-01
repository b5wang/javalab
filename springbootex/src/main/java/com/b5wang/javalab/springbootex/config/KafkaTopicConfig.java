package com.b5wang.javalab.springbootex.config;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaTopicConfig {

    public static final String TOPIC_1 = "topic-1";

    public static final String TOPIC_2 = "topic-2";

    public static final String TOPIC_3 = "topic-3";

    @Value(value = "${kafka.bootstrapAddress}")
    private String bootstrapAddress;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic topic1() {
        return new NewTopic(TOPIC_1, 1, (short) 1);
    }

    @Bean
    public NewTopic topic2() {
        return new NewTopic(TOPIC_2, 3, (short) 1);
    }

    @Bean
    public NewTopic topic3() {
        return new NewTopic(TOPIC_3, 1, (short) 1);
    }

}
