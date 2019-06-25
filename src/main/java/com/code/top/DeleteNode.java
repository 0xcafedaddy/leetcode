package com.code.top;

/**
 * Created by kunYang on 2019/06/25.
 *
 * 请编写一个函数，使其可以删除某个链表中给定的（非末尾）节点，你将只被给定要求被删除的节点。
 *
 *
 * 说明:
 *
 * 链表至少包含两个节点。
 * 链表中所有节点的值都是唯一的。
 * 给定的节点为非末尾节点并且一定是链表中的一个有效节点。
 * 不要从你的函数中返回任何结果。
 *
 */
public class DeleteNode {


    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }

    }


    /**
     * 正常题目都是给定一个链表的开始元素，一般解法都是从第一个开始往后遍历，找到相等的val 更改上一个节点的 next 到下一个的位置.
     *
     * 但是此题目给定的参数是当前的要删除的节点，只需要更改当前节点的链表的next元素即可
     * @param node
     */
    public void deleteNode1(ListNode node) {

        // 最后一个元素
        if (node.next == null){
            return;
        }

        ListNode next = node.next;

        node.val = next.val;
        node.next = next.next;

    }



    public void deleteNode2(ListNode node) {

        // 最后一个元素
        if (node.next == null){
            return;
        }

        ListNode next = node.next;

        node.val = next.val;
        node.next = next.next;

    }


}
