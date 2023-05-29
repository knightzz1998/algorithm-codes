package cn.knightzz.integer;

import java.util.Scanner;

/**
 * @author 王天赐
 * @title: Problem_001
 * @projectName algorithm-codes
 * @description:
 * @website <a href="https://knightzz.cn/">https://knightzz.cn/</a>
 * @github <a href="https://github.com/knightzz1998">https://github.com/knightzz1998</a>
 * @create: 2023-05-24 20:44
 */
@SuppressWarnings("all")
public class Problem_001 {

    public static void main(String[] args) {

        Problem_001 problem001 = new Problem_001();
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        System.out.printf("result = %d", problem001.divide(a, b));
    }

    public int divide(int a, int b) {

        // 处理特殊情况
        if (a == 0) {
            return 0;
        }
        // Overflow    Integer.MIN_VALUE <= a , b <= Integer.MAX_VALUE
        if (a == Integer.MIN_VALUE) {
            if (b == -1)
                return Integer.MAX_VALUE;
            if (b == 1)
                return Integer.MIN_VALUE;
        }

        if (b == Integer.MIN_VALUE) {
            return a == Integer.MIN_VALUE ? 1 : 0;
        }
        if (b == -1) {
            return -a;
        }

        // 符号
        boolean positive = (a > 0 && b > 0) || (a < 0 && b < 0);

        // 将所有的数字转换为负数 , 因为负数在家计算时不会出现溢出
        a = a < 0 ? a : -a;
        b = b < 0 ? b : -b;

        int result = 0;
        while (a <= b) {
            // 能进入循环 , 说明可以减, 那么 次数 + 1
            int count = 1;
            int div = b;
            // 注意, 这是负数,
            // 15 / 2 => -15 / -2 =>
            // -15 < -2
            // 逐步尝试最大能减多少
            while (a - div <= div) {
                // 如果能减, 那么 结果 翻倍
                count = count << 1;
                div = div << 1;
            }

            // 减不了了 , 逐步减小 div的值
            result += count;
            // 把之前减的减去
            a = a - div;
            // next : 再次循环重新计算
        }

        // 整体流程 :
        // 15 / 2 => -15 / -2
        // => 一步一步的尝试
        // -15 - (-2) = -13 <= -2 => count = 1
        // -15 - (-4) = -11 <= -4 => count = 2 (最大可以减的)
        // -15 - (-8) = -7  > -8 ==> xxx
        // result += count = 2
        // a = -15 - (-4) = -11

        // -11 < -2
        // 第二次循环
        // -11 - (-2) = -9 <= -2 => count = 1
        // -11 - (-4) = -7 <= -4 => count = 2
        // xxx
        // result = 2 + coount = 4
        // a = -11 - (-4) = -7
        // ... 以此类推

        // -7 - (-4) = -3 , count = 2 , result = 6
        // -3 - (-2) = -1 , count = 1 , result = 7
        // -1 > -2 => 跳出循环

        return positive ? result : -result;
    }

}
