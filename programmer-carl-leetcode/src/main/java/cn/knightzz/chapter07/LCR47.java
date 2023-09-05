package cn.knightzz.chapter07;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author 王天赐
 * @title: LCR47
 * @description: 47. 全排列 II
 * @create: 2023-09-02 19:59
 */
public class LCR47 {


    public List<List<Integer>> permuteUnique(int[] nums) {
        used = new boolean[nums.length];
        Arrays.fill(used, false);
        Arrays.sort(nums);
        backtrack(nums);
        return res;
    }

    List<List<Integer>> res = new LinkedList<>();
    LinkedList<Integer> track = new LinkedList<>();
    boolean[] used;

    public void backtrack(int[] nums) {

        if (track.size() == nums.length) {
            res.add(new LinkedList<>(track));
            return;
        }

        for (int i = 0; i < nums.length; i++) {

            // 去重, 这个是可以排序的
            // 不能排序的去重就用HashSet
            // 相邻的两个数字相等, 并且上一个已经使用过了
            if (i > 0 && (nums[i - 1] == nums[i] && used[i - 1])) {
                continue;
            }

            // 这种是没办法使用 track.contains(x) 这种方式去重的
            // 因为 1, 1, 2 中可以存放两个1, 有重复元素
            // 所以只能用 used 数组

            // 不相邻并且使用过的也要跳过
            if (!used[i]) {
                // 可选的是除了自己以外的元素
                // 添加
                track.add(nums[i]);
                used[i] = true;

                // 下一层
                backtrack(nums);

                // 撤销
                track.removeLast();
                used[i] = false;
            }


        }
    }

    public static void main(String[] args) {

        LCR47 lcr47 = new LCR47();
        lcr47.permuteUnique(new int[]{1, 1, 2}).forEach(System.out::println);

    }
}
