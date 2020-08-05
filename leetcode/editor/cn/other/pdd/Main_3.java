package pdd;

import java.util.Scanner;

/**
 * @program: LeetCode
 * @description:
 * @author: wd
 * @create: 2020-08-02 15:04
 **/

public class Main_3 {
    /**
     * 扔n个骰子，第i个骰子有可能投掷出Xi种等概率的不同的结果，数字从1到Xi。所有骰子的结果的最大值将作为最终结果。求最终结果的期望。
     **/
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] nums = new int[n];
        int max = -1;
        for (int i = 0; i < n; i++) {
            nums[i] = in.nextInt();
            max = Math.max(max, nums[i]);
        }
        double ans = 0;
        for (int i = 1; i <= max; i++) {
            double a = 1, b = 1;
            for (int j = 0; j < n; j++) {
                if (nums[j] >= i) {
                    a = (a * i) / nums[j];
                    if (i > 1) {
                        b = b * (i - 1) / nums[j];
                    } else {
                        b = 0;
                    }
                }
            }
            ans += i * (a - b);
        }
        System.out.printf("%.2f", ans);
    }
}
