package cn.knightzz.chapter01;

import java.util.Arrays;

/**
 * @author 王天赐
 * @title: Solution_27
 * @projectName algorithm-codes
 * @description: 27.移除元素
 * @website <a href="https://knightzz.cn/">https://knightzz.cn/</a>
 * @github <a href="https://github.com/knightzz1998">https://github.com/knightzz1998</a>
 * @create: 2023-06-13 19:21
 */
@SuppressWarnings("all")
public class Solution_27 {


    public static void main(String[] args) {

        Solution solution = new Solution_27().new Solution();
        int[] nums = new int[]{3, 2, 2, 3};
        int i = solution.removeElement(nums, 3);
        System.out.println(i);
        System.out.println(Arrays.toString(nums));
    }

    class Solution {
        public int removeElement(int[] nums, int val) {

            int N = nums.length;
            int slow = 0;
            for (int fast = 0; fast < N; fast++) {
                // 一旦发现 非 val 的就将其覆盖掉
                if (val != nums[fast]) {
                    nums[slow] = nums[fast];
                    slow++;
                }
            }
            return slow;
        }
    }
}
