package org.lasque.lib;

import java.util.ArrayList;
import java.util.List;

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
    }

    static public void main(String[] args) {
        List<List<Integer>> lists = levelOrder(new TreeNode(1,new TreeNode(2,new TreeNode(4),null),new TreeNode(3,null,new TreeNode(5))));
        System.out.println(lists.toString());
    }

    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> lists = new ArrayList<>();

    }

    public static void level(TreeNode node , List<List<Integer>> lists,List<Integer> list){

    }


    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) return list;
        postorder(root,list);
        return list;
    }

    public void postorder(TreeNode node,List<Integer> list){
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

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) return list;
        preorder(root,list);
        return list;
    }

    public void preorder(TreeNode node,List<Integer> list){
        list.add(node.val);
        if (node.left != null)preorder(node.left,list);
        if (node.right != null)preorder(node.right,list);
    }
}
