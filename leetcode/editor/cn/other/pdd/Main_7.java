package pdd;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @program: LeetCode
 * @description:
 * @author: wd
 * @create: 2020-08-02 19:29
 **/

public class Main_7 {
    /**
     * n m t 分别为 中餐种类 晚餐种类 美味值
     * 下面 n + m 行 ，每行 a热量值 b 美味值
     * 求 达到美味值 t 的情况下，热量最低的情况
     * 中餐 晚餐 只要能达到美味值 吃不吃都行
     * 分组背包
     **/
/*
3 3 9
8 9
3 1
7 4

5 10
7 5
7 2
*/
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int t = in.nextInt();
        Node[] numN = new Node[n];
        Node[] numM = new Node[m];
        int maxN = -1, maxM = -1, ans = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            maxN = Math.max(y, maxN);
            if (y >= t) ans = Math.min(ans, x);
            numN[i] = new Node(x, y);
        }
        for (int i = 0; i < m; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            maxM = Math.max(maxM, y);
            if (y >= t) ans = Math.min(ans, x);
            numM[i] = new Node(x, y);
        }

        if (t == 0) {
            System.out.println(0);
        } else if (maxM + maxN < t) {
            System.out.println(-1);
        } else {
            /**
             *  我们容易发现单调性 为了凑够美味度 从美味度从大到小
             *  热量是具有单调递减特性的（举个例子 如果你5热量可以满足7美味度
             *  那么5热量肯定满足<=7的美味度） 那么我们维护一个MINN数组从大到小维护
             *  下标 i 代表至少达到i美味度你需要吃的热量
             *  那么维护的时候 MINN[i] = min(MINN[I+1],MINN[i])
             **/
            int[] dp = new int[maxM + maxN + 1];
            Arrays.fill(dp, Integer.MAX_VALUE);
            for (int i = 0; i < n; i++) {
                dp[numN[i].y] = Math.min(dp[numN[i].y], numN[i].x);
            }
            for (int i = maxM + maxN - 1; i > 0; i--) {
                dp[i] = Math.min(dp[i], dp[i + 1]);
            }
            for (int i = 0; i < m; i++) {
                if (numM[i].y >= t) continue;
                ans = Math.min(ans, dp[t - numM[i].y] + numM[i].x);
            }
            System.out.println(ans);
        }

    }

    static class Node {
        public int x;
        public int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
