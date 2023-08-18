package cn.knightzz.chapter02;

import cn.knightzz.other.ListNode;

/**
 * @author 王天赐
 * @title: Solution_203
 * @projectName algorithm-codes
 * @description: 203.移除数组元素
 * @website <a href="https://knightzz.cn/">https://knightzz.cn/</a>
 * @github <a href="https://github.com/knightzz1998">https://github.com/knightzz1998</a>
 * @create: 2023-06-14 21:02
 */
@SuppressWarnings("all")
public class LCR203 {

    class Solution {
        public ListNode removeElements(ListNode head, int val) {

            // 建立虚拟头结点
            ListNode dummy = new ListNode(-1, head);
            ListNode p = dummy;

            while(p != null && p.next != null) {

                if(p.next.val == val) {
                    // 删除
                    ListNode node = p.next;
                    p.next = node.next;
                }else{
                    p = p.next;
                }
            }

            return dummy.next;
        }
    }
}
