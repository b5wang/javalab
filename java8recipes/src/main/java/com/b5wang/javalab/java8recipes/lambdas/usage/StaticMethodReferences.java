package com.b5wang.javalab.java8recipes.lambdas.usage;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class StaticMethodReferences {

    /**
     * RefType::staticMethod
     * */
    public static void main(String[] args){
        Integer[] intArr = new Integer[]{5,1,3,4,2};
        System.out.println(Arrays.toString(intArr));


        // implement Comparator<Integer>
        Arrays.sort(intArr,(x,y) -> (x > y) ? -1 : (x == y ? 0 : 1));
        System.out.println(Arrays.toString(intArr));

        // call a ready compare method
        Arrays.sort(intArr,Integer::compare);
        System.out.println(Arrays.toString(intArr));
    }

}
