package pdd;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @program: LeetCode
 * @description:
 * @author: wd
 * @create: 2020-08-02 20:17
 **/

public class Main_8 {
    /**
     * 10
     * 2 5 1 3 4 6
     * 5 4 3 2 1 6
     * 1 4 6 2 3 5
     * 1 5 6 3 4 2
     * 6 4 2 1 5 3
     * 3 6 4 5 2 1
     * 1 6 3 4 2 5
     * 5 1 4 2 6 3
     * 6 2 3 1 5 4
     * 5 3 6 1 4 2
     * <p>
     * 只有第4个骰子(1 5 6 3 4 2)与第8个骰子(5 1 4 2 6 3)属于同一类。
     * <p>
     * 一种可能的变换方式:
     * 1) 首先从右向左翻转1次
     * (1 5 6 3 4 2) -> (1 5 4 2 3 6)
     * 2) 然后从上向下翻转2次
     * (1 5 4 2 3 6) -> (6 3 4 2 1 5) -> (5 1 4 2 6 3)
     **/
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        List<Node> list = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        Point a, b, c;
        for (int i = 0; i < n; i++) {
            int a1 = in.nextInt();
            int a2 = in.nextInt();
            int b1 = in.nextInt();
            int b2 = in.nextInt();
            int c1 = in.nextInt();
            int c2 = in.nextInt();
            a = new Point(a1, a2);
            b = new Point(b1, b2);
            c = new Point(c1, c2);
            list.add(new Node(a, b, c));
        }
        for (Node node : list) {
            swap(node);
//            System.out.println(node.toString());
            map.put(node.toString(), map.getOrDefault(node.toString(), 0) + 1);
        }
        System.out.println(map.keySet().size());
        List<Integer> collect = map.values().stream().sorted(Collections.reverseOrder()).collect(Collectors.toList());
        for (int i = 0; i < collect.size(); i++) {
            if (i != 0) System.out.print(" ");
            System.out.print(collect.get(i));
        }

    }

    static void swap(Node node) {
        boolean flag = true;
        if (node.a.min > node.b.min) {
            flag = !flag;
            swap(node, 1);
        }
        if (node.b.min > node.c.min) {
            flag = !flag;
            swap(node, 2);
        }
        if (node.a.min > node.b.min) {
            flag = !flag;
            swap(node, 1);
        }
        if (node.a.x > node.a.y) {
            swap(node.a);
            flag = !flag;
        }
        if (node.b.x > node.b.y) {
            swap(node.b);
            flag = !flag;
        }
        if (!flag) {
            swap(node.c);
        }
    }

    static void swap(Point point) {
        int temp = point.x;
        point.x = point.y;
        point.y = temp;
    }

    static void swap(Node node, int x) {
        if (x == 1) {
            Point temp = node.a;
            node.a = node.b;
            node.b = temp;
        } else if (x == 2) {
            Point temp = node.b;
            node.b = node.c;
            node.c = temp;
        }
    }

    static class Node {
        Point a;
        Point b;
        Point c;

        public Node(Point a, Point b, Point c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        @Override
        public String toString() {
            return a.toString() + b.toString() + c.toString();
        }
    }

    static class Point {
        int x, y;
        int min;

        public Point() {
        }

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
            min = Math.min(x, y);
        }

        @Override
        public String toString() {
            return x + "" + y;
        }
    }
}

