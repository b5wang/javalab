package com.b5wang.javalab.java8recipes.concurrency.threadcoordination;

class WaitNotifyExample {

    public static void main(String[] args){
        Object obj = new Object();
        new Thread(new WaitThread(obj)).start();
        new Thread(new NotifyThread(obj)).start();
    }

}
