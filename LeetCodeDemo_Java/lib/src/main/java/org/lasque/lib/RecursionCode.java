package org.lasque.lib;

import org.lasque.lib.BinaryTreeCode.TreeNode;
import org.lasque.lib.LinkListCode.ListNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


/**
 * TuSDK
 * $
 *
 * @author H.ys
 * @Date $ $
 * @Copyright (c) 2019 tusdk.com. All rights reserved.
 */
public class RecursionCode {


    static public void main(String[] args) {
        RecursionCode ex = new RecursionCode();
        List<TreeNode> heads = ex.generateTrees(3);
        for (TreeNode node : heads){
            System.out.println(node);
            System.out.println();
        }

    }

    public List<TreeNode> generateTrees(int n) {
        List<TreeNode> ans = new ArrayList<>();
        if(n == 0){
            return ans;
        }
        return buildBST(1,n);
    }

    public List<TreeNode> buildBST(int start,int end){
        List<TreeNode> ans = new ArrayList<>();

        if (start > end){
            ans.add(null);
            return ans;
        }

        if (start == end){
            TreeNode tree = new TreeNode(start);
            ans.add(tree);
            return ans;
        }

        for (int i = start;i<= end;i++){
            List<TreeNode> leftTrees = buildBST(start,i -1);
            List<TreeNode> rightTrees = buildBST(i + 1,end);

            for (TreeNode left : leftTrees){
                for (TreeNode right : rightTrees){
                    TreeNode root = new TreeNode(i);
                    root.left = left;
                    root.right = right;
                    ans.add(root);
                }
            }
        }
        return ans;
    }




    public int kthGrammar(int N, int K) {
        K--;
        int count = 0;
        while (K != 0) {
            K = K & (K - 1);
            count++;
        }
        return count % 2;
    }

    public double myPow(double x, int n) {
        return Math.pow(x, n);
    }

    public int climbStairs(int n) {
        if (n - 2 == 0) {
            return 2;
        }
        if (n - 1 == 0) {
            return 1;
        }

        Integer c = fibM.get(n);
        if (c == null) {
            c = climbStairs(n - 1) + climbStairs(n - 2);
            fibM.put(n, c);
        }
        return c;
    }

    private static Map<Integer, Integer> fibM = new HashMap<>();

    public int fib(int N) {
        if (N < 2) return N;
        Integer fib = fibM.get(N);
        if (fib != null) {
            return fib;
        } else {
            int sum = fib(N - 1) + fib(N - 2);
            fibM.put(N, sum);
            return sum;
        }
    }


    public List<Integer> getRow(int rowIndex) {
        int[] row = new int[rowIndex + 1];
        row[0] = 1;
        for (int i = 1; i < rowIndex; i++) {
            int prev = row[0];
            for (int j = 1; j <= i; j++) {
                int temp = row[j];
                row[j] += prev;
                prev = temp;
            }
        }
        List<Integer> res = new ArrayList<>();
        for (int num : row) res.add(num);
        return res;
    }

/*    public List<Integer> getRow(int rowIndex) {
        List<List<Integer>> pascal = new ArrayList<>();
        pascal.add(Arrays.asList(1));
        pascal.add(Arrays.asList(1,1));
        for (int i = 2;i<=rowIndex + 1;i++){
            List<Integer> list =  pascal.get(i - 1);
            List<Integer> current = new ArrayList<>();
            current.add(1);
            for (int j = 1;j<list.size();j++){
                current.add(list.get(j -1) + list.get(j));
            }
            current.add(1);
            pascal.add(current);
        }
        return pascal.get(rowIndex);
    }*/


    public void reverseString(char[] s) {
        reverse(0, s.length - 1, s);
    }

    public static void reverse(int startIndex, int endIndex, char[] s) {
        if (endIndex <= startIndex) return;
        char c = s[startIndex];
        s[startIndex] = s[endIndex];
        s[endIndex] = c;
        reverse(startIndex + 1, endIndex - 1, s);
    }

    public ListNode swapPairs(ListNode head) {
        if (head == null) return null;
        if (head.next != null) {
            ListNode next = head.next;
            head.next = swapPairs(next.next);
            next.next = head;
            return next;
        }
        return head;
    }

    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) return null;
        if (root.val == val) return root;
        else {
            TreeNode node = searchBST(root.left, val);
            if (node != null) {
                return node;
            }
            node = searchBST(root.right, val);
            if (node != null) {
                return node;
            }
        }
        return null;
    }

}
