package cn.knightzz.backtrack;

import java.util.LinkedList;
import java.util.List;

/**
 * @author 王天赐
 * @title: LeetCode_17_LetterCombinationsOfAPhoneNumber
 * @projectName algorithm-codes
 * @description:
 * @website <a href="https://knightzz.cn/">https://knightzz.cn/</a>
 * @github <a href="https://github.com/knightzz1998">https://github.com/knightzz1998</a>
 * @create: 2023-03-01 15:08
 */
@SuppressWarnings("all")
public class LeetCode_17_LetterCombinationsOfAPhoneNumber {

    public List<String> letterCombinations(String digits) {

        if (digits == null || digits.length() == 0) {
            return result;
        }

        String[] words = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

        process(0, words, digits);
        return result;
    }

    LinkedList<String> result = new LinkedList<>();
    StringBuilder track = new StringBuilder();

    /**
     * @param cur    当前按键的下标
     * @param words  所有按键对应的字符串数组
     * @param digits 按键的数字
     */
    private void process(int cur, String[] words, String digits) {

        if (cur == digits.length()) {
            result.add(track.toString());
            return;
        }

        // 获取当前元素可选择列表
        String word = words[Integer.valueOf(String.valueOf(digits.charAt(cur)))];
        for (int i = 0; i < word.length(); i++) {
            // 选择
            track.append(word.charAt(i));
            // 选择下一个位置的元素
            // 举个例子 digits = "23"
            // _ _ => 第一个位置可选的 是 2 : "abc" 第二个位置可选的是 3 : "def"
            process(cur + 1, words, digits);
            // 撤销
            // 删除最后一个元素
            track.deleteCharAt(track.length() - 1);
        }

    }

    public static void main(String[] args) {

        LeetCode_17_LetterCombinationsOfAPhoneNumber combinations = new LeetCode_17_LetterCombinationsOfAPhoneNumber();

        List<String> list = combinations.letterCombinations("999");

        list.forEach(System.out::println);
    }
}

