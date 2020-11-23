package com.b5wang.javalab.java8recipes.collectionsframework;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Usually pronounced as deck, a deque is a double-ended-queue.
 * A double-ended-queue is a linear collection of elements that supports the insertion and removal of elements at both end points.
 *
 * The Deque interface is a richer abstract data type than both Stack and Queue
 * because it implements both stacks and queues at the same time.
 *
 * Note that the Deque interface can be used both as last-in-first-out stacks and first-in-first-out queues.
 * */
public class DemoDeque {

    public static void main(String[] args){

        /**
         * Provide 3 basic operations:
         * 1. Insert
         * 2. Remove
         * 3. Retrieve
         * */
        Deque<String> deque = new LinkedList<>();

        // Insert
        // add throws exception if deque is full
        // offer returns false if deque is full
        deque.add("1");
        deque.offer("111");
        deque.addFirst("2");
        deque.offerFirst("222");
        deque.addLast("3");
        deque.offerLast("333");
        deque.add("444");

        System.out.println(deque.toString());

        // Retrieve
        // get throws exception if no element
        // peek returns null if no element
        System.out.println("Get first : " + deque.getFirst());
        System.out.println("Peek first: " + deque.peekFirst());
        System.out.println("Get last  : " + deque.getLast());
        System.out.println("Peek last : " + deque.peekLast());
        System.out.println("Peek      : " + deque.peek());


        // Remove
        System.out.println("Poll      : " + deque.poll());// Queue operation
        System.out.println(deque.toString());
        System.out.println("Remove first: " + deque.removeFirst());
        System.out.println(deque.toString());
        System.out.println("Poll first  : " + deque.pollFirst());
        System.out.println(deque.toString());
        System.out.println("Remove last : " + deque.removeLast());
        System.out.println(deque.toString());
        System.out.println("Poll   last: " + deque.pollLast());
        System.out.println(deque.toString());

    }

}
