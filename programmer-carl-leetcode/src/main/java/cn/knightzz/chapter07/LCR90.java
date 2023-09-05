package cn.knightzz.chapter07;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author 王天赐
 * @title: LCR90
 * @description: 90.子集II
 * @create: 2023-09-02 10:49
 */
public class LCR90 {

    // 数组可重复,

    // 子集和排序组合的本质区别是 : 不要把子集和子数组搞混
    // 子数组是没办法排序的, 就是 [1,2,3] 的子数组 [2,3] 但是不能有 [3, 2]
    // 但是子集是 : 一个集合 , 这个子集合的元素时从 父集合抽取的, 可以随机选择, 并不是像字符串的子串或者数组的子数组一样

    List<List<Integer>> res = new LinkedList<>();

    LinkedList<Integer> track = new LinkedList<>();

    public List<List<Integer>> subsetsWithDup(int[] nums) {

        Arrays.sort(nums);
        backtrack(nums, 0);
        return res;
    }

    public void backtrack(int[] nums, int start) {

        res.add(new LinkedList<Integer>(track));

        for (int i = start; i < nums.length; i++) {

            // 在当前区间去重
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }

            track.add(nums[i]);

            backtrack(nums, i + 1);
            track.removeLast();

        }

    }
}
