package toutiao;

import util.ListNode;

/**
 * @program: LeetCode
 * @description: 链表 奇数位升序，偶数位降序，
 * 变为整体升序
 * 拆分，将偶数位反转，然后合并
 * @author: wd
 * @create: 2020-05-19 22:48
 **/

public class ListNodeMerge {
    static int[] ints = {1, 8, 3, 6, 5, 4, 7, 2, 9};

    public static void main(String[] args) {
        ListNode listNode = ListNode.bulideByLevelOrder(ints);
        ListNodeMerge listNodeMerge = new ListNodeMerge();
        ListNode merge = listNodeMerge.merge(listNode);
        System.out.println(merge);


    }

    public ListNode merge(ListNode root) {
        if (root == null || root.next == null) return root;

        ListNode head1 = new ListNode(0);
        ListNode head2 = new ListNode(0);
        ListNode p1 = head1, p2 = head2, p = root, q = root.next;
        while (p != null || q != null) {
            if (p1 != null) {
                p1.next = p;
                p1 = p1.next;
            }
            if (p2 != null) {
                p2.next = q;
                p2 = p2.next;
            }
            if (q != null && q.next != null) {
                q = q.next.next;
            } else q = null;
            if (p != null && p.next != null) {
                p = p.next.next;
            } else p = null;
        }
        ListNode cur = head2.next, pre = null, next;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        head2.next = pre;
        ListNode head = head1;
        cur = head;
        p = head1.next;
        q = head2.next;

        while (p != null && q != null) {
            if (p.val < q.val) {
                cur.next = p;
                p = p.next;
            } else {
                cur.next = q;
                q = q.next;
            }
            cur = cur.next;
        }
        if (q != null) {
            cur.next = q;
        }
        if (p != null) {
            cur.next = p;
        }
        return head.next;
    }

}
