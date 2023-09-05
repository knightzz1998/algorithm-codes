package cn.knightzz.chapter07;

import java.util.LinkedList;
import java.util.List;

/**
 * @author 王天赐
 * @title: LCR78
 * @description: 78.子集
 * @create: 2023-09-02 10:36
 */
public class LCR78 {

    // 1. 数组元素互不相同
    // 2. 结果中不能包含重复的子集

    List<List<Integer>> res = new LinkedList<>();

    public List<List<Integer>> subsets(int[] nums) {
        backtrack(nums, 0, new LinkedList<>());
        return res;
    }

    public void backtrack(int[] nums, int start, LinkedList<Integer> track) {

        // 每次遇到结果就添加
        res.add(new LinkedList<>(track));

        // 为了避免重复每次只能选择当前元素后面的元素, 所以要用 start 控制, 并且因为不能重复选择当前元素, 所以有
        for (int i = start; i < nums.length; i++) {
            track.add(nums[i]);
            // 进入下一层
            backtrack(nums, i + 1, track);
            // 撤销
            track.removeLast();
        }
    }

}
