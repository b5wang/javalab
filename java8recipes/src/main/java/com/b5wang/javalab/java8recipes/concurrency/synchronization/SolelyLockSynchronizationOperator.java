package com.b5wang.javalab.java8recipes.concurrency.synchronization;

public class SolelyLockSynchronizationOperator {

    private SolelyLockSynchronization counter = new SolelyLockSynchronization();

    void increase(){
        for(int i = 0; i < 1000000; i++){
            counter.increment();
        }
    }

    void decrease(){
        for(int i = 0; i < 1000000; i++){
            counter.decrement();
        }
    }

    /**
     * In SolelyLockSynchronization there are lock1 and lock2.
     * (1) increment() and decrement() are using different lock, synchronization doesn't work
     * (2) increment() and decrement() are using same lock, either lock1 or lock2, synchronization works
     * */
    public static void main(String[] args) throws InterruptedException {
        SolelyLockSynchronizationOperator cp = new SolelyLockSynchronizationOperator();
        Thread t1 = new Thread(()->cp.increase());
        Thread t2 = new Thread(()->cp.decrease());
        Thread t3 = new Thread(()->cp.increase());
        Thread t4 = new Thread(()->cp.decrease());
        Thread t5 = new Thread(()->cp.increase());
        Thread t6 = new Thread(()->cp.decrease());

        System.out.println(cp.counter.value());
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();

        t1.join();
        t2.join();
        t3.join();
        t4.join();
        t5.join();
        t6.join();
        System.out.println(cp.counter.value());
    }

}
