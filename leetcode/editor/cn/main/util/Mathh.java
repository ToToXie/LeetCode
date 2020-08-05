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
