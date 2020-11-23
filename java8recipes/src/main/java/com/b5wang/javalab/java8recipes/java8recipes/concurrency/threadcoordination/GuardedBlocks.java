package com.b5wang.javalab.java8recipes.concurrency.threadcoordination;

class GuardedBlocks {

    boolean joy;

    synchronized void guardedJoy() {
        // This guard only loops once for each special event, which may not
        // be the event we're waiting for.
        while(!joy) {
            try {
                System.out.println("Before wait!");
                wait();
                System.out.println("After wait!");
            } catch (InterruptedException e) {}
        }
        System.out.println("Joy and efficiency have been achieved!");
    }

    public static void main(String[] args) throws InterruptedException {
        GuardedBlocks gb = new GuardedBlocks();
        System.out.println("Before wait!");
        gb.wait();
        System.out.println("After wait!");
    }

}
