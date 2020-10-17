package com.b5wang.javalab.java8recipes.collectionsframework;

import java.util.*;
import java.util.stream.Collectors;

public class DemoSet {

    /**
     * a collection that cannot contain duplicate elements.
     * The Set interface contains only methods inherited from Collection and adds the restriction
     * that duplicate elements are prohibited. Set also adds a stronger contract on the behavior
     * of the equals and hashCode operations, allowing Set instances to be compared meaningfully
     * even if their implementation types differ.
     * Two Set instances are equal if they contain the same elements.
     *
     * Set implementations: HashSet, TreeSet, and LinkedHashSet. 
     * */
    public static void main(String[] args){
        demoHashSet();
        demoTreeSet();
        demoLinkedHashSet();
        demo();
    }

    /**
     * Implemented with HashMap, is the best-performing implementation; however it makes no guarantees
     * concerning the order of iteration.
     * */
    private static void demoHashSet(){
        System.out.println("-------- HashSet");
        Set<Integer> intSet = new HashSet<>();
        intSet.add(3);
        intSet.add(5);
        intSet.add(1);
        intSet.add(9);
        intSet.add(7);
        intSet.stream().forEach(System.out::println);

        Set<String> stringSet = new HashSet<>();
        stringSet.add("B");//ABCDE
        stringSet.add("E");
        stringSet.add("A");
        stringSet.add("C");
        stringSet.add("D");
        System.out.println(Arrays.toString(stringSet.toArray()));
    }

    /**
     *  TreeSet, which stores its elements in a red-black tree, orders its elements based on their values;
     *  it is substantially slower than HashSet.
     *
     *
     * */
    private static void demoTreeSet(){
        System.out.println("-------- TreeSet");
        Set<Integer> intSet = new TreeSet<>();
        intSet.add(3);
        intSet.add(5);
        intSet.add(1);
        intSet.add(9);
        intSet.add(7);
        intSet.stream().forEach(System.out::println);

        Set<String> stringSet = new TreeSet<>();
        stringSet.add("B");//ABCDE
        stringSet.add("E");
        stringSet.add("A");
        stringSet.add("C");
        stringSet.add("D");
        for(String s:stringSet){
            System.out.println(s);
        }
    }

    /**
     * LinkedHashSet, which is implemented as a hash table with a linked list running through it,
     * orders its elements based on the order in which they were inserted into the set (insertion-order).
     * LinkedHashSet spares its clients from the unspecified, generally chaotic ordering provided
     * by HashSet at a cost that is only slightly higher.
     * */
    private static void demoLinkedHashSet(){
        System.out.println("-------- LinkedHashSet");
        Set<Integer> intSet = new LinkedHashSet<>();
        intSet.add(3);
        intSet.add(5);
        intSet.add(1);
        intSet.add(9);
        intSet.add(7);
        intSet.stream().forEach(System.out::println);

        Set<String> stringSet = new LinkedHashSet<>();
        stringSet.add("B");//ABCDE
        stringSet.add("E");
        stringSet.add("A");
        stringSet.add("C");
        stringSet.add("D");
        for(String s:stringSet){
            System.out.println(s);
        }
    }

    private static void demo(){
        Collection<String> collection = Arrays.asList("E","B","A","C","D","D","D","A","E");
        System.out.println(Arrays.toString(collection.toArray()));

        Set<String> stringSet = collection.stream().collect(Collectors.toSet());
        System.out.println(Arrays.toString(stringSet.toArray()));

        Set<String> stringLinkedHashSet = collection.stream().collect(Collectors.toCollection(LinkedHashSet::new));
        System.out.println(Arrays.toString(stringLinkedHashSet.toArray()));
    }
}
