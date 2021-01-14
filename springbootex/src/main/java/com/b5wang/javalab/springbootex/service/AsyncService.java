package com.b5wang.javalab.springbootex.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class AsyncService {

    @Async("asynTasksPool")
    public CompletableFuture<Boolean> process(int id) throws InterruptedException {
        System.out.println(String.format("Job[%d] start ... --- %s",id,Thread.currentThread().getName()));
        Thread.sleep(1000);
        System.out.println(String.format("Job[%d] finish... --- %s",id,Thread.currentThread().getName()));
        return CompletableFuture.completedFuture(Boolean.TRUE);
    }

}
