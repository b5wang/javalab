package com.b5wang.javalab.alg.sort;

import java.util.Arrays;

class BubbleSort {

    /**
     * 交换排序
     * O(n^2)
     * */
    private static int[] ascentSort(int[] arr){
        int tmp;

        // Compare n-1 round
        for(int i = 0; i < arr.length - 1; i++){

            /** ====================================
             *  这个flag非常重要!!!!!!!! 可以将时间复杂度的最好情况降为O(n)
             *  如果没有交换，说明排序已经完成
             * ==================================== */
            boolean hasExchanged = false;

            // Compare from last element(arr.length - 1) to top element(i)
            for(int j = arr.length - 1; j > i; j--){
                if(arr[j] < arr[j - 1]){
                    tmp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = tmp;
                    hasExchanged = true;
                }
            }

            /**
             * 如果本轮比较没有交换发生，说明数组已经有序
             * */
            if(!hasExchanged){
                break;
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
