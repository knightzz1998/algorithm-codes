package cn.knightzz.chapter07;

import java.util.LinkedList;
import java.util.List;

/**
 * @author 王天赐
 * @title: LCR46
 * @description: 46.全排列
 * @create: 2023-09-02 15:46
 */
public class LCR46 {

    public List<List<Integer>> permute(int[] nums) {
        backtrack(nums);
        return res;
    }

    // 不重复, 全排列

    List<List<Integer>> res = new LinkedList<>();
    LinkedList<Integer> track = new LinkedList<>();

    public void backtrack(int[] nums) {

        if (track.size() == nums.length) {
            res.add(new LinkedList<>(track));
            return;
        }

        for (int i = 0; i < nums.length; i++) {

            if (track.contains(nums[i])) {
                continue;
            }

            track.add(nums[i]);

            // 进入下一层
            backtrack(nums);

            // 撤销
            track.removeLast();
        }
    }

    public static void main(String[] args) {

        LCR46 lcr46 = new LCR46();

        lcr46.permute(new int[]{1, 2, 3}).forEach(System.out::println);


    }

}
