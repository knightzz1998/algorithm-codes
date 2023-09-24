package cn.knightzz.chapter09;

import cn.knightzz.chapter07.LCR51;

import java.util.Arrays;

/**
 * @author 王天赐
 * @title: LCR746
 * @description: 746. 使用最小花费爬楼梯
 * @create: 2023-09-07 10:19
 */
public class LCR746 {

    public int minCostClimbingStairs(int[] cost) {
        this.n = cost.length;
        this.cost = cost;
        this.memo = new int[n];
        Arrays.fill(memo, -1);
        int r1 = backtrack(0);
        // 选择爬2阶
        int r2 = backtrack(1);
        return Math.min(r1, r2);
    }

    int n;
    int[] cost;
    int[] memo;

    /**
     * 返回从不同初始位置出发到达楼顶的最小花费
     */
    public int backtrack(int cur) {

        if (cur > n - 1) {
            //System.out.println(cur_cost);
            return 0;
        }

        if (memo[cur] != -1) {
            return memo[cur];
        }

        // 选择爬1阶
        int r1 = cost[cur] + backtrack(cur + 1);
        // 选择爬2阶
        int r2 = cost[cur] + backtrack(cur + 2);

        memo[cur] = Math.min(r1, r2);

        return memo[cur];
    }

    public static void main(String[] args) {

        LCR746 lcr746 = new LCR746();
        int[] cost = {10, 15, 20};
        int[] cost2 = {1, 100, 1, 1, 1, 100, 1, 1, 100, 1};
        System.out.println(lcr746.minCostClimbingStairs(cost));
        System.out.println(lcr746.minCostClimbingStairs(cost2));

    }
}
