package cn.knightzz.solutions.tree.binary;

import cn.knightzz.other.TreeNode;

/**
 * @author 王天赐
 * @title: ValidateBinarySearchTree
 * @projectName algorithm-codes
 * @description: 98. 验证二叉搜索树
 * @website <a href="http://knightzz.cn/">http://knightzz.cn/</a>
 * @github <a href="https://github.com/knightzz1998">https://github.com/knightzz1998</a>
 * @create: 2022-11-12 17:05
 */
public class ValidateBinarySearchTree_98 {
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

        public boolean isValidBST(TreeNode root) {
            return isValidBST(root, null, null);
        }

        // 辅助函数，增加函数参数列表，在参数中携带额外信息，将这种约束传递给子树的所有节点
        public boolean isValidBST(TreeNode root, TreeNode min, TreeNode max) {
            if(root == null){
                return true;
            }

            // 通过参数取控制最小值和最大值
            // 这里注意啊， 如果是左子树， 最大值一直都是null
            // 如果是右子树， 最小值一直都是null
            if(min != null && root.val <= min.val) {
                return false;
            }
            if(max != null && root.val >= max.val) {
                return false;
            }

            return isValidBST(root.left, min, root) && isValidBST(root.right, root, max);
        }

    }
}
