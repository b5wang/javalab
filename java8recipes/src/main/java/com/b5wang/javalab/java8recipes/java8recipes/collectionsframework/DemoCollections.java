package com.b5wang.javalab.java8recipes.collectionsframework;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class DemoCollections {

    public static void main(String[] args){
        traversingCollection();
    }

    /**
     * Traversing collections. 3 ways,
     *  (1) using aggregate operations, after jdk1.8
     *  (2) with the for-each construct
     *  (3) by using Iterators.
     * */
    public static void traversingCollection(){
        // (1)
        List<String> stringList = Arrays.asList("1","2","3","4","5","11","22","33","44","55");
        stringList.stream().filter(s -> s.length() < 2).forEach(System.out::println);

        // (2)
        for(String s : stringList){
            System.out.println("foreach:: " + s);
        }

        // (3)
        Iterator<String> it = stringList.iterator();
        while (it.hasNext()){
            String s = (String)it.next();
            System.out.println("iterator:: " + s);
        }
    }

}
