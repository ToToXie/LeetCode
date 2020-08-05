package toutiao;

import java.util.Scanner;

/**
 * @program: LeetCode
 * @description:
 * @author: wd
 * @create: 2020-05-19 13:41
 **/
//实现一个并查集

public class MainE {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = i;
        }
        int a, b, c;
        for (int i = 0; i < m; i++) {
            a = in.nextInt();
            b = in.nextInt();
            c = in.nextInt();
            if (a == 1) {
                if (isSameSet(nums, b, c)) {
                    System.out.println("Yes");
                } else {
                    System.out.println("No");
                }
            } else if (a == 2) {
                union(nums, b, c);
            }
        }
    }

    private static boolean isSameSet(int[] nums, int a, int b) {
        if (nums[a] == nums[b]) return true;
        else {
            while (nums[a] != a) {
                a = nums[a];
            }
            while (nums[b] != b) {
                b = nums[b];
            }
            return a == b;
        }
    }

    private static void union(int[] nums, int a, int b) {
        if (nums[a] != nums[b]) {
            while (nums[a] != a) {
                a = nums[a];
            }
            int temp;
            while (nums[b] != b) {
                temp = nums[b];
                nums[b] = a;
                b = temp;
            }
            nums[b] = a;
        }
    }

}
