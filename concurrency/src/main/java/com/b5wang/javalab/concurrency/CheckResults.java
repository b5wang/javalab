package com.b5wang.javalab.concurrency;

import java.util.logging.Logger;

/**
 * 2 threads, one increases the counter, another one monitors and read the counter.
 * */
public class CheckResults {

    private final static Logger LOGGER = Logger.getLogger(CheckResults.class.getName());

    private static int counter= 0;

    public static void main(String[] args){

        Thread counterReader = new Thread(()->{
            while(true){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                LOGGER.info("counter: " + counter);
            }
        });

        counterReader.setDaemon(true);
        counterReader.start();

        while (counter < 1000){
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            counter ++;
        }

        LOGGER.info("Counter stops running...");
    }
}
