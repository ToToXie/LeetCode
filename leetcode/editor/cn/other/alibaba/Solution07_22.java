package alibaba;


import util.Swap;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: LeetCode
 * @description: 阿里笔试题
 * @author: wd
 * @create: 2020-07-18 22:31
 **/

public class Solution07_22 {
    static int[] nums = {1, 2, 1, 2, 5};
    /**
     * 题目1:1. 给定一个n，求[1,n]这n个数字的排列组合有多少个。条件：相邻的两个数字的绝对值不能等于1
     * 思路：简单的回溯算法，注意保存上一次访问的位置用于判定绝对值
     **/
    int ans;

    public static void main(String[] args) {
        Solution07_22 main = new Solution07_22();
        System.out.println(
                main.getNum2(nums, 2)
        );
    }

    /**
     * 题目2: 给定一个数组a,数字m，求在数组a中 连续子数组中的某个元素的出现个数>=m的子数组个数。
     * 滚动窗口 l,r
     * 在 l  -  r  内 ，有某个元素 到 m 个 后 ，则有 n - r + 1 个子数组 符合条件
     * 遍历一边数组即可
     **/
    public int getNum2(int[] a, int m) {
        if (a == null || m > a.length) return 0;
        int left = 0, right = 0, ans = 0;
        Map<Integer, Integer> map = new HashMap<>();
        while (left <= right && right < a.length) {
            map.put(a[right], map.getOrDefault(a[right], 0) + 1);

            if (map.get(a[right]) >= m) {
                ans += (a.length - right);
                do {
                    map.put(a[left], map.getOrDefault(a[left], 1) - 1);
                    if (map.get(a[right]) >= m) {
                        ans += (a.length - right);
                    }
                    left++;
                } while (a[left - 1] != a[right]);
            }
            right++;
        }
        return ans;
    }

    private int getNum(int n) {
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = i + 1;
        }
        ans = 0;
        getNumCore(nums, 0, nums.length);
        return ans;

    }

    private void getNumCore(int[] nums, int s, int e) {
        if (s == e - 1) {
            if (isOk(nums)) {
                ans++;
            }
        } else {
            for (int i = s; i < e; i++) {
                Swap.swap(nums, i, s);
                getNumCore(nums, s + 1, e);
                Swap.swap(nums, i, s);
            }
        }

    }

    private boolean isOk(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            if (Math.abs(nums[i] - nums[i - 1]) <= 1) {
                return false;
            }
        }
        return true;
    }
}
