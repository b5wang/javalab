package com.b5wang.javalab.java8recipes.concurrency.threadcoordination.threadpool;

import java.util.LinkedList;
import java.util.List;

class JobComsumerPool {

    private static final int DEFAULT_POOL_SIZE = 5;

    private int poolSize;

    private List<JobConsumer> idles = new LinkedList<>();

    private List<JobConsumer> busies = new LinkedList<>();

    public JobComsumerPool(){
        this(DEFAULT_POOL_SIZE);
    }

    public JobComsumerPool(int poolSize){
        this.poolSize = poolSize;
        for (int i = 0; i < poolSize; i++){
            JobConsumer jc = new JobConsumer(this);
            idles.add(jc);
            new Thread(jc).start();
        }
    }

    public synchronized void idelToBusy(JobConsumer jobConsumer){
        idles.remove(jobConsumer);
        busies.add(jobConsumer);
        System.out.println("JobComsumerPool: idles=" + idles.size() + ", busies=" + busies.size());
    }

    public synchronized void busyToIdle(JobConsumer jobConsumer){
        busies.remove(jobConsumer);
        idles.add(jobConsumer);
        notify();
        System.out.println("JobComsumerPool: idles=" + idles.size() + ", busies=" + busies.size());
    }

    public synchronized void addJob(Job job){
        while (idles.size() == 0){
            try {
                wait();
            } catch (InterruptedException e) {}
        }

        JobConsumer jobConsumer = idles.get(0);
        jobConsumer.assignJob(job);
        idelToBusy(jobConsumer);
    }

}
