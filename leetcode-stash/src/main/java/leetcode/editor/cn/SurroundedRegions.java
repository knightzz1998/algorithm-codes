package leetcode.editor.cn;

import javax.management.remote.rmi._RMIConnection_Stub;

/**
 * 130.被围绕的区域
 *
 * @author
 * @date 2022-12-22 16:26:57
 */
@SuppressWarnings("all")
public class SurroundedRegions {
    public static void main(String[] args) {
        // 测试代码
        Solution solution = new SurroundedRegions().new Solution();
        char[][] board = new char[][]{
                {'O', 'X', 'X', 'O', 'X'},
                {'X', 'O', 'O', 'X', 'O'},
                {'X', 'O', 'X', 'O', 'X'},
                {'O', 'X', 'O', 'O', 'O'},
                {'X', 'X', 'O', 'X', 'O'}};
        solution.solve(board);
        System.out.println(board);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public void solve(char[][] board) {

            if (board.length == 0) {
                return;
            }

            // 获取棋盘行数m, 列数 m
            int m = board.length;
            int n = board[0].length;

            // 构建 UF, 多的一个是 dummy 节点, 我们需要把所有不被X包围的节点连接到dummy上
            UF uf = new UF(m * n + 1);
            // 序号是 0~16 , 这里不能 + 1
            int dummy = m * n;

            // 将首行与末行的 'O' 与 dummy连通
            for (int i = 0; i < n; i++) {
                if (board[0][i] == 'O') {
                    // 注意 union的具体实现啊, 不要连接反了
                    uf.union(i, dummy);
                }
                if (board[m - 1][i] == 'O') {
                    uf.union((m - 1) * n + i, dummy);
                }
            }

            // 将首列和末列的 'O' 与 dummy节点连通
            for (int j = 0; j < m; j++) {
                if (board[j][0] == 'O') {
                    // 这里无论是 dumy 连 x 点还是 x 连dumy 都无所谓, 只要连通就行
                    uf.union(j * n, dummy);
                    //System.out.printf("board[%d][%d] connect dummy\n", j, 0);
                }
                if (board[j][n - 1] == 'O') {
                    uf.union(j * n + n - 1, dummy);
                    //System.out.printf("board[%d][%d] connect dummy\n", j, n - 1);
                }
            }

            // 上下左右搜索, O与其上下左右的O连通
            int[][] dirts = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

            // 将内部的 'O' 与其上下左右的 'O' 连接
            for (int x = 1; x < m - 1; x++) {
                for (int y = 1; y < n - 1; y++) {
                    if (board[x][y] == 'O') {
                        // 上下左右搜索
                        for (int[] dirt : dirts) {
                            int row = x + dirt[0];
                            int col = y + dirt[1];
                            // 如果是 O 的情况下就连通
                            if(board[row][col] == 'O') {
                                uf.union(row * n + col, x * n + y);
                            }
                        }
                    }
                }
            }

            // 所有不和 dummy 连通的 O，都要被替换
            for (int x = 1; x < m - 1; x++) {
                for (int y = 1; y < n - 1; y++) {
                    if (!uf.connected(dummy, x * n + y)) {
                        board[x][y] = 'X';
                    }
                }
            }
        }
    }

    class UF {
        private int count;
        // 记录节点的父节点, 比如 parent[1] = 3 表示 1 的父节点是 3
        private int[] parent;

        public UF(int n) {
            this.count = n;
            // 默认自己是自己的父节点
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        /* 将 p 和 q 连接 */
        public void union(int p, int q) {
            // 如果某两个节点被连通，则让其中的（任意）一个节点的根节点接到另一个节点的根节点上：
            // 1. 找到两个节点的根节点
            int rootP = find(p);
            int rootQ = find(q);

            // 如果二者相等的情况下, 说明他们在同一个颗树上
            if (rootP == rootQ) {
                return;
            }

            parent[rootQ] = rootP;

            // 连通分量减一
            // 两个分量合二为一
            count--;
        }

        /**
         * 找到节点x所在的这棵树的根节点
         *
         * @param x
         * @return
         */
        private int find(int x) {
            // 注意根节点的父节点是它本身
            if (parent[x] != x) {
                // parent 是记录节点的父节点的数组, 比如 x 的父节点是 parent[x]
                // 路径压缩, 因为实际上我们并不关心这个树是什么结构的, 我们只需要知道这个颗树的所有最终根节点都是同一个节点
                // find(parent[x]) 返回的是这棵树的根节点;, 等于说把所有节点直接连到root上
                // 另外，如果使用路径压缩技巧，那么 size 数组的平衡优化就不是特别必要了。所以你一般看到的 Union Find 算法应该是如下实现：
                parent[x] = find(parent[x]);
                x = parent[x];
            }
            return parent[x];
        }

        /* 判断 p 和 q 是否连通 */
        public boolean connected(int p, int q) {

            // 这样，如果节点 p 和 q 连通的话，它们一定拥有相同的根节点：
            int rootP = find(p);
            int rootQ = find(q);

            return rootP == rootQ;
        }

        /* 返回图中有多少个连通分量 */
        public int count() {
            return count;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
