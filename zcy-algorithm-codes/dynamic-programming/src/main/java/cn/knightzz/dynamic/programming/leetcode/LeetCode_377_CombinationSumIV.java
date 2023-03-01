package cn.knightzz.dynamic.programming.leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * @author 王天赐
 * @title: LeetCode_377_CombinationSumIV
 * @projectName algorithm-codes
 * @description: 377. 组合总和 Ⅳ
 * @website <a href="https://knightzz.cn/">https://knightzz.cn/</a>
 * @github <a href="https://github.com/knightzz1998">https://github.com/knightzz1998</a>
 * @create: 2023-02-28 20:49
 */
@SuppressWarnings("all")
public class LeetCode_377_CombinationSumIV {

    // 题目类型 : 不可重复, 可复选, 排列问题
    //

    public static int combinationSum4(int[] nums, int target) {

        int[] dp = new int[target + 1];

        dp[0] = 1;

        for (int i = 1; i <= target; i++) {

            for (int j = 0; j < nums.length; j++) {

                // 假设 target = 10 nums = [1,2,5]
                // 那么 dp[10] = dp[10 - 1] + dp[10 - 2] + dp[10 - 5]
                // 意思是 dp[9] 假设有 5 种 ,这5种 再选择 1 => dp[10] = 5

                if (i - nums[j] >= 0) {
                    dp[i] += dp[i - nums[j]];
                }

            }

        }

        return dp[target];
    }


    public static LinkedList<List<Integer>> result = new LinkedList<>();

    public static LinkedList<Integer> track = new LinkedList<>();

    /**
     * 返回总和为 target 的元素组合的个数
     *
     * @param nums
     * @param target
     * @return
     * @note 数组元素是可以重复选的
     */
    public static void process(int[] nums, int target) {

        if (target == 0) {
            result.add(new LinkedList<>(track));
            return;
        }
        // 不合法的情况
        if (target < 0) {
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            // 选择
            track.addLast(nums[i]);
            process(nums, target - nums[i]);
            track.removeLast();
        }
    }

    public static int process2(int[] nums, int target) {

        if (target == 0) {
            return 1;
        }
        // 不合法的情况
        if (target < 0) {
            return 0;
        }

        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            // 选择
            count += process2(nums, target - nums[i]);
        }
        return count;
    }

    public static void main(String[] args) {

        int[] nums = {1, 2, 3};
        int target = 4;

        process(nums, target);

        for (List<Integer> integers : result) {
            System.out.println(integers);
        }

        int process2 = process2(nums, target);
        System.out.println("process2 = " + process2);

        int res = combinationSum4(nums, target);

        System.out.println(res);

    }

}
