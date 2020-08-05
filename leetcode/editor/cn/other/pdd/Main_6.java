package pdd;

import java.util.Scanner;

/**
 * @program: LeetCode
 * @description:
 * @author: wd
 * @create: 2020-08-02 19:05
 **/

public class Main_6 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int k = in.nextInt();
        int n = in.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = in.nextInt();
        }
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (k > nums[i]) {
                k -= nums[i];
            } else if (k < nums[i]) {
                k = nums[i] - k;
                count++;
            } else {
                count = -1;
                break;
            }
        }
        if (count == -1) {
            System.out.println("paradox");
        } else {
            System.out.print(k + " " + count);
        }
    }
}
