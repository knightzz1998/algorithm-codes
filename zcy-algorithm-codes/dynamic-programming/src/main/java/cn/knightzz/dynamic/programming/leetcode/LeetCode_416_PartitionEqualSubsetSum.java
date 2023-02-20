package cn.knightzz.dynamic.programming.leetcode;

/**
 * @author 王天赐
 * @title: LeetCode_416_PartitionEqualSubsetSum
 * @projectName algorithm-codes
 * @description: 416. 分割等和子集
 * @website <a href="http://knightzz.cn/">http://knightzz.cn/</a>
 * @github <a href="https://github.com/knightzz1998">https://github.com/knightzz1998</a>
 * @create: 2023-02-18 21:07
 */
@SuppressWarnings("all")
public class LeetCode_416_PartitionEqualSubsetSum {

    /**
     * 基于动态规划的方式
     *
     * @param nums
     * @return
     */
    public static boolean way01(int[] nums) {

        int sum = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
        }

        if (sum % 2 != 0) {
            return false;
        }

        sum = sum / 2;

        // sum = 背包容量
        // dp[i][j] 的含义 : 前 i 个nums元素中选择一部分元素是否可以将 容量为 sum 的背包装满
        boolean[][] dp = new boolean[n + 1][sum + 1];

        // base case : 初始化 当背包容量为 0 时, 那么我们不用选择任何元素, 都可以把背包装满
        for (int i = 0; i <= n; i++) {
            dp[i][0] = true;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= sum; j++) {

                // 剩余的空间无法放下
                // 背包内剩余的容量 : j - nums[i-1]

                if (j - nums[i - 1] < 0) {
                    // 无法装下的情况下, 说明当前dp的值, 等于上一个dp的值
                    // 表示 前i个物品是否选出能正好装满容量为j的背包
                    dp[i][j] = dp[i - 1][j];
                } else {
                    // 表示可以把第 i 个物品装入
                    // 不装 :
                    boolean case01 = dp[i - 1][j - nums[i - 1]];
                    // 装
                    boolean case02 = dp[i - 1][j];
                }
            }
        }

        return dp[n][sum];
    }


    public static boolean way02(int[] nums) {

        int sum = 0;
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            sum += nums[i];
        }

        if (sum % 2 != 0) {
            return false;
        }

        sum = sum / 2;
        boolean[][] dp = new boolean[n][sum];

        return dfs(nums, 0, 0, n, sum);
    }

    private static boolean dfs(int[] nums, int index, int current, int len, int target) {

        if (current == target) {
            return true;
        }

        // 遍历了所有的nums中的元素都没有找到合适的分配方案
        if (index == len) {
            return false;
        }

        // 如果存储已经计算过的值
        // index 和 current 这两个值是一直在变化的
        // dp[i][j] 表示 index = i 时, current = j 时, 是否存在满足target的方案

        // 选择 nums[i] 并考虑下一个数字nums[i+1]
        boolean result01 = dfs(nums, index + 1, current + nums[index], len, target);

        // 不选择nums[i] 并考虑下一个数字nums[i+1]
        boolean result02 = dfs(nums, index + 1, current, len, target);

        // 只要二者有一个是ok的, 说明就有结果
        return result01 || result02;
    }

    public static void test() {

        int[] nums = new int[]{1, 5, 11, 5, 1};

        boolean result = way02(nums);
        System.out.println("result " + result);

    }


    public static void main(String[] args) {
        test();
    }

}
