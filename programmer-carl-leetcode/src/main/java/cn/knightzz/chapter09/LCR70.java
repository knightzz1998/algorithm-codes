package cn.knightzz.chapter09;

import java.util.LinkedList;
import java.util.List;

/**
 * @author 王天赐
 * @title: LCR70
 * @description: 70.爬楼梯
 * @create: 2023-09-07 09:30
 */
public class LCR70 {

    // 1. 所有的dp都可以用搜索完成, 只不过是在搜索的过程中去掉了重复计算的过程


    int count = 0;

    /**
     * @param n 台阶的个数
     * @return n个台阶有多少种方法到达楼顶
     */
    public int backtrack(int n) {

        // 已经到顶了, 没办法爬了
        if (n <= 1) {
            // 这里为什么是等于1, 因为如果是 n = 1的话只有一种方法
            return 1;
        }

        // 选择爬1个台阶
        int r1 = backtrack(n - 1);

        // 选择爬两个台阶
        int r2 = backtrack(n - 2);

        return r1 + r2;
    }


    LinkedList<String> res = new LinkedList<>();
    LinkedList<Integer> track = new LinkedList<>();

    /**
     * @param n 台阶的个数
     * @return n个台阶有多少种方法到达楼顶
     */
    public int backtrack2(int n) {

        // 这里只要是小于 2 的就不向下走了, 所以永远不会出现负数
        // 已经到顶了, 没办法爬了
        if (n <= 2) {
            return n;
        }

        if(memo[n] > 0){
            return memo[n];
        }

        // 前序位置 : 记录走过的路径
        // 选择爬1个台阶
        int r1 = backtrack2(n - 1);
        // 选择爬两个台阶
        int r2 = backtrack2(n - 2);

        memo[n] = r1 + r2;

        return memo[n];
    }

    int[] memo = new int[4];
    public static void main(String[] args) {

        LCR70 lcr70 = new LCR70();
        int res = lcr70.backtrack2(3);
        System.out.println("res = " + res);
    }

}
