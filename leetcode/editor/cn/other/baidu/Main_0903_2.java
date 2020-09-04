package baidu;

import java.util.*;

/**
 * @program: LeetCode
 * @description:
 * @author: wd
 * @create: 2020-09-03 19:57
 **/

public class Main_0903_2 {
    /**
     * 牛牛管理这一片牧场，在这片牧场中，一共有 \mathit nn 头奶牛，为方便统计，它们排成了一排，编号为 \text 11 ~ \mathit nn。
     * <p>
     * 现在质检员牛妹在检测这些奶牛的质量，她列出了 \mathit mm 条特性，只有满足所有特性的奶牛才可称之为优质奶牛。
     * <p>
     * 但是，牛牛现在只知道对于某条特性，某几段连续区间内的奶牛是满足条件的，如果这样依次排查，会浪费很多时间。由于牛妹时间很急，马上要赶往下一个牧场，所以，牛牛请你帮助他筛选优质奶牛。
     * 本题为多组测试数据，第一行输入一个正整数 \mathit T(\text 1\ \leq\ \mathit T\ \leq\ \text {1000})T(1 ≤ T ≤ 1000)，代表测试数据组数。
     * 对于每组测试数据，在第一行输入两个正整数 \mathit n,\ \mathit m(\text 1\ \leq\ \mathit n\ \leq\ \text {1000},\ \text 1\ \leq\ \mathit m\ \leq\ \text {10})n, m(1 ≤ n ≤ 1000, 1 ≤ m ≤ 10)，代表奶牛数量以及需要满足的特性数量。
     * 接下去对于每个特性，一行输入一个正整数 \mathit k(\text 1\ \leq\ \mathit k\ \leq\ \text {100})k(1 ≤ k ≤ 100)，代表这个特性在奶牛中满足的区间数量，接着 \mathit kk 行，每行输入两个正整数 \mathit l,\ \mathit r(\text 1\ \leq\ \mathit l\ \leq\ \mathit r\ \leq\ \mathit n)l, r(1 ≤ l ≤ r ≤ n)，代表闭区间 [\mathit l,\ \mathit r][l, r] 内的奶牛满足这一特性。
     * <p>
     * 1
     * 10 2
     * 3
     * 1 2
     * 4 5
     * 8 8
     * 2
     * 1 4
     * 6 8
     * <p>
     * <p>
     * 4
     * 1 2 4 8
     **/
    public static void main(String[] args) {
//        getAnsByInterval();
//        getAnsBySet();
        getAnsByNums();
    }

    private static void getAnsByInterval() {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for (int i = 0; i < t; i++) {
            int n = in.nextInt();
            int m = in.nextInt();
            Queue<Node> queue = new LinkedList<>();
            boolean flag = false;
            queue.add(new Node(1, n));
            for (int j = 0; j < m; j++) {
                int size = in.nextInt();
                Queue<Node> temp = new PriorityQueue<>((a, b) -> a.s - b.s);
                for (int k = 0; k < size; k++) {
                    int a = in.nextInt();
                    int b = in.nextInt();
                    temp.add(new Node(a, b));
                }
                if (flag) {
                    continue;
                }
                while (!temp.isEmpty() && !queue.isEmpty()) {
                    Node node = temp.peek();
                    Node now = queue.peek();
                    if (now.e < node.s) {
                        queue.poll();
                    } else if (now.s < node.s && now.e >= node.s && now.e < node.e) {
                        queue.poll();
                        queue.add(new Node(now.s, Math.min(node.s, now.e)));
                        node.s = now.e + 1;
                    } else if (now.s < node.s && now.e >= node.e) {
                        queue.add(new Node(node.s, node.e));
                        temp.poll();
                        now.s = node.e + 1;
                    } else if (now.s >= node.s && now.e <= node.e) {
                        queue.poll();
                        queue.add(new Node(now.s, now.e));
                        node.s = now.e + 1;
                    } else if (now.s >= node.s && now.s <= node.e && now.e > node.e) {
                        temp.poll();
                        queue.add(new Node(now.s, node.e));
                        now.s = node.e + 1;
                    }
                    if (now.s > node.e) {
                        temp.poll();
                    }
                }
                if (queue.isEmpty()) {
                    flag = true;
                }

            }
            if (flag) {
                System.out.println(0);
            } else {
                List<Integer> ans = new ArrayList<>();
                while (!queue.isEmpty()) {
                    Node poll = queue.poll();
                    int x = poll.s;
                    while (x <= poll.e) {
                        ans.add(x);
                    }
                }
                System.out.println(ans.size());
                for (int l = 0; l < ans.size(); l++) {
                    System.out.print(ans.get(l));
                    if (l < ans.size() - 1) {
                        System.out.print(" ");
                    } else {
                        System.out.println();
                    }
                }

            }
        }
    }

    private static void getAnsBySet() {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for (int i = 0; i < t; i++) {
            int n = in.nextInt();
            int m = in.nextInt();
            Set<Integer> set = new HashSet<>();
            Set<Integer> temp = new HashSet<>();
            int a = 1, b = n;
            while (a <= b) {
                set.add(a);
                a++;
            }
            boolean flag = false;
            for (int j = 0; j < m; j++) {
                int size = in.nextInt();
//                Queue<Node> temp = new PriorityQueue<>((a, b) -> a.s - b.s);
                temp = new HashSet<>();
                for (int k = 0; k < size; k++) {
                    a = in.nextInt();
                    b = in.nextInt();
                    while (a <= b) {
                        if (set.contains(a)) {
                            temp.add(a);
                        }
                        a++;
                    }
                }
                if (temp.size() == 0) {
                    flag = true;
                    continue;
                } else {
                    set = temp;
                }


            }
            if (flag) {
                System.out.println(0);
            } else {
                int[] ints = temp.stream().sorted().mapToInt(it -> it.intValue()).toArray();
                System.out.println(ints.length);
                for (int l = 0; l < ints.length; l++) {
                    System.out.print(ints[l]);
                    if (l < ints.length - 1) System.out.print(" ");
                }

            }

        }
    }

    private static void getAnsByNums() {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for (int i = 0; i < t; i++) {
            int n = in.nextInt();
            int m = in.nextInt();
            int[] nums = new int[n + 1];
            for (int j = 0; j < m; j++) {
                int size = in.nextInt();
//                Queue<Node> temp = new PriorityQueue<>((a, b) -> a.s - b.s);
                for (int k = 0; k < size; k++) {
                    int a = in.nextInt();
                    int b = in.nextInt();
                    for (int z = a; z <= b; z++) {
                        nums[z]++;
                    }
                }
            }
            StringBuilder sb = new StringBuilder();
            int count = 0;
            for (int k = 1; k <= n; k++) {
                if (nums[k] == m) {
                    count++;
                    sb.append(k);
                    sb.append(" ");
                }
            }
            if (count == 0) {
                System.out.println(-1);
            } else {
                System.out.println(count);
                System.out.println(sb.toString().trim());
            }

        }
    }

    static class Node {
        int s, e;

        public Node(int s, int e) {
            this.s = s;
            this.e = e;
        }
    }


}
