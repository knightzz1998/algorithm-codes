package cn.knightzz.backtrack;

import java.util.LinkedList;
import java.util.List;

/**
 * @author 王天赐
 * @title: LeetCode_77_Combinations
 * @projectName algorithm-codes
 * @description: 77. 组合
 * @website <a href="https://knightzz.cn/">https://knightzz.cn/</a>
 * @github <a href="https://github.com/knightzz1998">https://github.com/knightzz1998</a>
 * @create: 2023-03-01 14:40
 */
@SuppressWarnings("all")
public class LeetCode_77_Combinations {

    public List<List<Integer>> combine(int n, int k) {
        process(n, 1, k);
        return result;
    }


    List<List<Integer>> result = new LinkedList<>();
    LinkedList<Integer> track = new LinkedList<>();

    public void process(int n, int index, int k) {

        // index 是用来约束可选择的元素列表的
        // 假设 k = 3
        // index = 1 => 可选元素 : 1 ~ N
        // 下一层 index = 2 => 可选元素 2 ~ N
        // 最后一层 index = 3 => 3 ~ N
        // 因为是组合, 不是排列, 组合内的元素是不考虑顺讯的 [2,4] 和 [4,2] 算作一种组合
        // 我们使用 index 约束可选择的元素列表, 这样的话 当你选择了 [2,4] 就不会再出现 [4,2]

        //
        if (track.size() == k) {
            result.add(new LinkedList<>(track));
            return;
        }

        // 状态列表
        for (int i = index; i <= n; i++) {

            // 选择当前元素
            track.addLast(i);
            process(n, i + 1, k);
            // 撤销选择
            track.removeLast();
        }
    }

    public static void main(String[] args) {

        LeetCode_77_Combinations leetCode77Combinations = new LeetCode_77_Combinations();
        List<List<Integer>> result = leetCode77Combinations.combine(4, 2);

        result.forEach((item) -> {
            System.out.println(item);
        });

    }
}
