package netease;


import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * @program: LeetCode
 * @description:
 * @author: wd
 * @create: 2020-08-08 15:30
 **/

public class Main_2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        Queue<Integer> queue = new LinkedList<>();
        boolean[] vis = new boolean[n];
        for (int i = 0; i < m; i++) {
            int x = in.nextInt();
            vis[x - 1] = true;
            queue.add(x);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (vis[i]) continue;
            while (!queue.isEmpty() && queue.peek() < i + 1) {
                vis[queue.peek() - 1] = true;
                sb.append(queue.poll());
                sb.append(" ");
            }
            vis[i] = true;
            sb.append(i + 1);
            sb.append(" ");
        }
        while (!queue.isEmpty()) {
            sb.append(queue.poll());
            sb.append(" ");
        }
        System.out.println(sb.toString().trim());
    }
}
