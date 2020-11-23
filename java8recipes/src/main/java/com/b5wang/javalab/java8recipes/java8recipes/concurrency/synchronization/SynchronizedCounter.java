package com.b5wang.javalab.java8recipes.concurrency.synchronization;

class SynchronizedCounter {

    private int c = 0;

    synchronized void increment() {
        c++;
    }

    synchronized void decrement() {
        c--;
    }

    synchronized int value() {
        return c;
    }

}
