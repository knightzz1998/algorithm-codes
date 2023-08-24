package cn.knightzz.chapter03;

/**
 * @author 王天赐
 * @title: LCR28
 * @description: 28.找出字符串中第一个匹配项的下标
 * @create: 2023-08-20 09:31
 */
public class LCR28 {

    public int strStr(String haystack, String needle) {

        if (needle.length() > haystack.length()) {
            return -1;
        }

        for (int i = 0; i < haystack.length(); i++) {

            if (haystack.charAt(i) == needle.charAt(0)) {

                // 情况分为两种, 一种是前面匹配, 后面 haystack 到结尾了
                int idx1 = i + 1;
                int idx2 = 1;
                boolean flag = true;

                while (idx1 < haystack.length() && idx2 < needle.length()) {
                    if(haystack.charAt(idx1) != needle.charAt(idx2)) {
                        flag = false;
                        break;
                    }
                    idx1++;
                    idx2++;
                }
                if(flag && idx2 == needle.length()) {
                    return i;
                }
            }
        }
        return -1;
    }

}
