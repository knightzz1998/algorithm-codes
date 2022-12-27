package leetcode.editor.cn;

import java.util.LinkedList;
import java.util.List;

/**
 * 51.N 皇后
 *
 * @author
 * @date 2022-12-20 20:56:01
 */
@SuppressWarnings("all")
public class NQueens {
    public static void main(String[] args) {
        // 测试代码
        Solution solution = new NQueens().new Solution();
        System.out.println(solution.solveNQueens(4));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<String>> solveNQueens(int n) {

            buildBoard(n);
            backtrack(0);

            return result;
        }

        private boolean isVaild(int row, int col) {

            int n = board.length;

            // 规则 : 按照国际象棋的规则，皇后可以攻击与之处在同一行或同一列或同一斜线上的棋子
            // 不能在同一行, 也不能在同一列, 也不可以是同一个斜线

            // 判断当前期棋盘是否合法, 只需要判断当前位置以前的位置即可
            // 判断当前列
            for (int i = 0; i <= row; i++) {
                if (board[i][col].equals("Q")) {
                    return false;
                }
            }

            // 检查右上方斜线位置是否有皇后冲突
            for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
                if (board[i][j].equals("Q")) {
                    return false;
                }
            }
            // 判断左上方斜线是否有皇后冲突
            for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
                if (board[i][j].equals("Q")) {
                    return false;
                }
            }

            return true;
        }

        public void backtrack(int row) {

            if(row == board.length) {
                addResult(board);
                return;
            }

            for(int col = 0; col < board.length;  col++) {
                // 先判断是否合法(剪枝操作, 先判断如果我在这个位置是否合法, 然后再去添加)
                if(!isVaild(row, col)) {
                    // 不合法直接跳过
                    continue;
                }

                // 选择
                board[row][col] = "Q";
                // 进入下一层
                backtrack(row + 1);
                // 撤销上面的选择
                board[row][col] = ".";
            }

        }

        private void addResult(String[][] board) {

            LinkedList<String> records = new LinkedList<>();

            int n = board.length;

            for(int row = 0; row < n; row++) {

                StringBuilder sb = new StringBuilder();
                for (int col = 0; col < n ; col++) {
                    sb.append(board[row][col]);
                }

                records.add(sb.toString());
            }

            result.add(records);
        }

        private String[][] board;
        private List<List<String>> result = new LinkedList<>();


        private void buildBoard(int n) {

            board = new String[n][n];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    board[i][j] = ".";
                }
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
