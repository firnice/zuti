package com.firnice.zuti.test;

import com.firnice.zuti.ListNode;

public class KeepTest {

    /**
     * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
     * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     * <p>
     * 示例：
     * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
     * 输出：7 -> 0 -> 8
     * 原因：342 + 465 = 807
     */


    public ListNode addTwoNumbers(ListNode a, ListNode b) {

        int x = 0;//进位
        ListNode first = new ListNode(0, null);//头
        ListNode index = first; //循环的指针
        while (a.getNext() != null && b.getNext() != null) { //两个都有值
            int i = a.getVal() + b.getVal() + x; //当前节点相加的结果
            if (i > 9) { //有进位
                x = 1;
                i = i - 10;
            } else {
                x = 0;
            }
            ListNode result = new ListNode(i, null);
            index.next = result;

            index = index.next;
            a = a.next;
            b = b.next;
        }
        //差缺补漏
        while (a.getNext() != null) {
            index.next = a.getNext();
            index = index.next;
            a = a.next;
        }
        while (b.getNext() != null) {
            index.next = b.getNext();
            index = index.next;
            b = b.next;
        }

        return first.next;
    }


    public static void main(String[] args) {

    }

}
