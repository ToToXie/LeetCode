package alibaba;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @program: LeetCode
 * @description:
 * @author: wd
 * @create: 2020-04-23 12:04
 **/

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.test();
    }

    /**
     * @Description: n个城市n个人，每个城市一个人，选择一个城市x，所有人去那聚会，聚合结束所有人返回各自城市。
     * 有m条单向路径，保证每个人可以到达城市x，一个人所消耗时间为往返距离和，且每个人都会选择最短路径，问最长的距离是多少。
     * 第一行 输入 n,m,l 对应城市、路径、聚会城市
     * 接下来m行，x,y,z从城市x到城市y的距离为z
     * 输出
     * 输出最长距离
     **/
    public int[] Dijkstra(int[][] g, int s) {
        int length = g.length;
        int[] dis = new int[length];
        Arrays.fill(dis, Integer.MAX_VALUE);
        boolean[] vis = new boolean[length];
        dis[s] = 0;
//        vis[s] = true;
        for (int i = 0; i < length; i++) {
            int u = -1, min = Integer.MAX_VALUE;
            for (int j = 0; j < length; j++) {
                if (!vis[j] && dis[j] < min) {
                    u = j;
                    min = dis[j];
                }
            }
            if (u == -1) break;
            vis[u] = true;
            for (int v = 0; v < length; v++) {
                if (!vis[v] && g[u][v] != 0 && dis[v] > dis[u] + g[u][v]) {
                    dis[v] = dis[u] + g[u][v];
                }
            }

        }
        return dis;
    }

    public void test() {
        Scanner in = new Scanner(System.in);
        int n, m, l;
        n = in.nextInt();
        m = in.nextInt();
        l = in.nextInt();
        int[][] gGo = new int[n][n];
        int[][] gBack = new int[n][n];
        int x, y, z;
        for (int i = 0; i < m; i++) {
            x = in.nextInt();
            y = in.nextInt();
            z = in.nextInt();
            gGo[y - 1][x - 1] = z;
            gBack[x - 1][y - 1] = z;
        }
        int[] dijkstra1 = Dijkstra(gGo, l - 1);
        int[] dijkstra2 = Dijkstra(gBack, l - 1);
        int max = -1;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, dijkstra1[i] + dijkstra2[i]);
        }
        System.out.println(max);

    }
}
