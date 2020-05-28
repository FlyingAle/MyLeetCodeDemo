package org.lasque.lib;


import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import javax.swing.tree.TreeNode;

/**
 * TuSDK
 * $
 *
 * @author H.ys
 * @Date $ $
 * @Copyright (c) 2019 tusdk.com. All rights reserved.
 */
public class BinaryTreeCode {

    static class TreeNode{
        int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(){}
        public TreeNode(int val){
            this.val = val;
        }

        public TreeNode(int val,TreeNode left,TreeNode right){
            this.val = val;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return "TreeNode{\n" +
                    "val=" + val +
                    ", left=" + left +
                    ", right=" + right +'\n' +
                    '}';
        }
    }

    static class Node{
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node(){}
        public Node(int val){
            this.val = val;
        }

        public Node(int val,Node left,Node right,Node next){
            this.val = val;
            this.left = left;
            this.right = right;
            this.next = next;
        }

        @Override
        public String toString() {
            return "Node{ \n" +
                    "val=" + val +
                    ", left=" + left +
                    ", right=" + right +
                    ", next=" + next + '\n' +
                    '}';
        }
    }

    static public void main(String[] args) {
        TreeNode root = new TreeNode(3,
                new TreeNode(5,
                        new TreeNode(6),
                        new TreeNode(2,
                                new TreeNode(7),
                                new TreeNode(4))),
                new TreeNode(1,
                        new TreeNode(0),
                        new TreeNode(8)));

        String s = serialize(root);
        System.out.println(s);
        TreeNode deserialize = deserialize(s);
        System.out.println(deserialize);

    }

    // Encodes a tree to a single string.
    public static String serialize(TreeNode root) {
        if (root == null) return "null";
        String s = serializeHelperWithPre(root);
        s += "&&";
        s+= serializeHelperWithIn(root);
        return s;
    }

    public static String serializeHelperWithPre(TreeNode root){
        if (root == null){
            return "";
        } else {
            String s = root.val +"," + serializeHelperWithPre(root.left);
            s+= serializeHelperWithPre(root.right);
            return s ;
        }
    }

    public static String serializeHelperWithIn(TreeNode root){
        if (root == null){
            return "";
        } else {
            String s = serializeHelperWithIn(root.left);
            if (s == "") s+=root.val;
            else s = s + "," + root.val + ",";
            s+= serializeHelperWithIn(root.right);
            return s;
        }
    }

    // Decodes your encoded data to tree.
    public static TreeNode deserialize(String data) {
        if (data.equals("null")) return null;
        String[] nodes = data.split("&&");
        String[] preList = nodes[0].trim().split(",");
        int[] intPreList = stringToInt(preList);
        String[] inList = nodes[1].trim().split(",");
        int[] intInList = stringToInt(inList);
        TreeNode root = buildTreeWithPreAndIn(intPreList,intInList);
        return root;
    }

    public static int[] stringToInt(String[] s){
        int[] ints = new int[s.length];
        for (int i = 0;i<s.length;i++){
            ints[i] = Integer.parseInt(s[i]);
        }
        return ints;
    }

    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null)return null;

        if (root.val == p.val || root.val == q.val) return root;

        TreeNode left = lowestCommonAncestor(root.left,p,q);
        TreeNode right = lowestCommonAncestor(root.right,p,q);

        if (left != null && right != null){
            return root;
        }
        if (left == null){
            return right;
        } else {
            return left;
        }
    }

    public static boolean findNode(TreeNode root,TreeNode target,List<TreeNode> path){
        if (root.left != null || root.right != null) path.add(root);
        if (root.val == target.val) return true;
        boolean leftChildHas = false,rightChildHas = false;
        if (root.left != null){
            leftChildHas = findNode(root.left,target,path);
        }
        if (!leftChildHas && root.right != null){
            rightChildHas = findNode(root.right,target,path);
        }
        if (!leftChildHas && !rightChildHas){
            path.remove(root);
            return false;
        }
        return true;
    }


    public static Node connect(Node root) {
        if (root == null) return null;
        createConnect(root);
        return root;
    }

    public static void createConnect(Node root){
        if (root == null){
            return;
        }
        if (root.left != null){
            root.left.next = getNext(root,root.left);
        }
        if (root.right != null){
            root.right.next = getNext(root,root.right);
        }
        createConnect(root.right);
        createConnect(root.left);
    }

    public static Node getNext(Node root,Node child){
        if (child == root.left){
            if (root.right != null){
                return root.right;
            }
        }

        root = root.next;
        while (root != null){
            if (root.left != null){
                return root.left;
            }
            if (root.right != null){
                return root.right;
            }
            root = root.next;
        }
        return null;
    }

    public void connect(Node left,Node right){
        if (left == null) return;
        if (right != null){
            left.next = right;
            connect(left.right,right.left);
            connect(right.left,right.right);
        }
        connect(left.left,left.right);
    }



    public static TreeNode buildTreeWithPreAndIn(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0 || inorder == null || inorder.length == 0 || preorder.length != inorder.length){
            return null;
        }
        int len = inorder.length;
        return buildTreeWithPreAndIn(preorder,inorder,0,len - 1,0,len - 1);
    }

    public static TreeNode buildTreeWithPreAndIn(int[] preorder,int[] inorder,int inStart,int inEnd,int preStart,int maxIndex){
        if (preStart > maxIndex || inStart > inEnd){
            return null;
        }
        TreeNode root = new TreeNode(preorder[preStart]);
        int rootIndex = inEnd;
        while (rootIndex >= 0){
            if (inorder[rootIndex] == preorder[preStart]){
                break;
            }
            rootIndex --;
        }
        root.left = buildTreeWithPreAndIn(preorder,inorder,inStart,rootIndex - 1,preStart + 1,maxIndex);
        root.right = buildTreeWithPreAndIn(preorder,inorder,rootIndex + 1,inEnd,preStart + 1 + (rootIndex - inStart),maxIndex);
        return root;
    }

    public static TreeNode buildTree(int[] inorder,int[] postorder){
        if(inorder==null || inorder.length==0 || postorder==null || postorder.length==0 || inorder.length!=postorder.length){
            return null;
        }
        int len=inorder.length;
        return buildTree(inorder, postorder, len-1, 0, len-1);
    }

    public static TreeNode buildTree(int[] inorder,int[] postorder,int inStart,int inEnd,int poStart){
        if (poStart < 0 || inStart < inEnd){
            return null;
        }

        TreeNode root = new TreeNode(postorder[poStart]);
        int rootIndex = inStart;
        while (rootIndex >=0){
            if (inorder[rootIndex] == postorder[poStart]){
                break;
            }
            rootIndex --;
        }
        root.right = buildTree(inorder,postorder,inStart,rootIndex + 1,poStart - 1);
        root.left = buildTree(inorder,postorder,rootIndex - 1,inEnd,poStart - 1 - (inStart - rootIndex));
        return root;
    }

