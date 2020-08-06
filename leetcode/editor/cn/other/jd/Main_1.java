package jd;

/**
 * @program: LeetCode
 * @description:
 * @author: wd
 * @create: 2020-08-06 15:40
 **/

public class Main_1 {
    /**
     * 小东所在公司要发年终奖，而小东恰好获得了最高福利，他要在公司年会上参与一个抽奖游戏，
     * 游戏在一个6*6的棋盘上进行，上面放着36个价值不等的礼物，每个小的棋盘上面放置着一个礼物，
     * 他需要从左上角开始游戏，每次只能向下或者向右移动一步，到达右下角停止，一路上的格子里的礼物小东都能拿到，
     * 请设计一个算法使小东拿到价值最高的礼物。
     * <p>
     * 给定一个6*6的矩阵board，其中每个元素为对应格子的礼物价值,左上角为[0,0],请返回能获得的最大价值，
     * 保证每个礼物价值大于100小于1000。
     **/
    public int getMost(int[][] board) {
        // write code here
        if (board == null || board.length < 1) return 0;
        int m = board.length;
        int n = board[0].length;
        int[] dp = new int[n];
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (j > 0) {
                    dp[j] = Math.max(dp[j], dp[j - 1]) + board[i][j];
                } else {
                    dp[j] += board[i][j];
                }
                max = Math.max(max, dp[j]);
            }
        }
        return max;
    }
    /**
     *  题目描述
     * 有一个含有n个数字的序列，每个数字的大小是不超过200的正整数，同时这个序列满足以下条件：
     * 1. a_1<=a_2
     * 2. a_n<=a_(n-1) （此时n>2）
     * 3. a_i<=max(a_{i-1},a_{i+1})
     * 但是很不幸的是，在序列保存的过程中，有些数字丢失了，请你根据上述条件，计算可能有多少种不同的序列可以满足以上条件。
     *
     * 输入描述:
     * 输入第一行是一个n，表示这个序列的长度。(3<=n<=10^4)
     *
     * 输入第二行有n个非负整数，中间用空格隔开，如果数字为0，说明这个数字丢失了，其他数字则都在1-200之间。
     * 输出描述:
     * 输出仅包含一个整数，即方案数对998244353取模的结果。
     *
     * 3
     * 2 0 1
     * 输出
     * 1
     **/
}
