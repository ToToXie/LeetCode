package baidu;

import java.util.Scanner;

/**
 * @program: LeetCode
 * @description:
 * @author: wd
 * @create: 2020-09-03 20:44
 **/

public class Main_0903_3 {
    /**
     * 牛牛回到家要走恰好n个台阶。
     * <p>
     * 由于牛牛步伐不太大，故单步只能跨最多m个台阶，最少跨一个台阶。
     * <p>
     * 牛牛有一个奇怪的习惯，他要求每步和之前两步走的台阶数目不能相同。
     * <p>
     * 牛牛想知道有多少种不同的走法，答案对10^9+710
     * 9
     * +7取模。
     * <p>
     * 一行输入两个整数n,m，表示台阶数目，单步跨越的最多台阶数目。
     * <p>
     * 对于30\%30%的数据有m\leq n\leq 5m≤n≤5。
     * <p>
     * 对于60\%60%的数据有n\leq 300n≤300。
     * <p>
     * 对于100\%100%的数据有1\leq n\leq 100000, 2\leq m\leq 71≤n≤100000,2≤m≤7。
     **/
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int M = in.nextInt();
        final int MOD = 1000000007;
        int[][][] dp = new int[N + 1][M + 1][M + 1];
        for (int i = 1; i <= M; i++) {
            for (int j = 1; j <= M; j++) {
                if (i + j > N || i == j) continue;
                dp[i + j][i][j] = 1;
            }
        }
        for (int i = 3; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                for (int k = 1; k <= M; k++) {
                    if (j == k) continue;
                    for (int m = 1; m <= M && i - m >= 0; m++) {
                        if (m == j || m == k) continue;

                        dp[i][k][m] += dp[i - m][j][k];
                        dp[i][k][m] %= MOD;
                    }
                }
            }
        }
        long ans = 0;
        for (int i = 1; i <= M; i++) {
            for (int j = 1; j <= M; j++) {
                ans += dp[N][i][j];
                ans %= MOD;
            }
        }
        System.out.println(ans);
    }
}
