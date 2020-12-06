package com.b5wang.javalab.java8recipes.concurrency.pool;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * 1) Executor, a simple interface that supports launching new tasks.
 *    This interface has one method only.
 *    If r is a Runnable object, and e is an Executor object, you can replace
 *    (new Thread(r)).start();
 *    with
 *    e.execute(r);
 * 2) ExecutorService, a subinterface of Executor, which adds features that help manage the lifecycle,
 * both of the individual tasks and of the executor itself.
 * 3) ScheduledExecutorService, a subinterface of ExecutorService, supports future and/or periodic execution of tasks.
 *
 * */
class SimpleExecutor {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Runnable r = () -> {
            System.out.println(Thread.currentThread().getName() + " - start executing");
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " - end!");
        };

        // newSingleThreadExecutor
//        ExecutorService executor = Executors.newSingleThreadExecutor();
//        for(int i = 0; i < 10; i++){
//            executor.execute(r);
//        }
//        executor.shutdown();

        // newFixedThreadPool
        ExecutorService executor = Executors.newFixedThreadPool(5);
        List<Future<String>> results = new ArrayList<>();
        for(int i = 0; i < 15; i++){
            //executor.submit(r);
            final int index = i;
            Future<String> future = executor.submit(() -> {
                TimeUnit.MILLISECONDS.sleep(500);
                return String.valueOf(index) + " is done!!!";
            });
            results.add(future);
        }

        System.out.println(" --- 检查结果");
        for(Future<String> result : results){
            String resultStr = result.get();
            System.out.println(" --- 结果: " + resultStr);
        }
        System.out.println(" --- 所有工作完成!!!");

        executor.shutdown();
    }

}
