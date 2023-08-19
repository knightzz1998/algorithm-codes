package cn.knightzz.chapter03;

/**
 * @author 王天赐
 * @title: LCR05
 * @description:
 * @create: 2023-08-19 11:26
 */
public class LCR05 {

    // https://leetcode.cn/problems/ti-huan-kong-ge-lcof/description/

    public String replaceSpace(String s) {

        if (s.length() == 0) {
            return s;
        }
        StringBuilder res = new StringBuilder();
        String replaceStr = "%20";
        for (int i = 0; i < s.length(); i++) {

            // 判断每个字符是否为 空字符
            if(s.charAt(i) == ' ') {
                res.append(replaceStr);
            }else{
                res.append(s.charAt(i));
            }
        }
        return res.toString();
    }
}
