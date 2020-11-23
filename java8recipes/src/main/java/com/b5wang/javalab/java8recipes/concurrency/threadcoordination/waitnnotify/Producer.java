package com.b5wang.javalab.java8recipes.concurrency.threadcoordination.waitnnotify;

import java.util.List;
import java.util.concurrent.TimeUnit;

class Producer implements Runnable{

    List<Integer> cache;

    public void put() throws InterruptedException {
        synchronized (cache) {
            while (cache.size() == 1) {
                try {
                    cache.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

            TimeUnit.SECONDS.sleep(1);

            cache.add(1);
            System.out.println(Thread.currentThread().getName() + "生产者生产了一条任务。");
            cache.notify();
        }
    }

    public Producer(List<Integer> cache) {
        this.cache = cache;
    }

    @Override
    public void run() {
        while (true) {
            try {
                put();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
