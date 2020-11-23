package com.b5wang.javalab.java8recipes.concurrency.threadcoordination;

class WaitThread implements Runnable {

    private Object obj;

    WaitThread(Object obj){
        this.obj = obj;
    }

    @Override
    public void run() {
        synchronized (obj) {
            try {
                System.out.println(Thread.currentThread().getName() + " | start waiting ......");
                obj.wait();
                System.out.println(Thread.currentThread().getName() + " | end waiting and finish.");
            } catch (InterruptedException e) {
                //
            }
        }
    }
}
