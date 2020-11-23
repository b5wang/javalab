package com.b5wang.javalab.java8recipes.concurrency.join;

class JoinMainThread extends Thread{

    /**
     * What is join?
     * https://docs.oracle.com/javase/tutorial/essential/concurrency/join.html
     * */
    public void run(){
        System.out.println("MainThread start!");
        SubThread subThread = new SubThread();
        subThread.start();
        try {
            subThread.join();
        } catch (InterruptedException e) {
            System.out.println("InterruptedException occurs!");
        }
        System.out.println("MainThread end!");
    }

    public static void main(String[] args){
        JoinMainThread joinMainThread = new JoinMainThread();
        joinMainThread.start();
    }

}
