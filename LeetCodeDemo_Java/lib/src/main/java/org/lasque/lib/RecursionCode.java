package org.lasque.lib;

import org.lasque.lib.BinaryTreeCode.TreeNode;
import org.lasque.lib.LinkListCode.ListNode;

import java.awt.Point;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * TuSDK
 * $
 *
 * @author H.ys
 * @Date $ $
 * @Copyright (c) 2019 tusdk.com. All rights reserved.
 */
public class RecursionCode {

    private static char[][] test = {
            {'5','3','.','.','7','.','.','.','.'},
            {'6','.','.','1','9','5','.','.','.'},
            {'.','9','8','.','.','.','.','6','.'},
            {'8','.','.','.','6','.','.','.','3'},
            {'4','.','.','8','.','3','.','.','1'},
            {'7','.','.','.','2','.','.','.','6'},
            {'.','6','.','.','.','.','2','8','.'},
            {'.','.','.','4','1','9','.','.','5'},
            {'.','.','.','.','8','.','.','7','9'}
    };


    static public void main(String[] args) {
        RecursionCode ex = new RecursionCode();
//        ex.solveSudoku(test);
//        ex.combine(4,2);
//        ex.generateParenthesis(6);
        System.out.println(ex.permute(new int[]{2,1,4,5,6}));
    }

    private HashMap<Integer, char[]> phoneNumberMap = new HashMap<Integer, char[]>();


    public List<String> letterCombinations(String digits) {
        phoneNumberMap.put(1,new char[]{});
        phoneNumberMap.put(2,new char[]{'a','b','c'});
        phoneNumberMap.put(3,new char[]{'d','e','f'});
        phoneNumberMap.put(4,new char[]{'g','h','i'});
        phoneNumberMap.put(5,new char[]{'j','k','l'});
        phoneNumberMap.put(6,new char[]{'m','n','o'});
        phoneNumberMap.put(7,new char[]{'p','q','r','s'});
        phoneNumberMap.put(8,new char[]{'t','u','v'});
        phoneNumberMap.put(9,new char[]{'w','x','y','z'});
        List<char[]> charList = new ArrayList<>();
        int size = digits.length();
        for (int i  = 0;i<size;i++){
            charList.add(phoneNumberMap.get(Integer.valueOf(digits.charAt(i))));
        }
        letter(charList);
    }

    private void letter(List<char[]> list){
        
    }



    public List<List<Integer>> permute(int[] nums) {
//        permuteAddItem(nums);
//        return allPermuteList;

        List<List<Integer>> result = new ArrayList<>();
        if (nums.length == 0) {
            result.add(new ArrayList<Integer>());
        }
        else {
            permutation(nums, 0, nums.length - 1, result);
        }
        return result;

    }

    public void permutation(int[] nums,int start,int end,List<List<Integer>> result){
        if (start == end){
            List<Integer> temp = new ArrayList<Integer>();
            for (int i : nums){
                temp.add(i);
            }
            result.add(temp);
        }
        for (int i = start; i <= end;i++){
            swap(nums,start,i);
            permutation(nums,start + 1,end,result);
            swap(nums,start,i);
        }
    }

    public void swap(int[] nums,int x,int y){
        int temp = nums[x];
        nums[x] = nums[y];
        nums[y] = temp;
    }


    private List<Integer> currentPermuteList = new ArrayList<>();
    private List<List<Integer>> allPermuteList = new ArrayList<>();
    HashSet<Integer> useless = new HashSet<>();

    private void permuteAddItem(int[] nums){
        if (useless.size() == nums.length){
            allPermuteList.add(new ArrayList<Integer>(currentPermuteList));
        }
        int size = nums.length;
        for (int i = 0;i<size;i++){
            if (!useless.contains(i)){
                currentPermuteList.add(nums[i]);
                useless.add(i);
                permuteAddItem(nums);
                currentPermuteList.remove(currentPermuteList.size() - 1);
                useless.remove(i);
            }
        }
    }

