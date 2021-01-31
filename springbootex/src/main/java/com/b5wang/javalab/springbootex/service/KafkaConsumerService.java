package com.b5wang.javalab.springbootex.service;

import com.b5wang.javalab.springbootex.config.KafkaConsumerConfig;
import com.b5wang.javalab.springbootex.config.KafkaTopicConfig;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
/**
 * topic-1, 1 partition, it only can support 1 consumer
 * topic-2, 3 partitions, it can support 3 consumers at most
 *
 * */
@Service
public class KafkaConsumerService {

    //@KafkaListener(topics = KafkaTopicConfig.TOPIC_1, groupId = KafkaConsumerConfig.GROUP_ID_1, containerFactory = "kafkaListenerContainerFactory0")
    public void topic1Listener1(String message){
        System.out.println(KafkaTopicConfig.TOPIC_1 + ":[listener-0] receive message: " + message);
    }

    //@KafkaListener(topics = KafkaTopicConfig.TOPIC_2, groupId = KafkaConsumerConfig.GROUP_ID_2, containerFactory = "kafkaListenerContainerFactory1")
    public void topic2Listener1(String message){
        System.out.println(KafkaTopicConfig.TOPIC_2 + ":[listener-1] receive message: " + message);
    }

    //@KafkaListener(topics = KafkaTopicConfig.TOPIC_2, groupId = KafkaConsumerConfig.GROUP_ID_2, containerFactory = "kafkaListenerContainerFactory2")
    public void topic2Listener2(String message){
        System.out.println(KafkaTopicConfig.TOPIC_2 + ":[listener-2] receive message: " + message);
    }

    //@KafkaListener(topics = KafkaTopicConfig.TOPIC_2, groupId = KafkaConsumerConfig.GROUP_ID_2, containerFactory = "kafkaListenerContainerFactory3")
    public void topic2Listener3(String message){
        System.out.println(KafkaTopicConfig.TOPIC_2 + ":[listener-3] receive message: " + message);
    }

}
