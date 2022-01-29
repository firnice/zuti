package com.firnice.zuti;

public class DidiTest2 {

    /**
     * 数字转汉字
     */

    public static String fun(int num) {

        String s = String.valueOf(num);

        String result = "";
        //高位
        for (int i = 0; i < s.length(); i++) {
            String s1 = numTohanzi(s.charAt(i));
            //当前位数>0 会加位,整体长度是4，
            if (s.charAt(i) > 0) {
                int i1 = 4 - s.length() +i;
                if ( i1== 0) {
                    s1 += "千";
                } else if (i1 == 1) {
                    s1 += "百";
                } else if (i1 == 2) {
                    s1 += "十";
                }
            }
            result += s1;
        }

        return result;

    }

    public static String numTohanzi(char num) {
        switch (num) {
            case '0':
                return "零";
            case '1':
                return "一";
            case '2':
                return "二";
            case '3':
                return "三";
            case '4':
                return "四";
            case '5':
                return "五";
            case '6':
                return "六";
            case '7':
                return "七";
            case '8':
                return "八";
            case '9':
                return "九";
            default:
                return "";
        }
    }


    public static void main(String[] args) {

        System.out.println(fun(123));
        System.out.println(fun(9999));
    }


}
