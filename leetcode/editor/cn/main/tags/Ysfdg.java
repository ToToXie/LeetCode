package tags;

/**
 * @program: LeetCode
 * @description:
 * @author: wd
 * @create: 2020-08-25 18:33
 **/

public class Ysfdg {
    public static void main(String[] args) {
        System.out.println(ysfdg(19, 6));
        System.out.println(ysfdg(19, 6, 19));
    }

    static int ysfdg(int sum, int value, int n) {
        if (n == 1)
            return (sum + value - 1) % sum;
        else
            return (ysfdg(sum - 1, value, n - 1) + value) % sum;
    }

    static int ysfdg(int n, int m) {
        if (n == 1)
            return (n + m - 1) % n;
        else
            return (ysfdg(n - 1, m) + m) % n;
    }
}
