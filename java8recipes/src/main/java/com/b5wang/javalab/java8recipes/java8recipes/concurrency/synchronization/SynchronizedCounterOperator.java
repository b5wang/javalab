package com.b5wang.javalab.java8recipes.concurrency.synchronization;

class SynchronizedCounterOperator {

    private SynchronizedCounter counter = new SynchronizedCounter();

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
     * 2 issues of concurrency: thread interference, and memory consistency.
     * synchronized method:
     * First, it is not possible for two invocations of synchronized methods on the same object to interleave.
     * Second, when a synchronized method exits, it automatically establishes a happens-before relationship with any
     * subsequent invocation of a synchronized method for the same object. This guarantees that changes to the state
     * of the object are visible to all threads.
     * */
    public static void main(String[] args) throws InterruptedException {
        SynchronizedCounterOperator cp = new SynchronizedCounterOperator();
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
