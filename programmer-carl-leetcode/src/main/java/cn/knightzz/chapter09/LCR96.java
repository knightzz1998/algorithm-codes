package cn.knightzz.chapter09;

/**
 * @author 王天赐
 * @title: LCR96
 * @description:
 * @create: 2023-09-25 11:37
 */
public class LCR96 {
    public int numTrees(int n) {
        return 1;
    }

    public int process1(int n) {
        return backtrack1(n);
    }

    // NOTE: count[n] = 左子树不同类型的数量 * 右子树不同类型的数量
    // 以 3 为根节电的二叉搜索树的数量 -
    public int backtrack1(int n) {
        if (n <= 1) {
            return 1;
        }

        int count = 0;
        for (int i = 1; i <= n; i++) {
            // 以 i 为根节点的树的个数
            int leftCount = backtrack1(i - 1);
            int rightCount = backtrack1(n - i);
            count += leftCount * rightCount;
        }
        return count;
    }

    public int process2(int n) {

        if(n <= 2) {
            return n;
        }
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= n; i++) {
            int count = 0;
            for(int j = 1 ; j <= i ; j++) {
                // j = 1 , 以 1 为根节点 = dp[0] + dp[2]
                // j = 2 , 以 2 为根节点 = dp[1] + dp[1]
                // j = 3 , 以 3 为根节点 = dp[2] + dp[0]
                count += dp[j - 1] * dp[i - j];
            }
            dp[i] = count;
        }
        return dp[n];
    }


    public static void main(String[] args) {

        LCR96 lcr96 = new LCR96();
        int res = lcr96.process2(3);
        System.out.println(res);
    }
}
