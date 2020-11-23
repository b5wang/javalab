package com.b5wang.javalab.java8recipes.concurrency.synchronization;

class SolelyLockSynchronization {

    private int c = 0;

    private Object lock1 = new Object();

    private Object lock2 = new Object();

    void increment() {
        synchronized (lock1) {
            c++;
        }
    }

    void decrement() {
        synchronized (lock2) {
            c--;
        }
    }

    public int value() {
        return c;
    }

}
