package pdd;

import java.util.Scanner;

/**
 * @program: LeetCode
 * @description:
 * @author: wd
 * @create: 2020-09-01 19:42
 **/

public class Main_0903 {
    /**
     * 4 4
     * -1 -1
     * 1 -1
     * -1 1
     * 6 6
     * 6
     * <p>
     * 在神奇的一天，多多背着一个神奇的背包来到一个神奇的商店，商店里有N个神奇的商品。
     * 店长让多多挑任意个商品放入背包带走。多多发现，这些商品中有些会占用背包的一部分空间，
     * 但也有些商品反而会让背包变的更大。同时，这些商品中有些具有一定的收益，但也有些商品是负收益。多多想知道它今天能带走的最大收益是多少。
     **/
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int m = in.nextInt();
        int n = in.nextInt();
        int[] C = new int[m];
        int[] V = new int[m];
        for (int i = 0; i < m; i++) {
            C[i] = in.nextInt();
            V[i] = in.nextInt();
        }
        int[] dp = new int[n + 1];
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < m; i++) {
            for (int j = n; j >= C[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - C[i]] + V[i]);
                max = Math.max(max, dp[j]);
            }
        }
        System.out.println(max);
    }
}
