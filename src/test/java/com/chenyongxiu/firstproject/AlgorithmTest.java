package com.chenyongxiu.firstproject;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class AlgorithmTest {

    public static void doSort(int[] arr) {

        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }

    public static void doSort2(int[] arr, int left, int right) {

        if (left >= right) {
            return;
        }
        int index = arr[left];
        int l = left;
        int r = right;
        while (l < r) {
            while (l < r && arr[r] > index) r--;
            if (l < r) {
                arr[l++] = arr[r];
            }
            while (l < r && arr[l] < index) l++;
            if (l < r) {
                arr[r--] = arr[l];
            }
        }
        arr[l] = index;
        doSort2(arr, left, l - 1);
        doSort2(arr, l + 1, right);
    }

    public static void main(String[] args) {
        final int[] getintarr = getintarr();
        long nano = Instant.now().toEpochMilli();
        doSort2(getintarr,0, getintarr.length-1);
//        doSort(getintarr);
        long nano1 = System.currentTimeMillis();
        System.out.println(nano1-nano);

    }

    public static int[] getintarr(){
        List<Integer> l=new ArrayList<>();
        IntStream.range(0,10000).forEach(x->{
            int i = new Random().nextInt(10000);
            l.add(i);
        });
        int[] ints = l.stream().mapToInt(Integer::intValue).toArray();
        return ints;
    }

}
