package meituan;

import java.util.*;

/**
 * @program: LeetCode
 * @description:
 * @author: wd
 * @create: 2020-08-15 16:11
 **/

public class Main_3 {
    /**
     *  5 5
     * 1 2
     * 2 2
     * 3 1
     * 4 2
     * 5 4
     **/
    /**
     * 时间限制： 3000MS
     * 内存限制： 589824KB
     * 题目描述：
     * 小团是美团外卖的区域配送负责人，众所周知，外卖小哥一般都会同时配送若干单，小团在接单时希望把同一个小区的单子放在一起，然后由一名骑手统一配送。但是由于订单是叠在一起的，所以，他归类订单时只能知道新订单和已有的某个订单的小区是相同的，他觉得这样太麻烦了，所以希望你帮他写一个程序解决这个问题。
     * <p>
     * 即给出若干个形如a b的关系，表示a号订单和b号订单是同一个小区的 ，请你把同一个小区的订单按照编号顺序排序，并分行输出，优先输出最小的订单编号较小的小区订单集合。订单的编号是1到n。(可能存在同时出现a b和b a这样的关系,也有可能出现a a这样的关系)
     * <p>
     * <p>
     * <p>
     * <p>
     * <p>
     * 输入描述
     * 输入第一行是两个正整数n，m，表示接受的订单数量和已知的关系数量。(1<=n，m<=10000)
     * <p>
     * 接下来有m行，每行两个正整数a和b，表示a号订单和b号订单属于同一个小区(1<=a,b<=n);
     * <p>
     * 输出描述
     * 输出第一行包含一个整数x，表示这些订单共来自x个不同的小区。
     * <p>
     * 接下来的输出包含x行，每行表示输出若干个订单编号，表示这些订单属于同一个小区，按照订单编号升序输出。优先输出最小的订单编号较小的小区。
     * <p>
     * <p>
     * 样例输入
     * 5 5
     * 1 2
     * 2 2
     * 3 1
     * 4 2
     * 5 4
     * 样例输出
     * 1
     * 1 2 3 4 5
     **/
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        UnionFind father = new UnionFind(n);
        int m = in.nextInt();
        int a, b;
        for (int i = 0; i < m; i++) {
            a = in.nextInt();
            b = in.nextInt();
            /**
             *  保证最小的是根
             **/
            if (a < b) father.union(a - 1, b - 1);
            else father.union(b - 1, a - 1);
        }
//        if(n <= 1) System.out.println("1\n1");
        Map<Integer, List<Integer>> map = new TreeMap<>();

        for (int i = 0; i < n; i++) {
            int f = father.find(i);
//            map.computeIfAbsent(f, k ->new ArrayList<Integer>()).add(i+1);
            if (!map.containsKey(f)) {
                map.put(f, new ArrayList<>());
            }
            map.get(f).add(i + 1);
        }
        System.out.println(map.size());
        int count = 0;
        for (List<Integer> value : map.values()) {
            if (count != 0) System.out.println();
            for (int i = 0; i < value.size(); i++) {
                if (i != 0) System.out.print(" ");
                System.out.print(value.get(i));
            }
//            System.out.println();
            count++;
        }
    }

    static class UnionFind {
        int[] parents;

        public UnionFind(int totalNodes) {
            parents = new int[totalNodes];
            for (int i = 0; i < totalNodes; i++) {
                parents[i] = i;
            }
        }

        public void union(int node1, int node2) {
            int root1 = find(node1);
            int root2 = find(node2);
            if (root1 != root2) {
                parents[root2] = root1;
            }
        }

        public int find(int node) {
            while (parents[node] != node) {
                parents[node] = parents[parents[node]];
                node = parents[node];
            }
            return node;
        }

        public boolean isConnected(int node1, int node2) {
            return find(node1) == find(node2);
        }
    }
}
