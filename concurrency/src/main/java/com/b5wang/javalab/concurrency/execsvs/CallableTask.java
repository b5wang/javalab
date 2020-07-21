package com.b5wang.javalab.concurrency.execsvs;

import java.util.Random;
import java.util.concurrent.Callable;

public class CallableTask implements Callable<String> {

    private final static Random RANDOM = new Random();

    private final static String NAME = RunnableTask.class.getSimpleName();

    private static int counter = 0;

    private String taskName;

    public CallableTask(){
        taskName = NAME + "(" + counter + ")";
        counter++;
    }

    @Override
    public String call() throws Exception {
        String msg = taskName + "@Thread-" + Thread.currentThread().getId();
        System.out.println(msg + " [start]");
        int seconds = RANDOM.nextInt(10);
        Thread.sleep(1000 * seconds);
        System.out.println(msg + " [end]");
        return msg + " [result]";
    }
}
