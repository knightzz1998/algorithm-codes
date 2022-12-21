package leetcode.editor.cn;

/**
 * 200.岛屿数量
 *
 * @author
 * @date 2022-12-21 15:09:11
 */
@SuppressWarnings("all")
public class NumberOfIslands {
    public static void main(String[] args) {
        // 测试代码
        Solution solution = new NumberOfIslands().new Solution();
        char[][] grid = {
                {'1' , '1' , '0' , '0' , '0'},
                {'1' , '1' , '0' , '0' , '0'},
                {'0' , '0' , '1' , '0' , '0'},
                {'0' , '0' , '0' , '1' , '1'}
        };
        System.out.println(solution.numIslands(grid));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int numIslands(char[][] grid) {

            m = grid.length;
            n = grid[0].length;

            int result = 0;

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == '1') {
                        result++;
                        dfs(grid, i, j);
                    }
                }
            }

            return result;
        }

        int m = 0, n = 0;

        int[][] dirts = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        private void dfs(char[][] grid, int i, int j) {

            if (i < 0 || j < 0 || i >= m || j >= n) {
                return;
            }

            // 0 == 海水, 也是边界
            if (grid[i][j] == '0') {
                return;
            }

            grid[i][j] = '0';

            // 上下左右移动
            for (int[] dirt : dirts) {
                int row = i + dirt[0];
                int col = j + dirt[1];
                dfs(grid, row, col);
            }

        }

    }
    //leetcode submit region end(Prohibit modification and deletion)

}
