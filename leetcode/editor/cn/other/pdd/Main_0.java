package pdd;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main_0 {

    static int n, m;
    static int[] tree;
    static List<String> ans;

    static boolean check(int left) {
        for (int i = 1; i <= n; i++) {
            if (tree[i] > (left + 1) / 2) return false;
        }
        return true;
    }

    static boolean dfs(int idx) {
        if (!check(m - idx)) return false;
        if (idx == m) {
            return true;
        } else {
            for (int i = 1; i <= n; i++) {
                if (idx == 0 || (tree[i] != 0 && i != Integer.valueOf(ans.get(idx - 1)))) {
                    tree[i]--;
                    ans.add(i + "");
                    if (dfs(idx + 1)) return true;
                    ans.remove(ans.size() - 1);
                    tree[i]++;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            n = sc.nextInt();
            tree = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                tree[i] = sc.nextInt();
                m += tree[i];
            }
            ans = new ArrayList<>();
            if (dfs(0)) {
                System.out.println(String.join(" ", ans));
            } else {
                System.out.println("-");
            }
        }
    }
}