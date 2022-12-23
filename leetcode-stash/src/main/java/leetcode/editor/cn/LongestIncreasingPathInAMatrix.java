package leetcode.editor.cn;

import org.omg.CORBA.MARSHAL;

/**
 * 329.矩阵中的最长递增路径
 *
 * @author
 * @date 2022-12-23 20:38:12
 */
@SuppressWarnings("all")
public class LongestIncreasingPathInAMatrix {
    public static void main(String[] args) {
        // 测试代码
        Solution solution = new LongestIncreasingPathInAMatrix().new Solution();
        int[][] matrix = {{0, 1, 2, 3, 4, 5, 6, 7, 8, 9},
                {19, 18, 17, 16, 15, 14, 13, 12, 11, 10},
                {20, 21, 22, 23, 24, 25, 26, 27, 28, 29},
                {39, 38, 37, 36, 35, 34, 33, 32, 31, 30},
                {40, 41, 42, 43, 44, 45, 46, 47, 48, 49},
                {59, 58, 57, 56, 55, 54, 53, 52, 51, 50},
                {60, 61, 62, 63, 64, 65, 66, 67, 68, 69},
                {79, 78, 77, 76, 75, 74, 73, 72, 71, 70},
                {80, 81, 82, 83, 84, 85, 86, 87, 88, 89},
                {99, 98, 97, 96, 95, 94, 93, 92, 91, 90},
                {100, 101, 102, 103, 104, 105, 106, 107, 108, 109},
                {119, 118, 117, 116, 115, 114, 113, 112, 111, 110},
                {120, 121, 122, 123, 124, 125, 126, 127, 128, 129},
                {139, 138, 137, 136, 135, 134, 133, 132, 131, 130},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};
        solution.longestIncreasingPath(matrix);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        int m = 0;
        int n = 0;
        int maxLen = 0;

        int[][] maxStep;

        public int longestIncreasingPath(int[][] matrix) {

            m = matrix.length;
            n = matrix[0].length;

            maxStep = new int[m][n];

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    // 注意啊 单纯的dfs是无法遍历所有的图的, 只能从某一点出发去遍历
                    dfs(matrix, i, j);
                }
            }

            return maxLen;
        }

        int[][] dirts = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        private int dfs(int[][] matrix, int x, int y) {

            // 这里是已经存储了当前 matrix[x][y] 这个点可以走的最大长度, 此时就不用再重复计算了, 直接返回
            if (maxStep[x][y] != 0) {
                return maxStep[x][y];
            }
            // 没有存储
            maxStep[x][y] = 1;

            for (int[] dirt : dirts) {
                int newX = x + dirt[0];
                int newY = y + dirt[1];
                if(!isUnValid(newX, newY) && matrix[x][y] < matrix[newX][newY]) {
                    // 因为有上下左右四个方向的, 我们要取最大的长度
                    maxStep[x][y] = Math.max(maxStep[x][y], dfs(matrix, newX, newY)  + 1);
                }
            }
            maxLen = Math.max(maxLen, maxStep[x][y]);
            return maxStep[x][y];
        }

        private boolean isUnValid(int x,  int y) {
            return x < 0 || y < 0 || x >= m || y >= n;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
