package netease;

import util.UnionFind;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * @program: LeetCode
 * @description:
 * @author: wd
 * @create: 2020-08-08 16:15
 **/

public class Main_4 {
    /**
     * 牛牛最近在研究运送货物的问题。
     * 有一张n个点m条边无向图，每条边有一个权值。
     * 牛牛希望构造一棵生成树（即仅保留n-1条边，但保持图连通），使得最大边权减去最小边权的值最小。
     * 牛牛希望你告诉他最小的这样的值是多少。
     * <p>
     * 第一行输入两个整数n,m，表示结点数目和边的个数。
     * 随后m行，每行输出两个整数u,v,w，表示有一条边连接u和v，边权为w。
     * 1\leq n\leq 1000,\ n-1\leq m\leq 3000, 1\leq w \leq 10^91≤n≤1000, n−1≤m≤3000,1≤w≤10
     * 9
     * 。
     * <p>
     * 数据保证初始图连通。
     * <p>
     * 3 5
     * 1 2 10
     * 1 3 5
     * 3 1 12
     * 2 3 19
     * 1 2 74
     * <p>
     * 2
     **/

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int a, b, c;
        List<Node> list = new ArrayList<>(m);
        for (int i = 0; i < m; i++) {
            a = in.nextInt();
            b = in.nextInt();
            c = in.nextInt();
            list.add(new Node(a, b, c));
        }
        int ans = Integer.MAX_VALUE;
        Collections.sort(list, (x, y) -> x.c - y.c);
        for (int k = 0; k < m - n + 1; k++) {
            UnionFind union = new UnionFind(n + 1);
            int count = 0, max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
            for (int i = k; i < m; i++) {
                Node node = list.get(i);
                if (!union.isConnected(node.a, node.b)) {
                    union.union(node.a, node.b);
                    count++;
                    max = Math.max(max, node.c);
                    min = Math.min(min, node.c);
                    if (count == n - 1) {
                        ans = Math.min(max - min, ans);
                        break;
                    }
                }
            }
        }
        System.out.println(ans);

    }

    static class Node {
        int a, b, c;

        public Node(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }


}
