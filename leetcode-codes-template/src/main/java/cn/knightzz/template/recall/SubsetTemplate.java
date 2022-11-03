package cn.knightzz.template.recall;

import java.util.LinkedList;
import java.util.List;

/**
 * @author 王天赐
 * @title: SubsetTemplate
 * @projectName algorithm-codes
 * @description: 子集模板
 * @website <a href="http://knightzz.cn/">http://knightzz.cn/</a>
 * @github <a href="https://github.com/knightzz1998">https://github.com/knightzz1998</a>
 * @create: 2022-11-03 21:41
 */
public class SubsetTemplate {

    class Solution {

        // https://labuladong.github.io/algo/4/31/107/

        List<List<Integer>> res = new LinkedList<>();
        // 回溯算法的递归路径
        LinkedList<Integer> track = new LinkedList<>();

        void backtrack(int[] nums, int start) {

            // 每一个节点都是一个子集, 路径是对应的值,
            res.add(new LinkedList<>(track));

            for (int i = start; i < nums.length; i++) {

                // 做选择, start 会帮助我们去选择每一条路径, 并且保证路径唯一
                track.addLast(nums[i]);
                // 移动到下一层, 这里注意, start 会控制可以选择的路径
                // 举个例子 : [1,2,3] , 当前 start = 0 , 移动到下一层的时候 int i = start = 1 , 并且 i = [1,2]
                backtrack(nums, start + 1);
                // 后序, 回到 nums[0] 的时候, 重新选择, 原本是 i = [0,1,2] , 0被选择过了, 所以要去选 1 和 2
                // 要把刚才的选择的 nums[0] 移除掉
                track.removeLast();
            }

        }

        public List<List<Integer>> subsets(int[] nums) {

            backtrack(nums, 0);
            return res;
        }

    }

}
