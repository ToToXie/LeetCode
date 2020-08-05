package meituan;

import java.util.Scanner;

/**
 * @program: LeetCode
 * @description:
 * @author: wd
 * @create: 2020-05-11 09:54
 **/

public class MainB {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = in.nextInt();
        }
        int max = -1;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                max = Math.max(max, getNum(nums[i], nums[j]));
            }
        }
        System.out.println(max);
    }

    private static int getNum(int a, int b) {
        int c = a ^ b;
        int count = 0;
        while (c != 0) {
            count++;
            c = c & (c - 1);
        }
        return count;
    }
}
