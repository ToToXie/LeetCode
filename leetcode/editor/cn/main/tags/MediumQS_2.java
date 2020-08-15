package tags;

import util.ListNode;

/**
 * @program: LeetCode
 * @description:
 * @author: wd
 * @create: 2020-08-14 22:04
 **/

public class MediumQS_2 {
    static int[] nums = {-1, 5, 3, 4, 0};

    public static void main(String[] args) {
        MediumQS_2 main = new MediumQS_2();
        ListNode root = ListNode.bulideByLevelOrder(nums);
        main.insertionSortList(root);
    }

    /**
     * 147 对链表进行插入排序
     **/
    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode root = new ListNode(-1);
        root.next = head;
        ListNode now = head.next, next, p;
        head.next = null;
        while (now != null) {
            next = now.next;
            now.next = null;
            p = root;
            while (p.next != null && now.val > p.next.val) {
                p = p.next;
            }
            now.next = p.next;
            p.next = now;
            now = next;
        }
        return root.next;
    }
}
