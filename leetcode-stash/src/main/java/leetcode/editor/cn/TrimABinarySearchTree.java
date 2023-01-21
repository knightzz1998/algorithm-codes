package leetcode.editor.cn;

import cn.knightzz.other.TreeNode;

/**
 * 669.修剪二叉搜索树
 *
 * @author
 * @date 2023-01-21 20:47:41
 */
@SuppressWarnings("all")
public class TrimABinarySearchTree {
    public static void main(String[] args) {
        // 测试代码
        Solution solution = new TrimABinarySearchTree().new Solution();
    }

//leetcode submit region begin(Prohibit modification and deletion)

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

        int min = 0;
        int max = 0;

        public TreeNode deleteNode(TreeNode root) {

            // 情况1 : root 为null
            if(root == null) {
                return null;
            }

            if(root.val < min) {
                TreeNode right = deleteNode(root.right);
                // root不在范围内, 直接删了, 去处理右边的树, 因为是二叉搜索树, 所以直接处理右子树
                return right;
            }

            if(root.val > max) {
                // 同理
                TreeNode left = deleteNode(root.left);
                return left;
            }
            // [2 , 1 , 3] min = 3, max = 4

            // 根节点符合条件的情况下, 去处理其他节点

            root.left = deleteNode(root.left);
            root.right = deleteNode(root.right);

            return root;
        }

        public TreeNode trimBST(TreeNode root, int low, int high) {

            min = low;
            max = high;

			return deleteNode(root);
        }
    }
	//leetcode submit region end(Prohibit modification and deletion)

}
