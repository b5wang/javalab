package com.b5wang.javalab.alg.sort;

import java.util.Arrays;

public class InsertionSort {

    /**
     * O(n^2)
     *
     * This alg is a little bit like bubble sort
     * */
    private static int[] ascentSort(int[] arr){
        int tmp;

        // Compare n-1 round
        for(int i = 0; i < arr.length - 1; i++){

            // Compare from top element(0) to last element(i - 1) with element(i)
            for(int j = i + 1; j  > 0; j--){
                if(arr[j - 1] > arr[j]){
                    tmp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = tmp;
                }else{
                    /**
                     * 数组0到i已经有序
                     * */
                    break;
                }
            }

        }

        return arr;
    }

    public static void main(String[] args){
        int[] arr = {10,8,6,3,9,2,1,4,5,7,6,5};
        System.out.println("Original array     : " + Arrays.toString(arr));
        System.out.println("Ascend sorted array: " + Arrays.toString(ascentSort(arr)));
    }

}
