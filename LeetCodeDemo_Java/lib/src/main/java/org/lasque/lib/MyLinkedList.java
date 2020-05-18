package org.lasque.lib;

import org.w3c.dom.Node;

/**
 * TuSDK
 * $
 *
 * @author H.ys
 * @Date $ $
 * @Copyright (c) 2019 tusdk.com. All rights reserved.
 */
class MyLinkedList {

    private Node mHeadNode;
    private Node mTailNode;
    private int size = 0;

    /**
     * Initialize your data structure here.
     */
    public MyLinkedList() {
        size = 0;
    }

    /**
     * Get the value of the index-th node in the linked list. If the index is invalid, return -1.
     */
    public int get(int index) {
        if (index >= size || index < 0) return -1;
        if (index == 0) return mHeadNode.value;
        if (index == size - 1) return mTailNode.value;
        return getNodeWithIndex(index) == null ? -1 : getNodeWithIndex(index).value;
    }

    /**
     * Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list.
     */
    public void addAtHead(int val) {
        Node node = new Node(null,null,val);
        node.value = val;
        if (mHeadNode == null && mTailNode == null){
            mHeadNode = node;
            mTailNode = node;
        } else {
            node.next = mHeadNode;
            mHeadNode.previous = node;
            mHeadNode = node;
        }
        size++;
    }

    /**
     * Append a node of value val to the last element of the linked list.
     */
    public void addAtTail(int val) {
        Node node = new Node(null,null,val);
        if (mHeadNode == null && mTailNode == null){
            mHeadNode = node;
            mTailNode = node;
        } else {
            mTailNode.next = node;
            node.previous = mTailNode;
            mTailNode = node;
        }
        size++;
    }

    /**
     * Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted.
     */
    public void addAtIndex(int index, int val) {
        if (index == 0){
            addAtHead(val);
        } else if (index == size){
            addAtTail(val);
        }else {
            Node indexNode = getNodeWithIndex(index);
            if (indexNode == null) {
                return;
            }
            Node newNode = new Node(indexNode,indexNode.next,val);
            if (indexNode.previous != null){
                indexNode.previous.next = newNode;
            }
            newNode.previous = indexNode.previous;
            newNode.next  = indexNode;
            indexNode.previous = newNode;
            size++;
        }

    }

    /**
     * Delete the index-th node in the linked list, if the index is valid.
     */
    public void deleteAtIndex(int index) {
        if (index == 0 && size == 1){
            mTailNode = null;
            mHeadNode = null;
        } else if (index == 0){
            mHeadNode = mHeadNode.next;
            mHeadNode.previous = null;
        } else if (index == size -1){
            mTailNode = mTailNode.previous;
            mTailNode.next = null;
        }else{
            Node targetNode = getNodeWithIndex(index);
            if (targetNode == null) return;
            if (targetNode.previous != null){
                targetNode.previous.next = targetNode.next;
            }
            if (targetNode.next != null){
                targetNode.next.previous = targetNode.previous;
            }
            targetNode = null;
        }
        size--;
    }

    private Node getNodeWithIndex(int index){
        if (mHeadNode == null || mTailNode == null) return null;
        if (index == 0) return mHeadNode;
        if (index == size - 1) return mTailNode;
        int middle = (size / 2) + (size % 2);
        if (index <= middle){
            int currentPos = 0;
            Node node = mHeadNode;
            while (node.hasNext()){
                node = node.next;
                currentPos ++;
                if (currentPos == index) return node;
            }
        } else {
            int currentPos = size - 1;
            Node node = mTailNode;
            while (node.hasPrevious()){
                node = node.previous;
                currentPos --;
                if (currentPos == index) return node;
            }
        }

        return null;
    }

    private class Node {
        int value;
        Node next;
        Node previous;

        Node(Node previous,Node next,int value) {
            this.next = next;
            this.previous = previous;
            this.value = value;
        }

        public boolean hasNext(){
            return next != null;
        }

        public boolean hasPrevious(){
            return previous != null;
        }

        public int getValue() {
            return value;
        }
    }
}

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */