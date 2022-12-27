package leetcode.editor.cn;

import cn.knightzz.other.ListNode;

import java.util.PriorityQueue;

/**
 * 23.合并K个升序链表
 *
 * @author
 * @date 2022-12-25 22:14:09
 */
@SuppressWarnings("all")
public class MergeKSortedLists {
    public static void main(String[] args) {
        // 测试代码
        Solution solution = new MergeKSortedLists().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode() {}
     * ListNode(int val) { this.val = val; }
     * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
    class Solution {
        public ListNode mergeKLists(ListNode[] lists) {

            // 判空
            if(lists.length == 0) {
                return null;
            }

            // 创建一个优先队列, 优先队列每次取出的都是最小值
            // 它是使用小根堆来维护的
            PriorityQueue<Node> queue = new PriorityQueue<>();
            for (ListNode listNode : lists) {
                if(listNode != null) {
                    queue.add(new Node(listNode.val, listNode));
                }
            }

            ListNode dummy = new ListNode();
            ListNode tail = dummy;
            while (!queue.isEmpty()) {

                // 获取最小值的节点
                Node node = queue.peek();
                queue.poll();

                // 将最小值的点插入到dummyNode中
                tail.next = node.listNode;
                // 移动尾部指针
                tail = tail.next;

                // 将剩余的链表重新插入到队列
                ListNode remainNodes = node.listNode.next;
                if (remainNodes != null) {
                    queue.add(new Node(remainNodes.val, remainNodes));
                }
            }
            return dummy.next;
        }
    }

    class Node implements Comparable<Node> {
        int key;
        ListNode listNode;

        public Node(int key, ListNode listNode) {
            this.key = key;
            this.listNode = listNode;
        }

        @Override
        public int compareTo(Node node) {
            return this.key - node.key;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
