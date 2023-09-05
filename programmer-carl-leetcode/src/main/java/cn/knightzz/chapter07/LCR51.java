package cn.knightzz.chapter07;

import java.util.LinkedList;
import java.util.List;

/**
 * @author 王天赐
 * @title: LCR51
 * @description: 51.N皇后
 * @create: 2023-09-04 14:26
 */
public class LCR51 {
    // 构建棋盘
    String[][] board;
    int N;

    public List<List<String>> solveNQueens(int n) {
        this.N = n;
        this.board = new String[N][N];

        // 填充棋盘
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = ".";
            }
        }

        backtrack(0);
        return res;
    }

    List<List<String>> res = new LinkedList<>();

    /**
     * 判断是否满足放置皇后的条件 , 注意啊, 检测只需要检查当前节点之前的
     *
     * @param row 表示行, 即水平排列
     * @param col
     * @return
     */
    public boolean isValid(int row, int col) {

        // [
        //  1, 2,  3,  4
        //  5, 6,  7,  8
        //  9, 10, 11, 12
        //  13,14, 15, 16
        //
        // ]
        //
        //

        // 判断时只需要判断当前位置前面的数据就可以了

        // 固定行, 遍历当前行的每一列
        for (int i = 0; i < row; i++) {
            if (board[i][col].equals("Q")) {
                return false;
            }
        }

        // 假设以 11, 先判断同一条斜线上从初始位置顺时针45度角的数据 [1, 6, 11, 16]
        // 11 : [row, col]
        // 从左到右. 左上方
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j].equals("Q")) {
                return false;
            }
        }

        // 检测 135度的 [8, 11, 14]
        // 当前 11 的位置向上向右移动1位到达斜边
        // 从右到左, 向上走, 而不是向下走, 因为不需要检查下面的
        for (int i = row - 1, j = col + 1; i >= 0 && j < N; i--, j++) {
            if (board[i][j].equals("Q")) {
                return false;
            }
        }
        return true;
    }

    /**
     * row 用来表示当前选择的行数
     *
     * @param row
     */
    public void backtrack(int row) {

        if (row == N) {
            LinkedList<String> resList = new LinkedList<>();
            for (int i = 0; i < N; i++) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < N; j++) {
                    sb.append(board[i][j]);
                }
                resList.add(sb.toString());
            }
            res.add(resList);
            return;
        }

        // 每次递归都是去下一层的每列 board[row][i] 看看是否符合
        for (int col = 0; col < N; col++) {
            if (!isValid(row, col)) {
                continue;
            }
            // 选择
            board[row][col] = "Q";
            // 进入下一层
            backtrack(row + 1);
            // 撤销当前的选择
            board[row][col] = ".";
        }
    }

    public static void main(String[] args) {

        LCR51 lcr51 = new LCR51();
        lcr51.solveNQueens(4).forEach(list -> {
            System.out.println();
            list.forEach((s) -> {
                System.out.printf("[%s]\n", s);
            });
        });

    }
}


