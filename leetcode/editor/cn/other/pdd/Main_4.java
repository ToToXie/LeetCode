package pdd;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * @program: LeetCode
 * @description:
 * @author: wd
 * @create: 2020-08-02 16:05
 **/

public class Main_4 {
    /**
     * 多多鸡打算造一本自己的电子字典，里面的所有单词都只由a和b组成。
     * 每个单词的组成里a的数量不能超过N个且b的数量不能超过M个。
     * 多多鸡的幸运数字是K，它打算把所有满足条件的单词里的字典序第K小的单词找出来，作为字典的封面。
     * <p>
     * 共一行，三个整数N, M, K。(0 < N, M < 50, 0 < K < 1,000,000,000,000,000)
     * <p>
     * 共一行，为字典序第K小的单词。
     **/
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int M = in.nextInt();
        long K = in.nextLong();
        BigInteger[][] dp = new BigInteger[N + 1][M + 1];
        for (int i = 0; i <= N; i++) {
            dp[i][0] = new BigInteger(Integer.toString(i));
        }
        for (int i = 0; i <= M; i++) {
            dp[0][i] = new BigInteger(Integer.toString(i));
        }
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                dp[i][j] = dp[i - 1][j].add(dp[i][j - 1]).add(new BigInteger(Integer.toString(2)));
            }
        }
        StringBuilder sb = new StringBuilder();
        int n = N, m = M;
        while (K > 0) {
            if (n > 0 && m > 0) {
                if (dp[n - 1][m].compareTo(new BigInteger(Long.toString(K - 1))) >= 0) {
                    K--;
                    sb.append('a');
                    n--;
                } else {
                    /**
                     *  dp[n - 1][m] + 1  不以 a 开头
                     *   + 1 确认以 b 开头
                     **/
                    K -= dp[n - 1][m].longValue() + 2;
                    sb.append('b');
                    m--;
                }
            } else if (n > 0 && m == 0) {
                sb.append('a');
                n--;
                K--;

            } else if (n == 0 && m > 0) {
                sb.append('b');
                m--;
                K--;
            } else K = 0;
        }
        System.out.println(sb.toString());
    }
}
