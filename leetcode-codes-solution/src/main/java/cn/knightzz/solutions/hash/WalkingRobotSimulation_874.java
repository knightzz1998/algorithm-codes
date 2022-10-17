package cn.knightzz.solutions.hash;

import java.util.HashSet;

/**
 * @author 王天赐
 * @title: WalkingRobotSimulation_874
 * @projectName algorithm-codes
 * @description: 874. 模拟行走机器人
 * @website <a href="http://knightzz.cn/">http://knightzz.cn/</a>
 * @github <a href="https://github.com/knightzz1998">https://github.com/knightzz1998</a>
 * @create: 2022-10-17 19:39
 */
public class WalkingRobotSimulation_874 {

    public static void main(String[] args) {

        HashSet<String> blockers = new HashSet<>();
    }

    class Solution {

        public String calcHash2(int x, int y) {
            return x + "," + y;
        }

        public long calcHash(int x, int y) {
            return (x + 30000) * 60000L + y + 30000;
        }

        public int robotSim(int[] commands, int[][] obstacles) {
            // 存储障碍物
            HashSet<Long> blockers = new HashSet<>();
            for (int[] obstacle : obstacles) {
                blockers.add(calcHash(obstacle[0], obstacle[1]));
            }

            // (-200, 300000) ==> 整体平移, 防止出现负数 ==> (-200 + 30000 , 30000 + 30000)
            // (-200 + 30000 , 30000 + 30000) ==> hash ==> (-200 + 30000 , 30000 + 30000) * 60000
            // + 30000 + 30000
            // String : "-200, 30009"

            // 存储方向数组
            //                 N  E  S  W
            int[] dx = new int[]{0, 1, 0, -1};
            int[] dy = new int[]{1, 0, -1, 0};
            int x = 0;
            int y = 0;
            // 方向
            int dir = 0; // 默认 dx[dir] 北
            int ans = 0;
            // 每次移动以下就计算当前距离
            for (int cmd : commands) {
                if (cmd > 0) {
                    // 向北
                    for (int i = 0; i < cmd; i++) {
                        // 下一个位置
                        int nextX = x + dx[dir];
                        int nextY = y + dy[dir];
                        if (blockers.contains(calcHash(nextX, nextY))) {
                            // 遇到障碍物直接停下
                            break;
                        }
                        // 移动
                        x = nextX;
                        y = nextY;
                        ans = Math.max(ans, x * x + y * y);
                    }
                } else if (cmd == -1) {
                    // 右转 ==> 当前是不确定朝向的
                    // 北 => 东, 东 => 南 , 南 => 西 , 西 => 北
                    // 这里保证取模后一定是下一个方向
                    dir = (dir + 1) % 4;

                } else if (cmd == -2) {
                    // 左转 ==> 当前是不确定朝向的
                    // 北 => 西, 东 => 北 , 南 => 东 , 西 => 南 (逆时针)
                    // 这里保证取模后一定是下一个方向 , +4 为了防止dir-2是负数
                    dir = (dir - 1 + 4) % 4;
                }
            }
            return ans;
        }
    }
}


