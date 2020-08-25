package Tenngxun;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @program: LeetCode
 * @description:
 * @author: wd
 * @create: 2020-08-23 20:52
 **/

public class Main_5 {
    /**
     * 小Q喜欢比较回文串，有一天他突然想知道s串在[l,r]最少被拆分成多少个回文子串，他想请聪明的你帮帮他。
     * 输入包含一组样例，接下来一行输入一个字符串s，接着输入一个数字Q代表询问个数,接下来Q行每行输入两个数字 l,r 代表询问的区间，s的区间为[1, |s|]。
     * 1 <= |s| <= 400, 1 <= Q <= 100000, 1 <= l <= r <= |s|
     * 注意：|s|表示s串的长度
     * <p>
     * abaabcec
     * 5
     * 1 8
     * 2 7
     * 1 7
     * 3 5
     * 3 6
     **/
    static String str;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        str = in.nextLine();
        int k = in.nextInt();
        List<Integer> query = new ArrayList<>(k * 2);
        int a, b;
        for (int i = 0; i < k; i++) {
            a = in.nextInt();
            b = in.nextInt();
            query.add(a);
            query.add(b);
        }
        int n = str.length();
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (check(i, j)) {
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = j - i + 1;
                }
            }
        }

        for (int len = 2; len <= n; len++) {
            for (int i = 0; i < n - len + 1; i++) {
                int j = i + len - 1;
                for (int l = i; l < j; l++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][l] + dp[l + 1][j]);
                }
            }
        }
        for (int i = 0; i < 2 * k; i += 2) {
            a = query.get(i);
            b = query.get(i + 1);
            System.out.println(str.substring(a - 1, b) + " :  " + dp[a - 1][b - 1]);
        }


    }

    static boolean check(int i, int j) {
        while (i < j) {
            if (str.charAt(i) == str.charAt(j)) {
                j--;
                i++;
            } else return false;
        }
        return true;
    }

}
