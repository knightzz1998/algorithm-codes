package cn.knightzz.chapter08;

import cn.knightzz.chapter09.LCR70;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author 王天赐
 * @title: LCR53
 * @description: 53. 最大子数组和
 * @create: 2023-09-19 10:17
 */
public class LCR53 {

    public int maxSubArray(int[] nums) {
        backtrack(nums, 0, 0);
        return res;
    }


    //<editor-fold desc="1. 暴力解法">
    public void process1(int[] nums) {
        backtrack(nums, 0, 0);
        result.forEach(System.out::println);
    }

    // 暴力搜索所有的连续子数组
    List<Integer> result = new LinkedList<>();
    int res = Integer.MIN_VALUE;

    public void backtrack(int[] nums, int start, int sum) {

        if (start > nums.length) {
            return;
        }


        // [1] , [1,2] , [1,2,3]
        for (int i = start; i < nums.length; i++) {
            sum += nums[i];
            // 记录当前结果
//            result.add(sum);
            res = Math.max(res, sum);
        }
        // 进入下一层 以 2开头
        backtrack(nums, start + 1, 0);
    }
    //</editor-fold>


    //<editor-fold desc="2. 动态规划 - 超时">
    public void process2(int[] nums) {
        int[] dp = new int[nums.length + 1];
        dp[0] = 0;
        // dp[i] 记录前 i 个数组元素的和
        for (int i = 0; i < nums.length; i++) {
            dp[i + 1] = dp[i] + nums[i];
        }
        // [1,2,3,4,5]
        // dp[1] = 1, dp[2] = [3] , dp[3] = [6]  dp[4] = dp[10]...
        // [2,3,4] = dp[4] = dp[1]

        int res = Integer.MIN_VALUE;
        for (int i = 0; i <= nums.length; i++) {
            for (int j = i + 1; j <= nums.length; j++) {
                int cur = dp[j] - dp[i];
                res = Math.max(cur, res);
            }
        }
        System.out.println(res);
    }
    //</editor-fold>


    //<editor-fold desc="正确的动态规划">
    public int process3(int[] nums) {

        int N = nums.length;

        if (N == 0) {
            return N;
        }

        // 我的思路错误了 : dp 数组本来就应该是防止重复计算的
        // dp[i] 记录以 i 结尾的 最大子数组和
        int[] dp = new int[N];
        // base case
        // 第一个元素前面没有子数组
        dp[0] = nums[0];
        for (int i = 1; i < N; i++) {
            // nums = [-2,1,-3,4,-1,2,1,-5,4]
            // dp[0] = -2
            // dp[1] = Math.max(1, -2 + 1);
            // 如果当前元素加上前面的元素和比当前元素小, 那么此时 [0,i] 数组和就是
            // 考虑问题的时候不要一下考虑整体, 每个dp[i] 都是以 i 结尾的最大子数组和, 那么要判断 i + 1 位置的 最大子数组和
            // 只需要比较 Math.max(nums[i], dp[i - 1] + nums[i])
            dp[i] = Math.max(nums[i], dp[i - 1] + nums[i]);
        }

        int res = Integer.MIN_VALUE;
        for (int i : dp) {
            res = Math.max(res, i);
        }
        return res;
    }
    //</editor-fold>

    public static void main(String[] args) {

        LCR53 lcr53 = new LCR53();
        //lcr53.process1(new int[]{5, 4, -1, 7, 8});
        lcr53.process2(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4});

    }
}
