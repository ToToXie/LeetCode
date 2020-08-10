package netease;

import java.util.Scanner;

/**
 * @program: LeetCode
 * @description:
 * @author: wd
 * @create: 2020-08-08 15:03
 **/

public class Main_1 {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        long ans = 0;
        int x = 0;
        for (int i = 0; i < n; i++) {
            x = in.nextInt();
            if (x != 1) {
                ans += x / 2;
            }
        }
        System.out.println(ans);
    }

}
