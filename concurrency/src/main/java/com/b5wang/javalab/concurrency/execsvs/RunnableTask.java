package com.b5wang.javalab.concurrency.execsvs;

import java.util.Random;

public class RunnableTask implements Runnable{

    private final static Random RANDOM = new Random();

    private final static String NAME = RunnableTask.class.getSimpleName();

    private static int counter = 0;

    private String taskName;

    private RunnableTaskResult resultContainer;

    public RunnableTask(){
        taskName = NAME + "(" + counter + ")";
        counter++;
    }

    public RunnableTask(RunnableTaskResult resultContainer){
        this();
        this.resultContainer = resultContainer;
    }

    @Override
    public void run() {
        String msg = taskName + "@Thread-" + Thread.currentThread().getId();
        System.out.println(msg + " [start]");
        int seconds = RANDOM.nextInt(20);
        try {
            Thread.sleep(1000 * seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(msg + " [end]");

        if(resultContainer != null){
            resultContainer.setMsg(msg + " [Done]");
        }
    }
}
