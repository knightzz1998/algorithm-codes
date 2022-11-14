package cn.knightzz.solutions.tree.binary;

import cn.knightzz.other.TreeNode;

/**
 * @author 王天赐
 * @title: DeleteNodeInABST_98
 * @projectName algorithm-codes
 * @description: 450. 删除二叉搜索树中的节点
 * @website <a href="http://knightzz.cn/">http://knightzz.cn/</a>
 * @github <a href="https://github.com/knightzz1998">https://github.com/knightzz1998</a>
 * @create: 2022-11-12 17:04
 */
@SuppressWarnings("all")
public class DeleteNodeInABST_450 {

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode() {}
     *     TreeNode(int val) { this.val = val; }
     *     TreeNode(int val, TreeNode left, TreeNode right) {
     *         this.val = val;
     *         this.left = left;
     *         this.right = right;
     *     }
     * }
     */
    class Solution {
        public TreeNode deleteNode(TreeNode root, int key) {

            if(root == null) return null;

            if(root.val == key) {

                // 分三种情况
                // 1. root是叶子节点
                // 注意 deleteNode 返回值是 删除key后的根节点
                // 假如当前是一个小的子树 3 null 4, 删除 3以后, 返回的根节点就是 4了
                // 下面的两行代码可以处理 叶子结点 和 只有左子树和只有右子树的情况
                if(root.left == null) return root.right;
                if(root.right == null) return root.left;

                // 如果是第三种情况 :
                // 左右子树都在
                // 就需要让左子树的最大值的节点作为根, 或者右子树的最小值作为根
                // 1. 获取左子树的最大值的节点
                TreeNode maxNode = getMax(root.left);
                // 删除左子树中最大值的那个节点
                root.left = deleteNode(root.left, maxNode.val);

                maxNode.left = root.left;
                maxNode.right = root.right;
                root = maxNode;
            }

            if(root.val > key) {
                root.left = deleteNode(root.left, key);
            }
            // 因为存在当前left 或 right 是 key的情况
            if(root.val < key) {
                root.right = deleteNode(root.right, key);
            }

            return root;
        }

        // 平衡二叉树最右边的就是最大的
        private TreeNode getMax(TreeNode root) {
            while(root.right != null) {
                root = root.right;
            }
            return root;
        }
    }
}
