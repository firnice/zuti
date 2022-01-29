package com.firnice.zuti.leetcode;

public class SmallestFactorization {

    public static int smallestFactorization(int a) {
        for (int i = 1; i < 999999999; i++) {
            long mul = 1, t = i;
            while (t != 0) {
                mul *= t % 10;
                t /= 10;
            }
            if (mul == a && mul <= Integer.MAX_VALUE)
                return i;
        }
        return 0;
    }


    public static void main(String[] args) {

        int i = smallestFactorization(200);
        System.out.println(i);
    }
}
