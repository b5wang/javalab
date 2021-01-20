package com.b5wang.javalab.springbootex.model;

import lombok.Data;

@Data
public class KafkaMsg {

    private String topic;

    private String content;

}
