package cn.knightzz.chapter07;

import java.util.LinkedList;
import java.util.List;

/**
 * @author 王天赐
 * @title: LCR131
 * @description: 131. 分割回文串
 * @create: 2023-08-31 15:57
 */
public class LCR131 {


    // 1. 字符串 : "aab"
    // 2. 子串 : 只能当前的字符和后面的字符组合, 不能出现后面字符组前面的字符
    // 3. 回文串: 将当前字符串反转后和原字符串比较下既可

    List<List<String>> res = new LinkedList<>();

    public List<List<String>> partition(String s) {
        backtrack(s, 0, new LinkedList<>());
        return res;
    }

    public void backtrack(String s, int start, LinkedList<String> track) {


        // 分割方案的条件, 已经走到末尾了
        if (start >= s.length()) {
            res.add(new LinkedList<>(track));
            return;
        }

        // 选择列表
        for (int i = start; i < s.length(); i++) {

            if (judge(s, start, i)) {
                // 获取子串
                // substring : [start, end) ,
                String substring = s.substring(start, i + 1);
                track.add(substring);
            } else {
                continue;
            }

            backtrack(s, i + 1, track);

            // 弹出已经添加的子串
            // 这里注意 : 因为只有满足条件时才会添加子串, 不满足不会撤销
            track.removeLast();
        }
    }

    public boolean judge(String s, int start, int end) {

        while (start <= end) {
            if (s.charAt(start) != s.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }

        return true;
    }

    public static void main(String[] args) {

        LCR131 lcr131 = new LCR131();
        lcr131.partition("aab").forEach(System.out::println);
    }
}
