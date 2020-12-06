package com.b5wang.javalab.java8recipes.concurrency.deadlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Solve DeadLockJobs with Lock
 * */
class SolveDeadLockWithLock {

    static class Job{

        private final Lock lock = new ReentrantLock();

        public boolean tryLock(SolveDeadLockWithLock.Job job) {
            Boolean myLock = false;
            Boolean yourLock = false;
            try {
                myLock = lock.tryLock();
                yourLock = job.lock.tryLock();
            } finally {
                if (! (myLock && yourLock)) {
                    if (myLock) {
                        lock.unlock();
                    }
                    if (yourLock) {
                        job.lock.unlock();
                    }
                }
            }
            return myLock && yourLock;
        }

        synchronized void stepOne(SolveDeadLockWithLock.Job job){
            System.out.println(Thread.currentThread().getName() + " is doing first step");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            job.stepTwo(this);
        }

        synchronized void stepTwo(SolveDeadLockWithLock.Job job){
            System.out.println(Thread.currentThread().getName() + " is doing second step");
        }

    }

    public static void main(String[] args){
        SolveDeadLockWithLock.Job job1 = new SolveDeadLockWithLock.Job();
        SolveDeadLockWithLock.Job job2 = new SolveDeadLockWithLock.Job();

        new Thread(new Runnable() {
            public void run() { job1.stepOne(job2); }
        }).start();
        new Thread(new Runnable() {
            public void run() { job2.stepOne(job1); }
        }).start();
    }

}
