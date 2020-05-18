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
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    static public void main(String[] args) {

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
}
