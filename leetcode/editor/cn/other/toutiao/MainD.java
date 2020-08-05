package toutiao;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * @program: LeetCode
 * @description:
 * @author: wd
 * @create: 2020-05-11 09:54
 **/

public class MainD {
    static int[] nums;

    /**
     * @Description: 首先输入一个字符串s
     * 接下来一行输入一个n 表示接下来有n个询问或者修改的操作。
     * 接下来有n行，每行表示一个提问或者修改的操作。
     * <p>
     * 如果是修改，则会先输入一个1，然后然后输入一个整数pos  和一个字符c，即将字符串中pos位置的字符替换成c
     * 如果是提问，则会先输入一个2，然后输入两个整数l r，即求s[l...r]子串中有多少个互不相同的字符
     **/
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        StringBuilder sb = new StringBuilder(str);
        int length = str.length();
        nums = new int[length];
        int n = in.nextInt();
        int order = 0, num = 0;
        String c;
        int l, r;
        for (int i = 0; i < n; i++) {
            order = in.nextInt();
            if (order == 1) {
                num = in.nextInt();
                c = in.nextLine().trim();
                goBack(sb, num - 1, c);
            } else if (order == 2) {
                l = in.nextInt();
                r = in.nextInt();
                System.out.println(getNum(sb, l - 1, r));
            }
        }
    }

    private static void goBack(StringBuilder str, int index, String c) {
        str.setCharAt(index, c.charAt(0));
    }

    private static int getNum(StringBuilder str, int l, int r) {
        Set<Character> set = new HashSet<>();
        int ans = 0;
        for (int j = l; j < r; j++) {

            char c = str.charAt(j);
            if (!set.contains(c)) {
                set.add(c);
                ans++;
            }
        }
        return ans;
    }
}
