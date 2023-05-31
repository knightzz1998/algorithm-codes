package cn.knightzz.integer;

/**
 * @author 王天赐
 * @title: Problem_005
 * @projectName algorithm-codes
 * @description: 剑指 Offer II 005. 单词长度的最大乘积
 * @website <a href="https://knightzz.cn/">https://knightzz.cn/</a>
 * @github <a href="https://github.com/knightzz1998">https://github.com/knightzz1998</a>
 * @create: 2023-05-30 21:49
 */
@SuppressWarnings("all")
public class Problem_005 {

    public static void main(String[] args) {

        Problem_005 problem005 = new Problem_005();

        String[] words = {"abcw", "baz", "foo", "bar", "fxyz", "abcdef"};
        int i = problem005.maxProduct(words);
        System.out.println(i);

    }

    public int maxProduct(String[] words) {

        int len = 0;

        int[] wordCode = new int[words.length];

        for (int i = 0; i < words.length; i++) {
            for (char c : words[i].toCharArray()) {

                // a -> 1 << 0 => 0 => 0 | 0 = 0
                // b -> 1 << 2(010) => 0100 | 0000 = 0100
                // c -> 1 << 3(011) => 0100 | 011 = 0111
    


            }


        }
        return len;
    }


}
