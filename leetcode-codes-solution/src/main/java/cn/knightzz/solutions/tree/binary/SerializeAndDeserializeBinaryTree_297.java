package cn.knightzz.solutions.tree.binary;

import cn.knightzz.other.TreeNode;

import java.util.LinkedList;

/**
 * @author 王天赐
 * @title: SerializeAndDeserializeBinaryTree_297
 * @projectName algorithm-codes
 * @description: 297. 二叉树的序列化与反序列化
 * @website <a href="http://knightzz.cn/">http://knightzz.cn/</a>
 * @github <a href="https://github.com/knightzz1998">https://github.com/knightzz1998</a>
 * @create: 2022-12-10 21:40
 */
@SuppressWarnings("all")
public class SerializeAndDeserializeBinaryTree_297 {
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
        StringBuilder sb;

        public String serialize(TreeNode root) {
            sb = new StringBuilder();
            traverse(root);
            return sb.toString();
        }

        LinkedList<String> strList = new LinkedList<>();

        String NULL = "#";
        String SEP = ",";

        public void traverse(TreeNode root) {

            if (root == null) {
                sb.append(NULL).append(SEP);
                return;
            }
            sb.append(String.valueOf(root.val)).append(SEP);
            traverse(root.left);
            traverse(root.right);
        }


        private LinkedList<String> seq;


        // [1,2, null, null, 3, 4, null, null, 5, null, null]
        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            seq = new LinkedList<String>();
            String[] strs = data.split(SEP);
            for (String str : strs) {
                seq.addLast(str);
            }
            return calc();
        }

        private TreeNode calc() {

            if (seq.isEmpty()) {
                return null;
            }
            // 拿到左边第一个元素
            String fisrt = seq.removeFirst();
            if (fisrt.equals(NULL)) {
                return null;
            }

            TreeNode root = new TreeNode(Integer.parseInt(fisrt));
            root.left = calc();
            root.right = calc();
            return root;
        }

    }

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));

}
