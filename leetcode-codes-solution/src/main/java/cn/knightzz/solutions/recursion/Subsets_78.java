package cn.knightzz.solutions.recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 王天赐
 * @title: Subsets_78
 * @projectName algorithm-codes
 * @description: 78. 子集
 * @website <a href="http://knightzz.cn/">http://knightzz.cn/</a>
 * @github <a href="https://github.com/knightzz1998">https://github.com/knightzz1998</a>
 * @create: 2022-10-20 21:10
 */
public class Subsets_78 {

    class Solution {
        public List<List<Integer>> ans;
        public List<Integer> set;

        public List<List<Integer>> subsets(int[] nums) {

            ans = new ArrayList<List<Integer>>();
            set = new ArrayList<Integer>();
            findSubsets(nums, 0);
            return ans;
        }

        public void findSubsets(int[] nums, int index) {

            // 终止条件
            if (nums.length == index) {
                // 将 set中的结果存储到ans, 注意不能把set直接加进去, 因为set会变化
                // 需要重新创建arraylist
                ans.add(new ArrayList<>(set));
                return;
            }

            // 处理当前层的逻辑, 当前层选择 nums[index]
            // 选 nums[index] 作为子集
            set.add(nums[index]);
            // 执行下一层
            findSubsets(nums, index + 1);

            // 不选nums[index] 作为子集, 那就直接换下一个
            findSubsets(nums, index + 1);

            // 还原当前层的状态(这个目的是回溯时, 保证当前层的状态是不变的) , 递归中局部变量是不需要改的
            set.remove(set.size() - 1);
        }


    }
}
