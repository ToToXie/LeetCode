package didi;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @program: LeetCode
 * @description:
 * @author: wd
 * @create: 2020-08-21 20:10
 **/

public class Main_1 {
    static int n;
    static boolean[] vis = new boolean[10];
    static List<Pair<Integer, Integer>> ans;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        ans = new ArrayList<>();
        getAns(0, new ArrayList<>());
        System.out.println(ans.size());
        for (Pair<Integer, Integer> an : ans) {
            System.out.println(an.getKey() + " " + an.getValue());
        }

    }

    static public void getAns(int index, List<Integer> list) {
        if (list.size() == 3) {
            Pair<Integer, Integer> check = check(list);
            if (check != null) {
                ans.add(check);
            }
            return;
        }
        for (int i = 0; i < 10; i++) {
            if (!vis[i]) {
                list.add(i);
                vis[i] = true;
                getAns(i + 1, list);
                vis[i] = false;
                list.remove(list.size() - 1);
            }
        }
    }

    static public Pair<Integer, Integer> check(List<Integer> list) {
        int a = list.get(0);
        if (a == 0) return null;
        int b = list.get(1);
        int c = list.get(2);
        int x = a * 100 + b * 10 + c;
        int y = a * 100 + c * 10 + c;
        if (x + y == n) {
            return new Pair<>(x, y);
        }
        return null;
    }
}
