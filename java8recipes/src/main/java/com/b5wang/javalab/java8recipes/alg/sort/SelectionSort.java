package com.b5wang.javalab.alg.sort;

import java.util.Arrays;

class SelectionSort {

    /**
     * O(n^2)
     * */
    private static int[] ascentSort(int[] arr){
        int indexOfMin,tmp;

        // Compare n-1 round
        for(int i = 0; i < arr.length - 1; i++){
            indexOfMin = i;

            // Compare from top element(i) to last element(arr.length - 1), to find the min value and record the index
            for(int j = i + 1; j < arr.length; j++){
                if(arr[j] < arr[indexOfMin]){
                    indexOfMin = j;
                }
            }

            // Exchange
            if(indexOfMin != i){
                tmp = arr[i];
                arr[i] = arr[indexOfMin];
                arr[indexOfMin] = tmp;
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
