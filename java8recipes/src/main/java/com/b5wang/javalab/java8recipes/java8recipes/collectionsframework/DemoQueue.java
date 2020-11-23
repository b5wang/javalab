package com.b5wang.javalab.java8recipes.collectionsframework;

import java.util.LinkedList;
import java.util.Queue;

public class DemoQueue {

    public static void main(String[] args){
        Queue<String> q = new LinkedList<String>();

        q.add("1");
        q.offer("2");

        // Get value, but not remove
        System.out.println(" - " + q.element());
        System.out.println(" - " + q.peek());

        // Get value, and remove
        System.out.println(" = " + q.remove());
        System.out.println(" = " + q.poll());
    }

}
