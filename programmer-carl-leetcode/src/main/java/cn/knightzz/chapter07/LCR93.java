package cn.knightzz.chapter07;

import java.util.LinkedList;
import java.util.List;

/**
 * @author 王天赐
 * @title: LCR93
 * @description: 93.复原IP地址
 * @create: 2023-08-31 17:42
 */
public class LCR93 {

    // 分割 :
    // 字符串 : 分割成4个部分, 每个部分转换成数字, 判断是否在 0~255之间

    List<String> res = new LinkedList<>();

    public List<String> restoreIpAddresses(String s) {
        backtrack(s, 0, 0, new LinkedList<>());
        return res;
    }

    public boolean judge(String s) {

        // 长度大于3的
        if (s.length() > 3) {
            return false;
        }

        if (s.equals("0")) {
            return true;
        }

        // 以 0 开头 且长度大于1 的 , 00 , 023
        if (s.startsWith("0") && s.length() > 1) {
            return false;
        }

        int v = Integer.parseInt(s);
        return v >= 0 && v <= 255;
    }

    public void backtrack(String s, int startIndex, int select, LinkedList<String> track) {

        if (track.size() == 4 && startIndex >= s.length()) {
            StringBuilder sb = new StringBuilder();
            track.forEach(str -> {
                sb.append(str);
                sb.append(".");
            });
            sb.deleteCharAt(sb.length() - 1);
            // System.out.println(sb.toString());
            res.add(sb.toString());
            return;
        }

        for (int j = startIndex; j < s.length(); j++) {

            // 剪枝
            if (track.size() > 4) {
                return;
            }
            // sub = [startIndex, j+1]

            String substr = s.substring(startIndex, j + 1);
            if (judge(substr)) {
                track.add(substr);
            } else {
                continue;
            }

            // 进入下一层
            backtrack(s, j + 1, select + 1, track);

            // 撤销
            track.removeLast();

        }
    }

    public static void main(String[] args) {

        LCR93 lcr93 = new LCR93();
        lcr93.restoreIpAddresses("101023").forEach(System.out::println);
        System.out.println("===============");
        lcr93.restoreIpAddresses("25525511135").forEach(System.out::println);
        System.out.println("=============");
        lcr93.restoreIpAddresses("010010").forEach(System.out::println);

    }
}
