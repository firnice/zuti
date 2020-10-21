package com.firnice.zuti.collection;

public class ListMain {

    public static void main(String[] args) {
        int[] a = new int[10];
        a[0] = 0;
        a[1] = 1;
        a[2] = 2;
        a[3] = 3;
        System.arraycopy(a, 0, a, 0, 7);

//        a[2] = 99;

        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i] + " ");
        }


        Integer aa = 1;
        Integer bb = aa;
        aa = 3;
        System.out.println(bb);
    }
}
