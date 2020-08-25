package Tenngxun;

import java.util.Scanner;

/**
 * @program: LeetCode
 * @description:
 * @author: wd
 * @create: 2020-08-23 20:23
 **/

public class Main_4 {

    private static long[] nums;
    private static int n;

    /**
     * 小Q给你一些木板，告诉你这些木板的高度，这些木板的宽度都是1，
     * 现在有一个刷子长宽也是1，这个刷子每一次可以横着去刷，也可以竖着去刷，
     * 但是刷的过程中任何时刻都要在板子上，也就是说横向的时候，若出现断层，
     * 就要算2次或更多次了，现在问你所有木板全部刷完需要的最小次数？
     * <p>
     * 例如，3个木板，高度分别为 2 1 2，那么横着刷高度为1需要一次，
     * 刷高度为2的时候就需要两次，因为第二个木板在高度为2的时候出现断层，
     * 无法一次刷完。所以答案为3。
     * 5
     * 2 2 1 2 1
     * 3
     **/

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        nums = new long[n];
        long min = Long.MAX_VALUE, max = -1;
        for (int i = 0; i < n; i++) {
            nums[i] = in.nextLong();
//            max = Math.max(max, nums[i]);
//            min = Math.min(min, nums[i]);
        }

        if (max >= n) {
            System.out.println(n);
        } else {
            System.out.println(getCount(0, n - 1));
        }
    }

    private static long getCount(int left, int right) {
        long min = Long.MAX_VALUE, count = 0;
        for (int i = left; i < right; i++) {
            min = Math.min(min, nums[i]);
        }
        long ans = min;
        int from = left;
        for (int i = left; i <= right; i++) {
            nums[i] -= min;
            if (nums[i] > 0) {
                if (i == 0 || nums[i - 1] == 0) {
                    from = i;
                }
                if (i == right) {
                    ans += getCount(from, i);
                }

            } else {
                if (i > 0 && nums[i - 1] > 0) {
                    ans += getCount(from, i - 1);
                }
            }
        }

        return Math.min(ans, right - left + 1);
    }
}
