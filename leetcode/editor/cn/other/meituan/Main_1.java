package meituan;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * @program: LeetCode
 * @description:
 * @author: wd
 * @create: 2020-08-15 16:37
 **/

public class Main_1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        List<Node> ans = new LinkedList<>();
        for (int i = 1; i < n / 4; i++) {
            if (check(i)) {
                ans.add(new Node(i, 4 * i));
            }
        }
        System.out.println(ans.size());
        for (Node an : ans) {
            System.out.println(an.a + " " + an.b);
        }
    }

    static boolean check(int x) {
        int y = x * 4;
        return Integer.toString(x).trim().equals(new StringBuilder(Integer.toString(y).trim()).reverse().toString());
    }

    static class Node {
        int a, b;

        public Node(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }


}
