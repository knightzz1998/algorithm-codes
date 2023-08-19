package cn.knightzz.chapter03;

import java.util.Scanner;

/**
 * @author 王天赐
 * @title: LCR541
 * @description: 541.反转字符串II
 * @create: 2023-08-19 10:21
 */
public class LCR541 {


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        int k = scanner.nextInt();

        LCR541 lcr541 = new LCR541();
        System.out.println(lcr541.reverseStr(s, k));
    }

    public String reverseStr(String s, int k) {

        // 计算
        // 假设 k = 2 , 2k = 4 , len = 15 , 15 % 4 = 3
        // 此时 余数 3 > k , 就要反转前两个数 , 此时 start = 15 - 3 + 1 = 13 , end = start + k - 1;
        // 假设 k = 2 , 2k = 4 , len = 13 , 13 % 4 = 1
        // 此时 余数 1 < k , 全部反转 , 此时 start = 13 - 1 + 1 = 13 , end = s.length - 1;
        char[] chars = s.toCharArray();
        int N = s.length();
        // 余数
        int S = N % (2 * k);
        // 满足2k 部分的长度
        int len = N - S - 1;
        // 阶段1 : 反转满足 2k 的部分
        for (int left = 0, right = (2 * k - 1);
             right <= len;
             left += (2 * k), right += (2 * k)) {
            handler(chars, left, (left + k - 1));
        }

        // 阶段2 处理剩余部分
        int start = (N - S + 1) - 1;
        if (S <= k) {
            handler(chars, start, chars.length - 1);
        } else {
            handler(chars, start, start + k - 1);
        }
        return new String(chars);
    }

    /**
     * 反转指定范围的字符
     *
     * @param s     需要反转的字符数组
     * @param start 开始位置
     * @param end   结束位置
     */
    public void handler(char[] s, int start, int end) {
        while (end > start) {
            char t = s[start];
            s[start] = s[end];
            s[end] = t;
            start++;
            end--;
        }
    }
}
