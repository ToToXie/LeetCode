package netease;

import java.util.Scanner;

/**
 * @program: LeetCode
 * @description:
 * @author: wd
 * @create: 2020-08-08 16:03
 **/

public class Main_3 {
    static int[] nums;
    static int n;
    static int ans;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for (int i = 0; i < t; i++) {
            ans = Integer.MAX_VALUE;
            n = in.nextInt();
            nums = new int[n];
            for (int j = 0; j < n; j++) {
                nums[j] = in.nextInt();
            }
            DFS(0, 0, 0, 0);
            System.out.println(ans);
        }
    }

    static void DFS(int index, int suma, int sumb, int aban) {
        if (index == n) {
            if (suma == sumb) {
                ans = Math.min(ans, aban);
            }
            return;
        } else {
            DFS(index + 1, suma + nums[index], sumb, aban);
            DFS(index + 1, suma, sumb + nums[index], aban);
            DFS(index + 1, suma, sumb, aban + nums[index]);
        }
    }
}
