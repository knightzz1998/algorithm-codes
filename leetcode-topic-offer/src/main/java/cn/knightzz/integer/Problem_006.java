package cn.knightzz.integer;

import java.util.HashMap;

/**
 * @author 王天赐
 * @title: Problem_006
 * @projectName algorithm-codes
 * @description: 剑指 Offer II 006. 排序数组中两个数字之和
 * @website <a href="https://knightzz.cn/">https://knightzz.cn/</a>
 * @github <a href="https://github.com/knightzz1998">https://github.com/knightzz1998</a>
 * @create: 2023-05-31 19:57
 */
@SuppressWarnings("all")
public class Problem_006 {

    public int[] twoSum(int[] numbers, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int[] result = new int[2];
        for (int i = 0; i < numbers.length; i++) {
            map.put(numbers[i], i);
        }
        for (int index = numbers.length - 1; index >= 0; index--) {
            // 比 target 大的值就不需要再比较了
            if (target < numbers[index]) {
                continue;
            }
            int key = target - numbers[index];
            if (map.containsKey(key) && index != map.get(key) ) {

                result[0] = Math.min(index, map.get(key));
                result[1] = Math.max(index, map.get(key));
                return result;
            }
        }
        return result;
    }
}
