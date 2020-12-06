package com.b5wang.javalab.java8recipes.concurrency.deadlock;

/**
 * 2 threads interleave, go to deadlock.
 * */
class DeadLockJobs {

    static class Job{

        synchronized void stepOne(Job job){
            System.out.println(Thread.currentThread().getName() + " is doing first step");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            job.stepTwo(this);
        }

        synchronized void stepTwo(Job job){
            System.out.println(Thread.currentThread().getName() + " is doing second step");
        }

    }

    public static void main(String[] args){
        Job job1 = new Job();
        Job job2 = new Job();

        new Thread(new Runnable() {
            public void run() { job1.stepOne(job2); }
        }).start();
        new Thread(new Runnable() {
            public void run() { job2.stepOne(job1); }
        }).start();
    }

}
