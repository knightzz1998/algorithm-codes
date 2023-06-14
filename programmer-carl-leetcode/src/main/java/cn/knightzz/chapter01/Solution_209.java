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

    public int minSubArrayLen2(int target, int[] nums) {

        int N = nums.length;
        int[] sum = new int[N + 1];
        sum[0] = nums[0];
        int res = Integer.MAX_VALUE;
        // key 是前缀和, value 是 数组下标
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
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

    class Solution {
        public int minSubArrayLen(int target, int[] nums) {

            // 滑动窗口
            int N = nums.length;
            int left = 0, right = 0;
            // 初始化长度和 count
            int len = 1;
            int count = nums[right];
            int res = Integer.MAX_VALUE;

            while (right < N && left <= right) {

                // 初始状态 right 已经累加过了
                if (count >= target) {
                    // 记录长度
                    res = Math.min(res, len);
                    // 缩小窗口
                    count = count - nums[left];
                    left++;
                    len--;
                } else {
                    // 扩大出窗口
                    right++;
                    if (right < N) {
                        count = count + nums[right];
                        len++;
                    }
                }
            }

            return res == Integer.MAX_VALUE ? 0 : res;
        }
    }
}
