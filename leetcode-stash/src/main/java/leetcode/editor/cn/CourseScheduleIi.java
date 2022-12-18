package leetcode.editor.cn;

import java.util.Collections;
import java.util.LinkedList;

/**
 * 210.课程表 II
 *
 * @author
 * @date 2022-12-18 22:52:42
 */
@SuppressWarnings("all")
public class CourseScheduleIi {
    public static void main(String[] args) {
        // 测试代码
        Solution solution = new CourseScheduleIi().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] findOrder(int numCourses, int[][] prerequisites) {

            canFinished(numCourses, prerequisites);

            if (hasCycle) {
                return new int[]{};
            }

            // 逆序后
            Collections.reverse(prepost);
            int[] res = new int[numCourses];
            for (int i = 0; i < numCourses; i++) {
                res[i] = prepost.get(i);
            }
            return res;
        }

        public void canFinished(int numCourses, int[][] prerequisites) {
            graph = new LinkedList[numCourses];
            prepost = new LinkedList<>();
            visited = new boolean[numCourses];
            onPath = new boolean[numCourses];
            buildGraph(numCourses, prerequisites);

            for (int i = 0; i < numCourses; i++) {
                traverse(graph, i);
            }
        }

        private LinkedList<Integer>[] graph;
        private boolean[] visited;
        private boolean[] onPath;
        private boolean hasCycle = false;
        private LinkedList<Integer> prepost;

        private void buildGraph(int numCourses, int[][] prerequisites) {
            for (int i = 0; i < numCourses; i++) {
                graph[i] = new LinkedList<>();
            }
            for (int[] edge : prerequisites) {
                // 添加一条从 from 指向 to 的有向边
                // 边的方向是「被依赖」关系，即修完课程 from 才能修课程 to
                // prerequisites[i] = [ai, bi] ，表示在选修课程 ai 前 必须 先选修 bi
                // bi -> ai
                int from = edge[1];
                int to = edge[0];
                graph[from].add(to);
            }
        }


        private void traverse(LinkedList<Integer>[] graph, int s) {

            if (onPath[s]) {
                hasCycle = true;
            }

            if (visited[s] || hasCycle) {
                return;
            }

            visited[s] = true;
            onPath[s] = true;

            for (int t : graph[s]) {
                traverse(graph, t);
            }
            prepost.add(s);
            onPath[s] = false;
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}
