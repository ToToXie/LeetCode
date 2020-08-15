package meituan;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * @program: LeetCode
 * @description:
 * @author: wd
 * @create: 2020-08-15 16:51
 **/

public class Main_2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        Set<String> set = new HashSet<>();
        String a, b;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            a = in.next();
            b = in.next();
            set.add(a);
            if (set.contains(b)) {
                ans++;
                set.clear();
            }
        }
        System.out.println(ans);
    }
}
