package cn.knightzz.chapter01;

import java.util.HashMap;

/**
 * @author 王天赐
 * @title: Solution_209
 * @projectName algorithm-codes
 * @description: 209.长度最小的子数组
 * @website <a href="https://knightzz.cn/">https://knightzz.cn/</a>
 * @github <a href="https://github.com/knightzz1998">https://github.com/knightzz1998</a>
 * @create: 2023-06-13 20:39
 */
@SuppressWarnings("all")
public class Solution_209 {

    public static void main(String[] args) {

    }

    class Solution {
        public int minSubArrayLen(int target, int[] nums) {

            int N = nums.length;
            int[] sum = new int[N + 1];
            sum[0] = nums[0];
            int res = Integer.MAX_VALUE;
            // key 是前缀和, value 是 数组下标
            HashMap<Integer, Integer> map = new HashMap<>();
            map.put(0,-1);
            int preSum = 0;
            for (int i = 0; i < N; i++) {
                // j ~ i - 1
                preSum += nums[i];
                // 前缀和中是否有满足 preSum - target 的
                // 遍历map
                for (Integer key : map.keySet()) {
                    if (preSum - key >= target) {
                        // 获取key
                        int idx = map.get(key);
                        // 计算长度
                        res = Math.min(res, (i - idx));
                    }
                }
                // 将当前的前缀和存入map
                map.put(preSum, i);
            }
            return res == Integer.MAX_VALUE ? 0 : res;
        }
    }
}
