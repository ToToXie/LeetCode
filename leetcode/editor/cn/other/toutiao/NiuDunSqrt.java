package toutiao;

import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Scanner;

/**
 * @program: LeetCode
 * @description:
 * @author: wd
 * @create: 2020-05-19 16:19
 **/

public class NiuDunSqrt {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int x = in.nextInt();
        System.out.println(sqrt(x));
        double y = 0.123456;

        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        numberFormat.setMaximumFractionDigits(3);
        numberFormat.setMinimumFractionDigits(3);
        numberFormat.setRoundingMode(RoundingMode.FLOOR);
        System.out.println(numberFormat.format(y));

    }

    static private double sqrt(int x) {
        double n = x;
        double ret = x;
        while (Math.abs(ret * ret - x) > 0.0001) {
            ret = ret / 2 + n / 2 / ret;
        }
        return ret;
    }
}
