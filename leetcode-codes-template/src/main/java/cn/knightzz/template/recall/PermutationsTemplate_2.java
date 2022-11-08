package cn.knightzz.template.recall;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author 王天赐
 * @title: PermutationsTemplate_2
 * @projectName algorithm-codes
 * @description: 全排列2
 * @website <a href="http://knightzz.cn/">http://knightzz.cn/</a>
 * @github <a href="https://github.com/knightzz1998">https://github.com/knightzz1998</a>
 * @create: 2022-11-07 21:02
 */
@SuppressWarnings("all")
public class PermutationsTemplate_2 {

    // https://leetcode.cn/problems/permutations-ii/submissions/

    class Solution {

        private List<List<Integer>> ans = new LinkedList<>();
        private LinkedList<Integer> track = new LinkedList<>();
        private boolean[] used;
        private int n;

        public List<List<Integer>> permuteUnique(int[] nums) {
            this.n = nums.length;
            this.used = new boolean[n];

            // 需要先确保相邻的数字排在一起, 这样才可以剪枝
            Arrays.sort(nums);

            trackback(nums);
            return ans;
        }

        private void trackback(int[] nums) {

            if (track.size() == n) {
                ans.add(new LinkedList<Integer>(track));
            }

            for (int i = 0; i < n; i++) {

                // 不重复的
                if (used[i]) {
                    continue;
                }

                // i = 0 => nums[0] = 1 {第一层}
                // i = 0 => used[0] = true continue {第二层}
                // i = 1 =>  nums[0] == nums[1] continue {第二层}
                // 但是 i = 1 时, 并不需要被剪枝,
                // 但是此时, nums[1] 并不需要被剪 , 所以需要加上

                // 当出现重复元素时，比如输入 nums = [1,2,2',2'']，
                // 2' 只有在 2 已经被使用的情况下才会被选择，同理，2''
                // 只有在 2' 已经被使用的情况下才会被选择，这就保证了相同元素在排列中的相对位置保证固定。
                if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                    continue;
                }


                track.addLast(nums[i]);
                used[i] = true;

                trackback(nums);
                // 退回上一层
                track.removeLast();
                used[i] = false;
            }
        }

    }
}
