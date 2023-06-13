package cn.knightzz.chapter01;

import java.util.Random;

/**
 * @author 王天赐
 * @title: Solution_704
 * @projectName algorithm-codes
 * @description: 704.二分查找
 * @website <a href="https://knightzz.cn/">https://knightzz.cn/</a>
 * @github <a href="https://github.com/knightzz1998">https://github.com/knightzz1998</a>
 * @create: 2023-06-13 18:40
 */
@SuppressWarnings("all")
public class Solution_704 {

    static Random random = new Random();
    static int count = 0;

    public static void main(String[] args) {

        int[] nums = new int[]{0, 1};
        Solution solution = new Solution_704().new Solution();
        int search = solution.search(nums, -1);
        for (int i = 0; i < 1000; i++) {
            judge();
        }

        System.out.println("count : " + count);
    }


    public static void judge() {

        // 随机生成对应数量的数组
        int N = random.nextInt(1000) + 1;
        int[] nums = new int[N];
        for (int i = 0; i < N; i++) {
            nums[i] = random.nextInt(100000);
        }

        // 判断
        int target = random.nextInt();
        int value1 = equalValue(nums, target);
        int value2 = search(nums, target);

        if (value1 != value2) {
            count++;
        }
    }

    /**
     * 对数器
     *
     * @param nums
     * @param target
     * @return
     */
    public static int equalValue(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target)
                return i;
        }
        return -1;
    }

    public static int search(int[] nums, int target) {

        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = (right + left) / 2;

            if (nums[mid] == target)
                return mid;

            if (target > nums[mid]) {
                // 左区间向右移动
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    class Solution {
        public int search(int[] nums, int target) {

            int left = 0;
            int right = nums.length - 1;

            while (left <= right) {
                int mid = (right + left) / 2;

                if (nums[mid] == target)
                    return mid;

                if (target > nums[mid]) {
                    // 左区间向右移动
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            return -1;
        }
    }
}

