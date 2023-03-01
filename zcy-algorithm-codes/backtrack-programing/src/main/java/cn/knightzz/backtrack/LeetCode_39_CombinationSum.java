package cn.knightzz.backtrack;

import java.util.LinkedList;
import java.util.List;

/**
 * @author 王天赐
 * @title: LeetCode_39_CombinationSum
 * @projectName algorithm-codes
 * @description: 39. 组合总和
 * @website <a href="https://knightzz.cn/">https://knightzz.cn/</a>
 * @github <a href="https://github.com/knightzz1998">https://github.com/knightzz1998</a>
 * @create: 2023-03-01 16:25
 */
@SuppressWarnings("all")
public class LeetCode_39_CombinationSum {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {

        if (candidates == null || candidates.length == 0) {
            return result;
        }

        process(candidates, target, 0, 0);
        // 1. candidates 数组元素不重复, 可重复选
        // 2. 题目要求是组合, 而不是排列, 不考虑顺序
        return result;
    }

    List<List<Integer>> result = new LinkedList<>();
    LinkedList<Integer> track = new LinkedList<>();

    public void process(int[] candidates, int target, int index, int cur) {

        if (cur == target) {
            result.add(new LinkedList<>(track));
            return;
        }

        if (cur > target) {
            return;
        }

        for (int i = index; i < candidates.length; i++) {
            // 选择
            track.addLast(candidates[i]);
            // 继续选择下一个数

            // candidates = [2,3,6,7], target = 7
            // 第一层 : [2,3,6,7] 选 2
            // 第二层 : [2 3,4,7] ...
            // .... 选出一种组合后
            // 第一层 : [2,3,6,7] 选 3
            // 第二层 : [3,4,7] ... , 无法再选择3之前的, 这就可以保证了 [2,3,...] 不会出现 [3,2,... ]

            // 我们想要的是元素可以重复选
            process(candidates, target, i, cur + candidates[i]);
            // 撤销
            track.removeLast();
        }
    }

    public static void main(String[] args) {

        int[] candidates = {2, 3, 6, 7};
        int target = 7;
        LeetCode_39_CombinationSum obj = new LeetCode_39_CombinationSum();

        List<List<Integer>> result = obj.combinationSum(candidates, target);

        result.forEach(item -> {
            System.out.println(item);
        });


    }
}
