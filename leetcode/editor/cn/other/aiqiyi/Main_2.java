package aiqiyi;

import java.util.Scanner;

/**
 * @program: LeetCode
 * @description:
 * @author: wd
 * @create: 2020-08-23 15:34
 **/

public class Main_2 {
    /**
     * 给你一个字符串 path，其中 path[i] 的值可以是 'N'、'S'、'E' 或者 'W'，
     * 分别表示向北、向南、向东、向西移动一个单位。机器人从二维平面上的原点 (0, 0)
     * 处开始出发，按 path 所指示的路径行走。如果路径在任何位置上出现相交的情况，
     * 也就是走到之前已经走过的位置，请返回 True ；否则，返回 False 。
     * <p>
     * ESSWNEE
     **/
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String path = in.nextLine();
        boolean flag = isFlag(path);
        System.out.println(flag ? "True" : "False ");
    }

    private static boolean isFlag(String path) {
        if (path == null || path.length() < 1) return true;
        int n = path.length();

        int m = 2 * n + 5;
        boolean[][] vis = new boolean[m][m];

//        int size = (n + 5) * (n + 5);
//        BitSet map = new BitSet(size);

        int x = n + 1, y = n + 1;
        vis[x][y] = true;
        boolean flag = false;
        for (char c : path.toCharArray()) {
            if (c == 'N') {
                x--;
            } else if (c == 'S') {
                x++;
            } else if (c == 'E') {
                y++;
            } else if (c == 'W') {
                y--;
            }
            if (vis[x][y]) {
                flag = true;
                break;
            }
            vis[x][y] = true;
//            if(map.get(x *( n + 1) + y)){
//                flag = true;
//                break;
//            }
//            map.set(x *( n + 1) + y);

        }
        return flag;
    }
}
