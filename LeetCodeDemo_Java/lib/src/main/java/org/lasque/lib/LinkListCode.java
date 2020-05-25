package org.lasque.lib;

/**
 * TuSDK
 * $
 *
 * @author H.ys
 * @Date $ $
 * @Copyright (c) 2019 tusdk.com. All rights reserved.
 */

import org.w3c.dom.Node;

import java.util.HashMap;

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

        ListNode() {}

        ListNode(int x) {
            val = x;
            next = null;
        }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    static class Node{
        public int val;
        public Node prev;
        public Node next;
        public Node child;
        public Node random;


        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    static public void main(String[] args) {
        ListNode listNode = rotateRight(
                new ListNode(1,new ListNode(2,new ListNode(3,new ListNode(4,new ListNode(5))))),
                6
        );
        printLinkedList(listNode);
    }

    public static ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null) return head;
        ListNode point = head, last = head,newLastPoint = null;
        int index = 0;
        while (point.next != null){
            point = point.next;
            index ++;
        }
        last = point;

        int newLastIndex = k <= index ? index - k : index - (k % (index +1));
        if (newLastIndex == index) return head;
        point = head;
        int newIndex = 0;
        while (point.next != null){
            if (newIndex == newLastIndex) newLastPoint = point;
            point = point.next;
            newIndex ++;
        }

        last.next = head;
        ListNode newHead = newLastPoint.next;
        newLastPoint.next = null;
        return newHead;
    }

    public Node copyRandomList(Node head) {
        if (head == null) return null;
        HashMap<Node,Node> nodeMap = new HashMap<>();
        Node point = head;
        do {
            Node newPoint = new Node(point.val);
            nodeMap.put(point, newPoint);
            point = point.next;
        } while (point != null);

        point = head;
        Node newHead = nodeMap.get(point);
        Node newPoint = newHead;
        while (point != null){
            if (newPoint == null) break;
            newPoint.next = nodeMap.get(point.next);
            newPoint.random = nodeMap.get(point.random);
            point = point.next;
            newPoint = newPoint.next;
        }
        return newHead;
    }

    public Node flatten(Node head) {
        if (head == null || head.next ==null && head.child == null) return head;
        Node point = head;
        while (point.next != null){
            if (point.child !=null){
                 Node childLast = traverseChild(point.child);
                 Node next = point.next;
                 childLast.next = next;
                 next.prev = childLast;
                 point.child.prev = point;
                 point.next = point.child;
                 point.child = null;
            }
            point = point.next;
        }
        if (point.child !=null){
            Node childLast = traverseChild(point.child);
            point.child.prev = point;
            point.next = point.child;
            point.child = null;
        }
        return head;
    }

    public Node traverseChild(Node head){
        Node point = head;
        while (point.next !=null){
            if (point.child != null){
                flatten(point);
            }
            point = point.next;
        }
        if (point.child != null){
            flatten(point);
        }
        return point;
    }

    public Node getChildLast(Node head){
        while (head.next != null){
            head = head.next;
        }
        return head;
    }



    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int l1Length = 1,l2Length = 1;
        ListNode mainPoint = l1;
        while (mainPoint.next != null){
            l1Length++;
            mainPoint = mainPoint.next;
        }
        mainPoint = l2;
        while (mainPoint.next != null){
            l2Length ++;
            mainPoint = mainPoint.next;
        }
        int forCount = Math.min(l1Length,l2Length);
        ListNode headNode = new ListNode();
        ListNode currentNode = headNode;
        int surPlus = -1;
        for (int i = 0;i<forCount;i++){
            int sum = l1.val + l2.val;
            if (surPlus != -1){
                sum += surPlus;
                surPlus = -1;
            }
            if (i == 0){
                if (sum >= 10){
                    surPlus = sum / 10;
                    currentNode.val = sum % 10;
                } else {
                    currentNode.val = sum;
                }
            } else {
                ListNode newNode = new ListNode();
                if (sum >= 10){
                    surPlus = sum / 10;
                    newNode.val = sum % 10;
                } else {
                    newNode.val = sum;
                }
                currentNode.next = newNode;
                currentNode = currentNode.next;
            }
            l1 = l1.next;
            l2 = l2.next;
        }
        currentNode.next = l1 != null ? l1 : l2;
        if (surPlus != -1){
            if (currentNode.next != null){
                while (currentNode.next != null){
                    currentNode = currentNode.next;
                    if (surPlus != -1){
                        currentNode.val += surPlus;
                        if (currentNode.val >= 10){
                            surPlus = currentNode.val / 10;
                            currentNode.val = currentNode.val % 10;
                        } else {
                            surPlus = -1;
                        }
                    }
                    if (currentNode.next == null && surPlus != -1){
                        currentNode.next = new ListNode(surPlus);
                        surPlus = -1;
                    }
                }
            } else {
                currentNode.next = new ListNode(surPlus);
                surPlus = -1;
            }
        }

        return headNode;
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        ListNode main = l1.val < l2.val ? l1 : l2;
        ListNode tamp2 = l1.val < l2.val ? l2 : l1;
        ListNode tamp1;
        ListNode result = main;
        while (main.next != null){
            if (main.next.val > tamp2.val){
                tamp1 = main.next;
                main.next = tamp2;
                tamp2 = tamp1;
            }
            main = main.next;
        }
        main.next = tamp2;
        return result;
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
