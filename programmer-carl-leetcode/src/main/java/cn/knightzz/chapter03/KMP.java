package cn.knightzz.chapter03;

import java.util.Arrays;

/**
 * @author 王天赐
 * @title: KMP
 * @description: KMP算法
 * @create: 2023-08-20 11:03
 */
public class KMP {

    public static void main(String[] args) {

        KMP kmp = new KMP();

        String sub = "ABABC";
        int[] next = new int[sub.length()];
        kmp.getNext(next, sub);

        System.out.println(Arrays.toString(next));
        String master = "ABABABC";
        kmp.kmp(next, master, sub);
    }

    public int handler(String s1, String s2) {
        return 1;
    }

    // 前缀表 :
    // 前缀 : 不包含最后一个元素的字符串, 后缀 : 不包含最后一个元素的字符串
    // next 数组 最长的既是其前缀又是其后缀的[子串]的长度
    // a a b a a f

    public int kmp(int[] next, String master, String sub) {

        for (int i = 0, j = 0; i < master.length(); ) {

            if (master.charAt(i) == sub.charAt(j)) {
                // 如果匹配的话, 就移动指针
                i++;
                j++;
            } else if (j > 0) {
                // 不匹配
                // 跳过一定长度的字符串
                // 这里j - 1 是因为要获取前缀长度
                // 比如 当前 ABABC, 当前是 C位置的, 我要想跳过, 就要获取前一个元素对应的公共前缀长度
                // next[j-1] = 2 , 那么可跳过的字符个数就是2 , 此时对应当前应该比较的位置是 第三个位置, 映射到数组下表就是 2
                j = next[j - 1];
            } else {
                i++;
            }

            if (j == sub.length()) {
                return i - j;
            }
        }
        return -1;
    }

    public void getNext(int[] next, String sub) {

        // 第一个之前元素不存在可以跳过的字符, 所以是 0
        int prefixLen = 0;
        next[0] = prefixLen;


        int i = 1;
        while (i < sub.length()) {

            // 比较下一个字符是否相同
            // 假设共同子串长度为1 , 那么此时 第一个元素和 当前位置的元素相同
            // [0, 1,  2,  3, 4]
            // [A, B,  A,  B, C]
            // [0, 0,  1,  2, 0]
            // 如上面所示 : 初始状态 A 肯定是0, 因为它没有公共前缀 此时 prefixLen = 0, i = 0 , 然后移动
            // prefixLen = 0, i = 1,  此时 A 和 B 不等, 继续移动
            // prefixLen = 0, i = 2,  此时 A 和 A 是相等的, prefixLen = 1
            // prefixLen = 1, i = 3, 注意 : 我们要判断 ABAB 前缀和长度, 一只 ABA 的前缀和是 1, 我们要判断只需要判断 1 位置和3位置是否一致既可, 如果一致
            // 就累加, 不一致的话, 那么

            if (sub.charAt(prefixLen) == sub.charAt(i)) {
                prefixLen++;
                next[i] = prefixLen;
                i++;
            } else {
                // 下一个字符不相同
                if (prefixLen == 0) {
                    next[i] = 0;
                    i++;
                } else {
                    // 查表, 看看是否存在更短的前后缀
                    // ABAC ABAB , 此时 比较 C 和 B的前后缀是否想等, 此时显然不相等
                    // 那么我们需要判断前一位 是否能组成相同的, AB 和 最后 的 A + B
                    // next[prefixLen - 1] = 1
                    prefixLen = next[prefixLen - 1];
                }
            }
        }
    }
}
