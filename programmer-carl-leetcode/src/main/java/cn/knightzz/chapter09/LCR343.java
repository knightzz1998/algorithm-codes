package cn.knightzz.chapter09;

import java.util.LinkedList;
import java.util.List;

/**
 * @author 王天赐
 * @title: LCR343
 * @description: 343.整数拆分
 * @create: 2023-09-25 09:37
 */
public class LCR343 {

    public int integerBreak(int n) {

        // dp[i] 表示 i 拆分后得到的最大乘积
        int[] dp = new int[n + 1];
        // BASE CASE
        dp[0] = 0;
        dp[1] = 0;
        dp[2] = 1;

        // 推导公式 :
        // dp[n] = Math.max(i * n - i , i * dp[n - i]);
        for (int i = 3; i <= n; i++) {
            // 没有办法直接从 dp[3] 推出dp[4] , 只能从 2 ~ 4
            // dp[4] = 1 * dp[3] 2 * dp[2] 3 * dp[1] , 1 * 3 , 2 * 2 , 3 * 1 ...
            for (int j = 2; j <= i; j++) {
                int r1 = j * (i - j);
                int r2 = j * dp[i - j];
                r1 = Math.max(r1, r2);
                dp[i] = Math.max(r1, dp[i]);
            }
        }
        return dp[n];
    }

    public void process1(int n) {
        backtrack(n);
    }

    int result = 1;

    LinkedList<Integer> track = new LinkedList<>();

    public void backtrack(int n) {

        // 不应该加判断条件, 因为要考虑所有的情况
        // 当 n 拆分到 1 时, 结束, 然后统计路径上的乘积
        if (n <= 1) {
            int res = 1;
            for (Integer integer : track) {
                System.out.printf("%d * ", integer);
                res *= integer;
            }
            System.out.printf("%d = %d\n", n, res);
            result = Math.max(result, res);
            return;
        }

        // 10 可以拆分 1 + backtrack(9) , 2 + backtrack(8) ...
        // 1 * 1 * 1 * 1 * 1 ...
        // 2 * 1 * 1 * 1 * 1 ...
        // ...
        // 1 * 2 * 1 * 1 ...
        for (int i = 1; i <= n; i++) {
            track.add(i);
            backtrack(n - i);
            track.removeLast();
        }
    }

    public int process2(int n) {
        return backtrack2(n);
    }

    public int backtrack2(int n) {

        if (n <= 1) {
            return 0;
        }

        if (n == 2) {
            return 1;
        }

        int maxProduct = 0;
        for (int i = 1; i <= n; i++) {

            // 1. 直接乘
            int product = i * (n - i);
            // 2. i * 剩余部分最大乘积
            int remain = i * backtrack2(n - i);

            product = Math.max(product, remain);
            maxProduct = Math.max(product, maxProduct);
        }
        return maxProduct;
    }

    public int process3(int n) {
        memo = new int[n + 1];
        return backtrack3(n);
    }

    // memo[n] : n拆分后的乘积最大值
    int[] memo;

    public int backtrack3(int n) {
        if (n <= 1) {
            return 0;
        }

        if (n == 2) {
            return 1;
        }

        if (memo[n] > 0) {
            return memo[n];
        }

        int maxProduct = 0;
        for (int i = 1; i <= n; i++) {

            // 1. 直接乘
            int product = i * (n - i);
            // 2. i * 剩余部分最大乘积
            int remain = i * backtrack3(n - i);

            product = Math.max(product, remain);
            maxProduct = Math.max(product, maxProduct);
        }
        memo[n] = maxProduct;
        return maxProduct;
    }

    public static void main(String[] args) {

        LCR343 lcr343 = new LCR343();
        // lcr343.process1(10);
        // System.out.println(lcr343.result);
        // int res = lcr343.process3(3);
        int res = lcr343.integerBreak(10);
        System.out.println("res = " + res);

    }
}
