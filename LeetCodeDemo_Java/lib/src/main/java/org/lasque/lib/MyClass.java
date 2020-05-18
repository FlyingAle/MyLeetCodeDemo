package org.lasque.lib;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

/**
 *
 */
public class MyClass {

    static public void main(String[] args) {
        long start = System.nanoTime();
//        int count = coinChangeWithDynamic(new int[]{1,2,5,10},234);//78200
//        int count = findMaxConsecutiveOnes(new int[]{1, 0, 1, 1, 0, 1});//78200
//        validMountainArray(new int[]{14,82,89,84,79,70,70,68,67,66,63,60,58,54,44,43,32,28,26,25,22,15,13,12,10,8,7,5,4,3});//78200
        thirdMax(new int[]{1,2,2,5,3,5});
//        int count = coinChange(new int[]{1,2,5,10},234); //167200
//        System.out.println(count);
    }
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> result = new ArrayList<>();
        int[] temp = new int[nums.length];
        for (int i : nums){
            temp[i - 1] = i;
        }
        for (int i =0;i<temp.length;i++){
            if (temp[i] == 0) result.add(i + 1);
        }
        return result;
    }

    public static int thirdMax(int[] nums) {
        long firstMax = Long.MIN_VALUE,secondMax = Long.MIN_VALUE,thirdMax = Long.MIN_VALUE;

        for (int x : nums){
            if (x > firstMax){
                thirdMax = secondMax;
                secondMax = firstMax;
                firstMax = x;
            } else if (x < firstMax && x > secondMax){
                thirdMax = secondMax;
                secondMax = x;
            } else if (x < firstMax && x < secondMax && x > thirdMax){
                thirdMax = x;
            }
        }
        return thirdMax == Long.MIN_VALUE ? (int)firstMax : (int)thirdMax;
    }

    public static int heightChecker(int[] heights) {
        int[] temp = new int[heights.length];
        System.arraycopy(heights,0,temp,0,heights.length);
        Arrays.sort(temp);
        int diff = 0;
        for (int i = 0;i<temp.length;i++){
            if (heights[i] != temp[i]) diff ++;
        }
        return diff;
    }

    public int[] sortArrayByParity(int[] A) {
        if (A.length == 0 || A.length == 1){
            return A;
        }
        int start = 0;
        int end = A.length - 1;
        while (start < end){
            while (start < end && (A[start] & 1) ==0){
                start++;
            }
            if ((A[end] & 1) == 0){
                int temp = A[end];
                A[end] = A[start];
                A[start] = temp;
                start++;
            }
            end --;
        }
        return A;
    }

    public void moveZeroes(int[] nums) {
        int i = 0,temp;
        for (int j = 0;j<nums.length;j++){
            if (nums[j] != 0){
                temp = nums[i];
                nums[i++] = nums[j];
                nums[j] =temp;
            }
        }
    }

    public static int[] replaceElements(int[] arr) {
        int currentMax = arr[arr.length -1];
        for (int i= arr.length-2;i>=0;i--){
            int temp = currentMax;
            if (arr[i] > currentMax){
                temp = arr[i];
            }
            arr[i] = currentMax;
            currentMax = temp;
        }
        arr[arr.length -1] = -1;
        return arr;
    }

    public static boolean validMountainArray(int[] A) {
        int N = A.length;
        int pos = 0;
        for (int i =0;i<N-1;i++){
            if (A[i] > A[i +1]){
                pos = i;
                break;
            } else if (A[i] == A[i +1]){
                return false;
            }
        }
        System.out.println(pos);
        if (pos == 0|| pos == N-1) return false;
        for (int i = N-1;i>=1;i--){
            if (A[i -1] < A[i]){
                System.out.println(i);
                if (i == pos){
                    return true;
                } else {
                    return false;
                }
            } else if (A[i-1] == A[i]){
                return false;
            }
        }
        return false;
    }

    public static boolean checkIfExist(int[] arr) {
        int zeroCount = 0;
        for (int i : arr){
            if (i == 0){
                zeroCount++;
            } else if (check(arr,i * 2)){
                return true;
            }
        }
        return zeroCount > 1;
    }

    public static boolean check(int[]arr,int number){
        for (int num : arr){
            if (num == number){
                return true;
            }
        }
        return false;
    }

    public static int removeDuplicates(int[] nums) {
        int i =0;
        for (int j =1;j<nums.length;j++){
            if (nums[i] != nums[j]){
                nums[++i] = nums[j];
            }
        }
        return ++i;
    }

    public static int removeElement(int[] nums, int val) {
        int N = nums.length -1;
        int i =0;
        int temp;
        while (i<=N){
            if (nums[i] == val){
                temp = nums[N];
                nums[N] = nums[i];
                nums[i] = temp;
                N --;
            } else {
                i++;
            }
        }
        return N + 1;
    }

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        System.arraycopy(nums2,0,nums1,m,n);
        Arrays.sort(nums1);
    }

    public static void duplicateZeros(int[] arr) {
        int N = arr.length;
        int index = 0;
        int[] B = new int[N];
        for (int i : arr){
            B[index++] = i;
            if (index ==N) break;
            if (i == 0){
                B[index++] = i;
            }
            if (index ==N) break;
        }
        System.arraycopy(B,0,arr,0,N);
    }

    public static int findMaxConsecutiveOnes(int[] nums) {
        int max = 0;
        int count = 0;
        for (int i : nums) {
            if (i == 1) {
                count++;
            } else {
                max = Math.max(count, max);
                count = 0;
            }
        }
        max = Math.max(count, max);
        return max;
    }

    public static int findNumbers(int[] nums) {
        int count = 0;
        for (int i : nums) {
            if (i >= 10 && i < 100 || i >= 1000 && i < 10000) count++;
        }
        return count;
    }


    public static int[] sortedSquares(int[] A) {
        if (A == null || A.length == 0) return A;
        else {
            int i, j, index, N = A.length;
            int firstPositiveIndex = -1;
            int[] B = new int[N];

            //寻找第一位大于0的整数的位置
            for (i = 0; i < N; i++) {
                if (A[i] >= 0) {
                    firstPositiveIndex = i;
                    break;
                }
            }

            if (firstPositiveIndex < 0) {
                //全部为负值时,倒序排序即可
                for (i = 0; i < N; i++) {
                    B[i] = A[N - 1 - i] * A[N - 1 - i];
                }
            } else {
                i = firstPositiveIndex;
                j = firstPositiveIndex - 1;
                index = 0;
                while (i < N && j >= 0) {
                    if (A[i] <= -A[j]) {
                        B[index++] = A[i] * A[i];
                        ++i;
                    } else {
                        B[index++] = A[j] * A[j];
                        --j;
                    }
                }

                while (i < N) {
                    B[index++] = A[i] * A[i];
                    ++i;
                }

                while (j >= 0) {
                    B[index++] = A[j] * A[j];
                    --j;
                }
            }
            return B;
        }
    }


    /**
     * 暴力递归解
     *
     * @param coins
     * @param amount
     * @return
     */
