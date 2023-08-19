package cn.knightzz.chapter03;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author 王天赐
 * @title: LCR344
 * @description:
 * @create: 2023-08-19 09:40
 */
public class LCR344 {

    public static void main(String[] args) {

        LCR344 lcr344 = new LCR344();

        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        char[] s = str.toCharArray();
        lcr344.reverseString(s);
        System.out.println(Arrays.toString(s));
    }

    // 基本思路 : 双指针, 交换字符

    public void reverseString(char[] s) {

        if (s.length == 0 || s.length == 1) {
            return;
        }

        int left = 0;
        int right = s.length - 1;

        while (right > left) {
            // 交换
            char t = s[left];
            s[left] = s[right];
            s[right] = t;

            left++;
            right--;
        }
    }
}
