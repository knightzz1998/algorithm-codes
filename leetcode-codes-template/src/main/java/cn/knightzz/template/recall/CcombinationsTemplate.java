package cn.knightzz.template.recall;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author 王天赐
 * @title: CcombinationsTemplate
 * @projectName algorithm-codes
 * @description:
 * @website <a href="http://knightzz.cn/">http://knightzz.cn/</a>
 * @github <a href="https://github.com/knightzz1998">https://github.com/knightzz1998</a>
 * @create: 2022-11-04 21:01
 */
@SuppressWarnings("all")
public class CcombinationsTemplate {
    class Solution {

        private List<List<Integer>> ans = new ArrayList<>();
        private LinkedList<Integer> track = new LinkedList<>();
        private int k;
        private int n;

        public List<List<Integer>> combine(int n, int k) {
            this.k = k;
            this.n = n;
            backtrack(1);
            return ans;
        }

        void backtrack(int start){

            if(track.size() == k) {
                ans.add(new ArrayList(track));
                return;
            }

            for(int i = start; i <= n; i++) {
                track.addLast(i);
                backtrack(i + 1);
                // 撤销
                track.removeLast();
            }
        };
    }
}
