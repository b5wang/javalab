package com.b5wang.javalab.concurrency.execsvs;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecuteTasksOnSingleThread {

    /**
     * Totally 2 threads
     * */
    public static void main(String[] args){
        ExecutorService executorService = null;
        // Thread 1, all tasks are executed in order.
        try {
            executorService = Executors.newSingleThreadExecutor();// All tasks are queued
            executorService.execute(() -> {
                for (int i = 0; i < 100; i++) {
                    System.out.println("Task A: " + i);
                }
            });
            executorService.execute(() -> {
                for (int i = 0; i < 100; i++) {
                    System.out.println("Task B: " + i);
                }
            });
            executorService.execute(() -> {
                for (int i = 0; i < 100; i++) {
                    System.out.println("Task C: " + i);
                }
            });

            // Thread 2
            System.out.println("End");
        }finally {
            if(executorService != null) {
                executorService.shutdown();
                // It would not stop any tasks have been submitted to the thread executor
                // It does not implement AutoCloseable, so can't use try-with-resources statement
                // executorService.shutdownNow();
                // attempt to stop all running tasks, return the tasks were submitted but never start
            }
        }

    }

}
