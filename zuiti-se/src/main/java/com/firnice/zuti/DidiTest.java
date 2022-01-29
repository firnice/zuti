package com.firnice.zuti;

public class DidiTest {

    public static ListNode fun(ListNode listNode) {

        ListNode index = listNode;
        ListNode tmp =new ListNode(0,null);
        ListNode lastNode = index;
        index = index.next;
        while (index.next != null) {
            tmp = index.next; //暂时保存
            index.next = lastNode; //下个指针指向前一个
            lastNode = index; //前一个指针指向当前节点
            index = tmp.next; //循环旧的下一个
        }
        return index;

    }

    public static void main(String[] args) {
        ListNode tmp =new ListNode(0,null);

        fun(tmp);
    }
}
