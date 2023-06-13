package cn.knightzz.chapter01;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author 王天赐
 * @title: Solution_977
 * @projectName algorithm-codes
 * @description: 977.有序数组的平方
 * @website <a href="https://knightzz.cn/">https://knightzz.cn/</a>
 * @github <a href="https://github.com/knightzz1998">https://github.com/knightzz1998</a>
 * @create: 2023-06-13 20:19
 */
@SuppressWarnings("all")
public class Solution_977 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int[] nums = new int[N];
        for (int i = 0; i < N; i++) {
            nums[i] = scanner.nextInt();
        }

        Solution solution = new Solution_977().new Solution();
        int[] ints = solution.sortedSquares(nums);
        System.out.println(Arrays.toString(ints));

    }

    class Solution {
        public int[] sortedSquares(int[] nums) {

            int N = nums.length;
            int left = 0, right = N - 1;
            int index = right;
            int[] res = new int[N];

            while (left <= right) {
                // 比较哪个平方后大
                int r1 = nums[left] * nums[left];
                int r2 = nums[right] * nums[right];

                if (left == right) {
                    res[index] = r1;
                    return res;
                }

                if (r1 > r2) {
                    res[index] = r1;
                    left++;
                } else {
                    res[index] = r2;
                    right--;
                }

                index--;
            }
            return res;
        }
    }
}
