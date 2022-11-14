package cn.knightzz.solutions.tree.binary;

import cn.knightzz.other.TreeNode;

/**
 * @author 王天赐
 * @title: MaximumDepthOfBinaryTree_104
 * @projectName algorithm-codes
 * @description: 二叉树最大深度
 * @website <a href="http://knightzz.cn/">http://knightzz.cn/</a>
 * @github <a href="https://github.com/knightzz1998">https://github.com/knightzz1998</a>
 * @create: 2022-11-12 19:22
 */
@SuppressWarnings("all")
public class MaximumDepthOfBinaryTree_104 {
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode() {}
     * TreeNode(int val) { this.val = val; }
     * TreeNode(int val, TreeNode left, TreeNode right) {
     * this.val = val;
     * this.left = left;
     * this.right = right;
     * }
     * }
     */
    class Solution {

        // 这是个需要遍历完所有的树才知道结果的问题


        int depth = 0;
        int res = 0;

        public int maxDepth(TreeNode root) {
            traverse(root);
            return res;
        }

        private void traverse(TreeNode root) {

            if (root == null) {
                return;
            }

            // 前序位置 : 未入 root节点前
            // 统计经历的节点
            depth++;
            traverse(root.left);
            // 进入当前节点
            // 中序位置
            traverse(root.right);

            // 需要在累加以后 回溯之前进行判断最大深度
            res = Math.max(res, depth);

            // 后续位置 回溯
            // 离开root节点, 这里注意, 这是返回上一个层了, 所以层数要恢复成原来的,上面加了1
            depth--;

        }
    }
}
