package cn.knightzz.dynamic.programming.leetcode;

/**
 * @author 王天赐
 * @title: LeetCode_516_PalindromeSubsequence
 * @projectName algorithm-codes
 * @description: 516.最长回文子序列
 * @website <a href="http://knightzz.cn/">http://knightzz.cn/</a>
 * @github <a href="https://github.com/knightzz1998">https://github.com/knightzz1998</a>
 * @create: 2023-02-20 22:21
 */
@SuppressWarnings("all")
public class LeetCode_516_PalindromeSubsequence {

    public static int longestPalindromeSubseq2(String s) {
        return process1(s.toCharArray(), 0, s.length() - 1);
    }


    /**
     * 返回 下标在 [L,R] 范围内的字符串的最长回文子串的长度
     *
     * @param strs 字符数组
     * @param L    起始下标
     * @param R    结束下标
     * @return
     */
    public static int process1(char[] strs, int L, int R) {

        if (L == R) {
            return 1;
        }
        if (L == R - 1) {
            return strs[L] == strs[R] ? 2 : 1;
        }

        // 范围尝试模型重点是 讨论开头 xx 和结尾如何如何 xxx
        // 情况1 : 最长回文子序列即不 以 L开头, 也不以 R结尾 ==> a12321b
        int p1 = process1(strs, L + 1, R - 1);
        // 情况2 : 最长回文子序列 以 L开头, 不以 R结尾 ==> 12321b
        int p2 = process1(strs, L, R - 1);
        // 情况3 : 最长回文子序列 不以 L开头, 以 R结尾 ==> a12321
        int p3 = process1(strs, L + 1, R);

        // 情况4 既包含 L 也包含 R
        int p4 = strs[L] != strs[R] ? 0 : 2 + process1(strs, L + 1, R - 1);


        return Math.max(Math.max(p1, p2), Math.max(p3, p4));
    }


    public static int longestPalindromeSubseq(String s) {

        if (s == null || s.length() == 0) {
            return 0;
        }

        char[] strs = s.toCharArray();
        int N = strs.length;

        // L ~ 0 - N - 1
        // R - 0 - N - 1
        int[][] dp = new int[N][N];

        // 对角线上 L = R 都是1
        for (int L = 0; L < N; L++) {
            // R = 0, 说明只有一个值, 因为 R 是结尾
            dp[L][L] = 1;
        }
        // 对角线上面 L = R - 1 时, L = R => 2 否则 1
        for (int L = 0; L < N - 1; L++) {
            dp[L][L + 1] = strs[L] == strs[L + 1] ? 2 : 1;
        }

        // R > L 部分是不合法的, 所以只计算上半部分

        // N - 1 和 N -2 已经计算过了
        for (int L = N - 3; L >= 0; L--) {
            for (int R = L + 2; R < N; R++) {

                // p1 是可以被优化掉的
                // 因为 p2 p3 p4 的范围都比p1要大!
                // 所以 p2, p3, p4 > p1
                //int p1 = dp[L + 1][R - 1];
                //int p2 = dp[L + 1][R];
                //int p3 = dp[L][R - 1];
                //int p4 = strs[L] != strs[R] ? 0 : (2 + dp[L + 1][R - 1]);
                //dp[L][R] = Math.max(Math.max(p1, p2), Math.max(p3, p4));

                dp[L][R] = Math.max(dp[L + 1][R], dp[L][R - 1]);
                // 如果满足第四种
                if (strs[L] == strs[R]) {
                    dp[L][R] = Math.max(dp[L][R], (2 + dp[L + 1][R - 1]));
                }

            }
        }

        return dp[0][N - 1];
    }


}
