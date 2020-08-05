package toutiao;

import java.util.Scanner;
import java.util.Stack;

/**
 * @program: LeetCode
 * @description:
 * @author: wd
 * @create: 2020-05-11 09:53
 **/

public class MainA {
    static StringBuilder sb = new StringBuilder();
    static Stack<Integer> lastOrder = new Stack<>();
    static Stack<Integer> lastNum = new Stack<>();
    static Stack<String> lastText = new Stack<>();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int order = 0, num = 0;
        String str;
        for (int i = 0; i < n; i++) {
            order = in.nextInt();
            if (order == 1) {
                str = in.next();
                lastOrder.add(order);
                lastText.add(str);
                sb.append(str);
            } else if (order == 4) {
                goBack();
            } else if (order == 2) {
                num = in.nextInt();
                lastOrder.add(order);
                lastNum.add(num);
                lastText.add(sb.substring(sb.length() - num, sb.length()));
                sb.delete(sb.length() - num, sb.length());
            } else if (order == 3) {
                num = in.nextInt();
                System.out.println(sb.charAt(num - 1));
            }
        }
    }

    private static void goBack() {
        if (!lastOrder.isEmpty()) {
            int order = lastOrder.pop();
            if (order != 0) {
                if (order == 1) {
                    String str = lastText.pop();
                    sb.delete(sb.length() - str.length(), sb.length());
                } else {
                    String str = lastText.pop();
                    sb.append(str);
                }
            }
        }
    }
}
