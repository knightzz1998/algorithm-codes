package cn.knightzz.chapter02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author 王天赐
 * @title: LCR15
 * @description: 15.三数之和
 * @create: 2023-08-18 10:17
 */
public class LCR15 {

    // https://leetcode.cn/problems/3sum/

    List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> threeSum(int[] nums) {

        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {

            // 如果排序后的第一个元素 大于0 , 说明不可能有结果等于0的元素了
            if (nums[0] > 0)
                return result;

            // 判断当前值和上一个是否重复, 如果是就不用在计算防止重复
            if (i - 1 >= 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int left = i + 1;
            int right = nums.length - 1;

            while (right > left) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum > 0) {
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    // 问题的关键在于, 你要先去找到满足条件, 再去重, 而不是先去重复的
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    while (right > left && nums[right] == nums[right - 1]) right--;
                    while (right > left && nums[left] == nums[left + 1]) left++;
                    right--;
                    left++;
                }
            }
        }
        return result;
    }

}
