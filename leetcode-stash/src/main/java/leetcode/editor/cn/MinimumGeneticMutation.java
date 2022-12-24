package leetcode.editor.cn;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 433.最小基因变化
 *
 * @author
 * @date 2022-12-24 22:56:44
 */
@SuppressWarnings("all")
public class MinimumGeneticMutation {
    public static void main(String[] args) {
        // 测试代码
        Solution solution = new MinimumGeneticMutation().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int minMutation(String startGene, String endGene, String[] bank) {
            return bfs(startGene, endGene, bank);
        }

        private int bfs(String startGene, String endGene, String[] bank) {

            Queue<String> q = new LinkedList<>();
            HashSet<String> visited = new HashSet<>();
            HashSet<String> bankSet = new HashSet<>();

            for (String cache : bank) {
                bankSet.add(cache);
            }

            if (startGene.equals(endGene)) {
                return 0;
            }

            if (!bankSet.contains(endGene)) {
                return -1;
            }


            int step = 1;

            // 添加初始节点
            q.offer(startGene);
            visited.add(startGene);

            char[] e = new char[]{'A', 'C', 'G', 'T'};

            while (!q.isEmpty()) {
                // 层序遍历
                int sz = q.size();

                // sz 这个 for循环算是一层
                for (int k = 0; k < sz; k++) {

                    // 获取队头元素
                    String gen = q.poll();

                    // 下面的目的是为了去, 选择 gen 每一个位置的元素,
                    for (int i = 0; i < 8; i++) {

                        for (int j = 0; j < 4; j++) {

                            if (e[j] != gen.charAt(i)) {
                                StringBuffer sb = new StringBuffer(gen);
                                // 替换对应位置的元素
                                sb.setCharAt(i, e[j]);
                                // 判断替换后的新元素
                                String newGen = sb.toString();
                                // 判断依据 :
                                // 1.不能被访问过
                                // 2.替换后的元素必须在基因库中
                                if (!visited.contains(newGen) && bankSet.contains(newGen)) {
                                    if (newGen.equals(endGene)) {
                                        return step;
                                    }

                                    // 将这个新的元素添加到队列
                                    q.offer(newGen);
                                    // 记录已经访问过的元素
                                    visited.add(newGen);
                                }
                            }
                        }
                    }
                }
                step++;
            }
            return -1;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