//    public TreeNode buildTree(int[] inorder, int[] postorder) {
//        if (inorder.length == 0 || postorder.length == 0) return null;
//        //首先指向中序遍历数组与后序遍历数组的最终节点
//        int ip = inorder.length - 1;
//        int pp = postorder.length - 1;
//
//        Stack<TreeNode> stack = new Stack<>();
//        TreeNode prev = null;
//        //后续遍历数组的最终节点,一定是根节点
//        TreeNode root = new TreeNode(postorder[pp]);
//        stack.push(root);
//        pp --;
//
//        while (pp >=0){
//            while (!stack.isEmpty() && stack.peek().val == inorder[ip]){
//                prev = stack.pop();
//                ip --;
//            }
//            TreeNode newNode = new TreeNode(postorder[pp]);
//            if (prev != null){
//                prev.left = newNode;
//            } else if (!stack.isEmpty()){
//                TreeNode currTop = stack.peek();
//                currTop.right = newNode;
//            }
//            stack.push(newNode);
//            prev = null;
//            pp --;
//        }
//        return root;
//    }

    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;
        if (root.left == null && root.right == null){
            if (sum == root.val) return true;
            else return false;
        }
        return hasPathSum(root.left,sum - root.val) || hasPathSum(root.right,sum - root.val);
    }

    public static boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return validate(root.left,root.right);
    }

    public static boolean validate(TreeNode left,TreeNode right){
        if (left == null && right == null) return true;
        if (left == null || right == null) return false;

        if (left.val != right.val) return false;

        return validate(left.left,right.right) && validate(left.right,right.left);
    }

    public static void leftToRight(TreeNode node, Queue<Integer> list){
        if (node == null){
            list.add(Integer.MIN_VALUE);
        } else {
            list.add(node.val);
            leftToRight(node.left,list);
            leftToRight(node.right,list);
        }
    }

    public static void rightToLeft(TreeNode node,Queue<Integer> list){
        if (node == null){
            list.add(Integer.MIN_VALUE);
        } else {
            list.add(node.val);
            rightToLeft(node.right,list);
            rightToLeft(node.left,list);
        }
    }

    public int maxDepth(TreeNode root) {
        return depth(root,0);
    }

    public int depth(TreeNode node,int depth){
        if (node == null){
            return depth;
        }
        if (node.left == null && node.right == null){
            return depth + 1;
        } else {
            return Math.max(depth(node.left,depth + 1),depth(node.right,depth + 1));
        }
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> lists = new ArrayList<>();
        if (root == null) return lists;
        List<Integer> list = new ArrayList<>();
        List<TreeNode> nodes = new ArrayList<>();
        nodes.add(root);
        level(nodes,lists,list);
        return lists;
    }

    public void level(List<TreeNode> nodes , List<List<Integer>> lists,List<Integer> list){
        List<TreeNode> nextNodes = new ArrayList<>();
        List<Integer> nextList = new ArrayList<>();
        for (TreeNode node : nodes){
            list.add(node.val);
            if (node.left != null) nextNodes.add(node.left);
            if (node.right != null) nextNodes.add(node.right);
        }
        lists.add(list);
        if (!nextNodes.isEmpty()){
            level(nextNodes,lists,nextList);
        }
    }


    public static List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) return list;
        postorder(root,list);
        return list;
    }

    public static void postorder(TreeNode node,List<Integer> list){
        if (node.left != null) postorder(node.left,list);
        if (node.right != null) postorder(node.right,list);
        list.add(node.val);
    }


    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) return list;
        inorder(root,list);
        return list;
    }

    public void inorder(TreeNode node,List<Integer> list){
        if (node.left != null) inorder(node.left,list);
        list.add(node.val);
        if(node.right != null) inorder(node.right,list);
    }

    public static List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) return list;
        preorder(root,list);
        return list;
    }

    public static void preorder(TreeNode node,List<Integer> list){
        list.add(node.val);
        if (node.left != null)preorder(node.left,list);
        if (node.right != null)preorder(node.right,list);
    }
}
