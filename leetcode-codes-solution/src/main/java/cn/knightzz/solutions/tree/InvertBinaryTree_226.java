package cn.knightzz.solutions.tree;

import cn.knightzz.other.TreeNode;

/**
 * @author 王天赐
 * @title: InvertBinaryTree_226
 * @projectName algorithm-codes
 * @description: 反转二叉树
 * @website <a href="http://knightzz.cn/">http://knightzz.cn/</a>
 * @github <a href="https://github.com/knightzz1998">https://github.com/knightzz1998</a>
 * @create: 2022-11-07 21:14
 */
@SuppressWarnings("all")
public class InvertBinaryTree_226 {

    // https://leetcode.cn/problems/invert-binary-tree/

    class Solution {


        public TreeNode invertTree(TreeNode root) {

            if (root == null) {
                return root;
            }
            // 翻转左子树
            TreeNode left = invertTree(root.left);
            // 翻转右子树
            TreeNode right = invertTree(root.right);

            TreeNode temp = left;
            root.left = right;
            root.right = temp;

            return root;
        }
    }
}
