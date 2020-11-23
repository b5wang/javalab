package com.b5wang.javalab.java8recipes.concurrency.threadcoordination.threadpool;

class Job {

    private int id;

    Job(int id){
        this.id = id;
    }

    void process(){
        System.out.println( id + ": Job start...");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println( id + ": Job complete!");
    }

    int getId(){
        return id;
    }

}
