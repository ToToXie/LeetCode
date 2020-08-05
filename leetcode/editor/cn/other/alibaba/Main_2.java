package alibaba;

import java.util.Scanner;

/**
 * @program: LeetCode
 * @description:
 * @author: wd
 * @create: 2020-07-29 09:26
 **/

public class Main_2 {
    /**
     *  5
     * 2 1 4 3
     * 9
     *
     * 6
     * 3 3 2 5 3
     * 15
     **/
    /**
     * 京都有nn个驿站排成一行且从左到右由1~n依次编号.并且有(n-1)个道路连接相邻的驿站.对于每个道路都有一个权重a[i]
     * 小强定义: 当每经过一次道路,其对应权重减1,但你会获得1分的价值.
     * 现在小强请你帮他计算出在选择任意一个驿站作为起点且满足不经过权重为0的道路的条件下所能获得的最大价值是多少?
     **/
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] ints = new int[n - 1];
        for (int i = 0; i < n - 1; i++) {
            ints[i] = in.nextInt();
        }
        int max = -1;
        for (int i = 0; i < n; i++) {
            max = Math.max(DFS(ints, i, i + 1, n - 1), max);
            max = Math.max(DFS(ints, i, i - 1, n - 1), max);
        }
        System.out.println(max);
    }

    static private int DFS(int[] ints, int s, int e, int n) {
        if (e < 0 || e > n) {
            return 0;
        } else if (ints[Math.min(s, e)] == 0) {
            return 0;
        } else {
            ints[Math.min(s, e)]--;
            int ans = 0;
            ans = Math.max(DFS(ints, e, e + 1, n), DFS(ints, e, e - 1, n));
            ints[Math.min(s, e)]++;
            return ans + 1;
        }
    }
}
