package com.b5wang.javalab.concurrency.execsvs;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class InvokeAnyTasks {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        List<CallableTask> tasks = new ArrayList<>();
        for(int i = 0; i < 100; i++){
            tasks.add(new CallableTask());
        }

        ExecutorService executorService = null;
        executorService = Executors.newFixedThreadPool(10);// Pool has 10 threads
        String result = executorService.invokeAny(tasks);// Return the one first finished
        System.out.println("------------------invokeAll finished");
        System.out.println(result);
    }

}