package com.b5wang.javalab.springbootex.model;

import lombok.Data;

@Data
public class KafkaMsg {

    private String topic;

    private Integer partition;

    private String key;

    private String content;

}
