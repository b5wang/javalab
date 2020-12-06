package com.b5wang.javalab.java8recipes.concurrency.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class SimpleConditionJob {

    private Lock lock = new ReentrantLock();

    private Condition condition = lock.newCondition();

    /**
     * 使用Condition前必须先获得锁
     * */
    void threadWait(){
        lock.lock();
        try{
            System.out.println(Thread.currentThread().getName() + " - 进入等待......");
            condition.await();
            System.out.println(Thread.currentThread().getName() + " - 恢复运转!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    void threadNotify(){
        lock.lock();
        try{
            System.out.println(Thread.currentThread().getName() + " - 通知线程恢复!");
            condition.signal();
        } finally {
            lock.unlock();
        }
    }

    void threadNotifyAll(){
        lock.lock();
        try{
            System.out.println(Thread.currentThread().getName() + " - 通知所有线程恢复!");
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SimpleConditionJob job = new SimpleConditionJob();

        for(int i = 0; i < 5; i++){
            Thread t = new Thread(job::threadWait);
            t.setName("Wait-Job-" + i);
            t.start();
        }

        TimeUnit.MILLISECONDS.sleep(1000);

        for(int i = 0; i < 5; i++){
            Thread t = new Thread(job::threadNotifyAll);
            t.setName("Wait-Job-" + i);
            t.start();
        }
    }
}
