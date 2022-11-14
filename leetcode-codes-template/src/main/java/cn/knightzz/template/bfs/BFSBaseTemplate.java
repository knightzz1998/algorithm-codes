package cn.knightzz.template.bfs;

import cn.knightzz.other.TreeNode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * @author 王天赐
 * @title: BFSBaseTemplate
 * @projectName algorithm-codes
 * @description: 广度优先搜索框架
 * @website <a href="http://knightzz.cn/">http://knightzz.cn/</a>
 * @github <a href="https://github.com/knightzz1998">https://github.com/knightzz1998</a>
 * @create: 2022-11-12 19:47
 */
@SuppressWarnings("all")
public class BFSBaseTemplate {

    // 以 https://leetcode.cn/problems/minimum-depth-of-binary-tree/ 为例

    int minDepth(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        // root 本身就是一层，depth 初始化为 1
        int depth = 1;

        while (!q.isEmpty()) {
            int sz = q.size();
            /* 将当前队列中的所有节点向四周扩散 */
            for (int i = 0; i < sz; i++) {
                TreeNode cur = q.poll();
                /* 判断是否到达终点 */
                if (cur.left == null && cur.right == null)
                    return depth;
                /* 将 cur 的相邻节点加入队列 */
                if (cur.left != null)
                    q.offer(cur.left);
                if (cur.right != null)
                    q.offer(cur.right);
            }
            /* 这里增加步数 */
            depth++;
        }
        return depth;
    }

}
