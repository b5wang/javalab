package com.b5wang.javalab.java8recipes.concurrency.threadcoordination.waitnnotify;

import java.util.List;

class Customer implements Runnable{

    List<Integer> cache;

    public Customer(List<Integer> cache) {
        this.cache = cache;
    }

    private void custom() {
        synchronized (cache) {
            while (cache.size() == 0) {
                try {
                    cache.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            cache.remove(0);
            System.out.println(Thread.currentThread().getName() + "消费者消费了一条。");
            cache.notify();
        }
    }
    @Override
    public void run() {
        while (true) {
            custom();
        }
    }
}
