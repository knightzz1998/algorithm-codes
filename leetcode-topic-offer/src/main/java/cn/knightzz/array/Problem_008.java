package cn.knightzz.array;

/**
 * @author 王天赐
 * @title: Problem_008
 * @projectName algorithm-codes
 * @description: 剑指 Offer II 008. 和大于等于 target 的最短子数组
 * @website <a href="https://knightzz.cn/">https://knightzz.cn/</a>
 * @github <a href="https://github.com/knightzz1998">https://github.com/knightzz1998</a>
 * @create: 2023-06-01 10:33
 */
@SuppressWarnings("all")
public class Problem_008 {

    public int minSubArrayLen(int target, int[] nums) {

        int n = nums.length;
        // 记录子数组长度
        int result = 0 ;
        int left = 0 , right = 0 ;
        int count = nums[left];
        while(left <= right) {
            //System.out.printf("count =  %d" , count);
            if(count >= target) {
                int len = right - left + 1;
                result = result == 0 ? len : Math.min(len, result);
                //System.out.printf("%d" , len);
                // left 向前移动
                count -= nums[left];
                left++;
                continue;
            }
            // 右边已经到边界了, 但是累加和仍小于目标值, 就没必要移动了
            if(count < target && right == n - 1) {
                break;
            }

            if(right < n - 1) {
                right++;
                count += nums[right];
            }
        }
        return result;
    }
}
