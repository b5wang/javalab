package com.b5wang.javalab.concurrency.execsvs;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SubmitRunnableTask {

    /**
     * ExecutorService.submit(ExecutorService), no return value
     * */
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = null;
        List<Future> results = new ArrayList<>();
        try {
            executorService = Executors.newFixedThreadPool(10);// Pool has 10 threads
            for(int i = 0; i < 100; i++){
                Future result = executorService.submit(new RunnableTask());// No return value
                results.add(result);
            }
        }finally {
            if(executorService != null) {
                executorService.shutdown();
                // It would not stop any tasks have been submitted to the thread executor
                // It does not implement AutoCloseable, so can't use try-with-resources statement
                // executorService.shutdownNow();
                // attempt to stop all running tasks, return the tasks were submitted but never start
            }
        }



        // Checking results
        boolean isRunning = true;
        while(!executorService.isTerminated()){
            // await all task finish
        }
        System.out.println("All tasks finished");

        int i = 0;
        for(Future result : results){
            System.out.println("Task " + i + " is done? - "+ result.isDone());
            i++;
        }
    }

}
