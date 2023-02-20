package cn.knightzz.dynamic.programming.leetcode;

/**
 * @author 王天赐
 * @title: Leetcode_1143_LongestCommonSubsequence
 * @projectName algorithm-codes
 * @description: 1143.最长公共子序列
 * @website <a href="http://knightzz.cn/">http://knightzz.cn/</a>
 * @github <a href="https://github.com/knightzz1998">https://github.com/knightzz1998</a>
 * @create: 2023-02-19 21:42
 */
@SuppressWarnings("all")
public class Leetcode_1143_LongestCommonSubsequence {

    // leetcode https://leetcode.com/problems/longest-common-subsequence/

    public static int longestCommonSubsequence2(String text1, String text2) {

        if (text1 == null || text2 == null || text1.length() == 0 || text2.length() == 0) {
            return 0;
        }

        char[] ch01 = text1.toCharArray();
        char[] ch02 = text2.toCharArray();

        return process1(ch01, ch02, ch01.length - 1, ch02.length - 1);
    }

    private static int process1(char[] ch01, char[] ch02, int i, int j) {

        // 前3种情况 :
        // ch01 = "a" ch02 = "b"
        // ch01 = "a1231" ch02 = "b"
        // ch01 = "a" ch02 = "b123a2"

        if (i == 0 && j == 0) {
            return ch01[i] == ch02[j] ? 1 : 0;
        } else if (i == 0) {
            if (ch01[i] == ch02[j]) {
                return 1;
            } else {
                return process1(ch01, ch02, i, j - 1);
            }
        } else if (j == 0) {
            // ch02 只有一个
            if (ch01[i] == ch02[j]) {
                return 1;
            } else {
                return process1(ch01, ch02, i - 1, j);
            }
        } else {

            // 第一种是 左边的不等于 ab123c 和 cf123d
            int p1 = process1(ch01, ch02, i - 1, j);
            // 不考虑右边的
            int p2 = process1(ch01, ch02, i, j - 1);
            // 左右都不考虑
            int p3 = ch01[i] == ch02[j] ? (1 + process1(ch01, ch02, i - 1, j - 1)) : 0;

            return Math.max(p1, Math.max(p2, p3));
        }
    }


    public static int longestCommonSubsequence(String text1, String text2) {

        // 一个样本做行, 一个样本做列, 一般是以结尾作为对应

        if (text1 == null || text2 == null || text1.length() == 0 || text2.length() == 0) {
            return 0;
        }

        char[] ch01 = text1.toCharArray();
        char[] ch02 = text2.toCharArray();

        // i 的所有取值 0 ~ ch01.length - 1
        // j 的所有取值 0 ~ ch02.length - 1
        // i 和 j 的所有可能性就构成了一个矩阵 dp[i][j] 表示 以 i 和 j 结尾的字符串的最长公共子集的长度

        // 初始化
        // 假设 i =0 , 那么, 如果 ch01[i] == ch[j] 想等的话, 那么对应的最长公共子集的长度就是 1
        // 因为 ch01 就一个 元素
        // 假设 j =0, 同理
        int M = ch01.length ;
        int N = ch02.length ;

        int[][] dp = new int[M][N];

        // 初始值
        dp[0][0] = ch01[0] == ch02[0] ? 1 : 0;

        for (int j = 1; j < N; j++) {
            // 因为是子集的原因, 所以, ch01[0] 即使 != ch02[j] , 也有可能前面的相等, 所以 以 j 结尾的数组的公共子序列依然可能是1
            // 比如 a 和 ba123
            // 假设 a 和 2 对比, 二者不等, 但是 以2结尾的子集的公共序列依然是1
            dp[0][j] = ch01[0] == ch02[j] ? 1 : dp[0][j - 1];
        }

        for (int i = 1; i < M; i++) {
            dp[i][0] = ch01[i] == ch02[0] ? 1 : dp[i - 1][0];
        }

        for (int i = 1; i < M; i++) {
            for (int j = 1; j < N; j++) {
                int p1 = dp[i - 1][j];
                int p2 = dp[i][j - 1];
                int p3 = ch01[i] == ch02[j] ? (1 + dp[i - 1][j - 1]) : 0;
                dp[i][j] = Math.max(p1, Math.max(p2, p3));
            }
        }

        return dp[M - 1][N - 1];
    }

}

