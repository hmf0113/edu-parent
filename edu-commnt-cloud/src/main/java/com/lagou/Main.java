package com.lagou;

import com.lagou.entity.StatusEnum;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int[] arr = new int[5];
        for (int i = 0;i <arr.length;i++){
            System.out.println(arr[i]);
        }
        arr[0]=11;
        arr[1]=21;
        arr[2]=31;
        arr[3]=41;
        arr[4]=51;
        System.out.println("赋值后的数组元素值：");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
        for (int i = arr.length-1;i>=1;i--){
            arr[i]=arr[i-1];
        }

        arr[0]=55;
        for (int i =0;i<arr.length;i++){
            System.out.println(arr[i]);
        }
    }


}