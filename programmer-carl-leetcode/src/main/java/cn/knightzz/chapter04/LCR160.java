package cn.knightzz.chapter04;

import cn.knightzz.other.ListNode;

/**
 * @author 王天赐
 * @title: LCR160
 * @description: 160.链表相交
 * @create: 2023-08-24 10:47
 */
public class LCR160 {

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {

        if(headA == null || headB == null) {
            return null;
        }

        // 计算两个节点的差值
        ListNode p1 = headA;
        ListNode p2 = headB;

        int sizeA = 0;
        int sizeB = 0;

        while(p1 != null) {
            sizeA++;
            p1 = p1.next;
        }

        while(p2 != null) {
            sizeB++;
            p2 = p2.next;
        }

        // 判断哪个大, 哪个小, 让二者起点保持一致
        p1 = headA;
        p2 = headB;
        if(sizeA > sizeB) {
            for(int i = 0 ; i < sizeA - sizeB; i++) {
                p1 = p1.next;
            }
        }else {
            for(int i = 0 ; i < sizeB - sizeA; i++) {
                p2 = p2.next;
            }
        }

        int min = Math.min(sizeA, sizeB);
        for(int i = 0 ; i < min; i++) {
            if(p1 == p2) {
                return p1;
            }

            p1 = p1.next;
            p2 = p2.next;
        }
        return null;
    }

}
