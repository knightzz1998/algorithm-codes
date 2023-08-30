package cn.knightzz.chapter07;

import java.util.LinkedList;
import java.util.List;

/**
 * @author 王天赐
 * @title: LCR77
 * @description: 77.组合
 * @create: 2023-08-30 10:44
 */
public class LCR77 {

    // https://leetcode.cn/problems/combinations/


    List<List<Integer>> res = new LinkedList<>();

    public List<List<Integer>> combine(int n, int k) {
        backtrack(n, k, new LinkedList<>());
        return res;
    }

    public void backtrack(int n, int k, LinkedList<Integer> track) {

        // 边界条件(叶子节点)
        if (track.size() == k) {
            res.add(new LinkedList<Integer>(track));
            return;
        }

        // 选择
        for (int i = n; i >= 1; i--) {

            // 优化 : 如果for循环选择的起始位置之后的元素个数 已经不足 我们需要的元素个数了，那么就没有必要搜索了。
            if(track.size() + n < k) {
                return;
            }

            // 排除不合法的选项
            if(track.contains(i)) {
                continue;
            }
            // 选择当前值
            track.addLast(i);
            // 进入下一层, 只能选择当前值前面的
            backtrack(i - 1, k, track);
            // 撤销当前的选择
            track.removeLast();
        }
    }



    public static void main(String[] args) {

        LCR77 lcr77 = new LCR77();
        List<List<Integer>> combine = lcr77.combine(4, 2);
        combine.forEach(System.out::println);

    }
}
