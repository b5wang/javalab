package com.b5wang.javalab.concurrency.execsvs;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class SubmitCallableTask {

    /**
     * ExecutorService.submit(ExecutorService), no return value
     * */
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = null;
        List<Future<String>> results = new ArrayList<>();
        try {
            executorService = Executors.newFixedThreadPool(10);// Pool has 10 threads
            for(int i = 0; i < 100; i++){
                Future<String> result = executorService.submit(new CallableTask());// Return string
                results.add(result);
            }
        }finally {
            if(executorService != null) {
                executorService.shutdown();
            }
        }



        // Checking results
        boolean isRunning = true;
        while(!executorService.isTerminated()){
            // await all task finish
        }
        System.out.println("All tasks finished");

        int i = 0;
        for(Future<String> result : results){
            System.out.println("Task " + i + " is done? - "+ result.isDone() + ", and msg: " + result.get());
            i++;
        }
    }

}
