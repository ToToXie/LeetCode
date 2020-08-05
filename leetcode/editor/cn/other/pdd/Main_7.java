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
        int maxN = -1, maxM = -1;
        for (int i = 0; i < n; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            maxN = Math.max(y, maxN);
            numN[i] = new Node(x, y);
        }
        for (int i = 0; i < m; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            maxM = Math.max(maxM, y);
            numM[i] = new Node(x, y);
        }

        if (t == 0) {
            System.out.println(0);
        } else if (maxM + maxN < t) {
            System.out.println(-1);
        } else {
//            暴力法
//            int ans = Integer.MAX_VALUE;
//            for (int i = 0; i < n; i++) {
//                Node a = numN[i];
//                if(a.y >= t) ans = Math.min(ans, a.x);
//                for (int j = 0; j < m; j++) {
//                    Node b = numM[j];
//                    if(b.y >= t) ans = Math.min(ans, b.x);
//                    if(a.y + b.y >= t){
//                        ans = Math.min(ans, a.x+b.x);
//                    }
//                }
//            }
//            System.out.println(ans);


//            dp[k][v] 第k组 物品 放入 空间v的背包中
//            F [k， v] 表示前 k 组物品花费费用 v 能取得的最大权值
            int ans = Integer.MAX_VALUE;
            int V = maxM + maxN + 1;
            int[] dp = new int[V];
            Arrays.fill(dp, Integer.MAX_VALUE);
            dp[0] = 0;
            for (int i = 0; i < 2; i++) {
                if (i == 0) {
                    for (int v = V - 1; v >= 0; v--) {
                        for (int j = 0; j < numN.length; j++) {
                            if (v - numN[j].y >= 0) dp[v] = Math.min(dp[v], dp[v - numN[j].y] + numN[j].x);
                            if (v >= t) ans = Math.min(ans, dp[v]);
                        }
                    }
                } else {
                    for (int v = V - 1; v >= 0; v--) {
                        for (int j = 0; j < numM.length; j++) {
                            if (v - numM[j].y >= 0) dp[v] = Math.min(dp[v], dp[v - numM[j].y] + numM[j].x);
                            if (v >= t) ans = Math.min(ans, dp[v]);
                        }
                    }
                }
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
