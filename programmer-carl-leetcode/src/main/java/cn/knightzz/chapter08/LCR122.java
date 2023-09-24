package cn.knightzz.chapter08;

import java.util.Arrays;

/**
 * @author 王天赐
 * @title: LCR122
 * @description: 122.买卖股票的最佳时机II
 * @create: 2023-09-19 12:24
 */
public class LCR122 {
    //<editor-fold desc="贪心算法">
    public int maxProfit(int[] prices) {

        // 贪心 : 只计算正利润 , 当前购入, 隔天卖出
        int result = 0;
        for (int i = 1; i < prices.length; i++) {
            result += Math.max(prices[i] - prices[i - 1], 0);
        }
        return result;
    }
    //</editor-fold>

    public int process1(int[] prices) {

        int N = prices.length;
        // 定义DP数组
        // dp[i][0] 表示第i天持有股票所得最多现金, 这里注意, 如果第 i - 1天就持有股票, 那么 dp[i][0] = dp[i - 1][0]
        // dp[i][1] 表示第i天不持有股票所得最多现金
        int[][] dp = new int[N][2];

        // 初始化 [7,1,5,3,6,4]
        // 第一天持有股票, 此时净亏 7 块, 因为你花了7块买了股票
        dp[0][0] = dp[0][0] - prices[0];
        // 不持有就不亏
        dp[0][1] = 0;


        for(int i = 1; i < prices.length; i++) {
            // 需要判断上一天是否持有股票
            //  上一天持有股票, 今天也维持原状 : dp[i - 1][0]
            //  上一天没有有股票, 今天购入股票(花费prices[i]) :   dp[i - 1][1] - prices[i]
            // dp[i][0] 表示一定要持有股票的, 可以是今天买的, 也可以是上一天就有的
            dp[i][0] =  Math.max(dp[i - 1][0], dp[i - 1][1] - prices[i]);

            // 上一天没有股票, 今天也维持原状  : dp[i - 1][1]
            // 上一天持有股票, 今天卖出回血 : dp[i - 1][0] + prices[i]
            dp[i][1] =  Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i]);
        }
        return dp[prices.length - 1][1];
    }

    //<editor-fold desc="暴力搜索 - 超时">
    public int process2(int[] prices) {
        backtrack(prices, 0, 0, 0, false);
        return maxProfit;
    }

    int maxProfit = Integer.MIN_VALUE;

    public void backtrack(int[] prices, int start, int profit, int price, boolean handler) {

        // 最后必须卖出
        if (start == prices.length && !handler) {
            maxProfit = Math.max(profit, maxProfit);
            System.out.println(profit);
            return;
        }

        if (start == prices.length) {
            return;
        }

        // handler = true, 表示当前持有股票, 只能选择卖出或者不卖
        if (handler) {
            // 卖出
            backtrack(prices, start + 1, profit + prices[start] - price, price, false);
            // 没卖
            backtrack(prices, start + 1, profit, price, true);
        } else {
            // 买入
            backtrack(prices, start + 1, profit, prices[start], true);
            // 没买
            backtrack(prices, start + 1, profit, 0, false);
        }
    }
    //</editor-fold>

    public static void main(String[] args) {

        LCR122 lcr122 = new LCR122();
        //System.out.println("res = " + lcr122.process2(new int[]{7, 1, 5, 3, 6, 4}));
        //System.out.println("res = " + lcr122.process2(new int[]{1,2,3,4,5}));
        System.out.println("res = " + lcr122.process2(new int[]{7, 6, 4, 3, 1}));
    }
}
