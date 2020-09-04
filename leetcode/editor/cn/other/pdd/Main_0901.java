package pdd;

import java.util.Scanner;

/**
 * @program: LeetCode
 * @description:
 * @author: wd
 * @create: 2020-09-01 19:13
 **/

public class Main_0901 {
    /**
     * 0 2 1 0
     * 3 0 0 8
     * 4 0 0 7
     * 0 5 6 0
     **/
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[][] nums = new int[n][n];
        int temp = 0;
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < n; i++) {
                if ((n % 2) == 1 && (j == n / 2 || i == n / 2)) continue;
                if (i == j || i == (n - j - 1)) continue;
                temp = 0;
                if (i > j) {
                    if (j < (n - i - 1)) {
                        if (i >= n / 2) {
                            temp = 1;
                        } else {
                            temp = 2;
                        }
                    } else {
                        if (j < (n / 2)) {
                            temp = 8;
                        } else {
                            temp = 7;
                        }
                    }
                } else {
                    if (j < (n - i - 1)) {
                        if (j < n / 2) {
                            temp = 3;
                        } else {
                            temp = 4;
                        }
                    } else {
                        if (i < n / 2) {
                            temp = 5;
                        } else {
                            temp = 6;
                        }
                    }
                }
                nums[j][i] = temp;
            }

        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(nums[i][j]);
                if (j == n - 1) System.out.println();
                else System.out.print(" ");
            }
        }
    }

}
