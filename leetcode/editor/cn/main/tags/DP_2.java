package tags;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @program: LeetCode
 * @description:
 * @author: wd
 * @create: 2020-08-16 10:07
 **/

public class DP_2 {
    static int[] ints1 = {1, 2, 3};
    /**
     * 264 丑数2
     **/
    static List<Integer> list = new ArrayList<>();
    static int a = 0, b = 0, c = 0;

    public static void main(String[] args) {
        DP_2 dp = new DP_2();
//        System.out.println(dp.nthUglyNumber(5));
//        System.out.println(dp.nthUglyNumber(10));
        System.out.println(Arrays.toString(dp.countBits(5)));
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
