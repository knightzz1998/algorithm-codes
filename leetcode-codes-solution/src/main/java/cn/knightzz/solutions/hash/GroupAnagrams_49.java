package cn.knightzz.solutions.hash;

import java.util.*;

/**
 * @author 王天赐
 * @title: GroupAnagrams_49
 * @projectName algorithm-codes
 * @description: 49.字母异位词分组
 * @website <a href="http://knightzz.cn/">http://knightzz.cn/</a>
 * @github <a href="https://github.com/knightzz1998">https://github.com/knightzz1998</a>
 * @create: 2022-10-17 21:43
 */
public class GroupAnagrams_49 {

    class Solution {
        public List<List<String>> groupAnagrams(String[] strs) {
            // 对字符串进行排序, 然后作为key
            Map<String, ArrayList<String>> results = new HashMap<>();
            for (String str : strs) {
                String copy = sort(str);
                if (results.containsKey(copy)) {
                    results.get(copy).add(str);
                } else {
                    ArrayList<String> values = new ArrayList<>();
                    values.add(str);
                    System.out.println("add" + copy + ":" + str);
                    results.put(copy, values);
                }
            }

            List<List<String>> ans = new ArrayList<>();
            for (String key : results.keySet()) {
                ans.add(results.get(key));
            }
            return ans;
        }

        public String sort(String str) {
            char[] chs = str.toCharArray();
            Arrays.sort(chs);
            return new String(chs);
        }
    }
}
