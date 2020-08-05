package huawei;

import java.util.*;

/**
 * @program: LeetCode
 * @description:
 * @author: wd
 * @create: 2020-05-20 18:53
 **/

public class MainB {
    //A->B;B->D;C->B;C->E;D->C
    static private char A = 'A';

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        String s = in.next();
        String[] split = s.split(";");
        int[][] g = new int[10][10];
        int[] input = new int[10];
        int[] output = new int[10];
        Set<Character> set = new HashSet<>();
        for (String s1 : split) {
            char a = s1.charAt(0);
            char b = s1.charAt(3);
            set.add(a);
            set.add(b);
            g[a - A][b - A] = 1;
            input[b - A]++;
            output[a - A]++;
        }
        Queue<Integer> queue = new LinkedList<>();
        int length = set.size();
        for (int i = 0; i < length; i++) {
            if (input[i] == 0) {
                queue.add(i);
                while (!queue.isEmpty()) {
                    int x = queue.poll();
                    for (int j = 0; j < length; j++) {
                        if (g[x][j] == 1) {
                            input[j]--;
                            output[x]--;
                            g[x][j] = 0;
                            if (input[j] == 0) {
                                queue.add(j);
                            }
                        }
                    }
                }

            } else if (output[i] == 0) {
                queue.add(i);
                while (!queue.isEmpty()) {
                    int x = queue.poll();
                    for (int j = 0; j < length; j++) {
                        if (g[j][x] == 1) {
                            input[x]--;
                            output[j]--;
                            g[j][x] = 0;
                            if (output[j] == 0) {
                                queue.add(j);
                            }
                        }
                    }
                }
            }
        }


        int min = -1;
        for (int i = 0; i < length; i++) {
            if (input[i] == 1 && output[i] == 1) {
                min = i;
                break;
            }
        }
        StringBuilder sb = new StringBuilder();
        int temp = 0;
        char c = (char) (min + A);
        sb.append(c);
        queue.add(min);
        while (!queue.isEmpty()) {
            int x = queue.poll();
            for (int j = 0; j < length; j++) {
                if (g[x][j] == 1) {
                    if (j == min) {
                        break;
                    }
                    c = (char) (j + A);
                    sb.append(c);
                    queue.add(j);
                }
            }
        }

        System.out.println(sb.toString());
    }
}
