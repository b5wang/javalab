package com.b5wang.javalab.java8recipes.concurrency.threadcoordination;

class ProducerConsumerExample {

    public static void main(String[] args) {
        Drop drop = new Drop();
        (new Thread(new Producer(drop))).start();
        (new Thread(new Consumer(drop))).start();
    }

}
