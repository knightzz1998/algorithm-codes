package cn.knightzz.backtrack;

import java.util.LinkedList;
import java.util.List;

/**
 * @author 王天赐
 * @title: LeetCode_216_CombinationSumIII
 * @projectName algorithm-codes
 * @description: 216. 组合总和 III
 * @website <a href="https://knightzz.cn/">https://knightzz.cn/</a>
 * @github <a href="https://github.com/knightzz1998">https://github.com/knightzz1998</a>
 * @create: 2023-03-01 14:57
 */
@SuppressWarnings("all")
public class LeetCode_216_CombinationSumIII {

    public List<List<Integer>> combinationSum3(int k, int n) {
        process(k, n, 1, 0);
        return result;
    }

    List<List<Integer>> result = new LinkedList<>();
    LinkedList<Integer> track = new LinkedList<>();

    /**
     * 所有可能的有效组合的列表
     *
     * @param k     限制组合中数的长度
     * @param n     和
     * @param index 当前选择元素的下标
     * @param cur   当前已选择元素的值
     */
    public void process(int k, int n, int index, int cur) {

        //
        if (track.size() == k && cur == n) {
            result.add(new LinkedList<>(track));
            return;
        }

        for (int i = index; i <= 9; i++) {

            // 选择当前元素
            track.addLast(i);
            // 选择下一个位置的元素
            process(k, n, i + 1, cur + i);
            // 撤销选择
            track.removeLast();
        }
    }

    public static void main(String[] args) {

        int k = 3, n = 9;
        LeetCode_216_CombinationSumIII combinationSumIII = new LeetCode_216_CombinationSumIII();
        List<List<Integer>> lists = combinationSumIII.combinationSum3(k, n);
        lists.forEach((item) -> {
            System.out.println(item);
        });

    }
}
