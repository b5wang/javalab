package com.b5wang.javalab.springbootex.task;

import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class WriteKeyToRedisTask implements Callable<Boolean> {

    private StringRedisTemplate stringRedisTemplate;

    private String key;

    private String value;

    public WriteKeyToRedisTask(StringRedisTemplate stringRedisTemplate,String key,String value){
        this.stringRedisTemplate = stringRedisTemplate;
        this.key = key;
        this.value = value;
    }

    @Override
    public Boolean call() throws Exception {
        BoundValueOperations<String, String> setnxOps = stringRedisTemplate.boundValueOps(key);
        return setnxOps.setIfAbsent(value, 1, TimeUnit.HOURS);
    }

}
