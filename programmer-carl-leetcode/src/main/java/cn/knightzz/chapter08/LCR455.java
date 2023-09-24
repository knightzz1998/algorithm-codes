package cn.knightzz.chapter08;

import cn.knightzz.chapter07.LCR47;

import java.util.Arrays;

/**
 * @author 王天赐
 * @title: LCR455
 * @description: 455.分发饼干
 * @create: 2023-09-05 14:29
 */
public class LCR455 {

    public int findContentChildren(int[] g, int[] s) {

        // 先对胃口和饼干数量排序 , 你的目标是尽可能满足越多数量的孩子(每个孩子只能分到一块),并输出这个最大数值
        // 我的想法 : 因为要尽可能的满足, 所以可以先满足小的孩子, 然后满足胃口大的孩子

        Arrays.sort(g);
        Arrays.sort(s);

        int start = 0;
        for (int i = 0; i < s.length && start < g.length; i++) {
            if (s[i] >= g[start]) {
                start++;
            }
        }

        return start;
    }

    public static void main(String[] args) {

        LCR455 lcr455 = new LCR455();
        int contentChildren = lcr455.findContentChildren(new int[]{1, 2}, new int[]{1, 2, 3});

        System.out.println("contentChildren = " + contentChildren);

    }
}
