package cn.knightzz.dynamic.programming.leetcode;

/**
 * @author 王天赐
 * @title: LeetCode_1049_LastStoneWeightIi
 * @projectName algorithm-codes
 * @description: 1049. 最后一块石头的重量 II
 * @website <a href="https://knightzz.cn/">https://knightzz.cn/</a>
 * @github <a href="https://github.com/knightzz1998">https://github.com/knightzz1998</a>
 * @create: 2023-02-21 18:34
 */
@SuppressWarnings("all")
public class LeetCode_1049_LastStoneWeightII {

    // https://leetcode.cn/problems/last-stone-weight-ii/

    // 子串必须连续, 子序列不需要连续

    public static int lastStoneWeightII2(int[] stones) {

        int sum = 0;

        for (int stone : stones) {
            sum += stone;
        }

        int p1 = process1(stones, 0, sum / 2);
        int p2 = sum - p1;

        return p2 - p1;
    }

    // [2,7,4,1,8,1]
    // [1,7,4,1,8]
    // [7,4,1,7]
    // [4,1] => [3]

    // [1,1,2,4,7,8] => [1,1,2,4,1] => [1,1,2,3] => [1,1,1] => [1]
    // [1,1,2,4,7,8] => [1,2,4,7,7] => [2,4,7,6] => []


    /**
     * 返回最接近rest 但是不能超过 rest 的最接近的累加和是多少
     *
     * @param stones
     * @param i      从 i ~ N 可以随便选择
     * @param target
     * @return
     */
    public static int process1(int[] stones, int i, int rest) {

        if (i == stones.length) {
            return 0;
        } else {
            // 不选当前元素
            int p1 = process1(stones, i + 1, rest);
            int p2 = 0;
            if (rest - stones[i] >= 0) {
                // 保证剩下的充足
                p2 = stones[i] + process1(stones, i + 1, rest - stones[i]);
            }
            return Math.max(p1, p2);
        }
    }


    public static int lastStoneWeightII(int[] stones) {

        int sum = 0;

        for (int stone : stones) {
            sum += stone;
        }

        int N = stones.length;
        int T = sum / 2;

        // dp[i][j] = 表示 前 i ~ N 个数可以随便选择, 尽可能选择接近 j 的值
        // i: 0 ~ N
        // j: 0 ~ T
        int[][] dp = new int[N + 1][T + 1];

        // base case
        // i = N 时, 没有可选的元素, 所以, dp[i][N] = 0
        for (int j = 0; j <= T; j++) {
            dp[N][j] = 0;
        }

        for (int i = N - 1; i >= 0; i--) {
            for (int j = 0; j <= T; j++) {
                int p1 = dp[i + 1][j];
                int p2 = 0;
                if(j - stones[i] >= 0) {
                    p2 = stones[i] + dp[i + 1][j - stones[i]];
                }
                dp[i][j] = Math.max(p1, p2);
            }
        }

        int p1 = dp[0][T];
        int p2 = sum - p1;
        return Math.abs(p1 - p2);
    }


    public static void main(String[] args) {

        int[] stones = {2, 7, 4, 1, 8, 1};
        int result = lastStoneWeightII(stones);
        System.out.println(result);
    }
}
