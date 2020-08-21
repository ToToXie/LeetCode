package tags;

import util.TreeNode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @program: LeetCode
 * @description:
 * @author: wd
 * @create: 2020-08-17 22:03
 **/

public class Tree {
    static Integer[] integers = {1};
    int curcount = 0;
    int maxcount = 0;
    int count = 0;
    boolean flag = true;
    TreeNode pre;
    int[] ans;
    int index = 0;

    public static void main(String[] args) {
        Tree tree = new Tree();
        TreeNode treeNode = TreeNode.buildByLevelOrder(integers);
        System.out.println(tree.findMode(treeNode));
    }

    /**
     * 501 二叉搜索树中的众数
     **/
    public int[] findMode(TreeNode root) {
        inOrder(root);
        System.out.println("0000000");
        flag = false;
        ans = new int[count];
        pre = null;
        inOrder(root);
        return ans;
    }

    public void inOrder(TreeNode root) {
        if (root == null) return;
        inOrder(root.left);
        if (pre != null && root.val == pre.val) {
            curcount++;
        } else {
            curcount = 1;
        }
        if (flag) {
            if (curcount > maxcount) {
                maxcount = curcount;
                count = 1;
            } else if (curcount == maxcount) {
                count++;
            }
        } else {
            if (curcount == maxcount) {
                ans[index++] = root.val;
            }
        }
        pre = root;
        inOrder(root.right);
    }

    /**
     * 450 删除二叉搜索树中的节点
     **/
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return root;
        if (key < root.val) {
            root.left = deleteNode(root.left, key);
        } else if (key > root.val) {
            root.right = deleteNode(root.right, key);
        } else {
            if (root.left == null && root.right == null) {
                root = null;
            } else if (root.right != null) {
                TreeNode now = root.right;
                while (now.left != null) {
                    now = now.left;
                }
                root.val = now.val;
                root.right = deleteNode(root.right, now.val);
            } else {
                TreeNode now = root.left;
                while (now.right != null) {
                    now = now.right;
                }
                root.val = now.val;
                root.left = deleteNode(root.left, now.val);
            }
        }
        return root;
    }

    /**
     * 145 二叉树的后序遍历
     **/
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> ans = new LinkedList<>();
        if (root == null) {
            return ans;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode now = root, pre = null;
        while (now != null || !stack.isEmpty()) {
            while (now != null) {
                stack.addLast(now);
                now = now.left;
            }
            now = stack.peekLast();
            if (now.right != null && pre != now.right) {
                now = now.right;
            } else {
                pre = now;
                ans.add(now.val);
                now = null;
                stack.pollLast();
            }
        }
        return ans;
    }
}
