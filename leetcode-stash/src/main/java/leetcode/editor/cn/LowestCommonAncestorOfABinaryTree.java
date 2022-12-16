package leetcode.editor.cn;

import cn.knightzz.other.TreeNode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 236.二叉树的最近公共祖先
 * @author 
 * @date 2022-12-16 20:33:01
 */
@SuppressWarnings("all")
public class LowestCommonAncestorOfABinaryTree{
	 public static void main(String[] args) {
	 	 // 测试代码
	 	 Solution solution = new LowestCommonAncestorOfABinaryTree().new Solution();
	 }
	 
//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

		markedNodes = new HashSet<>();
		fatherNodes = new HashMap<>();

		// 统计每个节点的父节点
		calcFatherNode(root);
		markedNodes.add(root.val);

		// 从 p 点向上开始mark所有的父节点
		while (p.val != root.val) {
			// 标记对应节点
			markedNodes.add(p.val);
			// 获取p的父节点
			p = fatherNodes.get(p.val);
		}

		// 从q点向上, 只要遇到同样是p的父节点, 那么这个节点就是p,q最近公共祖节点
		while (!markedNodes.contains(q.val)) {
			q = fatherNodes.get(q.val);
		}
 		return q;
    }

	// 所有 Node.val 互不相同
	private Set<Integer> markedNodes;

	private Map<Integer, TreeNode> fatherNodes;

	// 1. 统计每个节点父节点
	// 前序深搜
	public void calcFatherNode(TreeNode root) {

		if(root == null) {
			return;
		}

		if(root.left != null) {
			fatherNodes.put(root.left.val, root);
			// 继续向左
			calcFatherNode(root.left);
		}

		if(root.right != null) {
			fatherNodes.put(root.right.val, root);
			calcFatherNode(root.right);
		}
	}

}
//leetcode submit region end(Prohibit modification and deletion)

}
