package cn.knightzz.chapter10.base;

/**
 * @author 王天赐
 * @title: Solution
 * @description:
 * @create: 2023-10-06 10:02
 */
public class Solution {

    // 背包最大重量为4。
    //
    // 物品0 1	15
    // 物品1 3	20
    // 物品2 4	30

    // 问背包能背的物品最大价值是多少？

    // NOTE : 暴力搜索法
    public int process1(int[] weight, int[] values, int maxWeight) {
        this.weight = weight;
        this.values = values;
        this.maxWeight = maxWeight;
        return backtrack1(0, 0, 0);
    }

    int[] weight;
    int[] values;
    int maxWeight;

    public int backtrack1(int index, int curValue, int curWeight) {

        // 终止条件
        if (index == weight.length) {
            return curValue;
        }

        // 选择 weight[index] , values[index]
        int r1 = 0;
        // 判断当前是否还能装
        if (curWeight + weight[index] <= maxWeight) {
            r1 = curValue + values[index] +
                    backtrack1(
                            index + 1, r1, curWeight + weight[index]);
        }
        // 不选
        int r2 = backtrack1(
                index + 1, curValue, curWeight);
        return Math.max(r1, r2);
    }


    public static void main(String[] args) {
        int[] weights = {1, 3, 4};
        int[] values = {15, 20, 30};
        Solution solution = new Solution();
        int res = solution.process1(weights, values, 4);

        System.out.println("res = " + res);
    }

}
