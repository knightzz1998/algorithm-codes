package cn.knightzz.hash.hashmap;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 王天赐
 * @title: HashMapApply01
 * @projectName algorithm-codes
 * @description:
 * @website <a href="http://knightzz.cn/">http://knightzz.cn/</a>
 * @github <a href="https://github.com/knightzz1998">https://github.com/knightzz1998</a>
 * @create: 2022-10-16 20:57
 */
@SuppressWarnings("all")
public class HashMapApply01 {

    public static void main(String[] args) {
        
    }

    class Solution {

        // https://leetcode.cn/problems/two-sum/submissions/
        public int[] twoSum(int[] nums, int target) {

            Map<Integer, Integer> valueToIndex = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                // 查询 valueToIndex 中是否存在 满足 :
                // res = target - nums[i] 的 res , 查询的时候是
                // find nums[0, ... , i - 1] 需要在 i 的前面找
                // 如果是 nums[0, ... , n - 1] 这样会查到自己
                if (valueToIndex.containsKey(target - nums[i])) {
                    return new int[]{i, valueToIndex.get(target - nums[i])};
                }
                valueToIndex.put(nums[i], i);
            }
            return new int[2];
        }
    }
}
