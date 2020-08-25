package meituan;

import java.util.Arrays;

/**
 * @program: LeetCode
 * @description:
 * @author: wd
 * @create: 2020-08-25 19:47
 **/

public class Main_6 {


    /**
     * 数组小和的定义如下：
     * 例如，数组s = [1, 3, 5, 2, 4, 6]，在s[0]的左边小于或等于s[0]的数的和为0；
     * 在s[1]的左边小于或等于s[1]的数的和为1；
     * 在s[2]的左边小于或等于s[2]的数的和为1+3=4；
     * 在s[3]的左边小于或等于s[3]的数的和为1；
     * 在s[4]的左边小于或等于s[4]的数的和为1+3+2=6；
     * 在s[5]的左边小于或等于s[5]的数的和为1+3+5+2+4=15。
     * 所以s的小和为0+1+4+1+6+15=27
     * 给定一个数组s，实现函数返回s的小和
     * [要求]
     * 时间复杂度为O(nlogn)，空间复杂度为O(n)
     **/
    private static int[] temp;
    private static int sum;

    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 2, 4, 6};
        System.out.println(getNum(nums));
    }

    static int getNum(int[] nums) {
        int n = nums.length;
        sum = 0;
        temp = new int[n];
        for (int step = 2; step / 2 <= n; step *= 2) {
            for (int i = 0; i < n; i += step) {
                int mid = i + step / 2 - 1;
                if (mid + 1 >= n) continue;
                int l1 = i, l2 = mid + 1, r1 = mid, r2 = Math.min(n - 1, i + step - 1);
                merge(nums, l1, l2, r1, r2);
            }
        }
        System.out.println(Arrays.toString(nums));
        return sum;
    }

    private static void merge(int[] nums, int l1, int l2, int r1, int r2) {
        int index = 0;
        int s = l1;
        while (l1 <= r1 && l2 <= r2) {
            if (nums[l1] < nums[l2]) {
                sum += nums[l1] * (r2 - l2 + 1);
                temp[index++] = nums[l1++];
            } else {
                temp[index++] = nums[l2++];
            }
        }
        while (l1 <= r1) temp[index++] = nums[l1++];
        while (l2 <= r2) temp[index++] = nums[l2++];
        index = 0;
        for (int k = s; k <= r2; k++) {
            nums[k] = temp[index++];
        }
    }
}
