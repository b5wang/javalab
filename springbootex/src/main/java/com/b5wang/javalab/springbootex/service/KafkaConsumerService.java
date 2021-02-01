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

    /**
     * topic-1, 1 partition. only 1 consumer, msg-group-1
     * */
    @KafkaListener(topics = KafkaTopicConfig.TOPIC_1, groupId = KafkaConsumerConfig.GROUP_ID_1, containerFactory = "kafkaListenerContainerFactory0")
    public void topic1Listener1(String message){
        System.out.println(KafkaTopicConfig.TOPIC_1 + ":[listener-0] receive message: " + message);
    }

    /**
     * topic-2, 3 partitions. 3 consumers, and the same groupId msg-group-2
     * */
    @KafkaListener(topics = KafkaTopicConfig.TOPIC_2, groupId = KafkaConsumerConfig.GROUP_ID_2, containerFactory = "kafkaListenerContainerFactory1")
    public void topic2Listener1(String message){
        System.out.println(KafkaTopicConfig.TOPIC_2 + ":[listener-1] receive message: " + message);
    }

    @KafkaListener(topics = KafkaTopicConfig.TOPIC_2, groupId = KafkaConsumerConfig.GROUP_ID_2, containerFactory = "kafkaListenerContainerFactory2")
    public void topic2Listener2(String message){
        System.out.println(KafkaTopicConfig.TOPIC_2 + ":[listener-2] receive message: " + message);
    }

    @KafkaListener(topics = KafkaTopicConfig.TOPIC_2, groupId = KafkaConsumerConfig.GROUP_ID_2, containerFactory = "kafkaListenerContainerFactory3")
    public void topic2Listener3(String message){
        System.out.println(KafkaTopicConfig.TOPIC_2 + ":[listener-3] receive message: " + message);
    }

    /**
     * topic-3, 1 partitions. 2 consumers with different groupId
     * */
    @KafkaListener(topics = KafkaTopicConfig.TOPIC_3, containerFactory = "kafkaListenerContainerFactory4")
    public void topic3Listener1(String message){
        System.out.println(KafkaTopicConfig.TOPIC_3 + ":[listener-1] receive message: " + message);
    }

    @KafkaListener(topics = KafkaTopicConfig.TOPIC_3, containerFactory = "kafkaListenerContainerFactory5")
    public void topic3Listener2(String message){
        System.out.println(KafkaTopicConfig.TOPIC_3 + ":[listener-2] receive message: " + message);
    }
}
