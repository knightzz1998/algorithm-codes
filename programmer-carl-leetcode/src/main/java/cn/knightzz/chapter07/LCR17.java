package cn.knightzz.chapter07;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author 王天赐
 * @title: LCR17
 * @description: 17. 电话号码的字母组合
 * @create: 2023-08-31 09:35
 */
public class LCR17 {

    HashMap<String, List<String>> cacheMap = new HashMap<>();

    ArrayList<String> res = new ArrayList<>();

    public void pred() {
        cacheMap.put("2", Arrays.asList("a", "b", "c"));
        cacheMap.put("3", Arrays.asList("d", "e", "f"));
        cacheMap.put("4", Arrays.asList("g", "h", "i"));
        cacheMap.put("5", Arrays.asList("j", "k", "l"));
        cacheMap.put("6", Arrays.asList("m", "n", "o"));
        cacheMap.put("7", Arrays.asList("p", "q", "r", "s"));
        cacheMap.put("8", Arrays.asList("t", "u", "v"));
        cacheMap.put("9", Arrays.asList("w", "x", "y", "z"));
    }

    public List<String> letterCombinations(String digits) {

        if (digits.length() == 0) {
            return new ArrayList<>();
        }

        pred();
        String[] digitNums = digits.split("");
        backtrack(0, digitNums, new StringBuilder());

        return res;
    }

    public void backtrack(int start, String[] digitNums, StringBuilder sb) {

        if (sb.length() == digitNums.length) {
            res.add(sb.toString());
            return;
        }

        for (int j = start; j < digitNums.length; j++) {

            List<String> stringList = cacheMap.get(digitNums[j]);

            for (int i = 0; i < stringList.size(); i++) {
                // 选择
                sb.append(stringList.get(i));
                // 下一层
                backtrack(j + 1, digitNums, sb);
                // 撤销
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }

    public static void main(String[] args) {

        LCR17 lcr17 = new LCR17();
        List<String> stringList = lcr17.letterCombinations("23");

        stringList.forEach(System.out::println);


    }

}
