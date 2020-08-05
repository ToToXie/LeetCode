package meituan;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * @program: LeetCode
 * @description:
 * @author: wd
 * @create: 2020-05-11 09:53
 **/

public class MainA {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int[][] nums = new int[m][n];
        int[] maxs = new int[m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                nums[j][i] = in.nextInt();
                maxs[j] = Math.max(nums[j][i], maxs[j]);
            }
        }
        Set<Integer> set = new HashSet<>(n);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (nums[i][j] == maxs[i]) {
                    set.add(j);
                }
            }
        }
        System.out.println(set.size());
    }
}
