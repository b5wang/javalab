package com.b5wang.javalab.java8recipes.lambdas.usage;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.Supplier;

/**
 * How to use lambda expressions in your code?
 * How the code determines the exact type? From context.
 *
 * There 6 kinds of contexts you can provide appropriate target types.
 *   (1) From the arguments of method or constructors
 *   (2) From variable declaration and assignments
 *   (3) From return statement of method
 *   (4) From lambda expression bodies. The target type is the type expected for the body.
 *   (5) From ternary conditional expressions. Target type for both arms is provided by the context.
 *   (6) From cast expressions which provide the target type explicitly.
 * */
public class DetectTargetType {

    private Function<String,String> fun;

    // (1) From the arguments of method or constructors
    public DetectTargetType(Function<String,String> fun){
        this.fun = fun;
    }

    public void setFun(Function<String, String> fun) {
        this.fun = fun;
    }

    // (2) From variable declaration and assignments
    public Function<String,String> getFun(){
        if(fun == null){
            fun = s -> "[UsingLambdaExpressions.getFun()]: " + s;
        }
        return fun;
    }

    // (3)
    public Function<String,String> returnFun(){
        return (s) -> "[UsingLambdaExpressions.returnFun(String s)]: " + s;
    }

    // (5)
    public IntBinaryOperator getIntBinaryOperator(String operator){
        IntBinaryOperator opt = operator.equals("+") ? (x,y) -> x + y : (x,y) -> x - y;
        return opt;
    }


    public static void main(String[] args){
        // (2)
        IntBinaryOperator[] operatorsArray = new IntBinaryOperator[]{
                (x,y) -> x + y,
                (x,y) -> x - y,
                (x,y) -> x * y,
                (x,y) -> x / y,
        };

        List<IntBinaryOperator> operatorsList = new ArrayList<>();
        operatorsList.add((x,y) -> x + y);
        operatorsList.add((x,y) -> x - y);
        operatorsList.add((x,y) -> x * y);
        operatorsList.add((x,y) -> x / y);

        // (4)
        Function<String,Function<String,String>> funInFun = (s) -> (t) -> "[funInFun]: init str:" + s + ", second str: " + t;
        String str = funInFun.apply("Hello").apply("Lambda!");
        System.out.println(str);

        // (6)
        //Object o = () -> "Hi!";
        Object s = (Supplier<String>) () -> "Hello!";
        Object c = (Function<String,String>) (s1) -> s1 + ", ya!";
    }

}
