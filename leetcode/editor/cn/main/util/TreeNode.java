package util;

import java.util.Arrays;
import java.util.Deque;
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

    static int[] preOrder = {1, 2, 4, 5, 3, 6, 7};
    static int[] inOrder = {4, 2, 5, 1, 6, 3, 7};

    public static void main(String[] args) {
//        Integer[] nums = {1, 2, 3, 4, 5, 6, 7};
//        TreeNode ro = buildByLevelOrder(nums);
//        postOrder(ro);
        System.out.println(Arrays.toString(getPostOrderByPreOrderAndInOrder(preOrder, inOrder)));
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

    /**
     * 后续非递归
     **/
    static public void postOrder(TreeNode root) {
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode now = root, prev = null;
        while (now == root || !stack.isEmpty()) {
            if (now != null) {
                stack.addLast(now);
                now = now.left;
            } else {
                now = stack.peekLast();
                if (now.right != null && now.right != prev) {
                    now = now.right;
                } else {
                    now = stack.pollLast();
                    System.out.println(now.val);
                    prev = now;
                    now = null;
                }
            }
        }


    }

    static public int[] getPostOrderByPreOrderAndInOrder(int[] preOrder, int[] inOrder) {
        int length = preOrder.length;
        int[] postOrder = new int[length];
        getPostOrderByPreOrderAndInOrder(postOrder, preOrder, inOrder, 0, length, 0, length, length);
        return postOrder;
    }

    static private void getPostOrderByPreOrderAndInOrder(int[] postOrder, int[] preOrder, int[] inOrder,
                                                         int preleft, int preRight, int inLeft, int inRight, int postRight) {
        if (preleft == preRight || inLeft == inRight) return;
        int root = preOrder[preleft];
        int count = 0;
        for (int i = inLeft; i < inRight; i++) {
            if (root != inOrder[i]) {
                count++;
            } else {
                break;
            }
        }
        postOrder[postRight - 1] = root;
        getPostOrderByPreOrderAndInOrder(postOrder, preOrder, inOrder, preleft + 1, preleft + 1 + count, inLeft, inLeft + count, postRight - preRight + preleft + count);
        getPostOrderByPreOrderAndInOrder(postOrder, preOrder, inOrder, preleft + 1 + count, preRight, inLeft + count + 1, inRight, postRight - 1);
    }

    @Override
    public String toString() {
        Queue<TreeNode> queue = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        TreeNode now = this;
        queue.add(now);
        while (!queue.isEmpty()) {
            now = queue.poll();
            sb.append(now.val + " , ");
            if (now.left != null) queue.add(now.left);
            if (now.right != null) queue.add(now.right);
        }
        return sb.toString();
    }
}