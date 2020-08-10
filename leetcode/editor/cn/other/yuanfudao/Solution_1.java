package yuanfudao;

import tags.Index;

import java.util.Scanner;
import java.util.Stack;

/**
 * @program: LeetCode
 * @description:
 * @author: wd
 * @create: 2020-07-23 19:38
 **/

public class Solution_1 {
    public static void main1(String[] args) {
        Solution_1 main = new Solution_1();
        Scanner in = new Scanner(System.in);
        int a = in.nextInt();
        int b = in.nextInt();
        System.out.println(
                main.getNumberOfPlans2(a, b)
        );
    }

    public static void main2(String[] args) {
        Solution_1 main = new Solution_1();
        Scanner in = new Scanner(System.in);
        int m = in.nextInt();
        int n = in.nextInt();
        int k = in.nextInt();
        int[][] g = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                g[i][j] = in.nextInt();
            }
        }
        System.out.println(
                main.getNum(g, k)
        );
    }
    /**
     *  猿辅导APP需要下发一些宣传文本给学生，工程师们使用了一种字符压缩算法，
     *  为简单起见，假设被压缩的字符全部为大写字母序列，A,B,C,D....Z,压缩规则如下：
     * 1.AAAB可以压缩为A3B (单字符压缩不加括号)
     * 2.ABABA可以压缩为(AB)2A （多字符串压缩才加括号）
     *
     * 输入数据保证不会出现冗余括号，且表示重复的数字一定合法且大于1，即不会出现：
     * 1.（A)2B   ------- （应为：A2B）
     * 2.  ((AB))2C,-----(应为：（AB)2C  )
     * 3. （A)B  ----- （应为：AB）
     * 4.   A1B，（AB)1C，（应为 AB，ABC）
     *
     * 注意：数字可能出现多位数即A11B或者(AB)10C或者A02这种情况。
     * A11B = AAAAAAAAAAAB
     * (AB)10C = ABABABABABABABABABABC
     * A02 = AA
     **/

    /**
     * 有一个N*M大小的迷宫矩阵，迷宫的每一个格子有一个数值（a[i][j] <10^9）。
     * 小猿在迷宫中发现，它只能朝着上下左右四个方向的相邻格子前进，并且只能进入比当前位置数值更大的格子。
     * 但是小猿有个紧急呼救按钮，他可以通过按下按钮，强行进入到不满足数值大小要求的相邻格子，
     * 可惜这个按钮只能按K次。请问小猿从这个迷宫任选一个格子出发，在紧急呼救按钮的帮助下，
     * 最多能走多少步（开始位置计入步数，即站在起点是步数为1）
     **/

    private int getNum(int[][] g, int k) {
        int m = g.length;
        int n = g[0].length;
        boolean[] vis = new boolean[m * n];
        int max = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                max = Math.max(max, getNumDFS(m, n, i, j, vis, g, k));
            }
        }
        return max;

    }

    private int getNumDFS(int m, int n, int x, int y, boolean[] vis, int[][] g, int k) {
        vis[x * n + y] = true;
        int max = 0;
        for (int i = 0; i < 8; i += 2) {
            int tox = x + Index.nextIndexOfFour[i];
            int toy = y + Index.nextIndexOfFour[i + 1];
            if (tox < 0 || toy < 0 || tox >= m || toy >= n) continue;
            if (vis[tox * n + toy]) continue;
            if (g[tox][toy] > g[x][y]) {
                max = Math.max(max, getNumDFS(m, n, tox, toy, vis, g, k));
            } else {
                if (k > 0) {
                    max = Math.max(max, getNumDFS(m, n, tox, toy, vis, g, k - 1));
                }
            }
        }
        vis[x * n + y] = false;
        return max + 1;
    }

    /**
     * K(K>=3)个猿辅导的老师们在玩一个击鼓传花的小游戏。
     * 每击一次鼓，拿着花的老师要将花交给别人，不能留在自己手中。
     * 游戏开始前花在小猿手中，求击了N次鼓后，
     * 这朵花又回到小猿手中的方案数，请输出这个数
     * 模 1000000007后的结果。
     **/
    public int getNumberOfPlans(int n, int k) {
        final int MOD = 1000000007;
        if (n == 0 || k <= 0) return 1;
        long a = 1, b = 0, a1, b1;

        int ans = 1;
        for (int i = 1; i <= n; i++) {
            a1 = b * (k - 1) % MOD;
            b1 = ((k - 2) * b + a) % MOD;
            a = a1;
            b = b1;
        }
        return (int) a;
    }

    public int getNumberOfPlans2(int n, int k) {
        final int MOD = 1000000007;

        if (n == 0 || k <= 0) return 0;
        if (n == 2) return k - 1;
        long[][] A = {{0, k - 1}, {1, k - 2}};

        long[][] ans = pow(A, n);
        return (int) ans[0][0];
    }

    private long[][] pow(long[][] A, int n) {
        Stack<Integer> stack;
        long[][] ans = new long[][]{{1, 0}, {0, 1}};
        long[][] ret = A;

        while (n != 0) {
            if (n % 2 == 1) multi(ans, ret);
            multi(A, A);
            n /= 2;
        }
        return ans;
    }

    private void multi(long[][] A, long[][] B) {
        long a00, a01, a10, a11;
        int M;
        M = 1000000007;
        a00 = (A[0][0] * B[0][0] + A[0][1] * B[1][0]) % M;
        a01 = (A[0][0] * B[0][1] + A[0][1] * B[1][1]) % M;
        a10 = (A[1][0] * B[0][0] + A[1][1] * B[1][0]) % M;
        a11 = (A[1][0] * B[0][1] + A[1][1] * B[1][1]) % M;
        A[0][0] = a00;
        A[0][1] = a01;
        A[1][0] = a10;
        A[1][1] = a11;
    }

}
