package pdd;

import java.util.Scanner;

/**
 * @program: LeetCode
 * @description:
 * @author: wd
 * @create: 2020-09-01 19:51
 **/

public class Main_0904 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int[] nums = new int[m + 5];
        boolean flag = false;
        for (int i = 0; i < m; i++) {
            nums[i] = in.nextInt();
            if (nums[i] == 1) {
                flag = true;
            }
        }
        if (flag) {
            System.out.println(n);
        } else {
            boolean[] dp = new boolean[n + 100];
            int ans = 0;
            for (int i = 1; i <= n; i++) {
                if (dp[i]) {
                    ans++;
                    continue;
                }
                for (int j = 0; j < m; j++) {
                    if ((i % nums[j]) == 0) {
                        for (int k = i; k <= n; k += i) {
                            dp[k] = true;
                        }
                        break;
                    }
                }
                if (dp[i]) ans++;
            }
            System.out.println(ans);
        }

    }
}
