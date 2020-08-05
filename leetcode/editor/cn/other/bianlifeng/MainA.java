package bianlifeng;

import java.util.Scanner;

/**
 * @program: LeetCode
 * @description:
 * @author: wd
 * @create: 2020-05-07 19:07
 **/

public class MainA {
    private static int ans = 0;
    private static int[] widths;
    private static int[] values;
    private static int n;
    private static int length;

    /**
     * @Description: 背包问题，答案错误，是因为超时了，。。。。
     * 还以为是答案错误
     **/
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        length = Integer.valueOf(in.nextLine());
        String str = "";
        if (in.hasNext()) {
            str = in.nextLine();
        }
        String[] split = str.split(",");
        n = split.length;
        int[] w = new int[n];
        int[] v = new int[n];
        for (int i = 0; i < n; i++) {
            w[i] = Integer.valueOf(split[i]);
        }
        if (in.hasNext()) {
            str = in.nextLine();
        }
        split = str.split(",");
        for (int i = 0; i < n; i++) {
            v[i] = Integer.valueOf(split[i]);
        }
        widths = w;
        values = v;
        layoutDp(length, widths, values);
        in.close();


    }

    public static int layoutDp(int length, int[] widths, int[] values) {
        int[] dp = new int[length + 1];
        for (int i = 0; i <= length; i++) {
            dp[i] = 0;
        }

        for (int i = 0; i < n; i++) {
            for (int j = length; j >= widths[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - widths[i]] + values[i]);
            }
        }
        for (int i = 0; i <= length; i++) {
            ans = Math.max(ans, dp[i]);
        }
        System.out.println(ans);
        return ans;
    }

    public static int layout(int length, int[] widths, int[] values) {
        if (length <= 0 || widths == null || values == null || widths.length < 1 || values.length < 1) {
            System.out.println(ans);
            return ans;
        }
        layoutCore(0, length, 0);
        System.out.println(ans);
        return ans;
    }

    public static void layoutCore(int sumValue, int length, int index) {
        if (length <= 0) return;
        if (index >= n) {
            ans = Math.max(ans, sumValue);
            return;
        }
        layoutCore(sumValue + values[index], length - widths[index], index + 1);
        layoutCore(sumValue, length, index + 1);

    }
}
