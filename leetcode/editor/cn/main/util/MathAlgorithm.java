package util;

/**
 * @program: LeetCode
 * @description: 常用的数学类算法
 * @author: wd
 * @create: 2020-07-18 15:47
 **/

public class MathAlgorithm {
    /**
     * 求最大公约数
     **/
    static public int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    /**
     * 求最小公倍数
     * a，b 最大公约数 d
     * <p>
     * 即为  a * b /d
     **/
    static public int lcm(int a, int b) {
        return a * b / gcd(a, b);
    }

    /**
     * 牛顿法-求平方根
     **/
    static public double sqrt(int x) {
        double n = x;
        double ret = x;
        while (Math.abs(ret * ret - x) > 0.0001) {
            ret = ret / 2 + n / 2 / ret;
        }
        return ret;
    }
}
