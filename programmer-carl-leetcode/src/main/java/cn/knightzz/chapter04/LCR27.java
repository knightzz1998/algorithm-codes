package cn.knightzz.chapter04;

/**
 * @author 王天赐
 * @title: LCR27
 * @description: 27.移除元素
 * @create: 2023-08-24 10:07
 */
public class LCR27 {

    public int removeElement(int[] nums, int val) {

        int N = nums.length;
        int slow = 0;
        for (int fast = 0; fast < N; fast++) {
            // 一旦发现 非 val 的就将其覆盖掉
            if (val != nums[fast]) {
                nums[slow] = nums[fast];
                slow++;
            }
        }
        return slow;
    }
}
