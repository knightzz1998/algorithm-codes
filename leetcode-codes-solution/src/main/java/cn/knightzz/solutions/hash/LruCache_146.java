package cn.knightzz.solutions.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 王天赐
 * @title: LruCache_146
 * @projectName algorithm-codes
 * @description: 146. LRU 缓存
 * @website <a href="http://knightzz.cn/">http://knightzz.cn/</a>
 * @github <a href="https://github.com/knightzz1998">https://github.com/knightzz1998</a>
 * @create: 2022-10-19 15:33
 */
public class LruCache_146 {

    class LRUCache {

        // Hash表 + 双向链表
        // Hash表用于存储每个key对应的Node Reference
        // O(1)

        // Hash表
        Map<Integer, Node> map;
        // 保护节点
        Node head;
        Node tail;
        int capacity;

        class Node {
            public Node prev;
            public Node next;
            public int key;
            public int value;

            public String toString() {
                return "{" + key + ":" + value + "}";
            }
        }

        public LRUCache(int capacity) {
            this.capacity = capacity;
            this.map = new HashMap<>();
            head = new Node();
            tail = new Node();
            // 建立具有保护节点的空双向链表
            head.next = tail;
            tail.prev = head;
        }

        public int get(int key) {
            //System.out.println("查询 :" + key + "===" + this.map);
            // 判断是否存在key , 不存在返回 -1
            if (!this.map.containsKey(key)) {
                return -1;
            }
            // 如果不存在 :
            Node node = this.map.get(key);
            // 1. 删除链表和map中对应节点
            this.removeFromList(node);
            // 2. 重新在head插入新的节点, 主要目的是为了维护时间顺序, 保证刚使用的一定是在前面的
            this.insertToListHead(node.key, node.value);
            return node.value;
        }

        public void put(int key, int value) {
            //System.out.println("put start === > " + this.map + "====");
            // 判断 key 是否已经存在
            if (this.map.containsKey(key)) {
                // 如果存在 :
                Node node = this.map.get(key);
                // 1. 删除对应节点
                this.removeFromList(node);
                // 2. 重新在head插入新的节点数据
                this.insertToListHead(key, value);
            } else {
                // 如果不存在
                // 1. 创建新的节点
                // 2. 将新的节点插入到队头
                this.insertToListHead(key, value);
            }

            // 判断当前容量是否已经满了
            if (this.map.size() > this.capacity) {
                // 如果满了删除队尾元素
                this.removeFromList(this.tail.prev);
            }
            //System.out.println("put end === > " + this.map + "====");
        }

        public void removeFromList(Node node) {
            // 无论是插入还是移除都要维护Map中的映射关系
            // node1 <-> node <-> node2 删除 node
            // node1 -> node2
            node.prev.next = node.next;
            // node2 -> node1
            node.next.prev = node.prev;
            // 把映射关系移除
            this.map.remove(node.key);
        }

        public void insertToListHead(int key, int value) {
            // 创建Node
            Node node = new Node();
            node.key = key;
            node.value = value;
            // head <-> node1 ==> head <-> node <-> node1
            // 先处理 node 和 node1 部分的连接
            // node <- node1
            head.next.prev = node;
            // node -> node1
            node.next = head.next;
            // 处理 head 与 node
            node.prev = head;
            head.next = node;
            // 3. 维护HashMap的映射关系
            this.map.put(key, node);
        }

    }

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
}
