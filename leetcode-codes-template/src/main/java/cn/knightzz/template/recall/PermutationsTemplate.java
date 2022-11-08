package cn.knightzz.template.recall;

import java.util.LinkedList;
import java.util.List;

/**
 * @author 王天赐
 * @title: PermutationsTemplate
 * @projectName algorithm-codes
 * @description: 全排列模板
 * @website <a href="http://knightzz.cn/">http://knightzz.cn/</a>
 * @github <a href="https://github.com/knightzz1998">https://github.com/knightzz1998</a>
 * @create: 2022-11-04 21:32
 */
public class PermutationsTemplate {
    class Solution {

        List<List<Integer>> ans = new LinkedList<>();
        LinkedList<Integer> track = new LinkedList<>();
        private int n;
        private boolean[] used;

        public List<List<Integer>> permute(int[] nums) {
            this.n = nums.length;
            used = new boolean[n];
            backtrack(nums);
            return ans;
        }

        void backtrack(int[] nums) {

            if (track.size() == n) {
                ans.add(new LinkedList<>(track));
            }

            // 注意全排列和组合的区别 :
            // 组合中 : 假如 [1,2,3] => 如果你选择了1, 剩下的只能选[2,3]
            // 全排列 : 要求用到所有的数字, 并且不限制顺序
            for (int i = 0; i < n; i++) {

                if (used[i]) {
                    continue;
                }

                // 做选择
                used[i] = true;
                track.addLast(nums[i]);

                backtrack(nums);

                // 撤销选择
                track.removeLast();
                used[i] = false;
            }
        }
    }
}
