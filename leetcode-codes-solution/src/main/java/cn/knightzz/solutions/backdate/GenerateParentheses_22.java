package cn.knightzz.solutions.backdate;

import java.util.LinkedList;
import java.util.List;

/**
 * @author 王天赐
 * @title: GenerateParentheses
 * @projectName algorithm-codes
 * @description: 22.括号生成
 * @website <a href="http://knightzz.cn/">http://knightzz.cn/</a>
 * @github <a href="https://github.com/knightzz1998">https://github.com/knightzz1998</a>
 * @create: 2022-11-14 19:10
 */
@SuppressWarnings("all")
public class GenerateParentheses_22 {

    class Solution {

        private List<String> ans = new LinkedList();
        private LinkedList<String> track = new LinkedList();

        public List<String> generateParenthesis(int n) {
            // 注意啊,必须从0开始, 不然到最后一个位置还没选择就结束了
            backtrack(n, n);
            return ans;
        }

        private String listToString(LinkedList<String> strs){
            StringBuilder sb = new StringBuilder();
            for(String str : strs){
                sb.append(str);
            }
            return sb.toString();
        }

        public void backtrack(int left, int right) {

            // 如果出现左括号剩下的多的情况下就错了
            // 因为 任何情况下都是左括号先出现, 然后再出现右括号,
            if(left > right) return;
            // 数量小于 0 肯定是不合法的
            if(left < 0 || right < 0) return;
            if(left == 0 && right == 0) {
                // System.out.println(listToString(track));
                ans.add(listToString(track));
                return;
            }

            // 尝试选择左括号
            track.addLast("(");
            // 移动到下一个位置
            backtrack(left - 1, right);
            // 撤销选择
            track.removeLast();

            // 尝试选择右括号
            track.addLast(")");
            // 移动到下一个位置
            backtrack(left, right - 1);
            // 撤销选择
            track.removeLast();

        }
    }
}
