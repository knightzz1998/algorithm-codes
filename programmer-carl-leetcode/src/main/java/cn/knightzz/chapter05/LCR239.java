package cn.knightzz.chapter05;

import java.util.LinkedList;

/**
 * @author 王天赐
 * @title: LCR239
 * @description: 239. 滑动窗口最大值
 * @create: 2023-08-25 12:53
 */
public class LCR239 {

    public int[] maxSlidingWindow(int[] nums, int k) {

        // 1, 2, 3, 4, 5, 6, 7
        // nums.length - (k - 1)

        MyQueue queue = new MyQueue();

        for(int i = 0 ; i < k ; i++) {
            queue.push(nums[i]);
        }

        int len = nums.length - (k - 1);
        int[] res = new int[len];
        res[0] = queue.getMaxValue();
        int j = 1;
        for (int i = k; i < nums.length; i++) {
            queue.pop(nums[i - k]);
            queue.push(nums[i]);
            res[j] = queue.getMaxValue();
            j++;
        }
        return res;
    }
}

class MyQueue {

    LinkedList<Integer> queue;

    public MyQueue() {
        this.queue = new LinkedList<>();
    }

    public void push(int x) {

            // x > 队头元素, 就把队列中比 x 小的元素都删除, 因为这些元素没必要维护了, 滑动窗口向后移动也会把这些元素删除
            // 从大到小 , First = max , Last = min
            while (!queue.isEmpty() && queue.getLast() < x) {
                queue.removeLast();
            }
            queue.addLast(x);
    }

    public void pop(int x) {
        // 移除的时候判断移除是否是最大的元素
        if (!queue.isEmpty() && queue.getFirst() == x) {
            queue.removeFirst();
        }
    }

    public int getMaxValue() {
        return queue.getFirst();
    }

    public int size() {
        return queue.size();
    }
}