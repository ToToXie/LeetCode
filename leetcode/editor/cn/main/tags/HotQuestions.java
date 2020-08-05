package tags;

import util.TreeNode;

/**
 * @program: LeetCode
 * @description: 热题 HOT 100
 * @author: wd
 * @create: 2020-05-18 11:52
 **/

public class HotQuestions {
    static int[] ints = {2, 7, 9, 3, 1};

    public static void main(String[] args) {
        HotQuestions hotQuestions = new HotQuestions();
        int rob = hotQuestions.rob(ints);
        System.out.println(rob);
    }

    /**
     * 下一个排列
     **/
    public void nextPermutation(int[] nums) {
        int i = nums.length - 2;
        while (i >= 0 && nums[i] > nums[i + 1]) {
            i--;
        }

        if (i > 0) {
            int j = nums.length - 1;
            while (i < j && nums[i] > nums[j]) {
                j--;
            }
            swap(nums, i, j);
        }
        reverse(nums, i + 1);

    }

    private void reverse(int[] nums, int start) {
        int i = start, j = nums.length - 1;
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    /**
     * 反转二叉树
     **/
    public TreeNode invertTree(TreeNode root) {
        invertTreeCore(root);
        return root;
    }

    private TreeNode invertTreeCore(TreeNode root) {
        if (root == null) return root;
        TreeNode left = invertTreeCore(root.left);
        TreeNode right = invertTreeCore(root.right);
        root.left = right;
        root.right = left;
        return root;
    }

    /**
     * 打家劫舍
     **/
    public int rob(int[] nums) {
        if (nums == null || nums.length < 1) {
            return 0;
        }
        int pre = 0, pree = 0;
        for (int num : nums) {
            int temp = pre;
            pre = Math.max(pree + num, pre);
            pree = temp;
        }
        return pre;
    }

}
