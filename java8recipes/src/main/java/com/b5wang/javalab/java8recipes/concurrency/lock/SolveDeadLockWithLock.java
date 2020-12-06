package com.b5wang.javalab.java8recipes.concurrency.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class SolveDeadLockWithLock {

    private static class Job{

        private ReentrantLock lock = new ReentrantLock();

        /**
         * 必须同时获得两把锁，才进行操作，否则继续retry
         * */
        void stepOne(Job job){

            // 只有同时获取两把锁成功才会退出循环
            while(!tryLock(lock,job.lock)){
                System.out.println(Thread.currentThread().getName() + " - 尝试获取两把锁失败，继续尝试......");
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println(Thread.currentThread().getName() + " is doing first step");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            job.stepTwo(this);

            lock.unlock();
        }

        private void stepTwo(Job job){
            System.out.println(Thread.currentThread().getName() + " is doing second step");
            lock.unlock();
        }

        private boolean tryLock(Lock lock1, Lock lock2){
            boolean hasLock1 = false;
            boolean hasLock2 = false;

            try{
                hasLock1 = lock1.tryLock();
                hasLock2 = lock2.tryLock();
            } finally {
                /**
                 * 一但没有同时获得两把锁，需要释放已经获得的锁
                 * */
                if(!(hasLock1 && hasLock2)){
                    if(hasLock1){
                        lock1.unlock();
                    }
                    if(hasLock2){
                        lock2.unlock();
                    }
                }
            }

            return (hasLock1 && hasLock2);
        }

    }

    public static void main(String[] args) throws InterruptedException {
        Job job1 = new Job();
        Job job2 = new Job();

        Thread t1 = new Thread(new Runnable() {
            public void run() { job1.stepOne(job2); }
        });
        t1.start();

        Thread t2 = new Thread(new Runnable() {
            public void run() { job2.stepOne(job1); }
        });
        t2.start();

        Thread.sleep(200);
        System.out.println("job1's locked: " + job1.lock.isLocked());
        System.out.println("job2's locked: " + job2.lock.isLocked());

        System.out.println("t1 is waiting for job1's lock: " + job1.lock.hasQueuedThread(t1));
        System.out.println("t2 is waiting for job1's lock: " + job1.lock.hasQueuedThread(t2));
        System.out.println("t1 is waiting for job2's lock: " + job2.lock.hasQueuedThread(t1));
        System.out.println("t2 is waiting for job2's lock: " + job2.lock.hasQueuedThread(t2));
    }

}
