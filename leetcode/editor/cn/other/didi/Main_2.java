package didi;

import java.util.Scanner;

/**
 * @program: LeetCode
 * @description:
 * @author: wd
 * @create: 2020-08-21 19:42
 **/

public class Main_2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        if (n == 1) {
            System.out.println(1);
        } else {
            int size = n * n;
            long[] f = new long[size];
            f[0] = 1;
            f[1] = 1;
//        int a =1,b =1;
            for (int i = 2; i < size; i++) {
                f[i] = f[i - 1] + f[i - 2];
            }
            int index = size - 1;
            long[][] nums = new long[n][n];
            int up = 0, left = 0;
            int down = n, right = n;
            while (index >= 0) {
                for (int i = left; i < right - 1 && index >= 0; i++) {
                    nums[up][i] = f[index--];
                }
                for (int i = up; i < down - 1 && index >= 0; i++) {
                    nums[i][right - 1] = f[index--];
                }
                for (int i = right - 1; i > left && index >= 0; i--) {
                    nums[down - 1][i] = f[index--];
                }
                for (int i = down - 1; i > up && index >= 0; i--) {
                    nums[i][left] = f[index--];
                }
                left++;
                right--;
                up++;
                down--;
                if ((n % 2 == 1) && (left + 1 == right) && (up + 1 == down)) {
                    nums[up][left] = f[index--];
                }
            }
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    System.out.print(nums[i][j]);
                    if (j != n - 1) System.out.print(" ");
                }
                if (i != n - 1) System.out.println();
            }
        }
    }
}
