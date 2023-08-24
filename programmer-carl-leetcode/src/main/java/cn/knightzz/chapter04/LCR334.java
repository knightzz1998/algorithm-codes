package cn.knightzz.chapter04;

/**
 * @author 王天赐
 * @title: LCR334
 * @description: 334.反转字符串
 * @create: 2023-08-24 10:08
 */
public class LCR334 {

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
