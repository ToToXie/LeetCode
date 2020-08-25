package Tenngxun;

import java.util.Scanner;

/**
 * @program: LeetCode
 * @description:
 * @author: wd
 * @create: 2020-08-23 20:11
 **/

public class Main_3 {
    /**
     * 有一个数字n，现在想把这个数字拆成两个非负整数a和b，使得a+b=n，
     * 对于每一种方案，我们定义一个价值val=s(a)+s(b),其中s(x)代表x的数位和，
     * 例如数字x = 1234，那么s(x) = 1 + 2 + 3 + 4 = 10。
     * 现在想让你求可以选择的方案中val值最大的为多少？
     * <p>
     * 1 <= T <= 100
     * 1 <= n <= 1012
     * 1
     * 35
     * 17
     * a 尽量考虑最大值，即9
     **/
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        long[] nums = new long[t];
        for (int i = 0; i < t; i++) {
            nums[i] = in.nextLong();
        }
        for (int i = 0; i < t; i++) {
            System.out.println(getMax(nums[i]));
        }

    }

    static int getMax(long x) {
        int max = -1;
        if (x < 9) {
            for (long i = 0; i < 9; i++) {
                max = Math.max(max, getSum(i, x - i));
            }
        } else {
            for (long i = 0; i < x; i = i * 10 + 9) {
                max = Math.max(max, getSum(i, x - i));
            }
        }
        return max;
    }

    static int getSum(long a, long b) {
        int sum = 0;
        while (a != 0) {
            sum += a % 10;
            a /= 10;
        }
        while (b != 0) {
            sum += b % 10;
            b /= 10;
        }
        return sum;
    }
}
