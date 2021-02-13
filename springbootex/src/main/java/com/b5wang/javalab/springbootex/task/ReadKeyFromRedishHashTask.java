package com.b5wang.javalab.springbootex.task;

import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.Callable;

public class ReadKeyFromRedishHashTask implements Callable<String> {

    private RedisTemplate<String,Object> redisTemplate;

    private String hashName;

    private String key;

    public ReadKeyFromRedishHashTask(RedisTemplate<String,Object> redisTemplate,String hashName,String key){
        this.redisTemplate = redisTemplate;
        this.hashName = hashName;
        this.key = key;
    }

    @Override
    public String call() throws Exception {
        BoundHashOperations<String,String,String> redisMap = redisTemplate.boundHashOps(hashName);
        String value = redisMap.get(key);
        return value;
    }
}
