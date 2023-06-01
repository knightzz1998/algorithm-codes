package cn.knightzz.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author 王天赐
 * @title: Problem_007
 * @projectName algorithm-codes
 * @description: 剑指 Offer II 007. 数组中和为 0 的三个数
 * @website <a href="https://knightzz.cn/">https://knightzz.cn/</a>
 * @github <a href="https://github.com/knightzz1998">https://github.com/knightzz1998</a>
 * @create: 2023-05-31 20:51
 */
@SuppressWarnings("all")
public class Problem_007 {
    class Solution {
        public List<List<Integer>> threeSum(int[] nums) {

            List<List<Integer>> result = new ArrayList<>();

            // 对数组排序
            //  [-1,0,1,2,-1,-4] => [-4,-1,-1,0,1,2]
            Arrays.sort(nums);

            for(int i = 0 ; i < nums.length ; i++) {

                // 定义两个指针, 当前值只能用当前值前面的, 类似于排列组合
                // 无序的数组
                int left = i + 1, right = nums.length - 1;
                int target = 0 - nums[i];
                while(right > left) {

                    int sum = nums[left] + nums[right];
                    int leftVal = nums[left];
                    int rightVal = nums[right];

                    //System.out.printf("%d , %d, %d \n" ,nums[i] ,  nums[left] , nums[right]);
                    if( sum > target) {
                        // 减小整体的值 , 并跳过所有重复的
                        while(right > left && nums[right] == rightVal)
                            right--;

                    }else if(sum < target) {
                        // 增大整体的值
                        while(right > left && nums[left] == leftVal)
                            left++;

                    }else {
                        List<Integer> data = new ArrayList<>();
                        data.add(nums[i]);
                        data.add(leftVal);
                        data.add(rightVal);
                        result.add(data);

                        // 跳过所有重复的值
                        while(right > left && nums[left] == leftVal)
                            left++;
                        while(right > left && nums[right] == rightVal)
                            right--;

                    }
                }
                int val = nums[i];
                // 跳过所有重复的
                while(i < nums.length - 1 && nums[i+1] == val)
                    i++;
            }
            return result;

        }
    }
/**

 [-1,0,1,2,-1,-4] target = 0

 这个问题可以退化成 找到 target = 0 - (-1) 的 两个数 从 [-1,0,1,2,-1,-4]

 */
}
