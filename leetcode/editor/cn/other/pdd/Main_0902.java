package pdd;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @program: LeetCode
 * @description:
 * @author: wd
 * @create: 2020-09-01 20:12
 **/

public class Main_0902 {

    static int[] index = {0, 1, 1, 0, 0, -1, -1, 0};
    private static int[][] nums;
    private static boolean[][] vis;
    private static int m;
    private static int n;
    private static int ans = 0;
    private static int sum = 0;

    /**
     * 4 4
     * 1 0 1 1
     * 1 1 0 1
     * 0 0 0 0
     * 1 1 1 1
     * 8
     * <p>
     * 多多最近在玩一款叫做《野蛮六》的回合制策略游戏。在这个游戏中，地图可以视为一个 N * M 的矩形，划分为 N * M 个小正方形的格子。
     * 一个格子的上、下、左、右4个格子视为与该格子「相邻」。
     * 玩家可以在每个格子上布置一个士兵。并且每个士兵可以和相邻的士兵归为同一个队伍。在这个游戏中，同一个队伍的士兵数量越多，就越强大。
     * 多多现在有一个道具可以移动任意一个格子上的士兵到任意一个空格子中。求移动后可得到的最大队伍士兵数。
     * <p>
     * 第一行输入两个整数 N，M (1 <= N, M <= 400)，分别代表格子的行数和列数。
     * 接下来有 N 行，每行有 M 个数字（以空格隔开），数字为 0 或 1，代表每个格子里的士兵数量。
     **/
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        m = in.nextInt();
        n = in.nextInt();
        nums = new int[m][n];
        vis = new boolean[m][n];
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                nums[i][j] = in.nextInt();
                if (nums[i][j] == 1) count++;
            }
        }

        System.out.println(getAns(count));

    }

    static int getAns(int count) {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (nums[i][j] == 0) {
                    for (int k = 0; k < m; k++) {
                        Arrays.fill(vis[k], false);
                    }
                    sum = 0;
                    DFS(i, j);
                    ans = Math.min(ans, count);
                    if (ans == count) {
                        return ans;
                    }
                }
            }
        }
        return ans;
    }

    static void DFS(int x, int y) {
        vis[x][y] = true;
        sum++;
        ans = Math.max(ans, sum);
        for (int i = 0; i < 8; i += 2) {
            int newx = x + index[i];
            int newy = y + index[i + 1];
            if (newx >= m || newx < 0 || newy < 0 || newy >= n || vis[newx][newy] || nums[newx][newy] == 0) continue;
            DFS(newx, newy);
        }
    }

}
