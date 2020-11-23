package com.b5wang.javalab.java8recipes.concurrency.threadcoordination;

class NotifyThread implements Runnable{

    private Object obj;

    NotifyThread(Object obj){
        this.obj = obj;
    }

    @Override
    public void run() {
        synchronized (obj) {
            try {
                // sleep 5sec
                Thread.sleep(5000);
                System.out.println(Thread.currentThread().getName() + " | notify()");
                obj.notify();
                //or
                //obj.notifyAll();
            } catch (InterruptedException e) {
                //
            }
        }
    }
}
