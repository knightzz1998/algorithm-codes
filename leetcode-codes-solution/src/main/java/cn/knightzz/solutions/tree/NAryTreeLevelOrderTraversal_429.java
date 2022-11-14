package cn.knightzz.solutions.tree;

import cn.knightzz.other.Node;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author 王天赐
 * @title: NAryTreeLevelOrderTraversal_429
 * @projectName algorithm-codes
 * @description: 429. N 叉树的层序遍历
 * @website <a href="http://knightzz.cn/">http://knightzz.cn/</a>
 * @github <a href="https://github.com/knightzz1998">https://github.com/knightzz1998</a>
 * @create: 2022-11-14 20:36
 */
@SuppressWarnings("all")
public class NAryTreeLevelOrderTraversal_429 {

    /*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/

    class Solution {

        List<List<Integer>> ans = new LinkedList();

        private Queue<Node> queue = new LinkedList();

        public List<List<Integer>> levelOrder(Node root) {

            if (root == null) {
                return ans;
            }

            queue.offer(root);
            while (!queue.isEmpty()) {
                List<Integer> levelResult = new LinkedList();
                int queueSize = queue.size();

                for (int i = 0; i < queueSize; i++) {
                    // 1. 将当前节点的值放入每层结果中
                    Node node = queue.poll();
                    levelResult.add(node.val);
                    //System.out.println("i ==> " + i + " = " + levelResult);
                    // 将当前节点的子节点添加到队列
                    for (Node _children : node.children) {
                        queue.offer(_children);
                    }
                }
                // System.out.println("===============");
                // 将当前层的节点添加到 ans
                ans.add(levelResult);
            }

            return ans;
        }
    }
}
