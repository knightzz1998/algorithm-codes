package cn.knightzz.integer;

import java.util.Scanner;

/**
 * @author 王天赐
 * @title: Problem_004
 * @projectName algorithm-codes
 * @description:
 * @website <a href="https://knightzz.cn/">https://knightzz.cn/</a>
 * @github <a href="https://github.com/knightzz1998">https://github.com/knightzz1998</a>
 * @create: 2023-05-30 20:40
 */
public class Problem_004 {

    public static void main(String[] args) {
        Problem_004 problem004 = new Problem_004();
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }
        System.out.println(problem004.singleNumber(nums));
    }

    public int singleNumber(int[] nums) {

        int[] cur = new int[32];

        for (int x : nums) {
            for (int i = 0; i < 32; i++) {

                // 1 = 0001 , & 相同为0 , 不同为 1
                // 所以 如果最后一位是 1, 结果是 1, 如果不是1, 结果是 0
                if (((x >> i) & 1) == 1) {
                    // 位运算就是二进制运算
                    cur[i]++;
                }
            }
        }

        // 对结果求3的模
        int result = 0;
        for (int i = 0; i < cur.length; i++) {
            if (((cur[i] % 3) & 1) == 1) {
                // 1 << 2 => 001 -> 00100 = 2^2
                result += (1 << i);
            }
        }
        return result;
    }
}
