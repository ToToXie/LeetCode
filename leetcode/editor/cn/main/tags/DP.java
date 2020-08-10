package tags;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @program: LeetCode
 * @description:
 * @author: wd
 * @create: 2020-08-09 15:05
 **/

public class DP {

    public static final Scanner IN = new Scanner(System.in);

    public static void main(String[] args) {
//        getMaxSumInMap();
//        getMinCostInShop();
//        getMaxValueInOdd();
        getNumsOfPlans();
    }

    /**
     * 状态压缩dp
     * <p>
     * 给你一个n*n的格子的棋盘，每个格子里面有一个非负数。
     * 从中取出若干个数，使得任意的两个数所在的格子没有公共边，
     * 就是说所取的数所在的2个格子不能相邻，并且取出的数的和最大。
     * <p>
     * 包括多个测试实例，每个测试实例包括一个整数n 和n*n个非负数(n<=20)
     * 对于每个测试实例，输出可能取得的最大的和
     * <p>
     * 3
     * 75 15 21
     * 75 15 28
     * 34 70 5
     * 188
     **/
    static void getMaxSumInMap() {
        int n = IN.nextInt();
        int[][] nums = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                nums[i][j] = IN.nextInt();
            }
        }
        int size = (1 << n) - 1;

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i <= size; i++) {
            if (((i << 1) & i) == 0 && ((i >> 1) & i) == 0) {
                list.add(i);
            }
        }
        size = list.size();
        int[][] dp = new int[n][size + 1];
        size++;
        for (int i = 0; i < size; i++) {
            dp[0][i] = getLineSumInDp(nums, 0, i);
        }
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < size; j++) {
                int now = getLineSumInDp(nums, i, j);
                for (int k = 0; k < size; k++) {
                    if ((j & k) == 0) {
                        dp[i][j] = Math.max(dp[i][j], dp[i - 1][k] + now);
                    }
                }
            }
        }
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < size; i++) {
            ans = Math.max(ans, dp[n - 1][i]);
        }
        System.out.println(ans);

    }

    static int getLineSumInDp(int[][] nums, int line, int dp) {
        String s = Integer.toBinaryString(dp);
        int n = nums.length;
        ;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            if (s.length() - i - 1 >= 0 && s.charAt(s.length() - i - 1) == '1') {
                sum += nums[line][n - 1 - i];
            }
        }
        return sum;
    }

    /**
     * 状态压缩dp
     * 杂货铺老板一共有N件物品，每件物品具有ABC三种属性中的一种或多种。
     * 从杂货铺老板处购得一件物品需要支付相应的代价。现在你需要计算出如何购买物品，
     * 可以使得ABC三种属性都在购买的物品中出现，并且支付的总代价最小。
     * <p>
     * 输入 第一行包含一个整数N。 以下N行，
     * 每行包含一个整数C和一个只包含"ABC"的字符串，代表购得该物品的代价和其具有的属性。
     * 对于50%的数据，1 ≤ N ≤ 20
     * 对于100%的数据，1 ≤ N ≤ 1000 1 ≤ C ≤ 100000
     * 输出 一个整数，代表最小的代价。如果无论如何凑不齐ABC三种属性，输出-1。
     * 样例输入
     * 5
     * 10 A
     * 9 BC
     * 11 CA
     * 4 A
     * 5 B
     * 样例输出
     * 13
     **/
    static void getMinCostInShop() {
        int n = IN.nextInt();
        int size = (1 << 3) - 1;
        int[] dp = new int[size + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        size++;
        for (int i = 0; i < n; i++) {
            int a = IN.nextInt();
            String str = IN.next();
            int temp = 0;
            for (int j = 0; j < str.length(); j++) {
                if (str.charAt(j) == 'A') temp += 1;
                if (str.charAt(j) == 'B') temp += 2;
                if (str.charAt(j) == 'C') temp += 4;
            }
            for (int j = 0; j < size; j++) {
                if (dp[j] != Integer.MAX_VALUE) dp[j | temp] = Math.min(dp[j | temp], dp[j] + a);
            }

        }
        System.out.println(dp[7]);
    }

    /**
     * 小Hi现在有n个物品，每个物品都有一个价值。并且这n个物品总共有m个不同的属性，每个物品都具有其中若干属性。
     * 小Ho要从中选出若干物品，满足每个属性都正好有奇数个物品拥有，且被选出的物品价值总和最大。
     * 你能帮助小Ho完成任务么？
     * <p>
     * 输入
     * <p>
     * 第一行一个数T(<=10)，表示数据组数。对于每一组数据：
     * <p>
     * 第一行两个数n，m（1<=n<=1000，m<=10）
     * <p>
     * 接下来每两行描述一件物品。对于每一件物品：
     * <p>
     * 第一行两个数v和s，表示其价值和所含属性数量(v<=100000,s<=m)
     * <p>
     * 第二行s个数，表示该物品拥有的属性编号(1<=编号<=m)
     * <p>
     * 输出
     * <p>
     * 物品价值总和的最大值。
     * <p>
     * 样例输入
     * <p>
     * 1
     * 3 2
     * 2 1
     * 1
     * 2 1
     * 2
     * 5 2
     * 1 2
     * <p>
     * 样例输出
     * <p>
     * 5
     **/
    static void getMaxValueInOdd() {
        int T = IN.nextInt();
        int n = IN.nextInt();
        int m = IN.nextInt();
        int size = 1 << m;
        int[][] dp = new int[n + 1][size];
        for (int[] ints : dp) {
            Arrays.fill(ints, Integer.MIN_VALUE);
        }
        dp[0][0] = 0;
        for (int t = 0; t < T; t++) {
            for (int i = 1; i <= n; i++) {
                int val = IN.nextInt();
                int s = IN.nextInt();
                int temp = 0;
                for (int j = 0; j < s; j++) {
                    int p = IN.nextInt();
                    temp = temp | (1 << (p - 1));
                }
                for (int j = 0; j < size; j++) {
                    if (dp[i - 1][j] != Integer.MIN_VALUE) {
                        /**
                         *  选择 第i件 物品
                         *  则 dp[i - 1] 中的所有 这状态都有可能 被 更新
                         *  令 k = j ^ temp
                         *  则 dp[i][k] = 与 dp[i][k] dp[i - 1][j] 有关
                         **/
                        dp[i][j ^ temp] = Math.max(dp[i][j ^ temp], dp[i - 1][j] + val);
                        /**
                         *  不算第 i件物品 则 dp[i] 会受到 dp[i - 1] 的影响
                         **/
                        dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);
                    }
                }

            }
            System.out.println(dp[n][size - 1]);
        }

    }

    /**
     * 在N×N的棋盘里面放K个国王，使他们互不攻击，共有多少种摆放方案。
     * 国王能攻击到它上下左右，
     * 以及左上左下右上右下八个方向上附近的各一个格子，共8个格子。
     * 输入
     * 只有一行，包含两个数N，K （ 1 <=N <=9, 0 <= K <= N * N）
     * 输出
     * 所得的方案数
     * 样例输入
     * 3 2
     * 样例输出
     * 16
     **/
    static void getNumsOfPlans() {
        int n = IN.nextInt();
        int K = IN.nextInt();
        int size = (1 << n);
        List<Integer> list = new ArrayList<>();
        List<Integer> need = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            if (((i << 1) & i) == 0 && ((i >> 1) & i) == 0) {
                list.add(i);
                int temp = 0;
                int now = i;
                while (now != 0) {
                    temp++;
                    now &= (now - 1);
                }
                need.add(temp);
            }
        }
        int[][][] dp = new int[n][list.size()][K + 1];
        for (int i = 0; i < dp[0].length; i++) {
            dp[0][i][need.get(i)] = 1;
        }
//        Arrays.fill(dp[0], 1);
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < list.size(); j++) {
                int dpj = list.get(j);
                int needj = need.get(j);
                for (int k = 0; k < list.size(); k++) {
                    int dpk = list.get(k);
                    if ((dpj & dpk) != 0) continue;
                    if ((dpj & (dpk >> 1)) != 0) continue;
                    if ((dpj & (dpk << 1)) != 0) continue;
                    for (int cnt = K; cnt >= needj; cnt--) {
                        dp[i][j][cnt] += dp[i - 1][k][cnt - needj];
                    }
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < list.size(); i++) {
            ans += dp[n - 1][i][K];
        }
        System.out.println(ans);
    }

}
