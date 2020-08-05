package alibaba;

import java.util.Arrays;
import java.util.Scanner;


public class Test {
    public final char S = 'S';
    public final char X = 'X';
    public final int[] next = {0, 1, 1, 0, -1, 0, 0, -1};

    public static void main(String[] args) {
        Test test = new Test();

//        LC802 lc802 = new LC802();
//        test.getOut();
        test.getMaxNum();
    }

    /**
     * @Description: 题目描述
     * 有一个的图，图中有且仅有一块X的连通区域代表安全区域，有一个S代表起点，
     * 现在想让你在时间秒内从起点走到安全区域，图中C代表是车，有车的话可以走2个格，
     * 没车的话走一个格子，.代表空地，O代表障碍物无法通过，现在想问你是否可以在给定时间内到达安全区域，
     * 若可以的话先输出YES，在输出最短时间，若不可以输出NO。
     * 输入描述:
     * 第一行输入一个整数，代表有组测试数据。
     * 接下来 组，每一组第一行输入两个整数，代表图的大小和需要的时间。
     * 接下来行，每一行输入个字符，代表这个图内的情况。
     * 3
     * 2 3
     * .X
     * S.
     * 2 3
     * .X
     * SC
     * 2 4
     * .X
     * S.
     * <p>
     * 输出描述:
     * 对于每一组数据，若可以到达输出两行，第一行YES，第二行为最短时间，若不可以到达输出一行NO。
     * 示例1输入输出示例仅供调试，后台判
     **/
    public void getOut() {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        int n, t;

        for (int i = 0; i < T; i++) {
            n = in.nextInt();
            t = in.nextInt();
            char[][] G = new char[n][n];
            int x = -1, y = -1;
            for (int j = 0; j < n; j++) {
                String str;
                str = in.next();
                int index = 0;
                for (Character c : str.toCharArray()) {
                    if (c == S) {
                        x = j;
                        y = index;
                    }
                    G[j][index++] = c;
                }
            }
            boolean[][] vis = new boolean[n][n];
            if (x < 0) {
                System.out.println("NO");
            } else {
                vis[x][y] = true;
                int dfs = dfs(G, x, y, n, vis, 0, t);
                if (dfs != Integer.MAX_VALUE) {
                    System.out.println("YES");
                    System.out.println(dfs);
                } else {
                    System.out.println("NO");
                }
            }

        }
    }


    public int dfs(char[][] G, int x, int y, int n,
                   boolean[][] vis, int sum, int limit) {
        if (G[x][y] == 'O') return Integer.MAX_VALUE;
        if (sum > limit) return Integer.MAX_VALUE;
        if (G[x][y] == X) return 1;
        int temp = Integer.MAX_VALUE;
        for (int i = 0; i < 8; i += 2) {
            int nowx = x + next[i];
            int nowy = y + next[i + 1];
            if (nowx >= 0 && nowx < n && nowy >= 0 && nowy < n && !vis[nowx][nowy]) {
                vis[nowx][nowy] = true;
                if (G[nowx][nowy] == 'C') {
                    temp = Math.min(temp, dfs(G, nowx, nowy, n, vis, sum, limit));
                } else {
                    temp = Math.min(temp, dfs(G, nowx, nowy, n, vis, sum + 1, limit));
                }
                vis[nowx][nowy] = false;
            }
        }
        return sum + temp;
    }

    /**
     * @Description: 小强有一个长度为n的整数数组和一个数字m，他要在数组中选择一个子序列，使得这个子序列的和对m取模后的结果最大，
     * 子序列可以为空，请问这个结果最大为多少。
     * 输入描述:
     * 第一行，两个正整数。n m
     * 第二行，n个正整数。
     **/
    public int getMaxNum() {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = in.nextInt();
        }
        Arrays.sort(nums);
        int sum = 0;
        for (int it : nums) {
            if (sum + it < m) {
                sum += it;
            }
        }
        System.out.println(sum);
        return sum;
    }

}
