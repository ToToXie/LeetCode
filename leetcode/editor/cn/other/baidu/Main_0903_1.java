package baidu;

import java.util.Scanner;

/**
 * @program: LeetCode
 * @description:
 * @author: wd
 * @create: 2020-09-03 19:47
 **/

public class Main_0903_1 {
    /**
     * 牛牛有n张卡片，每张卡片上要么是0，要么是5。牛牛能从其中选出若干卡片然后组成一些数字，
     * 现在牛牛想请你找出所有可能的数字中能整除90的最大数字。若不存在则输出-1.
     * <p>
     * 11
     * 5 5 5 5 5 5 5 5 0 5 5
     * <p>
     * 5555555550
     **/
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] nums = new int[n];
        int a = 0;
        for (int i = 0; i < n; i++) {
            nums[i] = in.nextInt();
            if (nums[i] == 5) a++;
        }
        if (a == n || a < 9) System.out.println(-1);
        else {
            StringBuilder sb = new StringBuilder();
            int tem = 0;
            n = n - (a - a / 9 * 9);
            a = a / 9 * 9;
            while (tem < n) {
                if (tem < a) {
                    sb.append(5);
                } else {
                    sb.append(0);
                }
                tem++;
            }
            System.out.println(sb.toString());
        }
    }
}