//    public int coinChange(int[] coins,int amount){
//        if (amount == 0) return 0;
//        int ans = Integer.MAX_VALUE;
//        for (int coin : coins){
//            if (amount - coin < 0) continue;
//            int subProb = coinChange(coins,amount - coin);
//            if (subProb == -1) continue;
//            ans = Math.min(ans,subProb + 1);
//        }
//        return ans == Integer.MAX_VALUE ? -1 : ans;
//    }
    static public int coinChangeWithDynamic(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int i = 0; i < dp.length; i++) {
            for (int coin : coins) {
                if (i - coin < 0) continue;
                dp[i] = Math.min(dp[i], 1 + dp[i - coin]);
            }
        }
        return (dp[amount] == amount + 1) ? -1 : dp[amount];
    }


    static public int coinChange(int[] coins, int amount) {
        int[] memo = new int[amount + 1];
        Arrays.fill(memo, -2);
        return helper(coins, amount, memo);
    }

    static public int helper(int[] coins, int amount, int[] memo) {
        if (amount == 0) return 0;
        if (memo[amount] != -2) return memo[amount];
        int ans = Integer.MAX_VALUE;
        for (int coin : coins) {
            //金额不可达
            if (amount - coin < 0) continue;
            int subProb = helper(coins, amount - coin, memo);
            //子问题无解
            if (subProb == -1) continue;
            ans = Math.min(ans, subProb + 1);
        }
        memo[amount] = (ans == Integer.MAX_VALUE) ? -1 : ans;
        return memo[amount];
    }


}
