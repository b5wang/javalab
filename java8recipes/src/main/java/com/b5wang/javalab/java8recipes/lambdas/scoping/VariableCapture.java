package com.b5wang.javalab.java8recipes.lambdas.scoping;

import java.util.Arrays;
import java.util.List;

public class VariableCapture {

    private int sumField = 0;

    public void visitFieleVal(){
        List<Integer> intList = Arrays.asList(1,2,3,4,5);
        intList.stream().forEach(i->{
            sumField += i;
        });
        System.out.println("sumField: " + sumField);
    }


    public static void main(String[] args){

        // Local variable
        // Sample 1
        int localIntVal = 10;
        Integer localIntegertVal = 100;
        List<Integer> intList = Arrays.asList(1,2,3,4,5);
        intList.stream().forEach(i->{
            int result = i + localIntVal + localIntegertVal;// only can use but not change
            System.out.println("result: " + result);
        });

        // Sample 2, sum the int list
        int sum = 0;
        intList.stream().forEach(i->{
            //sum = sum + i;// it is not allowed to mutate the local variable
        });

        // stream provide a native way
        sum = intList.stream().mapToInt(Integer::intValue).sum();
        System.out.println("sum: " + sum);

        VariableCapture vc = new VariableCapture();
        vc.visitFieleVal();
    }

}
