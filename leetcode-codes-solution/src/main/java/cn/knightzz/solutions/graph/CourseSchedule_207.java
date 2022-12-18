package cn.knightzz.solutions.graph;

import java.util.LinkedList;
import java.util.List;

/**
 * @author 王天赐
 * @title: CourseSchedule_207
 * @projectName algorithm-codes
 * @description: 207.课程表
 * @website <a href="http://knightzz.cn/">http://knightzz.cn/</a>
 * @github <a href="https://github.com/knightzz1998">https://github.com/knightzz1998</a>
 * @create: 2022-12-18 22:27
 */
@SuppressWarnings("all")
public class CourseSchedule_207 {

    class Solution {

        private boolean[] visited;
        private boolean[] onPath;
        private LinkedList<Integer>[] graph;
        private boolean hasCycle = false;

        public boolean canFinish(int numCourses, int[][] prerequisites) {

            visited = new boolean[numCourses];
            onPath = new boolean[numCourses];
            graph = new LinkedList[numCourses];
            buildGraph(numCourses, prerequisites);

            for (int i = 0; i < numCourses; i++) {
                // 这个的目的是防止图中多个子图不连接的情况, 所以需要从每个节点出发执行一下DFS
                traverse(graph, i);
            }

            return !hasCycle;
        }

        private void traverse(LinkedList<Integer>[] graph, int s) {

            if (onPath[s]) {
                hasCycle = true;
            }

            if (visited[s] || hasCycle) {
                return;
            }

            // 前序
            visited[s] = true;
            onPath[s] = true;

            // graph[s] 得到的是一个列表 s = 1, graph[s] = [2,3,4,5... ] 表示 1->2, 1->3, 1->4, ...
            // 这个其实就是遍历当前节点的邻接节点
            for (int t : graph[s]) {
                traverse(graph, t);
            }
            // 后序位置, 这个位置是当前节点的所有的邻接节点都遍历完以后, 会执行的位置
            onPath[s] = false;
        }

        public List<Integer>[] buildGraph(int numCourses, int[][] prerequisites) {


            for (int i = 0; i < numCourses; i++) {
                graph[i] = new LinkedList<>();
            }
            for (int[] edge : prerequisites) {
                int from = edge[0];
                int to = edge[1];
                graph[from].add(to);
            }
            return graph;
        }
    }
}
