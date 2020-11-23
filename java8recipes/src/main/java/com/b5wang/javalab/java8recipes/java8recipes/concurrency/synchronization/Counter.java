package com.b5wang.javalab.java8recipes.concurrency.synchronization;

class Counter {

    private int c = 0;

    void increment() {
        c++;
    }

    void decrement() {
        c--;
    }

    int value() {
        return c;
    }

}
