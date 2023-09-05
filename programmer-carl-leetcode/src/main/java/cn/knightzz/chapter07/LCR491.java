package cn.knightzz.chapter07;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * @author 王天赐
 * @title: LCR491
 * @description: 491.递增子序列
 * @create: 2023-09-02 11:52
 */
public class LCR491 {

    List<List<Integer>> res = new LinkedList<>();
    LinkedList<Integer> track = new LinkedList<>();

    public List<List<Integer>> findSubsequences(int[] nums) {
        backtrack(nums, 0);
        return res;
    }

    public void backtrack(int[] nums, int start) {

        if (track.size() >= 2) {
            res.add(new LinkedList<>(track));
        }

        // 当前层创建一个set
        HashSet<Integer> set = new HashSet<>();
        for (int i = start; i < nums.length; i++) {

            // 同一层 : 注意啊. 同一层和下一层的区别
            // 比如 [4,6,7,7] 同一层内 会出现 两个 [4,6,7]
            // 注意子序列和连续子序列的区别 :
            // 如果是连续子序列就不可能出现两个 [4,6,7]

            // 防止出现两个 [4,7] , 父元素4和 子数组[6,7,7] 匹配
            if (set.contains(nums[i])) {
                continue;
            }

            // 添加数据的时机
            if (judge(nums[i])) {
                track.add(nums[i]);
                // 将添加的元素存入到set
                set.add(nums[i]);
            } else {
                // 不递增了就跳过
                continue;
            }

            // 进入下一层
            backtrack(nums, i + 1);

            // 撤销添加
            track.removeLast();
        }
    }

    public boolean judge(int val) {

        // 判断结果集中是否有值
        if (track.size() == 0) {
            return true;
        }

        // 获取最新的元素
        Integer top = track.getLast();

        // 新的值要 >= top 才能添加, 如果不是只能返回false
        return val >= top;
    }
}
