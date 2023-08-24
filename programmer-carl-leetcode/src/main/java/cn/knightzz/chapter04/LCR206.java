package cn.knightzz.chapter04;

import cn.knightzz.other.ListNode;

/**
 * @author 王天赐
 * @title: LCR206
 * @description: 206.反转链表
 * @create: 2023-08-24 10:15
 */
public class LCR206 {

    public ListNode reverseList(ListNode head) {

        if (head == null || head.next == null) {
            return head;
        }

        // 快慢指针
        ListNode slow = head;
        ListNode fast = head.next;
        slow.next = null;

        while (fast != null) {

            // 顺序
            // 1. 获取 fast的 下一个节点
            ListNode next = fast.next;

            // 2. 调整 快慢节点指向
            fast.next = slow;

            // 3. 移动指针
            slow = fast;
            fast = next;
        }

        return slow;
    }
}
