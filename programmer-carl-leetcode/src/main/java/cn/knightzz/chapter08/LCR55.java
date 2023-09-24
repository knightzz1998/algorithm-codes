package cn.knightzz.chapter08;

/**
 * @author 王天赐
 * @title: LCR55
 * @description: 55.跳跃游戏
 * @create: 2023-09-20 11:01
 */
public class LCR55 {

    public boolean canJump(int[] nums) {

        return false;
    }

    // 每个位置可以选择跳或者不跳
    boolean arrive = false;

    public void process1(int[] nums, int cur) {

        if (cur == nums.length - 1) {
            arrive = true;
            return;
        }

        // 每个位置都可以选择跳不同的步数
        int step = nums[cur];
        for (int i = 1; i <= step; i++) {

            if (arrive) {
                return;
            }

            process1(nums, cur + i);
        }
    }

    public boolean process2(int[] nums) {

        if (nums.length == 1) {
            return true;
        }

        // 当前可覆盖范围
        int range = 0;
        for (int i = 0; i <= range; i++) {

            range = Math.max(range, i + nums[i]);
            if (range >= nums.length - 1) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {

        LCR55 lcr55 = new LCR55();
        //lcr55.process1(new int[]{2,3,1,1,4}, 0);
        lcr55.process2(new int[]{2, 5, 0, 0});

    }
}
