package cn.knightzz.chapter07;

import java.util.LinkedList;
import java.util.List;

/**
 * @author 王天赐
 * @title: LCR216
 * @description: 216.组合综合III
 * @create: 2023-08-30 11:25
 */
public class LCR216 {


    List<List<Integer>> res = new LinkedList<>();

    public List<List<Integer>> combinationSum3(int k, int n) {
        backtrack(k, n, 1, new LinkedList<>(), 0);
        return res;
    }

    public void backtrack(int k, int n, int startIndex, LinkedList<Integer> track, int sum) {

        if (sum == n && track.size() == k) {
            res.add(new LinkedList<>(track));
            return;
        }

        for (int i = startIndex; i <= 9; i++) {

            // 剪枝
            if (9 - i + 1 + track.size() < k) {
                return;
            }

            track.addLast(i);
            sum += i;

            backtrack(k, n, i + 1, track, sum);
            // 撤销
            sum -= i;
            track.removeLast();
        }
    }

    public static void main(String[] args) {
        LCR216 lcr216 = new LCR216();
        List<List<Integer>> lists = lcr216.combinationSum3(3, 9);

        lists.forEach(System.out::println);
    }
}
