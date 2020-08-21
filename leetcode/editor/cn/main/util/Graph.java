package util;

import java.util.*;

/**
 * @program: LeetCode
 * @description:
 * @author: wd
 * @create: 2020-08-20 14:03
 **/

public class Graph {
    public static void main(String[] args) {
        Graph main = new Graph();

        String[][] equat = {{"a", "b"}, {"b", "c"}};
        String[][] quer = {{"a", "c"}, {"b", "a"}, {"a", "e"}, {"a", "a"}, {"x", "x"}};
        double[] val = {2.0, 3.0};
        List<List<String>> equations = new ArrayList<>();
        List<List<String>> query = new ArrayList<>();
        for (String[] strings : equat) {
            equations.add(Arrays.asList(strings));
        }
        for (String[] strings : quer) {
            query.add(Arrays.asList(strings));
        }

        main.calcEquation(equations, val, query);


    }

    /**
     * 399 除法求值
     * 带权并查集
     **/
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, Integer> mapToIndex = new HashMap<>();
        int index = 0;
        for (List<String> query : equations) {
            for (String s : query) {
                if (!mapToIndex.containsKey(s)) {
                    mapToIndex.put(s, index++);
                }
            }
        }
        int[] father = new int[index];
        double[] rela = new double[index];
        String aStr, bStr;
        int a, b;
        double x;
        for (int i = 0; i < father.length; i++) {
            father[i] = i;
            rela[i] = 1D;
        }
        for (int i = 0; i < equations.size(); i++) {
            List<String> query = equations.get(i);
            aStr = query.get(0);
            bStr = query.get(1);
            a = mapToIndex.get(aStr);
            b = mapToIndex.get(bStr);
            x = values[i];
            union(father, rela, a, b, x);
        }
        double[] ans = new double[queries.size()];

        for (int i = 0; i < queries.size(); i++) {

            aStr = queries.get(i).get(0);
            bStr = queries.get(i).get(1);
            a = mapToIndex.getOrDefault(aStr, -1);
            b = mapToIndex.getOrDefault(bStr, -1);
            if (a == -1 || b == -1) {
                ans[i] = -1.0D;
                continue;
            }
            if (isConnect(father, rela, a, b)) {
                ans[i] = rela[a] / rela[b];
            } else {
                ans[i] = -1.0D;
            }
        }
        return ans;
    }

    private boolean isConnect(int[] father, double[] rela, int a, int b) {
        return find(father, rela, a) == find(father, rela, b);
    }

    private void union(int[] father, double[] rela, int a, int b, double x) {
        int fa;
        int fb;
        fa = find(father, rela, a);
        fb = find(father, rela, b);
        if (fa != fb) {
            father[fb] = fa;
            rela[fb] = rela[a] * x / rela[b];
        }
    }

    private int find(int[] father, double[] rela, int a) {
        while (father[a] != a) {
            rela[a] = rela[a] * rela[father[a]];
            father[a] = father[father[a]];
            a = father[a];
        }
        return a;
    }

}
