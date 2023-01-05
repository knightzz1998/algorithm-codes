package leetcode.editor.cn;

import java.util.PriorityQueue;

/**
 * 295.数据流的中位数
 *
 * @author
 * @date 2023-01-01 20:57:33
 */
@SuppressWarnings("all")
public class FindMedianFromDataStream {
    public static void main(String[] args) {
        // 测试代码
        MedianFinder solution = new FindMedianFromDataStream().new MedianFinder();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class MedianFinder {

        // 记录小于中位数的数
        PriorityQueue<Integer> queueMin;
        // 记录大于中位数的数
        PriorityQueue<Integer> queueMax;

        public MedianFinder() {
            queueMax = new PriorityQueue<>();
            queueMin = new PriorityQueue<>((t1, t2) -> {
                return t2 - t1;
            });
        }

        public void addNum(int num) {
            // 大于中位值的数比小于中位值的数多, queueMax的最小值就是中位值
            if (queueMax.size() >= queueMin.size()) {
                // queueMax 最多比 queueMin多一个
                queueMax.offer(num);
                // 将queueMax的最小值添加到queueMin
                queueMin.offer(queueMax.poll());
            } else {
                queueMin.offer(num);
                // 这里因为小于中位值的数要比大于的多,
                // 要把最大值移动到queueMax, 让二者平衡
                queueMax.offer(queueMin.poll());
            }
        }

        public double findMedian() {
            if (queueMax.size() > queueMin.size()) {
                return queueMax.peek();
            } else if (queueMax.size() < queueMin.size()) {
                return queueMin.peek();
            }
            return (queueMax.peek() + queueMin.peek()) / 2.0;
        }
    }

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
    //leetcode submit region end(Prohibit modification and deletion)

}
