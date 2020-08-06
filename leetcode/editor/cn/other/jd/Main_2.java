package jd;

import java.util.Scanner;

/**
 * @program: LeetCode
 * @description:
 * @author: wd
 * @create: 2020-08-06 19:31
 **/

public class Main_2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        double a = 0;
        double b = 0;
        double suma = 0;
        double sumb = 0;
        for (int i = 1; i <= n; i++) {
            a += (1D / (2 * i - 1));
            b += ((1D / (2 * i)));
        }
        double ans = 0.2D * (a - b);
        System.out.printf("%.4f", ans);
    }
}
