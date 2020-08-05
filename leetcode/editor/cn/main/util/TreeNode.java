package util;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @program: LeetCode
 * @description:
 * @author: wd
 * @create: 2020-05-05 16:29
 **/


public class TreeNode {
    /**
     * 根据特殊的先序数组建立一棵树
     **/
    static int index;
    public int val;
    public TreeNode left;
    public TreeNode right;
    public TreeNode next;

    public TreeNode(int x) {
        val = x;
    }

    static public TreeNode buildByLevelOrder(Integer[] nums) {
        int n = nums.length;
        TreeNode root = new TreeNode(nums[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int index = 1;
        TreeNode now, left, right;
        while (!queue.isEmpty()) {
            now = queue.poll();
            if (now != null) {
                if (index < n) {
                    left = nums[index] == null ? null : new TreeNode(nums[index]);
                } else {
                    left = null;
                }
                index++;
                if (index < n) {
                    right = nums[index] == null ? null : new TreeNode(nums[index]);
                } else {
                    right = null;
                }
                index++;
                now.left = left;
                now.right = right;
                queue.add(left);
                queue.add(right);
            }

        }
        return root;
    }

    public static TreeNode bulideByPreOrder(Integer[] nums) {
        if (nums == null || nums.length < 1) return null;
        TreeNode root = null;
        index = 0;
        root = bulideByPreOrderCore(nums);
        return root;
    }

    static private TreeNode bulideByPreOrderCore(Integer[] nums) {
        if (index >= nums.length) return null;
        if (nums[index] == null) {
            index++;
            return null;
        } else {
            TreeNode root = new TreeNode(nums[index++]);
            root.left = bulideByPreOrderCore(nums);
            root.right = bulideByPreOrderCore(nums);
            return root;
        }
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "val=" + val +
                '}';
    }
}