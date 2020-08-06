package jd;

import java.util.Scanner;

/**
 * @program: LeetCode
 * @description:
 * @author: wd
 * @create: 2020-08-06 19:37
 **/

public class Main_3 {
    /**
     * 现有一个正整数，希望去掉这个数中某一个数字之后可以得到一个回文素数。
     * <p>
     * 回文素数是指一个素数同时还是一个回文数（回文数即从左到右和从右到左均一样的数，例如12321）。【注意：一位数也被认为是回文数】
     * <p>
     * 输入两个正整数N和M，满足N<M，请编写一个程序统计N和M之间存在多少个满足上述要求的数？
     **/
    static boolean[] nums;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int m = in.nextInt();
        int n = in.nextInt();
        nums = new boolean[n + 1];
        nums[1] = true;
        for (int i = 2; i <= n; i++) {
            if (!nums[i]) {
                for (int j = i + i; j <= n; j += i) {
                    nums[j] = true;
                }
            }
        }
        //110、111、112、113、114、115、116、117、118和119
        int ans = 0;
        for (int i = m; i <= n; i++) {
            if (getNumCore(i)) {
                ans++;
                System.out.println(i + " -- ");
            }
        }
        System.out.println(ans);

    }

    static public boolean isPraTrue(String str) {
        if (str == null || str.length() < 1) return false;
        int left = 0, right = str.length() - 1;
        while (left <= right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    static boolean getNumCore(int x) {
        String s = Integer.toString(x);
        boolean flag = false;
        for (int i = 0; i < s.length(); i++) {
            if (flag) break;
            String str;
            if (i == 0) {
                str = s.substring(i + 1);
            } else if (i == s.length() - 1) {
                str = s.substring(0, i);
            } else {
                str = s.substring(0, i) + s.substring(i + 1);
            }
            if (andTrue(str)) {
                flag = true;
            }
        }
        return flag;
    }

    static boolean andTrue(String str) {
        if (str == null) return false;
        int start = 0;
        while (start < str.length() && str.charAt(start) == '0') start++;
        str = str.substring(start);
        if ("".equals(str)) return false;
        if (!nums[Integer.parseInt(str)] && isPraTrue(str)) {
            return true;
        }
        return false;
    }

}
