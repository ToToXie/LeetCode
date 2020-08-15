package meituan;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @program: LeetCode
 * @description:
 * @author: wd
 * @create: 2020-08-15 17:27
 **/

public class Main_5 {
    static final int mod = 998244353;
    static int ans = 0;

    /**
     * 小团正在装饰自己的书桌，他的书桌上从左到右有m个空位需要放上装饰物。商店中每个整数价格的装饰物恰好有一种，且每种装饰物的数量无限多。
     * <p>
     * 小团去商店的时候，想到了一个购买方案，他要让右边的装饰物价格是左边的倍数。用数学语言来说，假设小团的m个装饰物价格为a1,a2...am，那么对于任意的1≤i≤j≤m，aj是ai的倍数。
     * <p>
     * 小团是一个节约的人，他希望最贵的装饰物不超过n元。现在，请你计算小团有多少种购买的方案？
     * <p>
     * <p>
     * <p>
     * 输入描述
     * 输入包含两个数，n和m（1≤n,m≤1000）
     * <p>
     * 输出描述
     * 输出一个数，结果对998244353取模，表示购买的方案数。
     * <p>
     * <p>
     * 样例输入
     * 4 2
     * 样例输出
     * 8
     * <p>
     * 提示
     * 样例解释
     * [1,1][1,2][1,3][1,4][2,2][2,4][3,3][4,4]共8种
     **/
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n, m;
        n = in.nextInt();
        m = in.nextInt();
        int[][] dp = new int[m + 1][n + 1];
        Arrays.fill(dp[1], 1);
        for (int i = 1; i < m; i++) {
            for (int j = 1; j <= n; j++) {
                for (int k = 1; j * k <= n; k++) {
                    dp[i + 1][j * k] = (dp[i + 1][j * k] + dp[i][j]) % mod;
                }
            }
        }
        for (int i = 1; i <= n; i++) {
            ans = (ans + dp[m][i]) % mod;
        }
        System.out.println(ans);


    }
}
