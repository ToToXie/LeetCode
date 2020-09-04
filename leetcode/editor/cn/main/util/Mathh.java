package util;

/**
 * @program: LeetCode
 * @description:
 * @author: wd
 * @create: 2020-05-21 17:42
 **/

public class Mathh {
    public static int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    public static int lcm(int a, int b) {
        return a * b / gcd(a, b);
    }

    static public int C(int m, int n) {
        int ans = 1;
        for (int i = 1; i <= m; i++) {
            ans *= (n - m + i) / i;
        }
        return ans;
    }

    /**
     * 求阶乘
     **/
    static public int factorial(int n) {
        int ans = 1;
        for (int i = 0; i <= n; i++) {
            ans *= i;
            if (ans < 0) return -1;
        }
        return ans;
    }
}
