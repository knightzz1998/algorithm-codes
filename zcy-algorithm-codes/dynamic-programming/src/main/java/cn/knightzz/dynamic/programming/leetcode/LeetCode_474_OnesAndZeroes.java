package cn.knightzz.dynamic.programming.leetcode;

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


    public static int findMaxForm1(String[] strs, int m, int n) {

        int N = strs.length;
        Tuple[] tuples = new Tuple[N];
        for (int i = 0; i < N; i++) {
            char[] chars = strs[i].toCharArray();
            int zero = 0;
            int one = 0;
            for (char c : chars) {
                switch (c) {
                    case '0':
                        zero++;
                        break;
                    case '1':
                        one++;
                        break;
                }
            }
            tuples[i] = new Tuple(zero, one);
            System.out.println(tuples[i]);
        }

        return process1(tuples, 0, m, n);
    }

    // process 定义 有多少种满足 m 和 n 的子集的最大的个数
    public static int process1(Tuple[] tuples, int i, int m, int n) {

        if (i == tuples.length) {
            return 0;
        }

        // z 是 当前字符串中 0 的个数
        int z = tuples[i].zero;
        int o = tuples[i].one;

        int p1 = 0;
        // 选第i个字符串
        if (m - z >= 0 && n - o >= 0) {
            p1 = 1 + process1(tuples, i + 1, m - z, n - o);
        }
        // 不选第i个字符串
        int p2 = process1(tuples, i + 1, m, n);

        return Math.max(p1, p2);
    }


    public static int findMaxForm(String[] strs, int m, int n) {

        int N = strs.length;
        Tuple[] tuples = new Tuple[N];
        for (int i = 0; i < N; i++) {
            char[] chars = strs[i].toCharArray();
            int zero = 0;
            int one = 0;
            for (char c : chars) {
                switch (c) {
                    case '0':
                        zero++;
                        break;
                    case '1':
                        one++;
                        break;
                }
            }
            tuples[i] = new Tuple(zero, one);
            //System.out.println(tuples[i]);
        }

        int[][] dp = new int[m + 1][n + 1];

        dp[0][0] = 0;

        for (int k = 0; k < tuples.length; k++) {

            int zero = tuples[k].zero;
            int one = tuples[k].one;

            for (int i = m; i >= 0; i--) {
                for (int j = n; j >= 0; j--) {
                    int p1 = 0;
                    if(i >= zero && j >= one) {
                        p1 = dp[i - zero][j - one] + 1;
                    }
                    // 不选
                    int p2 = dp[i][j];
                    dp[i][j] = Math.max(p1, p2);
                }
            }
        }

        return dp[m][n];
    }

    public static void main(String[] args) {

        String[] strs = {"10", "0001", "111001", "1", "0"};
        int m = 5, n = 3;

        int res = findMaxForm(strs, m, n);
        System.out.println(res);

    }

}

class Tuple {
    public int zero = 0;
    public int one = 0;


    public Tuple(int zero, int one) {
        this.zero = zero;
        this.one = one;
    }

    @Override
    public String toString() {
        return "Tuple{" +
                "zero=" + zero +
                ", one=" + one +
                '}';
    }
}