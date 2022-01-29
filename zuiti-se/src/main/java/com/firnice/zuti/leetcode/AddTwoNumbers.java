package com.firnice.zuti.leetcode;

import com.firnice.zuti.ListNode;

public class AddTwoNumbers {
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        StringBuffer a = new StringBuffer();
        StringBuffer b = new StringBuffer();

        ListNode curL1 = l1, curL2 = l2;
        do {
            a.insert(0, curL1.val);
            curL1 = curL1.next;
        } while (curL1 != null);
        do {
            b.insert(0, curL2.val);
            curL2 = curL2.next;
        } while (curL2 != null);

        ListNode last = null;
        ListNode head = null;
        int carry = 0;
        String s1 = a.toString();
        String s2 = b.toString();

        int j1 = a.length(), j2 = b.length();
        do {
            int x=0, y = 0;
            if (j1 > 0) {
                x = Integer.valueOf(Character.getNumericValue(s1.charAt(j1-1)));
                j1--;
            }
            if (j2 > 0) {
                y = Integer.valueOf(Character.getNumericValue(s2.charAt(j2-1)));
                j2--;
            }

            int i = x + y + carry;

            if (i > 9) {
                i = i - 10;
                carry = 1;
            }else {
                carry = 0;
            }
            ListNode cur =  new ListNode(i, null);
            if(last != null){
                last.next = cur;
            }else {
                head = cur;
            }
            last = cur;

        } while (j1 > 0 || j2 > 0);

        if (carry > 0) {
            ListNode cur = new ListNode(1, null);
            last.next = cur;
        }
        return head;

    }

    public static void main(String[] args) {

//        ListNode listNode3 = new ListNode(3);
//        ListNode listNode2 = new ListNode(4, listNode3);
//        ListNode listNode1 = new ListNode(2, listNode2);
//
//        ListNode listNode23 = new ListNode(4);
//        ListNode listNode22 = new ListNode(6, listNode23);
//        ListNode listNode21 = new ListNode(5, listNode22);
//
//        ListNode listNode = addTwoNumbers(listNode1, listNode21);

        ListNode listNode1 = new ListNode(9);

        ListNode listNode29 = new ListNode(9);
        ListNode listNode28 = new ListNode(9, listNode29);
        ListNode listNode27 = new ListNode(9, listNode28);
        ListNode listNode26 = new ListNode(9, listNode27);
        ListNode listNode25 = new ListNode(9, listNode26);
        ListNode listNode24 = new ListNode(9, listNode25);
        ListNode listNode23 = new ListNode(9, listNode24);
        ListNode listNode22 = new ListNode(9, listNode23);
        ListNode listNode21 = new ListNode(9, listNode22);
        ListNode listNode20 = new ListNode(1, listNode21);

        ListNode listNode = addTwoNumbers(listNode1, listNode20);

        do {
            System.out.println(listNode.val);
            listNode = listNode.next;
            if (listNode == null) {
                break;
            }
        } while (true);
    }


}

