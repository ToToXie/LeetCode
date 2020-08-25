package tags;

import javafx.util.Pair;
import util.TreeNode;

import java.util.*;

/**
 * @program: LeetCode
 * @description:
 * @author: wd
 * @create: 2020-08-16 10:07
 **/

public class DP_2 {
    static int[] ints1 = {1, 100, 1, 1, 1, 100, 1, 1, 100, 1};
    /**
     * 264 丑数2
     **/
    static List<Integer> list = new ArrayList<>();
    static int a = 0, b = 0, c = 0;

    int max = -1;

    public static void main(String[] args) {
        DP_2 dp = new DP_2();
//        System.out.println(dp.nthUglyNumber(5));
//        System.out.println(dp.nthUglyNumber(10));
        System.out.println(dp.waysToStep(5));
    }

    /**
     * 面试题 08.01 三步问题
     **/
    public int waysToStep(int n) {
        long a = 0, b = 0, c = 0;
        long temp = 1;
        final int MOD = 1000000007;
        for (int i = 0; i < n; i++) {
            a += temp;
            b += temp;
            c += temp;
            temp = a % MOD;
            a = b % MOD;
            b = c % MOD;
            c = 0;
        }
        return (int) temp;
    }

    /**
     * 1025 除数博弈
     **/
    public boolean divisorGame(int N) {
        boolean[] win = new boolean[N + 5];
        win[1] = false;
        win[2] = true;
        for (int i = 3; i <= N; i++) {
            for (int j = 1; j < i; j++) {
                if (i % j == 0 && !win[i - j]) {
                    win[i] = true;
                    break;
                }
            }
        }
        return win[N];
    }

    /**
     * 746 使用最小的力气爬楼梯
     **/
    public int minCostClimbingStairs(int[] cost) {
        int[] dp = new int[cost.length + 1];
        int a = 0, b = 0, c = 0;

        for (int i = cost.length - 1; i >= 0; i--) {
            c = cost[i] + Math.min(a, b);
            b = a;
            a = c;

        }
        return Math.min(a, b);
    }

    /**
     * 508 出现次数最多的子树元素和
     **/
    public int[] findFrequentTreeSum(TreeNode root) {
        if (root == null) return null;
        Map<Integer, Integer> map = new HashMap<>();
        findFrequentTreeSumCore(root, map);
        return map.entrySet().stream().filter(it -> it.getValue() == max).mapToInt(it -> it.getKey()).toArray();

    }

    private int findFrequentTreeSumCore(TreeNode root, Map<Integer, Integer> map) {
        if (root == null) return 0;
        int left = findFrequentTreeSumCore(root.left, map);
        int right = findFrequentTreeSumCore(root.right, map);
        int temp = root.val + left + right;
        map.put(temp, map.getOrDefault(temp, 0) + 1);
        max = Math.max(max, map.get(temp));
        return temp;
    }

