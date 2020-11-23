package com.b5wang.javalab.java8recipes.collectionsframework;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class DemoList {

    public static void main(String[] args){

        List<String> list = Arrays.asList("1","2","3","4","5","6","7","8","9","10");
        System.out.println(list);

        Collections.shuffle(list, new Random());
        System.out.println(list);
        System.out.println(list.indexOf("5"));
        System.out.println(list.lastIndexOf("5"));


    }

}
