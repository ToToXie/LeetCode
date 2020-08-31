package fly.SimpleSearch;

import java.util.Scanner;

/**
 * @program: LeetCode
 * @description:
 * @author: wd
 * @create: 2020-08-31 16:21
 **/

public class POJ_1321 {
    /**
     * https://vjudge.net/problem/POJ-1321
     * 在一个给定形状的棋盘（形状可能是不规则的）上面摆放棋子，棋子没有区别。要求摆放时任意的两个棋子不能放在棋盘中的同一行或者同一列，
     * 请编程求解对于给定形状和大小的棋盘，摆放k个棋子的所有可行的摆放方案C。
     * <p>
     * 输入含有多组测试数据。
     * 每组数据的第一行是两个正整数，n k，用一个空格隔开，表示了将在一个n*n的矩阵内描述棋盘，以及摆放棋子的数目。 n <= 8 , k <= n
     * 当为-1 -1时表示输入结束。
     * 随后的n行描述了棋盘的形状：每行有n个字符，其中 # 表示棋盘区域， . 表示空白区域（数据保证不出现多余的空白行或者空白列）。
     * <p>
     * 对于每一组数据，给出一行输出，输出摆放的方案数目C （数据保证C<2^31）。
     * <p>
     * 2 1
     * #.
     * .#
     * 4 4
     * ...#
     * ..#.
     * .#..
     * #...
     * -1 -1
     * <p>
     * 2
     * 1
     **/
    static boolean[] vis;
    static int sum;
    private static int n;
    private static String[] chars = new String[9];

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (true) {
            n = in.nextInt();
            int k = in.nextInt();
            if (n == -1 && k == -1) break;
            vis = new boolean[n];
            sum = 0;
            for (int i = 0; i < n; i++) {
                chars[i] = in.next();
            }
            DFS(0, k);
            System.out.println(sum);

        }
    }

    static private void DFS(int index, int k) {
        if (k == 0) {
            sum++;
            return;
        }
        for (int i = index; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (chars[i].charAt(j) == '#' && !vis[i]) {
                    vis[i] = true;
                    DFS(index + 1, k - 1);
                    vis[i] = false;
                }
            }
        }


    }
}
