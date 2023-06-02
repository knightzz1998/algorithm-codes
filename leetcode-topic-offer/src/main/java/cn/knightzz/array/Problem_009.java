package cn.knightzz.array;

/**
 * @author 王天赐
 * @title: Problem_009
 * @projectName algorithm-codes
 * @description: 剑指 Offer II 009. 乘积小于 K 的子数组
 * @website <a href="https://knightzz.cn/">https://knightzz.cn/</a>
 * @github <a href="https://github.com/knightzz1998">https://github.com/knightzz1998</a>
 * @create: 2023-06-01 11:38
 */
@SuppressWarnings("all")
public class Problem_009 {

    class Solution {
        public int numSubarrayProductLessThanK(int[] nums, int k) {

            // 滑动窗口 , 本题没有对结果的顺序或者重复性有要求
            int n = nums.length;
            int count = 0 ;
            int left = 0 ;
            int right = 0 ;
            int product = 1;

            while(right < n){
                product = product * nums[right];
                // 如果结果大于 k , 就缩小窗口
                while (left <= right && product >= k) {
                    product = product / nums[left];
                    left++;
                }
                // 窗口连续组数组个数
                if(left <= right) {
                    // 统计窗口内的元素的个数, 子数组在 right++ 时就已经统计上了
                    count += right - left + 1;
                }
                right++;
            }
            return count;
        }
    }
}