    private int binary(int[] heights,int left,int right){
        if (left > right) return 0;
        if (left == right) return heights[left];

        boolean increase = true,decrease = true;
        int minIndex = left;
        for (int i= left + 1;i<= right;i++){
            if (heights[i] < heights[i-1]) increase = false;
            if (heights[i] > heights[i-1]) decrease = false;
            if (heights[i] < heights[minIndex]){
                minIndex = i;
            }
        }

        int res = 0;
        if (decrease){
            for (int i = right;i>=left;i--){
                res = Math.max(res,heights[i] * (i - left + 1));
            }
        } else if (increase){
            for (int i = left;i<=right;i++){
                res = Math.max(res,heights[i] * (right - i + 1));
            }
        } else {
            int l = binary(heights,left,minIndex - 1);
            int r = binary(heights,minIndex + 1,right);
            res = Math.max(Math.max(l,r),heights[minIndex] * (right - left + 1));
        }
        return res;
    }


    public int largestRectangleArea(int[] heights) {
        int size = heights.length;
        int maxSize = -1;
        int width = 0;
        for (int i = 0;i<size;i++){
            for (int j = 0;j<size;j++){
                if (heights[j]< heights[i]){
                    if ((size - width) * heights[i] < maxSize || j > i){
                        break;
                    }
                    if (j < i){
                        width = 0;
                    }
                } else {
                    width ++;
                }
            }
            maxSize = Math.max(maxSize,heights[i] * width);
            width = 0;
        }
        return maxSize;
    }

    private String mCurrent = "";
    private List<String> parenthesisList = new ArrayList<>();

    public List<String> generateParenthesis(int n) {
        int leftCount = n,rightCount = n,complete = 0;
        mCurrent += "(";
        helper(leftCount -1,rightCount);
        return parenthesisList;
    }

    private void helper(int leftCount,int rightCount){
        if (rightCount == 0){
            parenthesisList.add(mCurrent);
            return;
        }
        if (leftCount == rightCount){
            mCurrent += "(";
            helper(leftCount - 1,rightCount);
        } else if (leftCount < rightCount){
            if (leftCount != 0){
                mCurrent +="(";
                helper(leftCount - 1,rightCount);
                mCurrent = mCurrent.substring(0,mCurrent.lastIndexOf("("));
            }
            if (rightCount != 0){
                mCurrent +=")";
                helper(leftCount,rightCount - 1);
                mCurrent = mCurrent.substring(0,mCurrent.lastIndexOf(")"));
            }
        }
    }

    public boolean check(TreeNode p ,TreeNode q){
        if (p == null && q == null) return true;
        if (q == null || p == null) return false;
        if (p.val != q.val) return false;
        return true;
    }

    public boolean isSameTree(TreeNode p, TreeNode q){
        if (q == null && p == null) return true;
        if (!check(q,p)) return false;
        ArrayDeque<TreeNode> deqP = new ArrayDeque<>();
        ArrayDeque<TreeNode> deqQ = new ArrayDeque<>();
        deqP.addLast(p);
        deqQ.addLast(q);

        while (!deqP.isEmpty()){
            p = deqP.removeFirst();
            q = deqQ.removeFirst();

            if (!check(p,q)) return false;
            if (p != null){
                if (!check(p.left,q.left)) return false;
                if (p.left != null){
                    deqP.add(p.left);
                    deqQ.add(q.left);
                }

                if (!check(p.right,q.right)) return false;
                if (p.right != null){
                    deqP.addLast(p.right);
                    deqQ.addLast(q.right);
                }
            }

        }
        return true;
    }

    public List<List<Integer>> combineList = new ArrayList<>();
    public List<Integer> currentList = new ArrayList<>();

    public List<List<Integer>> combine(int n, int k) {
//        for (int i =1;i<n;i++){
//            for (int j = i + 1;j<=n;j++){
//                List<Integer> list = new ArrayList<>();
//                list.add(i);
//                list.add(j);
//                combineList.add(list);
//            }
//        }
        addItem(1,n,k);
        return combineList;
    }

