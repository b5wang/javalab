package com.b5wang.javalab;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class TestCode {

    private static void fun2(int A, int B, int C){
        Map<Integer,String> map = new HashMap<>();
        map.put(A,"a");
        map.put(B,"b");
        map.put(C,"c");

        int[] arr = {A,B,C};

    }

    public static void main(String[] args){

        long i = 1;

        System.out.println("" + (i % 5000));

    }

    private static int fun3(int N, int K){
        if(K == 0){
            return N - 1;
        }

        int chips = N;
        int allIn = 0;
        int bet1 = 0;
        while( allIn < K && chips > 2){
            if(N % 2 == 0){
                chips = chips / 2;
                allIn++;
            }else{
                chips--;
                bet1++;
            }
            //System.out.println("chips=" + chips + ", bet1=" + bet1 + ", allIn=" + allIn);
        }
        bet1+=chips;
        return bet1 + allIn;
    }

    private static void demoFun1(){
        int a = 100000000;
        int b = 100000000;
        System.out.println(fun1(a,b));
    }

    private static int fun1(int A, int B){
        BigInteger bA = BigInteger.valueOf(A);
        BigInteger bB = BigInteger.valueOf(B);
        BigInteger bAB = bA.multiply(bB);
        String bABStr = bAB.toString(2);
        System.out.println(bABStr);
        int count = 0;
        for(int i = 0; i < bABStr.length(); i++){
            char c = bABStr.charAt(i);
            if('1' == c){
                count++;
            }
        }
        return count;
    }
}
