package cn.knightzz.dynamic.programming.leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * @author 王天赐
 * @title: LeetCode_474_OnesAndZeroes
 * @projectName algorithm-codes
 * @description: 474. 一和零 https://leetcode.cn/problems/ones-and-zeroes/
 * @website <a href="http://knightzz.cn/">http://knightzz.cn/</a>
 * @github <a href="https://github.com/knightzz1998">https://github.com/knightzz1998</a>
 * @create: 2023-02-19 20:31
 */
@SuppressWarnings("all")
public class LeetCode_474_OnesAndZeroes {


    static LinkedList<String> track = new LinkedList<>();
    static List<LinkedList<String>> result = new LinkedList<>();

    public static void dfs(String[] strs, int start, int m, int n) {

        // 判断是否满足条件

        boolean valid = isValid(track, m, n);

        if (valid) {
            result.add(new LinkedList<>(track));
        }

        // 选择第 i 个
        // 每一次都是从剩下的列表中选择, start 固定选择的范围
        // => [10, 0001, 111001, 1, 0] 选择
        // => [0001, 111001, 1, 0]
        // => [111001, 1, 0]
        for (int i = start; i < strs.length; i++) {
            // 选择第 i 个元素
            track.addLast(strs[i]);
            // 进入下一层, 做选择
            dfs(strs, i + 1, m, n);
            // 撤销选择
            track.removeLast();
        }
    }

    private static boolean isValid(LinkedList<String> track, int zeroMax, int oneMax) {
        int m = 0;
        int n = 0;
        for (String str : track) {
            char[] chars = str.toCharArray();
            for (char ch : chars) {
                switch (ch) {
                    case '0':
                        m++;
                        break;
                    case '1':
                        n++;
                        break;
                }
            }
        }

        return m <= zeroMax && n <= oneMax;
    }

    // =====================================================================================

    /*
        题目 : 给你一个二进制字符串数组 strs 和两个整数 m 和 n 。
        请你找出并返回 strs 的最大子集的长度，该子集中 最多 有 m 个 0 和 n 个 1


        理解题意 :
        1. 找到 strs 中所有的子集
        2. 筛选, 子集要符合条件, 条件就是 子集中的字符串中的 nums(1) <= n & nums(0) <= m
        3. 在满足条件的子集中找到长度最大的子集
     */


    public int process(String[] strs, int index) {

        // 选择
        process(strs, index + 1);
        // 不选
        process(strs, index + 1);


        return 0;
    }


    public static void process02(String[] strs, int m, int n) {

        // 背包问题 : 物品 ["10", "0001", "111001", "1", "0"]
        // 价值 :
        // dp[i] 前 i 个物品中, 满足 m, n 的最大子集长度
        int len = strs.length;
        int[] dp = new int[len];

        // dp[i] = ?
        // 选择 第 i 个 : maxLen = max{选择, 不选}
        // 不能选 , 无法装入的情况下 maxLen = dp[i-1]

    }


    public static void main(String[] args) {

        //String[] strs = {"10", "0001", "111001", "1", "0"};
        //int m = 5;
        //int n = 3;

        String[] strs = {"10", "0", "1"};
        int m = 1;
        int n = 1;

        dfs(strs, 0, m, n);

        int maxLen = 0;

        for (List<String> track : result) {
            maxLen = Math.max(track.size(), maxLen);
        }

        result.forEach((set) -> {
            System.out.println(set);
        });

        System.out.println("=============> max len : " + maxLen);

    }

}
