package pdd;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @program: LeetCode
 * @description:
 * @author: wd
 * @create: 2020-05-06 15:00
 **/

public class MainB {
    /**
     * @Description: 火柴拼正方形
     * leetcode 473
     **/
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();

        for (int i = 0; i < t; i++) {
            int n = in.nextInt();
            int[] nums = new int[n];
            int temp = 0, sum = 0;
            for (int j = 0; j < n; j++) {
                nums[j] = in.nextInt();
                sum += nums[j];
            }
            temp = sum % 4;
            if (n < 4 || temp != 0) {
                System.out.println("NO");
                continue;
            }
            Arrays.sort(nums);
            if (nums[n - 1] > temp) {
                System.out.println("NO");
                continue;
            }
            if (true) {
                System.out.println("YES");
            }
        }
    }
}
