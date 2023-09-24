package cn.knightzz.chapter09;

/**
 * @author 王天赐
 * @title: LCR63
 * @description: 63.不同路径II
 * @create: 2023-09-24 21:12
 */
public class LCR63 {

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {

        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        if (obstacleGrid[0][0] == 1 || obstacleGrid[m - 1][n - 1] == 1) {
            return 0;
        }

        int[][] dp = new int[m][n];

        // BASE CASE
        boolean has = false;
        for (int i = m - 1; i >= 0; i--) {
            // 取决于前面位置是否有障碍物
            dp[i][n - 1] = has ? 0 : 1;
        }
        has = false;
        for (int j = n - 1; j >= 0; j--) {
            // 取决于前面位置是否有障碍物
            dp[m - 1][j] = has ? 0 : 1;
        }


        return dp[0][0];
    }

    int count = 0;
    int m, n;
    int[][] grid;

    public void process1(int[][] obstacleGrid) {
        m = obstacleGrid.length;
        n = obstacleGrid[0].length;

        grid = obstacleGrid;
        backtrack1(0, 0);

    }

    public void backtrack1(int i, int j) {

        if (i == m - 1 && j == n - 1) {
            count++;
            return;
        }

        // 选择列表
        if (i + 1 < m && j < n && grid[i + 1][j] == 0) {
            backtrack1(i + 1, j);
        }

        if (i < m && j + 1 < n && grid[i][j + 1] == 0) {
            backtrack1(i, j + 1);
        }
    }

    public int process2(int[][] obstacleGrid) {
        m = obstacleGrid.length;
        n = obstacleGrid[0].length;
        if (obstacleGrid[0][0] == 1) {
            return 0;
        }
        memo = new int[m][n];
        grid = obstacleGrid;
        return backtrack2(0, 0);
    }

    // memo[i][j] 从 (i,j) 到达终点的路径数
    int[][] memo;

    public int backtrack2(int i, int j) {
        if (i == m - 1 && j == n - 1) {
            return 1;
        }

        if (memo[i][j] > 0) {
            return memo[i][j];
        }

        int r1 = 0;
        int r2 = 0;
        if (i + 1 < m && j < n && grid[i + 1][j] == 0) {
            r1 = backtrack2(i + 1, j);
        }

        if (i < m && j + 1 < n && grid[i][j + 1] == 0) {
            r2 = backtrack2(i, j + 1);
        }
        memo[i][j] = r1 + r2;
        return memo[i][j];
    }

    public static void main(String[] args) {

        LCR63 lcr63 = new LCR63();
        // int[][] grid = {{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
        // int[][] grid = {{0, 1}, {0, 0}};
        int[][] grid = {{1}};
        //lcr63.process1(grid);
        int res = lcr63.process2(grid);
        System.out.println(res);
    }

}
