package leetcode.editor.cn;

import sun.security.pkcs11.wrapper.CK_SSL3_KEY_MAT_OUT;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 239.滑动窗口最大值
 *
 * @author
 * @date 2022-12-30 17:11:11
 */
@SuppressWarnings("all")
public class SlidingWindowMaximum {
    public static void main(String[] args) {
        // 测试代码
        Solution solution = new SlidingWindowMaximum().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] maxSlidingWindow(int[] nums, int k) {

			int len = nums.length - k + 1;
			int[] ans = new int[len];

			ArrayList<Integer> ansList = new ArrayList<>();

			// 默认是实现小根堆, 这里重写compare方法, 实现大根堆
			PriorityQueue<Tuple> queue = new PriorityQueue<>(new Comparator<Tuple>() {
				@Override
				public int compare(Tuple o1, Tuple o2) {
					// 01
					return o2.value - o1.value;
				}
			});

			// 把前k个元素添加到队列
			for(int i = 0; i < k - 1; i++) {
				queue.add(new Tuple(nums[i], i));
			}

			for(int i = k - 1; i < nums.length; i++) {

				// 添加到优先队列
				queue.add(new Tuple(nums[i], i));

				// 弹出前面的元素
				// [1, 3, -1, {-3, 5 , 3} ,6,7] , max = 3
				// [1,3,-1] max = 3
				// [3,-1,-3] max = 3
				// [-1,-3,5] max = 5\

				//System.out.println("top value : " + queue.peek().value);
				// 只要最大值不在范围内, 就移除, 在范围内就不影响, 其他的只要不影响最大值的获取, 就不用管
				// i - k = 从 i 位置出发,向左移动 k 步
				while(queue.peek().index <= i - k) {
					// System.out.println("del peek : " + queue.peek().value);
					queue.poll();
				}
				//System.out.println("add peek : " + queue.peek().value);
				ansList.add(queue.peek().value);
			}

			for (int i = 0; i < ans.length; i++) {
				ans[i] = ansList.get(i);
			}

			// 懒惰删除
			return ans;
        }
    }

	class Tuple{
		public int value;
		public int index;

		public Tuple(int value, int index) {
			this.value = value;
			this.index = index;
		}

		@Override
		public String toString() {
			return "Tuple{" +
					"value=" + value +
					", index=" + index +
					'}';
		}
	}
	//leetcode submit region end(Prohibit modification and deletion)

}
