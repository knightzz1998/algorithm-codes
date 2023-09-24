package cn.knightzz.chapter08;

/**
 * @author 王天赐
 * @title: LCR45
 * @description: 45.跳跃游戏II
 * @create: 2023-09-20 12:29
 */
public class LCR45 {
    public int jump(int[] nums) {
        if (nums == null || nums.length == 0 || nums.length == 1) {
            return 0;
        }
        //记录跳跃的次数
        int count=0;
        //当前的覆盖最大区域
        int curDistance = 0;
        //最大的覆盖区域
        int maxDistance = 0;
        for (int i = 0; i < nums.length; i++) {
            //在可覆盖区域内更新最大的覆盖区域
            maxDistance = Math.max(maxDistance,i+nums[i]);
            //说明当前一步，再跳一步就到达了末尾
            if (maxDistance>=nums.length-1){
                count++;
                break;
            }
            //走到当前覆盖的最大区域时，更新下一步可达的最大区域
            if (i == curDistance){
                curDistance = maxDistance;
                count++;
            }
        }
        return count;
    }

    public int process1(int[] nums) {
        backtrack1(nums, 0, 0);
        return minJump;
    }




    //<editor-fold desc="暴力搜索1">
    int minJump = Integer.MAX_VALUE;

    public void backtrack1(int[] nums, int cur, int jump) {

        // 终止条件,
        if (cur >= nums.length - 1) {
            minJump = Math.min(jump, minJump);
            System.out.println(jump);
            return;
        }

        // 选择
        int step = nums[cur];
        for (int i = 1; i <= step; i++) {
            backtrack1(nums, cur + i, jump + 1);
        }
    }
    //</editor-fold>

    //<editor-fold desc="暴力搜索2">
    public int process2(int[] nums) {

        if (nums.length == 1) {
            return 0;
        }

        return backtrack2(nums, 0, 0);
    }

    public int backtrack2(int[] nums, int cur, int jump) {

        // 终止条件,
        if (cur >= nums.length - 1) {
            return 1;
        }

        // 选择
        int step = nums[cur];
        int minJump = nums.length + 1;
        for (int i = step; i >= 1; i--) {
            int count = jump + backtrack2(nums, cur + i, 1);
            minJump = Math.min(minJump, count);
            // System.out.println(count);
        }
        return minJump;
    }
    //</editor-fold>

    public static void main(String[] args) {

        LCR45 lcr45 = new LCR45();
        //lcr45.process1(new int[]{2,3,1,1,4});
        // System.out.println(lcr45.process2(new int[]{2, 3, 0, 1, 4}));
        System.out.println(lcr45.process2(new int[]{5,6,4,4,6,9,4,4,7,4,4,8,2,6,8,1,
                5,9,6,5,2,7,9,7,9,6,9,4,1,6,8,8,4,4,2,0,3,8,5}));
        // System.out.println("lcr45.minJump = " + lcr45.minJump);

    }
}
