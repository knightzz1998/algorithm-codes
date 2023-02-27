package cn.knightzz.dynamic.programming.leetcode;

import java.util.HashMap;

/**
 * @author 王天赐
 * @title: LeetCode_494_TargetSum
 * @projectName algorithm-codes
 * @description: 494. 目标和
 * @website <a href="https://knightzz.cn/">https://knightzz.cn/</a>
 * @github <a href="https://github.com/knightzz1998">https://github.com/knightzz1998</a>
 * @create: 2023-02-22 22:01
 */
@SuppressWarnings("all")
public class LeetCode_494_TargetSum {

    // https://leetcode.cn/problems/target-sum/

    public static int findTargetSumWays2(int[] nums, int target) {
        return process1(nums, 0, target);
    }


    static HashMap<String, Integer> memo = new HashMap<>();

    public static int process1(int[] nums, int i, int rest) {

        if (i == nums.length) {
            // 说明到最后了, 判断当前值是否是0 还是 1
            return rest == 0 ? 1 : 0;
        }

        String key = i + "," + rest;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        // 这个什么意思呢
        // target = 3
        // 如果当前的 1 是 + , i = 0
        // 那么 我要求剩下的 1 ~ N 的数组 要满足 2 的表达式的个数 : process1(nums, i + 1, rest - nums[i]);

        // 选择 +
        int p1 = process1(nums, i + 1, rest - nums[i]);
        // 选择 -
        // [表达式] - nums[i] = res => [剩下的表达式] = rest + nums[i]
        int p2 = process1(nums, i + 1, rest + nums[i]);

        // 将计算的结果添加到备忘录
        memo.put(key, p1 + p2);

        return p1 + p2;
    }

    public static int findTargetSumWays(int[] nums, int target) {

        int sum = 0;

        for (int num : nums) {
            sum += num;
        }

        // i 表示前 i 个数
        int N = nums.length + 1;

        // dp[i][j] : nums数组前 i 个数,

        int T = sum * 2 + 1;

        int[][] dp = new int[N][T];
        // 这个全是 + 号, 只有一种情况
        dp[0][sum] = 1;

        // j = 0 表示 [nums +/-] = 0
        // j = 1 表示 [nums +/-] = 1
        // j = sum 表示 [nums +/-] = sum
        // 但是 j >= 0 无法表示 -sum 或者 -1 ... 这种值
        // 所以

        for (int i = 1; i < N; i++) {
            for (int j = 0; j < T; j++) {

                if (j + nums[i - 1] < T) {

                }

            }
        }

        return 1;
    }

    public int findTargetSumWays1(int[] nums, int target) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        // 如果目标值大于 所有nums元素的和的情况下
        if (Math.abs(target) > Math.abs(sum)) {
            return 0;
        }
        int[][] dp = new int[nums.length + 1][2 * sum + 1];
        // 这个是只有有一种解法 : 全是 +
        dp[0][sum] = 1;

        // j 是 nums 中所有元素运算可能的结果
        // 最大是 sum
        // 最小是 -sum (所有都是 - )
        // j 表示所有可能的结果

        for (int i = 1; i <= nums.length; i++) {
            for (int j = 0; j <= 2 * sum; j++) {
                if (j + nums[i - 1] <= 2 * sum) {
                    dp[i][j] += dp[i - 1][j + nums[i - 1]];
                }
                if (j - nums[i - 1] >= 0) {
                    dp[i][j] += dp[i - 1][j - nums[i - 1]];
                }
            }
        }
        return dp[nums.length][target + sum];
    }

    public static void main(String[] args) {

        int[] nums = {1, 1, 1, 1, 1};
        int target = 3;

        System.out.println(findTargetSumWays(nums, target));

    }
}
