package com.b5wang.javalab.java8recipes.concurrency.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class TryLockRetry {


    private Lock lock = new ReentrantLock();

    void process(){
        boolean hasLock = false;
        while (!(hasLock = lock.tryLock())){
            System.out.println(Thread.currentThread().getName() + " retry to get lock!");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        try{
            //处理任务，当前是线程安全的
            System.out.println(Thread.currentThread().getName() + " start processing");
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + " end processing!!!");
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            //当获取锁成功时最后一定要记住finally去关闭锁
            lock.unlock();   //释放锁
        }
    }

    public static void main(String[] args){
        TryLockRetry tl = new TryLockRetry();

        for(int i = 0; i < 5; i++){
            Thread t = new Thread(tl::process);
            t.setName("" + i);
            t.start();
        }
    }
}
