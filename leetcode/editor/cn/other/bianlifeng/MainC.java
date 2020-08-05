package bianlifeng;

import java.util.Scanner;

/**
 * @program: LeetCode
 * @description:
 * @author: wd
 * @create: 2020-05-07 19:07
 **/

public class MainC {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        StringBuilder sb = new StringBuilder();
        char a, b;
        for (int i = 0; i < str.length(); i += 2) {
            a = str.charAt(i);
            b = str.charAt(i + 1);
            int count = Integer.valueOf(a - '0');
            for (int j = 0; j < count; j++) {
                sb.append(b);
            }
        }
        System.out.println(sb.toString());
        in.close();


    }
}
