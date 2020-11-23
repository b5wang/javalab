package com.b5wang.javalab.alg.sort;

import java.util.Arrays;

/**
 * 交换排序
 * */
public class QuickSort {

    /**
     * 递归实现
     * */
    private static void ascentSort(int[] arr, int start, int end){
        if(start > end){
            return;
        }else{
            int benchmarkIndex = findBenchmarkIndex(arr, start, end);
            ascentSort(arr, start, benchmarkIndex - 1);//排序基准值左侧的子数组
            ascentSort(arr, benchmarkIndex + 1, end);//排序基准值右侧的子数组
        }
    }

    private static int findBenchmarkIndex(int[] arr, int start, int end){
        int benchmarkIndex = start;//数组的第一个元素作为基准值
        int tmp;
        while(start < end){
            //(1) 从右向左分别与基准值进行比较
            while((start < end) && arr[benchmarkIndex] <= arr[end]){
                end--;
            }

            //能跳出循环(1)有两个条件:a.start == end；b. arr[benchmarkIndex] > arr[end]
            //如果是条件b，这两个值应该交换位置
            if(start < end){
                tmp = arr[benchmarkIndex];
                arr[benchmarkIndex] = arr[end];
                arr[end] = tmp;
                benchmarkIndex = end;//变换基准值的位置
            }

            //(2) 从左向右分别与基准值进行比较
            while((start < end) && arr[start] <= arr[benchmarkIndex]){
                start++;
            }

            //能跳出循环(2)有两个条件:a.start == end；b. arr[start] > arr[benchmarkIndex]
            //如果是条件b，这两个值应该交换位置
            if(start < end){
                tmp = arr[benchmarkIndex];
                arr[benchmarkIndex] = arr[start];
                arr[start] = tmp;
                benchmarkIndex = start;//变换基准值的位置
            }
        }
        return benchmarkIndex;
    }


    public static void main(String[] args){
        int[] arr = {5,4,3,2,1,2,6,9,7,8,5};
        System.out.println("Original array  : " + Arrays.toString(arr));

        ascentSort(arr, 0, arr.length - 1);
        System.out.println("ascentSort array: " + Arrays.toString(arr));
    }

}
