package leetcode.editor.cn;

import javax.swing.tree.TreeNode;

/**
 * 297.二叉树的序列化与反序列化
 *
 * @author
 * @date 2022-12-11 11:31:00
 */
@SuppressWarnings("all")
public class SerializeAndDeserializeBinaryTree {
    public static void main(String[] args) {
        // 测试代码
        Codec solution = new SerializeAndDeserializeBinaryTree().new Codec();
    }

//leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode(int x) { val = x; }
     * }
     */
    public class Codec {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            return "";
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            return null;
        }
    }

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));
//leetcode submit region end(Prohibit modification and deletion)

}
