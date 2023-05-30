package cn.knightzz.integer;

import java.util.Scanner;

/**
 * @author 王天赐
 * @title: Problem_002
 * @projectName algorithm-codes
 * @description: 剑指 Offer II 002. 二进制加法
 * @website <a href="https://knightzz.cn/">https://knightzz.cn/</a>
 * @github <a href="https://github.com/knightzz1998">https://github.com/knightzz1998</a>
 * @create: 2023-05-29 21:58
 */
@SuppressWarnings("all")
public class Problem_002 {

    public static void main(String[] args) {

        Problem_002 problem002 = new Problem_002();

        Scanner scanner = new Scanner(System.in);
        String a = scanner.nextLine();
        String b = scanner.nextLine();
        System.out.println("result => " + problem002.addBinary(a, b));
    }

    public String addBinary(String a, String b) {

        // 判断长度
        if(a.length() != b.length()) {


            if(a.length() > b.length()) {
                // b 补零
                StringBuilder stringBuilder = new StringBuilder(b);
                for(int i = 0 ; i < a.length() - b.length(); i++) {
                    stringBuilder.insert(0, "0");
                }
                b = stringBuilder.toString();
            }else{
                StringBuilder stringBuilder = new StringBuilder(a);
                for(int i = 0 ; i < b.length() - a.length(); i++) {
                    stringBuilder.insert(0, "0");
                }
                a = stringBuilder.toString();
            }
        }


        StringBuilder sb = new StringBuilder();

        char[] left = a.toCharArray();
        char[] right = b.toCharArray();


        // 1111 + 1100
        // 1 , 1
        // 1 , 0
        // 0 , 0

        boolean flag = false;
        for (int i = left.length - 1; i >= 0; i--) {

            if (left[i] == right[i]) {

                // 都是 0
                if (left[i] == '0') {
                    // flag = true上一位有进位
                    sb.append(flag ? "1" : "0");
                    // 重置
                    flag = false;
                }
                // 都是 1
                if (left[i] == '1') {
                    // 查看上一位的情况
                    sb.append(flag ? "1" : "0");
                    // 当前位进一位
                    flag = true;
                }
            } else {
                // 1001
                // 1011
                // 不相等 , 0 , 1
                // 需要查看上一位的情况
                sb.append(flag ? "0" : "1");
            }
        }

        if(flag) {
            sb.append("1");
        }

        return sb.reverse().toString();
    }
}
