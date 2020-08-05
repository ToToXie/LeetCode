package bianlifeng;

import java.util.HashMap;
import java.util.Scanner;

/**
 * @program: LeetCode
 * @description:
 * @author: wd
 * @create: 2020-05-07 19:07
 **/

public class MainB {
    private static int ans = -1;
    private static int n;
    private static char[][] G = {{'0', '1', 'C', 'H', 'A'}, {'9', 'E', '7', 'B', 'I'}
            , {'K', 'D', '4', '8', 'J'}, {'6', '5', 'F', 'G', 'O'}, {'L', 'N', 'M', '2', '3'}};

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            String s = in.nextLine();
            if ("".equals(s)) {
                continue;
            }
            int x = -1, y = -1;
            for (int i = 0; i < 5; i++) {
                if (x != -1) break;
                for (int j = 0; j < 5; j++) {
                    if (s.charAt(0) == G[i][j]) {
                        x = i;
                        y = j;
                        break;
                    }
                }
            }
            HashMap<Character, Boolean> vis = new HashMap<>();
            for (Character c : s.toCharArray()) {
                vis.put(c, false);
            }
            n = s.length();
            vis.put(s.charAt(0), true);
            if (x == -1) {
                System.out.println("N");
            } else {
                if (dfs(1, x, y, vis)) {
                    System.out.println("Y");
                } else {
                    System.out.println("N");
                }
            }
        }
        in.close();


    }

    public static boolean dfs(int index, int x, int y, HashMap<Character, Boolean> vis) {
        if (index == n) return true;
        for (int j = -1; j < 2; j += 2) {
            int newx = x + j, newy = y + j;
            if (newx < 5 && newx >= 0 && vis.containsKey(G[newx][y])) {
                if (vis.get(G[newx][y]) == false) {
                    vis.put(G[newx][y], true);
                    return dfs(index + 1, newx, y, vis);
                }
            }
            if (newy < 5 && newy >= 0 && vis.containsKey(G[x][newy])) {
                if (vis.get(G[x][newy]) == false) {
                    vis.put(G[x][newy], true);
                    return dfs(index + 1, x, newy, vis);
                }
            }
        }
        return false;
    }

}
