package org.lasque.lib;

/**
 * TuSDK
 * $
 *
 * @author H.ys
 * @Date $ $
 * @Copyright (c) 2019 tusdk.com. All rights reserved.
 */

/**
 * Definition for singly-linked list.
 * class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) {
 * val = x;
 * next = null;
 * }
 * }
 */
public class LinkListCode {
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    static public void main(String[] args) {
        oddEvenList(new ListNode(1,new ListNode(2,new ListNode(3,new ListNode(4,new ListNode(5,null))))));
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        ListNode mainPoint = l1;
        ListNode movePoint = l2;
        while (movePoint != null){
            ListNode node = movePoint.next;
            ListNode next = mainPoint.next;
            movePoint.next = mainPoint.next;
            mainPoint.next = movePoint;
            movePoint = node;
            mainPoint = next;
        }
        return l1;
    }

    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) return true;
        ListNode fastPoint = head.next;
        ListNode slowPoint = head;
        //首先 使快速指针以二倍速前进到链表最后
        while (fastPoint != null && fastPoint.next != null){
            fastPoint = fastPoint.next.next;
            slowPoint = slowPoint.next;
        }
        //重新定位快速指针到中间值后一位
        fastPoint = slowPoint.next;
        slowPoint.next = null;

        //翻转快速指针所指的链表
        ListNode reverse = null;
        ListNode prev = null;
        while (fastPoint != null){
            prev = fastPoint;
            fastPoint = fastPoint.next;
            prev.next = reverse;
            reverse = prev;
        }
        //比较原始链表前半部分与翻转后的链表
        while (head != null && reverse != null){
            if (head.val != reverse.val){
                return false;
            }
            head = head.next;
            reverse = reverse.next;
        }
        return true;
    }

    public static ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode fastPoint = head;
        ListNode slowPoint = head;
        ListNode currentEvenPoint = head.next;
        int step = 1;
        while (fastPoint != null){
            if (fastPoint == slowPoint){
                fastPoint = fastPoint.next;
                step++;
                continue;
            }
            if (step % 2 == 1){
                ListNode preNode = slowPoint;
                ListNode currentNode = fastPoint;
                System.out.println();
                slowPoint = fastPoint;
                fastPoint = fastPoint.next;
                step++;
                currentEvenPoint.next = currentNode.next;
                currentNode.next = preNode.next;
                preNode.next = currentNode;
                currentEvenPoint = currentEvenPoint.next;
                continue;
            }
            fastPoint = fastPoint.next;
            step++;
        }
        printLinkedList(head);
        return head;
    }

    public ListNode removeElements(ListNode head, int val) {
        while (head != null && head.val == val)
            head = head.next;
        if (head == null) return head;

        ListNode cur = head;
        while (cur.next != null){
            if (cur.next.val == val){
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return head;
    }

    public ListNode reverseList(ListNode head) {
        if (head == null) return null;
        if (head.next == null) return head;
        ListNode currentHead = head;
        ListNode currentItem = head.next;
        ListNode nextItem = head;
        while (nextItem != null){
            nextItem = currentItem.next;
            head.next = currentItem.next;
            currentItem.next = currentHead;
            currentHead = currentItem;
            currentItem = nextItem;
        }
        return currentHead;
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) return null;
        if (head.next == null && n == 1) {
            return null;
        }
        ListNode fastPoint = head;
        ListNode slowPoint = head;

        int step = 0;
        while (fastPoint.next != null){
            if (step >= n){
                slowPoint = slowPoint.next;
            }
            fastPoint = fastPoint.next;
            step ++;
        }
        if (step+1 - n == 0){
            return head.next;
        } else if (n == 1){
            slowPoint.next = null;
        } else {
            slowPoint.next = slowPoint.next.next;
        }
        return head;
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;

        int lenA = 1,lenB = 1;
        ListNode curA = headA;
        ListNode curB = headB;

        while (curA.next != null){
            lenA ++;
            curA = curA.next;
        }

        while (curB.next != null){
            curB = curB.next;
            lenB++;
        }

        if (curB != curA) return null; //如果有交集 最终节点会相同

        if (lenA > lenB){
            for (int i =0;i<lenA -lenB;i++){
                headA = headA.next;
            }
        } else {
            for (int i =0;i<lenB - lenA;i++){
                headB = headB.next;
            }
        }
        while (headA != headB){
            headA = headA.next;
            headB = headB.next;
        }
        return headA;
    }

    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) return false;
        ListNode slowPoint = head;
        ListNode fastPoint = head.next;
        while(true){
            if (slowPoint == null || fastPoint == null) return  false;
            if (slowPoint.next == null || fastPoint.next == null) return false;
            if (fastPoint.next == slowPoint || fastPoint == slowPoint) return true;
            slowPoint = slowPoint.next;
            fastPoint = fastPoint.next;
            if (fastPoint == null) return false;
            fastPoint = fastPoint.next;
        }
    }

    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null )return null;
        ListNode slowPoint = head;
        ListNode fastPoint = head;
        while (slowPoint != null && fastPoint != null && fastPoint.next != null){
            slowPoint = slowPoint.next;
            fastPoint = fastPoint.next.next;
            if (slowPoint == fastPoint) break;
        }
        fastPoint = head;
        while (slowPoint != null && fastPoint != null && slowPoint != fastPoint){
            slowPoint = slowPoint.next;
            fastPoint = fastPoint.next;
        }
        return slowPoint;
    }

    public static void printLinkedList(ListNode head){
        System.out.println(head.val);
        if (head.next !=null){
            printLinkedList(head.next);
        }
    }
}
