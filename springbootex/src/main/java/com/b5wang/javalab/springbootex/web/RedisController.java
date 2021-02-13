package com.b5wang.javalab.springbootex.web;

import com.b5wang.javalab.springbootex.model.Booking;
import com.b5wang.javalab.springbootex.task.ReadKeyFromRedishHashTask;
import com.b5wang.javalab.springbootex.task.WriteKeyToRedisTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Redis 5种数据结构 及使用场景分析 - https://zhuanlan.zhihu.com/p/145384563
 * Springboot整合redis(Lettuce版本) - https://zhuanlan.zhihu.com/p/99385034
 * */
@Slf4j
@RequestMapping("/redis")
@RestController
public class RedisController {

    private static final int THREAD_POOL_SIZE = 500;

    private static final String KEY_SET_PREFIX = "IDSET.";

    private static final String KEY_REDIS_MAP_NAME = "redis.map";

    private static final String KEY_REDIS_PREFIX = "key.";

    private ExecutorService executorService = Executors.newFixedThreadPool(THREAD_POOL_SIZE);

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

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
        BoundHashOperations<String,String,String> boundHashOperations = stringRedisTemplate.boundHashOps(KEY_REDIS_MAP_NAME);
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

    /**
     * 测试设计：
     * 1. 生成5000个key，存放在hash中
     * 2. 多线程读取这5000个key,看要花多久时间
     * */
    @RequestMapping(value="/hashReadSpeed",method = RequestMethod.GET)
    @ResponseBody
    public String hashReadSpeed() throws InterruptedException, ExecutionException {
        List<ReadKeyFromRedishHashTask> readKeyFromRedishHashTasks = new LinkedList<>();

        BoundHashOperations<String,String,String> redisMap = redisTemplate.boundHashOps(KEY_REDIS_MAP_NAME);
        long t1 = System.currentTimeMillis();
        for(int i = 0; i < 5000; i++){
            String uuid = UUID.randomUUID().toString();
            redisMap.put(uuid,uuid);
            readKeyFromRedishHashTasks.add(new ReadKeyFromRedishHashTask(redisTemplate, KEY_REDIS_MAP_NAME, uuid));
        }
        long t2 = System.currentTimeMillis();

        List<Future<String>> futureList = executorService.invokeAll(readKeyFromRedishHashTasks);
        for(Future<String> future:futureList){
            future.get();
        }
        long t3 = System.currentTimeMillis();
        long putTime = t2 - t1;
        long getTime = t3 - t2;

        log.info("Put time: {}, Get time: {}",putTime,getTime);
        return "Ok";
    }

    /**
     * 5000 key 多线程写入
     * */
    @RequestMapping(value="/keyWriteSpeed",method = RequestMethod.GET)
    @ResponseBody
    public String keyWriteSpeed() throws InterruptedException, ExecutionException {
        List<WriteKeyToRedisTask> writeKeyToRedisTaskList = new LinkedList<>();
        for(int i = 0; i < 5000; i++){
            String key = KEY_REDIS_PREFIX + UUID.randomUUID().toString();
            String value = "Y";
            writeKeyToRedisTaskList.add(new WriteKeyToRedisTask(stringRedisTemplate,key,value));
        }

        long t1 = System.currentTimeMillis();
        List<Future<Boolean>> futureList = executorService.invokeAll(writeKeyToRedisTaskList);
        for(Future<Boolean> future:futureList){
            future.get();
        }
        long t2 = System.currentTimeMillis();
        long time = t2 - t1;
        log.info("Write time: {}", time);
        return "Ok";
    }

}