    public void addItem(int index,int n,int k){
        if (k == 0){
            combineList.add(new ArrayList<>(currentList));
            return;
        }
        for (int i = index;i<= n - k + 1;i++){
            currentList.add(i);
            addItem(i + 1,n,k -1);
            currentList.remove(currentList.size() - 1);
        }
    }

    public void solveSudoku(char[][] board) {
        solver(board);
    }

    private boolean solver(char[][] board){
        for (int i =0;i<9;i++){
            for (int j = 0;j<9;j++){
                if (board[i][j] == '.'){
                    char count = '1';
                    while (count <= '9'){
                        if (isValid(i,j,board,count)){
                            board[i][j] = count;
                            if (solver(board)){
                                return true;
                            } else {
                                //下一个位置没有数字,就还原,然后当前位置尝试新的数
                                board[i][j] = '.';
                            }
                        }
                        count ++;
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isValid(int row,int col,char[][] board,char c){
        for (int i =0;i<9;i++){
            if (board[row][i] == c) return false;
        }

        for (int i=0;i<9;i++){
            if (board[i][col] == c) return false;
        }

        int start_row = row / 3 * 3;
        int start_col = col / 3 * 3;

        for (int i =0;i<3;i++){
            for (int j =0;j<3;j++){
                if (board[start_row + i][start_col + j] == c) return false;
            }
        }
        return true;
    }

    public void robotCleaning(){

    }

    public int n_queen(int n) {
//        return totalNQueenHelper(0, 0, n);


        boolean[] col = new boolean[n];
        boolean[] diag1 = new boolean[2 * n];
        boolean[] diag2 = new boolean[2 * n];
        numTotalNQueens(0,col,diag1,diag2,n);
        return count;
    }

    int count = 0;

    private void numTotalNQueens(int row, boolean[] col, boolean[] diag1, boolean[] diag2, int n) {
        if (row == n)
            count++;
        for (int i = 0; i < n; i++) {
            if (col[i] || diag1[row - i + n] || diag2[row + i])
                continue;
            col[i] = true;
            diag1[row - i + n] = true;
            diag2[row + i] = true;
            numTotalNQueens(row + 1, col, diag1, diag2, n);
            col[i] = false;
            diag1[row - i + n] = false;
            diag2[row + i] = false;
        }
    }


    private final Set<Integer> occupiedCols = new HashSet<>();
    private final Set<Integer> occupiedDiag1s = new HashSet<>();
    private final Set<Integer> occupiedDiag2s = new HashSet<>();

    private int totalNQueenHelper(int row, int count, int n) {
        for (int col = 0; col < n; col++) {
            if (occupiedCols.contains(col)) continue;
            int diag1 = row - col;
            if (occupiedDiag1s.contains(diag1)) continue;
            int diag2 = row + col;
            if (occupiedDiag2s.contains(diag2)) continue;

            if (row == n - 1)
                count++;
            else {
                occupiedCols.add(col);
                occupiedDiag1s.add(diag1);
                occupiedDiag2s.add(diag2);
                count = totalNQueenHelper(row + 1, count, n);

                occupiedCols.remove(col);
                occupiedDiag1s.remove(diag1);
                occupiedDiag2s.remove(diag2);
            }
        }
        return count;
    }


    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length < 1 || matrix[0].length < 1) {
            return false;
        }
        int col = matrix[0].length - 1;
        int row = 0;
        while (col >= 0 && row <= matrix.length - 1) {
            if (target == matrix[row][col]) {
                return true;
            } else if (target < matrix[row][col]) {
                col--;
            } else {
                row++;
            }
        }
        return false;
    }


    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        return helper(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean helper(TreeNode root, long min, long max) {
        if (root == null) return true;
        if (root.val <= min || root.val >= max) {
            return false;
        }
        if (root.val <= min || root.val >= max) {
            return false;
        }
        return helper(root.left, min, root.val) && helper(root.right, root.val, max);
    }


    private HashMap<TreeNode, Integer> maxMap = new HashMap<>();
    private HashMap<TreeNode, Integer> minMap = new HashMap<>();

    public boolean checkBTS(TreeNode root) {
        if (root == null || (root.right == null && root.left == null)) return true;
        if (root.right != null)
            if (getChildMinValue(root.right, root.right.val) <= root.val) {
                return false;
            }
        if (root.left != null)
            if (getChildMaxValue(root.left, root.left.val) >= root.val) {
                return false;
            }
        return checkBTS(root.left) && checkBTS(root.right);
    }

    public int getChildMaxValue(TreeNode root, int max) {
        if (maxMap.get(root) != null) {
            return maxMap.get(root);
        }
        if (root.left == null && root.right == null) {
            return Math.max(max, root.val);
        }
        int left = root.val;
        if (root.left != null) {
            left = getChildMaxValue(root.left, left);
        }
        int right = root.val;
        if (root.right != null) {
            right = getChildMaxValue(root.right, right);
        }
        int childMax = Math.max(Math.max(left, right), root.val);
        maxMap.put(root, childMax);
        return childMax;
    }

    public int getChildMinValue(TreeNode root, int min) {
        if (minMap.get(root) != null) {
            return minMap.get(root);
        }
        if (root.left == null && root.right == null) {
            return Math.min(min, root.val);
        }
        int left = root.val;
        if (root.left != null) {
            left = getChildMinValue(root.left, left);
        }
        int right = root.val;
        if (root.right != null) {
            right = getChildMinValue(root.right, right);
        }
        int childMin = Math.min(Math.min(left, right), root.val);
        minMap.put(root, childMin);
        return childMin;
    }

    public int[] sortArray(int[] input) {
        return countSort(input);
    }

    private void quickSort(int[] nums, int l, int r) {
        if (l < r) {
            int pivot = partition(nums, l, r);
            quickSort(nums, l, pivot - 1);
            quickSort(nums, pivot + 1, r);
        }
    }

    /**
     * 选择一个中枢值 分别分类两边的值
     *
     * @param nums
     * @param l
     * @param r
     * @return
     */
    private int partition(int[] nums, int l, int r) {
        int pivot = nums[r];
        int i = l - 1;
        for (int j = l; j < r; j++) {
            if (nums[j] < pivot) {
                i++;
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }
        }
        int temp = nums[i + 1];
        nums[i + 1] = nums[r];
        nums[r] = temp;
        return i + 1;
    }

    private int[] countSort(int[] input) {
        // O(n) time and O(max(max-min, n)) space
        int min = input[0], max = input[0];
        for (int num : input) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }

        int n = max - min + 1;
        int[] count = new int[n];
        for (int num : input) {
            int idx = num - min;
            count[idx]++;
        }

        int i = 0;
        int[] result = new int[input.length];
        for (int k = 0; k < n; k++) {
            int c = count[k];
            if (c != 0) {
                int num = k + min;
                for (int p = 0; p < c; p++) {
                    result[i++] = num;
                }
            }
        }
        return result;
    }

    public int[] merge_sort_bottom_up(int[] input) {
        if (input.length <= 1) {
            return input;
        }
        List<int[]> range_list = new ArrayList<>();
        int size = input.length;
        for (int i = 0; i < size; i++) {
            range_list.add(new int[]{input[i]});
        }
        while (true) {
            range_list = conquer(range_list);
            if (range_list.size() == 1) return range_list.get(0);
        }
    }

    public List<int[]> conquer(List<int[]> range) {
        List<int[]> range_list = new ArrayList<>();
        for (int i = 0; i < range.size(); i++) {
            if (i + 1 < range.size()) {
                range_list.add(merge(range.get(i), range.get(++i)));
            } else {
                range_list.add(range.get(i));
            }
        }
        return range_list;
    }

    public int[] merge_sort(int[] input) {
        if (input.length <= 1) {
            return input;
        }
        int pivot = input.length / 2;
        int[] left_list = merge_sort(Arrays.copyOfRange(input, 0, pivot));
        int[] right_list = merge_sort(Arrays.copyOfRange(input, pivot, input.length));
        return merge(left_list, right_list);
    }

    public int[] merge(int[] left_list, int[] right_list) {
        int[] ret = new int[left_list.length + right_list.length];
        int left_cursor = 0, right_cursor = 0, ret_cursor = 0;
        while (left_cursor < left_list.length && right_cursor < right_list.length) {
            if (left_list[left_cursor] < right_list[right_cursor]) {
                ret[ret_cursor++] = left_list[left_cursor++];
            } else {
                ret[ret_cursor++] = right_list[right_cursor++];
            }
        }

        while (left_cursor < left_list.length) {
            ret[ret_cursor++] = left_list[left_cursor++];
        }

        while (right_cursor < right_list.length) {
            ret[ret_cursor++] = right_list[right_cursor++];
        }
        return ret;
    }

    List<TreeNode>[][] dp;

    public List<TreeNode> generateTreesWithDp(int n) {
        if (n == 0) {
            return new ArrayList<>();
        }
        dp = new List[n][n];
        return getCombinations(1, n);
    }

    public List<TreeNode> getCombinations(int start, int end) {
        List<TreeNode> nodes = new ArrayList<>();
        if (start > end) {
            nodes.add(null);
        } else {
            if (dp[start - 1][end - 1] != null) {
                return dp[start - 1][end - 1];
            }

            if (start == end) {
                nodes.add(new TreeNode(start));
            } else if (start < end) {
                for (int i = start; i <= end; i++) {
                    List<TreeNode> left = getCombinations(start, i - 1);
                    List<TreeNode> right = getCombinations(i + 1, end);

                    for (TreeNode leftNode : left) {
                        for (TreeNode rightNode : right) {
                            TreeNode root = new TreeNode(i);
                            root.left = leftNode;
                            root.right = rightNode;
                            nodes.add(root);
                        }
                    }
                }
            }
            dp[start - 1][end - 1] = nodes;
        }
        return nodes;
    }


    private TreeNode clone(TreeNode n, int offset) {
        if (n == null) {
            return null;
        }
        TreeNode node = new TreeNode(n.val + offset);
        node.left = clone(n.left, offset);
        node.right = clone(n.right, offset);
        return node;
    }


    public List<TreeNode> generateTreesWithDPAndClone(int n) {
        ArrayList<TreeNode>[] dp = new ArrayList[n + 1];
        dp[0] = new ArrayList<TreeNode>();
        if (n == 0) {
            return dp[0];
        }
        dp[0].add(null);
        for (int len = 1; len <= n; len++) {
            dp[len] = new ArrayList<>();
            for (int root = 1; root <= len; root++) {
                int left = root - 1;
                int right = len - root;
                for (TreeNode leftTree : dp[left]) {
                    for (TreeNode rightTree : dp[right]) {
                        TreeNode treeRoot = new TreeNode(root);
                        treeRoot.left = leftTree;
                        treeRoot.right = clone(rightTree, root);
                        dp[len].add(treeRoot);
                    }
                }
            }
        }
        return dp[n];
    }

    public List<TreeNode> generateTrees(int n) {
        List<TreeNode> ans = new ArrayList<>();
        if (n == 0) {
            return ans;
        }
        return buildBST(1, n);
    }

    public List<TreeNode> buildBST(int start, int end) {
        List<TreeNode> ans = new ArrayList<>();

        if (start > end) {
            ans.add(null);
            return ans;
        }

        if (start == end) {
            TreeNode tree = new TreeNode(start);
            ans.add(tree);
            return ans;
        }

        for (int i = start; i <= end; i++) {
            List<TreeNode> leftTrees = buildBST(start, i - 1);
            List<TreeNode> rightTrees = buildBST(i + 1, end);

            for (TreeNode left : leftTrees) {
                for (TreeNode right : rightTrees) {
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
