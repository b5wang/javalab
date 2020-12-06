package com.b5wang.javalab.java8recipes.concurrency.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class DeadLockWithLock {

    private static class Job{

        private ReentrantLock lock = new ReentrantLock();

        /**
         * 如何使用tryLock解决死锁，详见SolveDeadLockWithLock
         * */
        void stepOne(Job job){
            lock.lock();

            System.out.println(Thread.currentThread().getName() + " is doing first step");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            job.stepTwo(this);

            lock.unlock();
        }

        void stepTwo(Job job){
            lock.lock();

            System.out.println(Thread.currentThread().getName() + " is doing second step");

            lock.unlock();
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
