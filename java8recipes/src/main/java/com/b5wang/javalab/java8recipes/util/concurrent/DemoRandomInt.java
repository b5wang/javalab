package com.b5wang.javalab.java8recipes.util.concurrent;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class DemoRandomInt {

    private static ExecutorService executor = Executors.newFixedThreadPool(500);

    private static class RandomIntGenerator implements Callable<Integer>{

        private static final Random random = new Random();

        private static final int bound = 20;

        @Override
        public Integer call() throws Exception {
            return random.nextInt(bound);
        }
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        List<RandomIntGenerator> randomIntGeneratorList = new LinkedList<>();
        for(int i = 0; i<500; i++){
            randomIntGeneratorList.add(new RandomIntGenerator());
        }

        List<Future<Integer>> futureList = executor.invokeAll(randomIntGeneratorList);

        // Stats result
        Map<Integer, AtomicInteger> statsMap = new HashMap<>();
        for(Future<Integer> future : futureList){
            Integer randomInt = future.get();

            AtomicInteger stats = statsMap.get(randomInt);
            if(stats == null){
                stats = new AtomicInteger();
                statsMap.put(randomInt,stats);
            }
            stats.incrementAndGet();
        }

        // Print result
        for(Map.Entry<Integer,AtomicInteger> entry: statsMap.entrySet()){
            System.out.println(String.format("%s --- %s",entry.getKey(),entry.getValue()));
        }

        executor.shutdown();
    }

}
