package cn.knightzz.solutions.tree.binary;

import cn.knightzz.other.TreeNode;

import java.util.HashMap;

/**
 * @author 王天赐
 * @title: ConstructBinaryTreeFromPreorderAndInorderTraversal_105
 * @projectName algorithm-codes
 * @description: 105. 从前序与中序遍历序列构造二叉树
 * @website <a href="http://knightzz.cn/">http://knightzz.cn/</a>
 * @github <a href="https://github.com/knightzz1998">https://github.com/knightzz1998</a>
 * @create: 2022-11-22 20:57
 */
@SuppressWarnings("all")
public class ConstructBinaryTreeFromPreorderAndInorderTraversal_105 {

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

        // 前序 [3,9,20,15,7]
        // 中序 [9,3,15,20,7] // 中序的根节点的左边是左子树, 右边是右子树
        // [9] 3 [15,20,7] 相当于拆成了两个部分 [9] 和 [15,20,7]
        // 也可以看做一个子问题
        // [9] 对应的 前序是 [9] , [15,20,7] 对应的前序是 [20,15,7]
        // 以此类推, 我们可以继续拆分 : 对于 [9] 来说, 它只有一个, 那就是根节点
        // 对于  [15,20,7] 来说, 前序 [20,15,7] , 20是根节点 ,所以可以拆分为 [15] 和 [7] 分别对应左右子树

        HashMap<Integer, Integer> valToIndex = new HashMap<>();

        public TreeNode buildTree(int[] preorder, int[] inorder) {

            for (int i = 0 ; i < inorder.length; i++) {
                valToIndex.put(inorder[i] , i);
            }

            return buildTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
        }

        public TreeNode buildTree(int[] preorder, int preStart, int preEnd,
                                  int[] inorder, int inStart, int inEnd) {
            // prestart 和 preend 代表的是拆分后, 对应前缀后缀位置

            // [ ) 左闭右开
            if(preStart > preEnd) {
                return null;
            }

            // 关于区间的问题
            // [1,2,3,4,5] => [3 ~ 5] 之间有几个数
            // 如果使用 5 - 3 默认就是 5可以取到, 3不能取到, 如果3可以取到的话应该是 5 - 2

            // 不同的取法 : 假如 我要求 (3, 5) => 4 - 3 = 1 (下标)
            // 如果是 [3,5) => 4 - 2 = 2; 因为 3是可以取到的 , 所以实际范围是 [3,4]

            // 根据序列的理解方法 [1,2,3] 是 3 的序列, 5的序列是 [1,2,3,4,5]
            // 把 3 的序列去除是 [4,5] , 2 这个结果是 3 走几步到 5
            // 5 - 3 = 2 可以理解为 3 走两步到 5

            // 假如是 (3,5) => 4
            // 或者说是 [3,5) => 3,4

            // 构建根节点
            int rootVal = preorder[preStart];

            // 找到根节点在中序遍历结果中的位置 , 前序的第一个位置就是根节点
            int rootIndex = valToIndex.get(rootVal);

            TreeNode root = new TreeNode(rootVal);

            // 计算左子树的节点数量, 因为3是取不到的
            // [9,3,15,20,...] 这个实际上是 [9,3) 3 因为是root, 无法取到, 但是 9可以被取到
            // 所以实际的区间是 [-1, 0] 应该是 0 - (-1) = 1
            int leftSize = rootIndex - inStart;

            // 分别构建左右子树
            // 左子树区间 : [preStart + 1 , index - 1](中序) , [preStart + 1, preStart + leftSize]
            // 右子树区间 : [index+1, preEnd](中序) , [preStart + leftSize + 1, inEnd]

            // 右子树区间 : [preStart + leftSize , preEnd]
            root.left = buildTree(preorder, preStart + 1, preStart + leftSize, inorder, inStart, rootIndex - 1);

            root.right = buildTree(preorder, preStart + leftSize + 1 , preEnd, inorder, rootIndex + 1, inEnd);

            return root;
        }
    }
}
