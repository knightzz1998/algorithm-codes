package cn.knightzz.chapter05;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * @author 王天赐
 * @title: LCR347
 * @description: 347. 前 K 个高频元素
 * @create: 2023-08-25 13:45
 */
public class LCR347 {

    public int[] topKFrequent(int[] nums, int k) {

        HashMap<Integer, Integer> cacheMap = new HashMap<>();
        for (int num : nums) {
            cacheMap.put(num, cacheMap.getOrDefault(num, 0) + 1);
        }

        // 定义一个大顶堆, 从大到小排列
        PriorityQueue<Tuple> queue = new PriorityQueue<>((t1, t2) -> {
            return t2.count - t1.count;
        });

        for (Integer integer : cacheMap.keySet()) {
            queue.add(new Tuple(integer, cacheMap.get(integer)));
        }

        int[] res = new int[k];

        for (int i = 0; i < k; i++) {
            res[i] = queue.poll().value;
        }

        return res;
    }

}


class Tuple {

    int value;
    int count;

    public Tuple(int value, int count) {
        this.value = value;
        this.count = count;
    }

    public void increment() {
        this.count++;
    }
}
