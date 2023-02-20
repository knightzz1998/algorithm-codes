package cn.knightzz.solutions.tree.binary;

import cn.knightzz.other.TreeNode;

import java.util.HashMap;

/**
 * @author 王天赐
 * @title: ConstructBinaryTreeFromInorderAndPostorderTraversal_106
 * @projectName algorithm-codes
 * @description: 106. 从中序与后序遍历序列构造二叉树
 * @website <a href="http://knightzz.cn/">http://knightzz.cn/</a>
 * @github <a href="https://github.com/knightzz1998">https://github.com/knightzz1998</a>
 * @create: 2022-11-22 21:31
 */
@SuppressWarnings("all")
public class ConstructBinaryTreeFromInorderAndPostorderTraversal_106 {

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

        // 中序 [9,3,15,20,7], 中序中的根节点在中间
        // 后序 [9,15,7,20,3], 后序中最后一个是根节点(3)

        // 中序 :   9 (左子树) | 3 | 15, 20, 7 (右子树节点)

        HashMap<Integer, Integer> valueToIndex = new HashMap<>();


        public TreeNode buildTree(int[] inorder, int[] postorder) {

            for (int i = 0; i < inorder.length; i++) {
                valueToIndex.put(inorder[i], i);
            }
            return buildTree(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
        }

        public TreeNode buildTree(int[] inorder, int inStart, int inEnd, int[] postorder, int postStart, int postEnd) {

            if (inStart > inEnd) {
                return null;
            }

            // 获取根节点的值
            int rootVal = postorder[postEnd];
            // 获取根节点在中序中的位置
            int index = valueToIndex.get(rootVal);

            // 计算左子树的大小
            int leftSize = index - inStart;

            // 创建根节点
            TreeNode root = new TreeNode(rootVal);
            // 左子树 :  [inStart + 1 , index - 1](中序) , [postEnd - leftSize , postEnd - 1]
            // 右子树 :  [index + 1 , inEnd] , [postStart, postEnd - leftSize - 1]
            // postStart + leftSize - 1 这里减一是因为我们要考虑 postStart 这个位置的数
            // 前序是 preStart + leftSize , 是因为前序第一个是根节点, 不用考虑
            root.left = buildTree(inorder, inStart, index - 1, postorder, postStart, postStart + leftSize - 1);
            root.right = buildTree(inorder, index + 1, inEnd, postorder, postStart + leftSize, postEnd - 1);

            return root;
        }

    }
}
