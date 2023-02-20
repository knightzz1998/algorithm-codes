package cn.knightzz.dynamic.programming.demo;

import java.util.Arrays;

/**
 * @author 王天赐
 * @title: Demo01
 * @projectName algorithm-codes
 * @description: 416. 分割等和子集
 * @website <a href="http://knightzz.cn/">http://knightzz.cn/</a>
 * @github <a href="https://github.com/knightzz1998">https://github.com/knightzz1998</a>
 * @create: 2023-02-18 20:14
 */
public class Demo01 {

    /*
        给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等

        输入：nums = [1,5,11,5]
        输出：true
        解释：数组可以分割成 [1, 5, 5] 和 [11] 。
        最朴素的想法, 就是把所有的子集组合都挨个判断下, 是否能够分割
     */


    public static void way01(int[] nums) {

        // 计算总和
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        int target = sum / 2;

        if (sum % 2 != 0) {
            // 如果sum是个奇数, 直接返回false, 不可能存在这样的
            return;
        }

        // 要求子集的元素和相等, 也就是说要求满足 从 nums 中取出 x 个数, 保证对应的值等于 sum / 2
        // 排序, 让相近的元素靠在一起 [1, 5, 11, 5] => [1, 5, 5, 11]
        Arrays.sort(nums);
        backtrack(nums, 0, 0, target);

    }

    public static void backtrack(int[] nums, int start, int cur, int target) {

        if (cur == target) {
            System.out.println("cur == " + cur);
            return;
        }

        for (int i = start; i < nums.length; i++) {
            // 剪枝
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }

            backtrack(nums, i + 1, cur + nums[i], target);
        }
    }


    public static void main(String[] args) {

        int[] nums = new int[]{1, 5, 11, 5};
        way01(nums);
    }
}
