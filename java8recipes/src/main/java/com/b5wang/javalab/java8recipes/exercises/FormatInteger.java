package com.b5wang.javalab.exercises;

import java.util.Deque;
import java.util.LinkedList;

public class FormatInteger {

    /**
     * Convert 1789033346 to 1,789,033,346
     * */
    public static void main(String[] args){
        withDeque();
    }

    /**
     * Using Stack (Deque)
     * */
    public static void withDeque(){
        String intStr = "1789033346";
        Deque<Character> charStack = new LinkedList<>();
        int counter = 0;
        for(int i = intStr.length() - 1; i >= 0 ; i--){
            if(counter == 3){
                charStack.push(',');
                counter = 1;
            }else{
                counter ++;
            }
            charStack.push(intStr.charAt(i));
        }

        while(charStack.size() > 0){
            System.out.print(charStack.pop());
        }
        System.out.println();
    }

    /**
     * Using list
     * */
    public static void withList(){
        String intStr = "1789033346";
        LinkedList<Character> charList = new LinkedList<>();
        int counter = 0;
        for(int i = intStr.length() - 1; i >= 0 ; i--){
            if(counter == 3){
                charList.add(',');
                counter = 1;
            }else{
                counter ++;
            }
            charList.add(intStr.charAt(i));
        }

        StringBuilder sb = new StringBuilder();
        for(int j = charList.size() - 1; j >= 0 ; j--){
            sb.append(charList.get(j));
        }
        System.out.println(sb.toString());
    }

}
