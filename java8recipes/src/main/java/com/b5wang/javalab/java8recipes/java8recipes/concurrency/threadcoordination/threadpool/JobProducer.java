package com.b5wang.javalab.java8recipes.concurrency.threadcoordination.threadpool;

class JobProducer {

    public static void main(String[] args){

        JobComsumerPool jobComsumerPool= new JobComsumerPool();

        for(int i = 0; i < 20; i++){
            jobComsumerPool.addJob(new Job(i));
        }

        System.out.println("Producer running end");
    }

}
