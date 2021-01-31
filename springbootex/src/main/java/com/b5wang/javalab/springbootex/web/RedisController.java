package com.b5wang.javalab.springbootex.web;

import com.b5wang.javalab.springbootex.config.RedisConfig;
import com.b5wang.javalab.springbootex.model.Booking;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * Redis 5种数据结构 及使用场景分析 - https://zhuanlan.zhihu.com/p/145384563
 * Springboot整合redis(Lettuce版本) - https://zhuanlan.zhihu.com/p/99385034
 * */
@Slf4j
@RequestMapping("/redis")
@RestController
public class RedisController {

    private static final String KEY_SET_PREFIX = "IDSET.";

    @Autowired
    private RedisTemplate<String, Serializable> redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping(value="/setSpeed",method = RequestMethod.GET)
    @ResponseBody
    public String setSpeed(@RequestParam("idSize") int idSize, @RequestParam("saveTime") int saveTime){
        /**
         * Generate 6000 UUIDs,and store into redis 1000 times, record the total time.
         * */
        Set<String> idSet = new HashSet<>();
        for(int i = 0; i < idSize; i++){
            String id = UUID.randomUUID().toString();
            idSet.add(id);
        }
        log.info("Generate id: {}",idSet.size());

        long startTime = System.currentTimeMillis();
        for(int i = 0; i < saveTime; i++){
            String key = KEY_SET_PREFIX + i;
            BoundSetOperations<String,String> boundSetOperations = stringRedisTemplate.boundSetOps(key);
            boundSetOperations.add(idSet.toArray(new String[0]));
        }
        long endTime = System.currentTimeMillis();
        long time = endTime - startTime;
        return String.format("IdSet size: %d, Save into redis times: %d, Total time: %d",idSize,saveTime,time);
    }

    @RequestMapping(value="/removeAll",method = RequestMethod.GET)
    @ResponseBody
    public String setRemoveSpeed(@RequestParam("keyPrefix") String keyPrefix){

        String keyPattern = keyPrefix + "*";
        long t1 = System.currentTimeMillis();
        Set<String> keySet = stringRedisTemplate.keys(keyPattern);
        long t2 = System.currentTimeMillis();
        long t21 = t2 - t1;

        log.info("Key pattern: {}, Find keys: {}, Time: {}",keyPattern,keySet.size(),t21);

        long t3 = System.currentTimeMillis();
        long deletedSize = stringRedisTemplate.delete(keySet);
        long t4 = System.currentTimeMillis();
        long t43 = t4 - t3;

        log.info("Delete size: {}, Time: {}",deletedSize,t43);
        return "Done!";
    }

    @RequestMapping(value="/map",method = RequestMethod.GET)
    @ResponseBody
    public String map(){

        /**
         * Redis string operations
         * */
        BoundHashOperations<String,String,String> boundHashOperations = stringRedisTemplate.boundHashOps("redis.map");
        boundHashOperations.put("key-1","value-1");
        boundHashOperations.put("key-2","value-2");
        boundHashOperations.put("key-3","value-3");

        // Some set operations
        long size = boundHashOperations.size();
        boolean keyExists = boundHashOperations.hasKey("key-2");
        String val2 = boundHashOperations.get("key-2");
        log.info("Map size: {}, keyExists: {}, value of key: {}",size,keyExists,val2);

        boundHashOperations.delete("key-3");
        size = boundHashOperations.size();
        log.info("Map size: {}, after remove a value",size);
        Map<String,String> map = boundHashOperations.entries();
        for(Map.Entry<String,String> entry:map.entrySet()){
            log.info("map member: key={}, value={}",entry.getKey(),entry.getValue());
        }

        /**
         * Redis json string operations
         * */
        BoundHashOperations<String,String,Booking> bookingCache = redisTemplate.boundHashOps("cache.bookings");
        Booking b1 = new Booking("beijing-shanghai",1);
        bookingCache.put(b1.getTicketId(),b1);
        Booking b2 = new Booking("shenyang-dalian",5);
        bookingCache.put(b2.getTicketId(),b2);

        log.info("cache.bookings size: {}", bookingCache.size());
        for(Map.Entry<String,Booking> entry : bookingCache.entries().entrySet()){
            log.info("cache.bookings member: {}",entry.getValue());
        }



        return "Done!";
    }


}
