package org.lasque.lib.KMP;

/**
 * TuSDK
 * $
 *
 * @author H.ys
 * @Date $ $
 * @Copyright (c) 2019 tusdk.com. All rights reserved.
 */

/**
 * Knuth-Morris-Pratt 算法
 * 使用有限状态机实现
 * */
public class KMP {

    private String pat;
    private int[][] dp;

    public KMP(String pat){
        this.pat = pat;
        int M = pat.length();
        //DP[状态][字符]
        dp = new int[M][256];
        // base case
        dp[0][pat.charAt(0)] = 1;
        //影子状态X 初始化为0
        int X = 0;
        // 当前状态j 从1开始
        for(int j= 1;j<M;j++){
            for (int c = 0;c<256;c++){
                dp[j][c] = dp[X][c];
            }
            dp[j][pat.charAt(j)] = j+1;
            X = dp[X][pat.charAt(j)];
        }
    }

    public int search(String txt){
        int M = pat.length();
        int N = txt.length();

        // pat的初始态为0
        int j = 0;
        for (int i =0;i<N;i++){
            j = dp[j][txt.charAt(i)];
            if (j == M) return i - M + 1;
        }
        return -1;
    }
}
