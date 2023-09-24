package cn.knightzz.chapter09;

import java.util.Arrays;

/**
 * @author 王天赐
 * @title: LCR62
 * @description: 62. 不同路径
 * @create: 2023-09-07 10:54
 */
public class LCR62 {

    public int uniquePaths(int m, int n) {

        // 从后往前推
        int[][] dp = new int[m][n];
        // base case
        dp[m - 1][n - 1] = 1;

        // 初始化
        for (int i = 0; i < m; i++) {
            dp[i][n - 1] = 1;
        }
        for (int j = 0; j < n; j++) {
            dp[m - 1][j] = 1;
        }


        // dp[m][n] = dp[m + 1][n] + dp[n + 1]
        for (int i = m - 2; i >= 0; i--) {
            for (int j = n - 2; j >= 0; j--) {
                int r1 = dp[i + 1][j];
                int r2 = dp[i][j + 1];
                dp[i][j] = r1 + r2;
            }
        }
        return dp[0][0];
    }


    int count = 0;

    public void process1(int i, int j, int m, int n) {

        // 终止条件
        if (i == m && j == n) {
            System.out.printf("=> %d, %d\n", i, j);
            count++;
            return;
        }

        System.out.printf("{%d, %d} ", i, j);
        // NOTE 机器人只能向下或者向右
        // 选择列表
        // 向下
        if (i < m) {
            process1(i + 1, j, m, n);
        }
        // 向右
        if (j < n) {
            process1(i, j + 1, m, n);
        }
    }

    public int process2(int m, int n) {
        memo = new int[m + 1][n + 1];
        return backtrack(1, 1, m, n);
    }

    int[][] memo;

    public int backtrack(int i, int j, int m, int n) {

        // 终止条件
        if (i == m && j == n) {
            return 1;
        }

        // memo[i][j] 的含义 : 从 {i, j}位置出发, 到达 {m, n} 位置有多少条路径

        if (i > m || j > n) {
            return 0;
        }

        if (memo[i][j] > 0) {
            return memo[i][j];
        }

        int r1 = backtrack(i + 1, j, m, n);
        int r2 = backtrack(i, j + 1, m, n);

        memo[i][j] = r1 + r2;
        return memo[i][j];
    }

    public static void main(String[] args) {

        LCR62 lcr63 = new LCR62();
        // lcr63.process1(1, 1, 3, 3);
        // int res = lcr63.process2(1, 1);
        int res = lcr63.uniquePaths(3, 3);
        System.out.println(res);
    }
}
