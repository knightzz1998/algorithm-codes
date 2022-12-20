package leetcode.editor.cn;

import java.util.*;

/**
 * 17.电话号码的字母组合
 *
 * @author
 * @date 2022-12-20 20:04:12
 */
@SuppressWarnings("all")
public class LetterCombinationsOfAPhoneNumber {
    public static void main(String[] args) {
        // 测试代码
        Solution solution = new LetterCombinationsOfAPhoneNumber().new Solution();
        System.out.println(solution.letterCombinations(""));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<String> letterCombinations(String digits) {

            if(digits.equals("")) {
                return new ArrayList<String>();
            }

            // [2,3]
            String[] digitArray = digits.split("");

            buildNumToWord();
            dfs(digitArray, 0);
            return result;
        }

        private void dfs(String[] digitArray, int idx) {

            if(idx >= digitArray.length) {
                // 到最后的叶子节点的时候, 就要把路径添加到结果中
                result.add(track.toString());
                return;
            }

            // 终止条件
            for (String word : numberToWord.get(digitArray[idx])) {
                // 选择
                track.append(word);
                dfs(digitArray, idx + 1);
                // 撤销选择
                track.deleteCharAt(track.length() - 1);
            }
        }


        private StringBuilder track = new StringBuilder();
        private LinkedList<String> result = new LinkedList<>();


        private HashMap<String, String[]> numberToWord = new HashMap<>();
        private void buildNumToWord(){
            numberToWord.put("2","abc".split("") );
            numberToWord.put("3","def".split("") );
            numberToWord.put("4","ghi".split("") );
            numberToWord.put("5","jkl".split("") );
            numberToWord.put("6","mno".split("") );
            numberToWord.put("7","pqrs".split("") );
            numberToWord.put("8","tuv".split("") );
            numberToWord.put("9","wxyz".split("") );
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
