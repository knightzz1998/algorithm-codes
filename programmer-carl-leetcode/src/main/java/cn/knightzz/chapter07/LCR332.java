package cn.knightzz.chapter07;


import java.util.*;

/**
 * @author 王天赐
 * @title: LCR332
 * @description: 332.重新安排行程
 * @create: 2023-09-04 10:33
 */
public class LCR332 {


    // 邻接表形式的图, key是指机场名字, value 是从该机场出发可以到达的机场列表
    Map<String, List<String>> graph = new HashMap<>();
    // 记录每张机票是否被使用过
    Map<String, boolean[]> used = new HashMap<>();

    List<List<String>> tickets;

    public List<String> findItinerary(List<List<String>> tickets) {

        this.tickets = tickets;

        // 构造邻接表
        for (List<String> ticket : tickets) {

            String from = ticket.get(0);
            String to = ticket.get(1);

            if (!graph.containsKey(from)) {
                graph.put(from, new ArrayList<>());
            }
            graph.get(from).add(to);
        }

        // 对邻接表的每一行进行排序, 保证字典序最小
        for (List<String> list : graph.values()) {
            Collections.sort(list);
        }

        // 初始化used结构
        for (String key : graph.keySet()) {
            int size = graph.get(key).size();
            used.put(key, new boolean[size]);
        }

        // 设置起点 JFK
        track.add("JFK");
        backtrack("JFK");
        return res;
    }

    // 回溯算法使用的数据结构
    LinkedList<String> track = new LinkedList<>();
    // 回溯算法记录结果
    List<String> res = null;

    public void backtrack(String airport) {

        if (res != null) {
            // 已经找到答案了，不用再计算了
            return;
        }

        if (track.size() == tickets.size() + 1) {
            // track 里面包含了所有的机票，即得到了一个合法的结果
            // 注意 tickets.size() 要加一，因为 track 里面额外包含了起点 "JFK"
            res = new LinkedList<>(track);
            return;
        }

        // 深搜的重点, 无路可走[走过的不能走了]
        if (!graph.containsKey(airport)) {
            // 没有可以走的边
            return;
        }

        // 遍历以当前机场为起点所有能到达的机场
        List<String> nextAirPorts = graph.get(airport);
        for (int nextIndex = 0; nextIndex < nextAirPorts.size(); nextIndex++) {

            String nextAirport = nextAirPorts.get(nextIndex);
            // 如果这张机票已经被使用过, 就直接跳过
            if (used.get(airport)[nextIndex]) {
                continue;
            }

            // 选择
            used.get(airport)[nextIndex] = true;
            track.add(nextAirport);

            // 递归
            backtrack(nextAirport);

            // [["JFK","KUL"],["JFK","NRT"],["NRT","JFK"]]
            // 会存在 A->B, 然后 B-> A的情况, 如果不把已经走过的路径标记的话
            // 会重新再走一遍
            used.get(airport)[nextIndex] = false;
            track.removeLast();

        }
    }

}
