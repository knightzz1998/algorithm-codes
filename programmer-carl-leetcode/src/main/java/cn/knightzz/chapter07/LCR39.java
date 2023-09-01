package cn.knightzz.chapter07;

import java.util.LinkedList;
import java.util.List;

/**
 * @author 王天赐
 * @title: LCR39
 * @description: 39.组合总和
 * @create: 2023-08-31 10:39
 */
public class LCR39 {


    // 1. 无重复元素 的整数数组 candidates
    // 2. 使数字和为目标数 target 的 所有 不同组合 ，并以列表形式返回
    // 3. candidates 中的 同一个 数字可以 无限制重复被选取
    // 之所以是 i 不是 i + 1 , 是因为单个数字可以重复选
    // backtrack(target, candidates, i,track, sum);
    // 4. 如果至少一个数字的被选数量不同，则两种组合是不同的。


    List<List<Integer>> res = new LinkedList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
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

            // 选择
            sum += candidates[i];
            track.addLast(candidates[i]);

            // 进入下一层
            // 之所以是 i 不是 i + 1 , 是因为单个数字可以重复选
            backtrack(target, candidates, i,track, sum);

            // 撤销
            sum -= candidates[i];
            track.removeLast();
        }
    }

    public static void main(String[] args) {

        LCR39 lcr39 = new LCR39();
//        List<List<Integer>> lists = lcr39.combinationSum(new int[]{2, 3, 6, 7}, 7);
//        lists.forEach(System.out::println);

        List<List<Integer>> lists = lcr39.combinationSum(new int[]{2, 3, 5}, 8);
        lists.forEach(System.out::println);
    }
}
