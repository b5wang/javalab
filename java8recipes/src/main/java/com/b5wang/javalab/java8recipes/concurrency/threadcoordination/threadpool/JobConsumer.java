package com.b5wang.javalab.java8recipes.concurrency.threadcoordination.threadpool;

class JobConsumer implements Runnable{

    private JobComsumerPool pool;

    private Job job = null;

    private boolean active = true;

    JobConsumer(JobComsumerPool pool){
        this.pool = pool;
    }

    @Override
    public void run() {
        while(active) {
            while (job == null) {
                try {
                    synchronized (this) {
                        System.out.println(Thread.currentThread().getName() + ": waiting for a new job");
                        wait();
                    }
                } catch (InterruptedException e) {
                }
            }

            System.out.println(Thread.currentThread().getName() + ": process job - " + job.getId());
            job.process();
            job = null;
            pool.busyToIdle(this);
        }
    }

    public void assignJob(Job job){
        System.out.println(Thread.currentThread().getName() + ": get job - " + job.getId());
        this.job = job;
        synchronized (this) {
            notify();
        }
    }

    public void close(){
        active = false;
    }
}
