package other;

/**
 * @program: LeetCode
 * @description: 问境科技二面笔试 ：
 * 1: 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * 注意：给定 n 是一个正整数。
 * 例如
 * 输入： 3
 * 输出： 3
 * 解释： 有三种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶 + 1 阶
 * 2.  1 阶 + 2 阶
 * 3.  2 阶 + 1 阶
 * 2. merge 两个有序数组，要求merge之后的数组也是有序的
 * 示例：输入[-1,0,1,4],[2,3,4],结果[-1，0，1，2，3，4，4]
 * 3. 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * 示例：
 * 输入: [-2,1,-3,4,-1,2,1,-5,4],
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 **/

public class WenJingKeJi {
    static int[] nums1 = {-1, 0, 1, 4};
    static int[] nums2 = {-2, 1, -3, 4, -1, 2, 1, -5, 4};

    public static void main(String[] args) {
        WenJingKeJi wenJingKeJi = new WenJingKeJi();
//        System.out.println(wenJingKeJi.getNum(4));
        int merge = wenJingKeJi.getMax(nums2);
        System.out.println(merge);
    }

    public int getNum(int n) {
        if (n == 1) {
            return 1;
        } else if (n == 2) {
            return 2;
        }
        int a = 1, b = 2;
        int temp = 0;
        for (int i = 3; i <= n; i++) {
            temp = b;
            b = a + b;
            a = temp;
        }
        return b;
    }

    public int[] merge(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length < 1) return nums2;
        if (nums2 == null || nums2.length < 1) return nums1;
        int l1 = nums1.length, l2 = nums2.length;
        int[] ans = new int[l1 + l2];
        int start1 = 0, start2 = 0, start3 = 0;

        while (start1 < l1 && start2 < l2) {
            if (nums1[start1] < nums2[start2]) {
                ans[start3] = nums1[start1];
                start1++;
            } else {
                ans[start3] = nums2[start2];
                start2++;
            }
            start3++;
        }
        while (start1 < l1) {
            ans[start3++] = nums1[start1++];
        }
        while (start2 < l2) {
            ans[start3++] = nums2[start2++];
        }
        return ans;
    }

    public int getMax(int[] nums) {
        if (nums == null || nums.length < 1) {
            return 0;
        }
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int max = dp[0];
        for (int i = 1; i < nums.length; i++) {
            if (dp[i - 1] < 0) {
                dp[i] = nums[i];
            } else {
                dp[i] = nums[i] + dp[i - 1];
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }

}
