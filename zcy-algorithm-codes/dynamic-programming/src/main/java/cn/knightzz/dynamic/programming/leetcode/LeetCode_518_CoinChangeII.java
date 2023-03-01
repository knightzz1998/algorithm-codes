package cn.knightzz.dynamic.programming.leetcode;

/**
 * @author 王天赐
 * @title: LeetCode518_CoinChangeII
 * @projectName algorithm-codes
 * @description: 518. 零钱兑换 II
 * @website <a href="https://knightzz.cn/">https://knightzz.cn/</a>
 * @github <a href="https://github.com/knightzz1998">https://github.com/knightzz1998</a>
 * @create: 2023-02-28 18:06
 */
@SuppressWarnings("all")
public class LeetCode_518_CoinChangeII {


    public static int change(int amount, int[] coins) {

        int N = coins.length;
        int T = amount;
        int[][] dp = new int[N + 1][T + 1];

        // base case
        for (int i = 1; i <= N; i++) {
            // 当amount == 0 时, 组合数为 1
            dp[i][0] = 1;
        }

        // 注意从 0 开始和 从 1开始的区别
        // 从 0开始 coins[i] 就是第 i 个硬币
        // 从 1 开始 就是 第 coins[i-1]
        for (int i = 1; i <= N; i++) {

            for (int j = 1; j <= T; j++) {

                // 判断剩余金额是否大于当前硬币面额
                // i - 1 表示 第 i 个物品 (数组下标从0开始的, index = 1 => coins[0])
                if (j - coins[i - 1] >= 0) {
                    // 选
                    int p1 = dp[i][j - coins[i - 1]];
                    // 不选当前硬币
                    // 0 ~ i - 1 和 0 ~ i 组合数是一样的
                    // 不适用 coins[i-1] 这个面额的硬币

                    // 注意区分 dp[i] 和 coins[i] 的区别
                    // dp[i] 表示 1 ~ i 个硬币 coins[i] 表示 第 i+1 个面额的硬币
                    // 因为 coins 这个数组是 从 0 开始的 , 第 i 个硬币

                    // 这里的 i - 1 表示 1 ~ i - 1个硬币 不选第 i 个硬币, 不考虑这个硬币的情况下
                    // 满足 金额为 j 的 组合数 => 0 ~ i -1
                    int p2 = dp[i - 1][j];

                    dp[i][j] = p1 + p2;
                } else {
                    dp[i][j] = dp[i - 1][j];
                }

            }
        }

        return dp[N][T];
    }


    public static int change2(int amount, int[] coins) {
        return process(coins, 0, amount);
    }

    /**
     * @param coins  coins 表示可选硬币面额
     * @param index  index 表示当前考虑的硬币下标
     * @param amount amount 表示目标金额
     * @return
     */
    public static int process(int[] coins, int index, int amount) {

        if (amount == 0) {
            return 1;
        }

        if (amount < 0 || index >= coins.length) { // 组合无效
            return 0;
        }

        int count = 0;
        for (int i = index; i < coins.length; i++) {
            count = process(coins, index + 1, amount - coins[index]);
        }
        return count;
    }


    public static void main(String[] args) {

        int amount = 5;
        int[] coins = {1, 2, 5};

        int change = change(amount, coins);
        System.out.println("change = " + change);

    }

}
