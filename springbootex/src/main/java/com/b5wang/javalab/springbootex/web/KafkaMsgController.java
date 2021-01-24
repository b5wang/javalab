package com.b5wang.javalab.springbootex.web;

import com.b5wang.javalab.springbootex.config.KafkaTopicConfig;
import com.b5wang.javalab.springbootex.model.KafkaMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kafka")
public class KafkaMsgController {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String sendMsg(@RequestBody KafkaMsg msg){
        System.out.println("Receive msg: " + msg.toString());

        if(KafkaTopicConfig.TOPIC_1.equals(msg.getTopic())){
            kafkaTemplate.send(msg.getTopic(),msg.getContent());
        }else{
            String key = msg.getKey() != null? msg.getKey() : msg.getPartition().toString();
            kafkaTemplate.send(msg.getTopic(),msg.getPartition(),msg.getKey(),msg.getContent());
        }

        return "OK";
    }


}
