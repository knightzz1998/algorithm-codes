package cn.knightzz.chapter07;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author 王天赐
 * @title: LCR40
 * @description: 40. 组合总和 II
 * @create: 2023-08-31 11:28
 */
public class LCR40 {
    List<List<Integer>> res = new LinkedList<>();

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {

        Arrays.sort(candidates);
        backtrack(target, candidates, 0, new LinkedList<>(), 0);
        return res;
    }

    public void backtrack(int target, int[] candidates, int start, LinkedList<Integer> track, int sum) {

        if (sum == target) {
            res.add(new LinkedList<>(track));
            return;
        }

        // 可以重复选, 但是排列不能重复

        for (int i = start; i < candidates.length; i++) {

            // 剪枝, 如果总和已经大于target, 就不用继续了
            if (sum > target) {
                break;
            }

            // 问题在于 : [1,1,2, 2, 5,6,7,] 有两个满足的

            // 跳过第一个数字相同的
            // 为什么是 i > start , 是因为
            // 需要在当前区间内去重, 比如 第一个数组选了 1, 剩下的就是 [1,2,2,5,6,7]
            // 第二个数字选了 1, 剩下的区间就是 [2,2,5,6,7]
            // 如果是直接 > 0 的话, 在选第二个1的时候, 因为两个1相邻, 就会跳过,
            // 但是实际上我们应该在当前选择列表的区间内跳过
            // 当前区间 [start, len - 1 ];
            if (i > start && candidates[i] == candidates[i - 1]) {
                continue;
            }

            // 选择
            sum += candidates[i];
            track.addLast(candidates[i]);

            // 进入下一层
            // 每个数字只能使用一次, 所以是 i + 1
            backtrack(target, candidates, i + 1, track, sum);

            // 撤销
            sum -= candidates[i];
            track.removeLast();
        }
    }

    public static void main(String[] args) {

        LCR40 lcr40 = new LCR40();
        // lcr40.combinationSum2(new int[]{10, 1, 2, 7, 6, 1, 5}, 8).forEach(System.out::println);
        lcr40.combinationSum2(new int[]{2,5,2,1,2}, 5).forEach(System.out::println);


    }
}
