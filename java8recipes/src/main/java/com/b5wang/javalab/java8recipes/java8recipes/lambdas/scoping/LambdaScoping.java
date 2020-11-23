package com.b5wang.javalab.java8recipes.lambdas.scoping;

public class LambdaScoping extends SuperLambdaScoping{

    /**
     * (1) The meaning of this and super
     * this and super all refer to enclosed environment
     * */
    Runnable r1 = () -> System.out.println(this.getClass().getName());
    Runnable r2 = () -> System.out.println(super.getClass().getName());// super is object
    Runnable r3 = () -> System.out.println(toString());
    Runnable r4 = () -> System.out.println(this);
    //Runnable r5 = () -> System.out.println(super);//not allowed


    Runnable r6 = new Runnable() {
        @Override
        public void run() {
            System.out.println(this.getClass().getName());
        }
    };

    Runnable r7 = new Runnable() {
        @Override
        public void run() {
            System.out.println(super.getClass().getName());
        }
    };

    Runnable r8 = new Runnable() {
        @Override
        public void run() {
            System.out.println(toString());
        }
    };

    Runnable r9 = new Runnable() {
        @Override
        public void run() {
            System.out.println(super.toString());
        }
    };

    public String toString(){
        return "Hello, LambdaScoping";
    }

    /**
     * If you override a method from your superclass (or your superclass's superclass etc.),
     * super.theMethod() will invoke the original method instead of the one you overrode it with.
     * If you did not actual override theMethod, super.theMethod() will act exactly like theMethod().
     * In this case I did not override getClass(), therefore super.getClass() returns the current class name.
     * */
    public void printParent(){
        System.out.println("this is " + this.getClass().getName());
        System.out.println("super is " + super.getClass().getName());
        System.out.println("super is " + super.getClass().getSuperclass().getName());
    }


    public static void main(String[] args){
        /**
         * Lambda implement functional interface (effective functional interface, only one abstract method)
         * 接口中允许存在的元素：
         *  1. abstract method
         *  2. static final fields
         *  3. static nested classes
         *  4. default method
         * Calling inherited 2,3,4 is not allowed.
         * */
        MyFunction<String,String> myFunLambda = s -> {
            //printSomething(); can't call
            //String msg = MSG;
            return "Hello, " + s;
        };

        MyFunction<String,String> myFunInnerClass = new MyFunction<String,String>(){
            @Override
            public String apply(String s) {
                printSomething();
                return s + " ... " + MSG;
            }
        };

        /**
         * 在Lambda实现中无法调用接口中的元素，但是通过Lambda实现对象来调用这些元素还是可以的。
         */
        System.out.println(MyFunction.MSG);
        System.out.println(myFunLambda.MSG);
        myFunLambda.printSomething();

        new LambdaScoping().r1.run();
        new LambdaScoping().r2.run();
        new LambdaScoping().r3.run();
        new LambdaScoping().r4.run();

        // Inner class
        myFunInnerClass.apply("Ye ya yo~");

        new LambdaScoping().r6.run();
        new LambdaScoping().r7.run();
        new LambdaScoping().r8.run();
        new LambdaScoping().r9.run();

        /**
         * About super.getClass()
         * */
        LambdaScoping ls = new LambdaScoping();
        ls.printParent();
    }

}
