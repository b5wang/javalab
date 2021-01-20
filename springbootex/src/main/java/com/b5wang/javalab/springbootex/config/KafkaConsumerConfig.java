package com.b5wang.javalab.springbootex.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;

/**
 * @EnableKafka is used to detect @KafkaListener annotation on spring managed beans.
 * */
@EnableKafka
@Configuration
public class KafkaConsumerConfig {
}
