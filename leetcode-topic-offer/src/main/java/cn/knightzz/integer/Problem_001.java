package cn.knightzz.integer;

/**
 * @author 王天赐
 * @title: Problem_001
 * @projectName algorithm-codes
 * @description:
 * @website <a href="https://knightzz.cn/">https://knightzz.cn/</a>
 * @github <a href="https://github.com/knightzz1998">https://github.com/knightzz1998</a>
 * @create: 2023-05-24 20:44
 */
@SuppressWarnings("all")
public class Problem_001 {

    public static void main(String[] args) {

        Problem_001 problem001 = new Problem_001();

    }

    public int divide(int a, int b) {


        // 处理 被除数为 0 的情况
        if (a == 0) {
            return 0;
        }

        if (b > a) {
            return 0;
        }

        // 处理越界情况
        // -2^32 / 1
        // -2^32 / -1
        // 2^32 - 1 /  1
        // 2^32 - 1 / -1
        if (a == Integer.MIN_VALUE && b == -1) {
            return Integer.MAX_VALUE;
        }

        // 所有值全部取负 , 如果取正的情况下, -2^32 取正, 就越界了
        int x = a > 0 ? -a : a;
        int y = b > 0 ? -b : b;

        int result = 0;
        int count = 1;

        // 88 / 2 => 2, 4, 8,
        while (true) {
            if (x - y > 0) {
                break;
            } else {
                result = result == 0 ? result : result + count;
            }
            // 0
            // x - 2 : 1 , r = 0 + 1
            // x - 4 : 2 , r = 1 + 2
            // x - 6 : 3 , r = 3 + 3
            x = x - y;
            count++;
            y = y + b;
        }

        // 判断符号
        boolean negative = (a < 0) != (b < 0);

        return negative ? -result : result;
    }

}
