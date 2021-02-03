package com.b5wang.javalab.java8recipes.util.concurrent;

import java.util.concurrent.atomic.AtomicInteger;

public class DemoAtomic {

    public static void main(String[] args){
        int max = 19;//0 ~ 19
        AtomicInteger messageKey = new AtomicInteger();

        for(int i = 0; i<50; i++){
            int result = messageKey.getAndUpdate(key->{
                if(key<max){
                    return key + 1;
                }else{
                    return 0;
                }
            });

            System.out.println(result);
        }

        System.out.println("----------------------");

//        messageKey = new AtomicInteger();
//        for(int i = 0; i<20; i++){
//            int result = messageKey.updateAndGet(key->{
//                if(key<max){
//                    return key + 1;
//                }else{
//                    return 1;
//                }
//            });
//
//            System.out.println(result);
//        }

    }

}
