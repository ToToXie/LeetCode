package yuanfudao;

import tags.Index;

import java.util.Scanner;

/**
 * @program: LeetCode
 * @description:
 * @author: wd
 * @create: 2020-07-25 00:14
 **/

public class Solution_2 {
    /**
     * 有一个N*M大小的迷宫矩阵，迷宫的每一个格子有一个数值（a[i][j] <10^9）。
     * 小猿在迷宫中发现，它只能朝着上下左右四个方向的相邻格子前进，并且只能进入比当前位置数值更大的格子。
     * 但是小猿有个紧急呼救按钮，他可以通过按下按钮，强行进入到不满足数值大小要求的相邻格子，
     * 可惜这个按钮只能按K次。请问小猿从这个迷宫任选一个格子出发，在紧急呼救按钮的帮助下，
     * 最多能走多少步（开始位置计入步数，即站在起点是步数为1）
     **/
    static int m, n, k;
    static int[][] g;
    static int[][][] dp;

    public static void main(String[] args) {
        Solution_2 main = new Solution_2();
        Scanner in = new Scanner(System.in);
        m = in.nextInt();
        n = in.nextInt();
        k = in.nextInt();
        g = new int[m][n];
        dp = new int[m][n][k + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                g[i][j] = in.nextInt();
            }
        }
        System.out.println(
                main.getNum(g, k)
        );
    }

    private int getNum(int[][] g, int k) {
        int max = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                max = Math.max(max, getNumDFS(i, j, k));
            }
        }
        return max;

    }

    private int getNumDFS(int x, int y, int k) {
        if (k < 0) return 0;
        if (dp[x][y][k] != 0) return dp[x][y][k];
        dp[x][y][k] = 1;
        for (int i = 0; i < 8; i += 2) {
            int tox = x + Index.nextIndexOfFour[i];
            int toy = y + Index.nextIndexOfFour[i + 1];
            if (tox < 0 || toy < 0 || tox >= m || toy >= n) continue;
            if (g[tox][toy] > g[x][y]) {
                dp[x][y][k] = Math.max(dp[x][y][k], getNumDFS(tox, toy, k) + 1);
            } else if (k > 0) {
                dp[x][y][k] = Math.max(dp[x][y][k], getNumDFS(tox, toy, k - 1) + 1);
            }
        }
        return dp[x][y][k];
    }
}
