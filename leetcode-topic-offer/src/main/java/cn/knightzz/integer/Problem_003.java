package cn.knightzz.integer;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author 王天赐
 * @title: Problem_003
 * @projectName algorithm-codes
 * @description: 剑指 Offer II 003. 前 n 个数字二进制中 1 的个数
 * @website <a href="https://knightzz.cn/">https://knightzz.cn/</a>
 * @github <a href="https://github.com/knightzz1998">https://github.com/knightzz1998</a>
 * @create: 2023-05-30 20:14
 */
@SuppressWarnings("all")
public class Problem_003 {


    public static void main(String[] args) {

        Problem_003 problem003 = new Problem_003();

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] ints = problem003.countBits(n);

        System.out.println(Arrays.toString(ints));
    }


    public int[] countBits(int n) {

        int[] dp = new int[n + 1];

        Arrays.fill(dp, -1);

        int[] result = new int[n + 1];
        result[0] = 0;
        for (int i = 1; i <= n; i++) {

            // 转换为二进制 5 = 101
            // 5 % 2 ~ 1
            // 2 % 2 ~ 0
            // 1 % 2 ~ 1
            int val = i;
            int count = 0;

            while (val > 0) {
                if (dp[val] != -1) {
                    count += dp[val];
                    break;
                }
                if (val % 2 != 0) {
                    count++;
                }
                val /= 2;
            }
            dp[i] = result[i] = count;
        }
        return result;
    }

}
