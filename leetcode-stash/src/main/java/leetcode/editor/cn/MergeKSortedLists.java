package leetcode.editor.cn;

import cn.knightzz.other.ListNode;

import java.util.*;

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

            if (lists.length == 0) {
                return null;
            }

            BinaryHeap heap = new BinaryHeap();

            for(ListNode listNode : lists) {
                if(listNode != null){
                    heap.push(new Node(listNode.val, listNode));
                }
            }

            ListNode dummy = new ListNode();
            ListNode tail = dummy;

            while(!heap.isEmpty()) {
                // 获取小根堆顶元素
                Node node = heap.peek();
                // 移除队列中的元素
                heap.poll();


                // 将元素添加到堆尾部
                tail.next = node.listNode;
                tail = tail.next;

                // 把剩下的节点添加到队列
                ListNode remainNode = node.listNode.next;
                if(remainNode != null) {
                    heap.push(new Node(remainNode.val, remainNode));
                }
            }

            return dummy.next;
        }

        public ListNode mergeKLists2(ListNode[] lists) {

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
                ListNode remainNode = node.listNode.next;
                if (remainNode != null) {
                    queue.add(new Node(remainNode.val, remainNode));
                }
            }
            return dummy.next;
        }
    }

    class BinaryHeap{
        // 实现一个二叉堆
        // 使用数组存储完全二叉树, 索引从0开始
        private List<Node> heap;

        public BinaryHeap() {
            this.heap = new ArrayList<Node>();
        }

        public void push(Node node){
            // 先添加到末尾
            heap.add(node);
            // 然后再向上交换
            heapIfUp(heap.size() - 1);
        }

        /**
         * 要向上交换的元素的下标
         * @param p
         */
        private void heapIfUp(int p) {

            // 和它的父亲比较, 只要比父亲小就交换
            while(p > 0) {
                // 公式 : 完全二叉树以数组形式存储在数组中, 索引从0开始
                // 索引为p的节点的左孩子索引为 p * 2 + 1 , 右孩子索引为 p * 2 + 2
                // 索引为p的节点的父亲节点索引是 (p - 1) / 2 (向下取整)
                int father = (p - 1) / 2;
                // 判断key
                if(heap.get(p).key < heap.get(father).key) {
                    // 交换元素
                    Collections.swap(heap, p, father);
                    p = father;
                }else {
                    break;
                }
            }
        }

        public Node poll(){
            Node node = heap.get(0);
            // pop的思路 :
            // 将末尾的值交换到头部, 然后再删除末尾
            Collections.swap(heap, 0, heap.size() - 1);
            heap.remove(heap.size() - 1);

            // 然后再把头部的值向下交换
            heapIfDown(0);

            return node;
        }

        private void heapIfDown(int p) {

            // 获取孩子节点
            int children = p * 2 + 1;
            while(children < heap.size()) {

                int otherChildren = p * 2 + 2;

                // 比较两个孩子节点, p 节点和小的节点比较
                if(otherChildren < heap.size() && heap.get(children).key > heap.get(otherChildren).key) {
                    children = otherChildren;
                }

                // 比较较小的节点和 p
                if(heap.get(children).key < heap.get(p).key) {
                    // 交换, 让小的子节点交换到上面
                    Collections.swap(heap, children, p);
                    // 移动p 然后重新计算儿子节点
                    p = children;
                    children = p * 2 + 1;
                }else {
                    break;
                }
            }
        }

        public Node peek(){
            return heap.size() > 0 ? heap.get(0) : null;
        }

        public boolean isEmpty() {
            return heap.size() == 0;
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
