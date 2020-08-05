package util;

/**
 * @program: LeetCode
 * @description:
 * @author: wd
 * @create: 2020-05-14 15:40
 **/

public class ListNode {
    public int val;
    public ListNode next;
    public ListNode child;

    public ListNode(int x) {
        val = x;
    }

    public ListNode() {
        val = -1;
    }

    public static ListNode bulideByLevelOrder(int[] nums) {
        if (nums == null || nums.length < 1) return null;
        ListNode root = new ListNode(nums[0]);
        ListNode p = root;
        for (int i = 1; i < nums.length; i++) {
            p.next = new ListNode(nums[i]);
            p = p.next;
        }
        p.next = null;
        return root;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        ListNode p = this;
        while (p != null) {
            sb.append(p.val + " -> ");
            p = p.next;
        }
        int i = sb.lastIndexOf(" -> ");
        return sb.substring(0, i).toString();
    }

//    @Override
//    public String toString() {
//        return "ListNode{" +
//                "val=" + val +
//                ", next=" + next +
//                '}';
//    }
}
