package com.b5wang.javalab.java8recipes.concurrency.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class UsedAsObjectLock {

    private ReentrantLock lock = new ReentrantLock();

    ReentrantLock getLock(){
        return lock;
    }

    void methodA(){
        try{
            lock.lock();
            System.out.println("methodA begin ThreadName=" + Thread.currentThread().getName() + " time=" + System.currentTimeMillis());
            Thread.sleep(1000 * 5);
            System.out.println("methodA end ThreadName=" + Thread.currentThread().getName() + " time=" + System.currentTimeMillis());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();// unlock in finally block
        }
    }

    void methodB(){
        try{
            lock.lock();
            System.out.println("methodB begin ThreadName=" + Thread.currentThread().getName() + " time=" + System.currentTimeMillis());
            Thread.sleep(1000 * 5);
            System.out.println("methodB end ThreadName=" + Thread.currentThread().getName() + " time=" + System.currentTimeMillis());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        UsedAsObjectLock uaol = new UsedAsObjectLock();

        Thread tA = new Thread(uaol::methodA);
        tA.setName("Thread-A");
        tA.start();

        Thread tAA = new Thread(uaol::methodA);
        tAA.setName("Thread-AA");
        tAA.start();

        Thread tB = new Thread(uaol::methodB);
        tB.setName("Thread-B");
        tB.start();

        Thread tBB = new Thread(uaol::methodB);
        tBB.setName("Thread-BB");
        tBB.start();


        Thread.sleep(1000 * 3);
        // 获取等待此锁的线程数
        System.out.println("等待锁的线程数: " + uaol.getLock().getQueueLength());

        // 查询是否有线程等待获取此锁定
        System.out.println("是否有线程已经获取此锁: " + uaol.getLock().hasQueuedThreads());
        System.out.println("是否有线程已经获取此锁: " + uaol.getLock().isLocked());
        System.out.println("当前线程已经获取此锁  : " + uaol.getLock().isHeldByCurrentThread());

        // 判断指定线程是否在等待此锁
        System.out.println("线程tA  是否等待锁: " + uaol.getLock().hasQueuedThread(tA));
        System.out.println("线程tAA 是否等待锁: " + uaol.getLock().hasQueuedThread(tAA));
        System.out.println("线程tB  是否等待锁: " + uaol.getLock().hasQueuedThread(tB));
        System.out.println("线程tBB 是否等待锁: " + uaol.getLock().hasQueuedThread(tBB));

        //

    }

}
