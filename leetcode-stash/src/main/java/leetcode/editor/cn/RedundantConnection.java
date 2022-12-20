package leetcode.editor.cn;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * 684.冗余连接
 *
 * @author
 * @date 2022-12-19 20:25:37
 */
@SuppressWarnings("all")
public class RedundantConnection {
    public static void main(String[] args) {
        // 测试代码
        Solution solution = new RedundantConnection().new Solution();

        // int[][] arrs = new int[][]{{1,2}, {1,3}, {2,3}};
        int[][] arrs = new int[][]{{1,2}, {2,3}, {1,5}, {3,4}, {1,4}};
        int[] redundantConnection = solution.findRedundantConnection(arrs);
        System.out.println(redundantConnection[0] + ":" + redundantConnection[1]);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] findRedundantConnection(int[][] edges) {

            n = getNodeCount(edges) + 1;
            visited = new boolean[n];
            onPath = new boolean[n];
            graph = new LinkedList[n];
            buildGraph(edges,n);
            return result;
        }

        private int getNodeCount(int[][] edges) {

            for (int i = 0; i < edges.length; i++) {
                int u = edges[i][0];
                int v = edges[i][1];
                n = Math.max(u, n);
                n = Math.max(v, n);
            }
            return n;
        }

        private void traverse(LinkedList<Integer>[] graph, int s, int fatherNode) {

            if(onPath[s]) {
                hasCycle = true;
            }

            if(visited[s] || hasCycle) {
                return;
            }

            visited[s] = true;
            onPath[s] =  true;

            for (int t : graph[s]) {

                // 因为是无向图, 这里是为了判断 1->2 和 2->1 这种不算环
                if(t == fatherNode) {
                    continue;
                }
                traverse(graph, t, s);
            }

            onPath[s] = false;
        }

        private void buildGraph(int[][] edges, int nodeNumbers) {

            for (int i = 1; i < nodeNumbers; i++) {
                graph[i] = new LinkedList<>();
            }

            for(int[] edge : edges) {
                int u = edge[0];
                int v = edge[1];

                graph[u].add(v);
                graph[v].add(u);

                for (int i = 1; i < n; i++) {
                    visited[i] = false;
                    onPath[i] = false;
                }

                // 判断是否有环
                traverse(graph,u, -1);

                if(hasCycle) {
                    result[0] = u;
                    result[1] = v;
                    return;
                }
            }
        }

        // 以邻接矩阵形式存储
        private LinkedList<Integer>[] graph;
        private boolean[] visited;
        private boolean[] onPath;
        private boolean hasCycle = false;
        private int n = -1;
        private int[] result = new int[2];


    }
    //leetcode submit region end(Prohibit modification and deletion)

}
