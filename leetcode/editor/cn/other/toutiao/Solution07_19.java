package toutiao;

import java.util.Arrays;

/**
 * @program: LeetCode
 * @description:
 * @author: wd
 * @create: 2020-07-19 17:11
 **/

public class Solution07_19 {
    public static void main(String[] args) {
        Solution07_19 main = new Solution07_19();
        System.out.println(

                main.getProbabilityByKTimes(8, 4, 5, 7)

        );
    }

    /**
     * 棋盘 n*n
     * i，j 点有一个棋子
     * 棋子只能从上下左右四个方向走，每次走一格
     * 问：k 步后，这个棋子还在棋盘的概率有多大？
     **/
    private double getProbabilityByKTimes(int n, int x, int y, int k) {
        if (x < 0 || x >= n || y < 0 || y >= n) return 0;
        double[][] dp = new double[n][n];
        dp[x][y] = 1;
        int[] index = {0, 1, 0, -1, 1, 0, -1, 0};
        for (int t = 0; t < k; t++) {
            double[][] temp = new double[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    for (int z = 0; z < 8; z += 2) {
                        int toX = i + index[z];
                        int toY = j + index[z + 1];
                        if (toX < 0 || toX >= n || toY < 0 || toY >= n) continue;
                        temp[toX][toY] += dp[i][j] / 4;

                    }
                }
            }
            dp = temp;
        }
        return Arrays.stream(dp).mapToDouble(it -> Arrays.stream(it).sum()).sum();
    }
}
