package cn.knightzz.array;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 王天赐
 * @title: Problem_010
 * @projectName algorithm-codes
 * @description: 剑指 Offer II 010. 和为 k 的子数组
 * @website <a href="https://knightzz.cn/">https://knightzz.cn/</a>
 * @github <a href="https://github.com/knightzz1998">https://github.com/knightzz1998</a>
 * @create: 2023-06-02 11:12
 */
@SuppressWarnings("all")
public class Problem_010 {

    public static void main(String[] args) {

        Solution solution = new Problem_010().new Solution();

        int[] nums = {1,2,3};
        int k = 3;

        int result = solution.subarraySum(nums, k);
        System.out.println("result = " + result);

    }

    class Solution {
        public int subarraySum(int[] nums, int k) {

            int N = nums.length;
            int res = 0;
            int[] prefixSum = new int[N];
            prefixSum[0] = nums[0];
            // 计算前缀和数组
            for (int i = 1; i < N; i++) {
                // 1, 2, 3, ... i - 1 , i , i + 1
                prefixSum[i] = prefixSum[i - 1] + nums[i];
            }

            Map<Integer, Integer> map = new HashMap<>();
            // key : 区间 [0,x] 的前缀和
            // value : 满足 prefixSum[x] - k = prefixSum[j] 这个条件的 prefixSum[j] 的个数
            map.put(0, 1); // 当前前缀和即等于 k => sum[i] - k == 0
            for (int i = 0; i < N; i++) {
                // i = 0 , 前缀和就是 [0,0] 的和
                // i = 1 , prefixSum 就是 [0,1] 的值的和
                // target 表示 i 的前缀和
                // prefixSum[i] - prefixSum[j] = [x, i] 的区间的和值
                int d = prefixSum[i] - k;

                // 假设 [j , i] 的与元素的和 == k => prefixSum[i] - prefixSum[j] == k => refixSum[i] - k == prefixSum[j]
                // 查查有没有满足 [0,j] = refixSum[i] - k 因此我们只需要找出存在几个这样的 sum[k], 使得 （sum[i] - k == sum[j]）
                // 为了统计 sum[j] 的个数，我们使用 HashMap 进行记录 ，其 key 为 sum[j] 的值，value 为值相同的 sum[j] 的个数
                // 每算出一个前缀和就去往前翻翻之前计算的前缀和里有没有等于新前缀和减去k的，如果有就代表区间存在，加到结果里
                res += map.getOrDefault(d, 0);
                // 统计在 i 之前相同的 sum[j] 的个数
                // 把当前的前缀和加进 map , 这样后面其他的前缀和查询时, 就有数据了
                map.put(prefixSum[i], map.getOrDefault(prefixSum[i], 0) + 1);
            }
            return res;
        }
    }
}
