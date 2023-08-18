package cn.knightzz.chapter02;

import java.util.HashMap;

/**
 * @author 王天赐
 * @title: LCR01
 * @description: 1.双数之和
 * @create: 2023-08-18 09:44
 */
public class LCR01 {

    // 基本思路 :
    // 1. 扫描数组元素, 使用 Hash 表存储已经读取的数组元素, 每扫描一个, 就查询数组中有没有要的目标值
    // 2. res = target - current;

    // https://leetcode.cn/problems/two-sum/
    public int[] twoSum(int[] nums, int target) {

        HashMap<Integer, Integer> cacheMap = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {

            int current = nums[i];
            int res = target - current;
            // 判断数组中是否存在, 此时不应该先把current添加进入,
            // 因为如果出现 current = target / 2 时, 就是自己加自己了
            if (cacheMap.containsKey(res)) {
                // 如果包含, 说明满足条件, 题目说明只有一个答案, 所以直接返回既可
                int idx2 = cacheMap.get(res);
                return new int[]{i, idx2};
            }

            cacheMap.put(current, i);
        }
        return new int[]{};
    }
}
