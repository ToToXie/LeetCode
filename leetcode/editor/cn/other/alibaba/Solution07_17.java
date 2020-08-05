package alibaba;


import java.util.Scanner;

/**
 * @program: LeetCode
 * @description: 阿里笔试题
 * @author: wd
 * @create: 2020-07-18 22:31
 **/

public class Solution07_17 {
    /**
     * 题目2:包粽子, 四个数n, m, c0, d0, 一共n 克面粉， m种馅料
     * 然后m行,每行四个数ai, bi, ci, di, ai 表示一共多少克该种馅料
     * 每个粽子包法, bi克第i种馅料 + ci 克面粉, 收益di, 或者 c0 克面粉, 不包馅料, 收益d0
     * 求最大收益
     **/
    public int ti07_17() {
        Scanner in = new Scanner(System.in);
        int N, M, C, D, a, b, c, d, m;
        N = in.nextInt();
        M = in.nextInt();
        C = in.nextInt();
        D = in.nextInt();

        int[] dp = new int[M + 1];
        for (int i = 0; i < M; i++) {
            a = in.nextInt();
            b = in.nextInt();
            c = in.nextInt();
            d = in.nextInt();
            m = a / b;
            if (m * b >= N) {
                for (int j = c; j <= N; j++) {
                    dp[j] = Math.max(dp[j - c] + d, dp[j]);
                }
            } else {
                int k = 1;
                while (k <= m) {
                    for (int n = N; n >= c; n--) {
                        dp[n] = Math.max(dp[n], dp[n - k * c] + k * d);
                    }
                    m -= k;
                    k = 2 * k;
                }
                for (int n = N; n >= m * c; n--) {
                    dp[n] = Math.max(dp[n], dp[n - m * c] + m * d);
                }
            }
        }
        for (int i = C; i <= N; i++) {
            dp[i] = Math.max(dp[i], dp[i - C]);
        }
        return dp[N];
    }

    /**
     *  题目1:给定一个数x，数据对 (a, b)使得a ^ b ^ x能达到最大，求使|a - b|最小的方案总数有多少。
     * x,a,b的范围都是0 - （2^31 次方-1）
     *
     * 0 -> 2, 100 -> 16
     **/
}
