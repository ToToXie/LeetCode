package toutiao;

/**
 * @program: LeetCode
 * @description: 旋转数组 找 下标
 * @author: wd
 * @create: 2020-07-16 17:49
 **/

public class Solution07_16_2 {

    public int getIndex(int[] nums, int x) {
        if (nums == null || nums.length < 1) return -1;
        int left = 0, right = nums.length - 1, mid = 0;
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (nums[mid] == x) {
                return mid;
            } else if (nums[mid] >= nums[right]) {
                if (nums[mid] < x) {
                    left = mid;
                } else {
                    if (nums[right] > x) {
                        left = mid;
                    } else if (nums[right] < x) {
                        right = mid;
                    }
                }
            } else if (nums[mid] < nums[right]) {
                if (nums[mid] > x) {
                    right = mid;
                } else if (nums[mid] < x) {
                    if (nums[right] > x) {
                        left = mid;
                    } else {
                        right = mid;
                    }
                }
            }
        }
        if (left == nums.length) return -1;
        return nums[left] == x ? left : -1;
    }
}
