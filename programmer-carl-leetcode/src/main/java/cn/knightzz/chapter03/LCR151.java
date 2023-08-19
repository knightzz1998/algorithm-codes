package cn.knightzz.chapter03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author 王天赐
 * @title: LCR151
 * @description: 151.反转字符串中的单词
 * @create: 2023-08-19 14:05
 */
public class LCR151 {

    // https://leetcode.cn/problems/reverse-words-in-a-string/

    // 核心点 : 找到所有的单词

    public static void main(String[] args) {

        LCR151 lcr151 = new LCR151();

        lcr151.reverseWords("a good   example");

    }

    public String reverseWords(String s) {
        // 解析所有的单词
        String[] strings = s.trim().split(" ");
        List<String> list = Arrays.asList(strings);
        return handler(list);
    }

    public String handler(List<String> stringList) {

        if (stringList.size() == 0) {
            return "";
        }

        StringBuilder res = new StringBuilder();

        for (int i = stringList.size() - 1; i >= 0; i--) {
            String s = stringList.get(i);
            if (s.length() == 0 || s.startsWith(" "))
                continue;
            res.append(s);
            res.append(" ");
        }
        return res.toString().trim();
    }
}