    /**
     * 51 n皇后
     **/
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> ans = new LinkedList<>();
        if (n < 1) return ans;
        int[] dp = new int[n];
        boolean[] vis = new boolean[n];
        String str = "";
        for (int i = 0; i < n; i++) {
            str += '.';
        }
        final char[] chars = str.toCharArray();
        solveNQueens(dp, vis, 0, n, ans, new ArrayList<>(), chars);
        return ans;

    }

    public void solveNQueens(int[] dp, boolean[] vis, int index, int n, List<List<String>> ans, List<String> list, char[] chars) {
        if (index == n) {
            ans.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < n; i++) {
            if (!vis[i] && isTrue(dp, index, i)) {
                vis[i] = true;
                dp[index] = i;
                chars[i] = 'Q';
                list.add(new String(chars));
                chars[i] = '.';
                solveNQueens(dp, vis, index + 1, n, ans, list, chars);
                list.remove(list.size() - 1);
                vis[i] = false;
            }
        }

    }

    private boolean isTrue(int[] dp, int index, int value) {
        for (int j = 0; j < index; j++) {
            if (Math.abs(index - j) == Math.abs(value - dp[j])) {
                return false;
            }
        }
        return true;
    }

    /**
     * 52 n皇后 2
     **/
    public int backtrack(int row, int hills, int next_row, int dales, int count, int n) {
        /**
         row: 当前放置皇后的行号
         hills: 主对角线占据情况 [1 = 被占据，0 = 未被占据]
         next_row: 下一行被占据的情况 [1 = 被占据，0 = 未被占据]
         dales: 次对角线占据情况 [1 = 被占据，0 = 未被占据]
         count: 所有可行解的个数
         */

        // 棋盘所有的列都可放置，
        // 即，按位表示为 n 个 '1'
        // bin(cols) = 0b1111 (n = 4), bin(cols) = 0b111 (n = 3)
        // [1 = 可放置]
        int columns = (1 << n) - 1;

        if (row == n)   // 如果已经放置了 n 个皇后
            count++;  // 累加可行解
        else {
            // 当前行可用的列
            // ! 表示 0 和 1 的含义对于变量 hills, next_row and dales的含义是相反的
            // [1 = 未被占据，0 = 被占据]
            int free_columns = columns & ~(hills | next_row | dales);

            // 找到可以放置下一个皇后的列
            while (free_columns != 0) {
                // free_columns 的第一个为 '1' 的位
                // 在该列我们放置当前皇后
                int curr_column = -free_columns & free_columns;

                // 放置皇后
                // 并且排除对应的列
                free_columns ^= curr_column;

                count = backtrack(row + 1,
                        (hills | curr_column) << 1,
                        next_row | curr_column,
                        (dales | curr_column) >> 1,
                        count, n);
            }
        }

        return count;
    }

    public int totalNQueens(int n) {
        return backtrack(0, 0, 0, 0, 0, n);
    }


    /**
     * 338 比特位计数
     **/
    public int[] countBits(int num) {
        if (num == 0) {
            return new int[]{0};
        } else if (num == 1) {
            return new int[]{0, 1};
        }
        int[] nums = new int[num + 1];
        nums[1] = 1;
        int now = 2;
        int left = 1, right = 2;
        while (now <= num) {
            for (int i = left; i < right && now <= num; i++) {
                nums[now++] = nums[i];
            }
            for (int i = left; i < right && now <= num; i++) {
                nums[now++] = 1 + nums[i];
            }
            left = right;
            right = now;
        }
        return nums;
    }

    /**
     * 474 一和零 二维费用的背包问题
     **/
    public int findMaxForm(String[] strs, int m, int n) {
        if (strs == null || strs.length < 1) return 0;
        List<Pair<Integer, Integer>> list = new ArrayList<>(strs.length);
        int[][] dp = new int[m + 1][n + 1];
        dp[0][0] = 0;
        int a, b;
        for (int i = 0; i < strs.length; i++) {
            Pair<Integer, Integer> it = getNumOf_01_str(strs[i]);
            a = it.getKey();
            b = it.getValue();
            for (int j = m; j >= a; j--) {
                for (int k = n; k >= b; k--) {
                    dp[j][k] = Math.max(dp[j][k], dp[j - a][k - b] + 1);
                }
            }
        }
        return dp[m][n];
    }

    private Pair<Integer, Integer> getNumOf_01_str(String str) {
        int a = 0, b = 0;
        for (char c : str.toCharArray()) {
            if (c == '0') {
                a++;
            } else b++;
        }
        return new Pair<>(a, b);
    }

    /**
     * 377 组合总合4
     **/
    public int combinationSum4(int[] nums, int target) {
        if (nums == null || nums.length < 1) return 0;
        int length = nums.length;
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 0; i <= target; i++) {
            for (int j = 0; j < length; j++) {

                if (i - nums[j] >= 0) dp[i] += dp[i - nums[j]];
            }
        }
        return dp[target];
    }

    public int nthUglyNumber(int n) {
        if (list.size() >= n) return list.get(n - 1);
        if (list.size() == 0) list.add(1);
        int numa = 0, numb = 0, numc = 0, min;
        while (list.size() < n) {
            numa = list.get(a);
            numb = list.get(b);
            numc = list.get(c);
            min = Math.min(numa * 2, Math.min(numb * 3, numc * 5));
            if (min == numa * 2) {
                a++;
            } else if (min == numb * 3) {
                b++;
            } else {
                c++;
            }
            if (min != list.get(list.size() - 1)) {
                list.add(min);
            }
        }
        System.out.println(list.toString());
        return list.get(list.size() - 1);
    }

}
