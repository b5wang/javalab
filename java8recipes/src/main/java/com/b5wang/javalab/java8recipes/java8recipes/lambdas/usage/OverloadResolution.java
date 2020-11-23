package com.b5wang.javalab.java8recipes.lambdas.usage;

import java.util.Comparator;

public class OverloadResolution {

    public static void main(String[] args){
        // java: incompatible types: no instance(s) of type variable(s) T,U exist so that java.util.Comparator<T> conforms to java.lang.Comparable<java.lang.String>
        Comparator<String> cs = Comparator.comparing(s -> s.length());
    }

}
