package pdd;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * @program: LeetCode
 * @description:
 * @author: wd
 * @create: 2020-05-06 13:52
 **/

public class MainA {
    /**
     * @Description: leetcode 945
     * 整数数组，要求每个数字都不一样，数字只能增加
     * 给定整数数组 A，每次 move 操作将会选择任意 A[i]，并将其递增 1。
     * <p>
     * 返回使 A 中的每个值都是唯一的最少操作次数。
     * 排序，然后贪心，如果当前的数<=前一个数，那么就变成前一个数+1
     **/
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        long[] nums = new long[n];
        long sum = 0;
        Set<Long> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            nums[i] = in.nextLong();
        }
        in.close();

        Arrays.sort(nums);
        for (int i = 1; i < n; i++) {
            if (nums[i] <= nums[i - 1]) {
                sum += nums[i - 1] - nums[i] + 1;
                nums[i] = nums[i - 1] + 1;
            }
        }
        System.out.println(sum);
    }

    public int minIncrementForUnique(int[] A) {
        int n = A.length;
        int sum = 0;
        Arrays.sort(A);
        for (int i = 1; i < n; i++) {
            if (A[i] <= A[i - 1]) {
                sum += A[i - 1] - A[i] + 1;
                A[i] = A[i - 1] + 1;
            }
        }
        return sum;
    }

}
