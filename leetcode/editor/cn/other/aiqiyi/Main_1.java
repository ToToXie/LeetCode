package aiqiyi;

import java.util.Scanner;

/**
 * @program: LeetCode
 * @description:
 * @author: wd
 * @create: 2020-08-23 15:49
 **/

public class Main_1 {
    /**
     * 正整数n的阶乘(n!)中的末尾有多少个0?
     * <p>
     * 例如：n = 5, n! = 120.末尾有1个0
     * <p>
     * 实现：int CountZero(int n);
     **/
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        System.out.println(CountZero(n));

    }

    static int CountZero(int n) {
        int count_2 = 0;
        int count_5 = 0;
        while (n >= 2) {
            int temp = n;
            while (temp != 0 && temp % 5 == 0) {
                temp /= 5;
                count_5++;
            }
            while (temp != 0 && temp % 2 == 0) {
                temp /= 2;
                count_2++;
            }
            n--;
        }
        return Math.min(count_2, count_5);
    }
}
