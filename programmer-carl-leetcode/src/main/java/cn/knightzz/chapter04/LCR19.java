package cn.knightzz.chapter04;

import cn.knightzz.other.ListNode;

/**
 * @author 王天赐
 * @title: LCR19
 * @description: 删除链表的倒数第N个节点
 * @create: 2023-08-24 10:46
 */
public class LCR19 {

    public ListNode removeNthFromEnd(ListNode head, int n) {

        ListNode dummyNode = new ListNode(-1, head);

        ListNode slow = dummyNode;
        ListNode fast = dummyNode;

        // 让 fast 距离 slow n 个节点
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }

        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }

        slow.next = slow.next.next;
        return dummyNode.next;
    }
}
