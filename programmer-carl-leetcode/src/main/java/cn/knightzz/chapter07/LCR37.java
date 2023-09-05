package cn.knightzz.chapter07;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * @author 王天赐
 * @title: LCR37
 * @description: 37.数独
 * @create: 2023-09-04 22:18
 */
public class LCR37 {

    char[][] board;

    int R = 9;
    int C = 9;

    public void solveSudoku(char[][] board) {
        this.board = board;
        backtrack(0, 0);
    }

    // 检测当前位置是否有效

    List<List<Integer>> contained = new LinkedList<>();

    public boolean isValid(int row, int col, char target) {

        for (int i = 0; i < 9; i++) {

            if (board[row][i] == target) {
                return false;
            }
            if (board[i][col] == target) {
                return false;
            }

            // 判断 3 * 3 方格是否存在重复
            if (board[(row/3)*3 + i/3][(col/3)*3 + i%3] == target) {
                return false;
            }
        }

        return true;
    }

    public boolean backtrack(int row, int col) {



        if (col == C) {
            // 当前行穷举到最后一列的时候, 就停下来
            return backtrack(row + 1, 0);
        }

        if (row == R) {
            return true;
        }

        if (board[row][col] != '.') {
            // 当前位置有问题, 选择当前行的下一个位置
            return backtrack(row, col + 1);
        }

        // 遍历row行的每一列
        // 当前位置, 挨个试
        for (char c = '1'; c <= '9'; c++) {

            if (!isValid(row, col, c)) {
                continue;
            }
            // 选择
            board[row][col] = c;

            // 进入同一行的下一列
            // 如果找到一个可行的解立马结束
            if (backtrack(row, col + 1)) {
                return true;
            }
            // 撤销
            board[row][col] = '.';
        }
        return false;
    }

    public static void main(String[] args) {
        char[][] sudokuBoard = {
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };

        LCR37 lcr37 = new LCR37();
        lcr37.solveSudoku(sudokuBoard);

        // 打印二维字符数组
        for (int i = 0; i < sudokuBoard.length; i++) {
            for (int j = 0; j < sudokuBoard[i].length; j++) {
                System.out.print(sudokuBoard[i][j] + " ");
            }
            System.out.println();
        }
    }
}
