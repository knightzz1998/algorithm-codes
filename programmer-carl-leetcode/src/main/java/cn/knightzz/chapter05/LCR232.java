package cn.knightzz.chapter05;

import java.util.Stack;

/**
 * @author 王天赐
 * @title: LCR232
 * @description: 232. 用栈实现队列
 * @create: 2023-08-24 11:34
 */
public class LCR232 {

    class MyQueue {


        private Stack<Integer> in = new Stack<>();
        private Stack<Integer> out = new Stack<>();

        public MyQueue() {
        }

        public void push(int x) {
            if(out.empty()) {
                // 将 in 中的数据存入out
                while(!in.empty()) {
                    out.push(in.pop());
                }
            }
            in.push(x);
        }

        public int pop() {
            if(out.empty()) {
                // 将 in 中的数据存入out
                while(!in.empty()) {
                    out.push(in.pop());
                }
            }
            return out.pop();
        }

        public int peek() {
            if(out.empty()) {
                // 将 in 中的数据存入out
                while(!in.empty()) {
                    out.push(in.pop());
                }
            }
            return out.peek();
        }

        public boolean empty() {
            return out.empty() && in.empty();
        }
    }

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */
}
