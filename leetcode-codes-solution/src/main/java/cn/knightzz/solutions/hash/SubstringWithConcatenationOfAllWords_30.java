package cn.knightzz.solutions.hash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author 王天赐
 * @title: SubstringWithConcatenationOfAllWords_30
 * @projectName algorithm-codes
 * @description: 30. 串联所有单词的子串
 * @website <a href="http://knightzz.cn/">http://knightzz.cn/</a>
 * @github <a href="https://github.com/knightzz1998">https://github.com/knightzz1998</a>
 * @create: 2022-10-18 21:42
 */
public class SubstringWithConcatenationOfAllWords_30 {

    class Solution {
        public List<Integer> findSubstring(String s, String[] words) {
            // 串联子串 是指一个包含  words 中所有字符串以任意顺序排列连接起来的子串。
            int total = words.length * words[0].length();
            List<Integer> ans = new ArrayList<>();
            for (int i = 0; i + total <= s.length(); i++) {
                if (isSame(s.substring(i, i + total), words)) {
                    System.out.println("word : " + s.substring(i, i + total) + ": ==> ");
                    ans.add(i);
                }
            }
            return ans;
        }

        // 基本思路 :
        // 注意 : 串联子串 是指一个包含  words 中所有字符串以任意顺序排列连接起来的子串
        // 1. 对所有的子串进行分割, 分割成长度为 word.length 的单词
        // 2. 计算子串的hash值
        // 3. 和 words 去比较

        public boolean isSame(String s, String[] words) {
            // 按照words中的单词的长度分割单词
            int len = words[0].length();
            ArrayList<String> subWords = new ArrayList<>();
            for (int i = 0; i + len <= s.length(); i += len) {
                // sunstring(start, end) ==> [start, end) ==> abc => ab : (0,2) , abc : (0,3)
                subWords.add(s.substring(i, i + len));
            }
            return equals(wordCount(subWords), wordCount(Arrays.asList(words)));
        }

        private boolean equals(HashMap<String, Integer> a, HashMap<String, Integer> b) {
            if (a.size() != b.size()) {
                return false;
            }
            // 遍历a, 判断 a b 的 k 和 v 是否相等
            for (String key : a.keySet()) {
                // 包装类型判断时要用 equals
                if (!b.containsKey(key) || !b.get(key).equals(a.get(key))) {
                    return false;
                }
            }

            return true;
        }

        private HashMap<String, Integer> wordCount(List<String> words) {
            HashMap<String, Integer> wordCounts = new HashMap<>();
            for (String word : words) {
                wordCounts.put(word, wordCounts.getOrDefault(word, 0) + 1);
            }
            return wordCounts;
        }
    }
// 包装类型判断时要用 equals
// sunstring(start, end) ==> [start, end) ==> abc => ab : (0,2) , abc : (0,3)
}
