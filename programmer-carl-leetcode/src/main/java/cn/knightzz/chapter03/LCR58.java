package cn.knightzz.chapter03;

/**
 * @author 王天赐
 * @title: LCR58
 * @description: 剑指 Offer 58 - II. 左旋转字符串
 * @create: 2023-08-19 14:33
 */
public class LCR58 {

    public static void main(String[] args) {
        LCR58 lcr58 = new LCR58();

        lcr58.reverseLeftWords("abcdefg", 2);

    }

    public String reverseLeftWords(String s, int n) {

        if (s.length() == 0 || s.length() == n) {
            return s;
        }

        char[] chars = s.toCharArray();
        char[] cache = new char[n];
        for (int i = 0; i < n; i++) {
            cache[i] = chars[i];
        }

        // 移动未反转的字符
        for (int i = n; i < chars.length; i++) {
            chars[i - n] = chars[i];
        }
        // chars.length - n = 向前移动 n 位
        for (int i = 0; i < n; i++) {
            chars[chars.length - n + i] = cache[i];
        }
        return new String(chars);
    }
}
