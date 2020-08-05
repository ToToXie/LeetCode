package yuanfudao;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * @program: LeetCode
 * @description:
 * @author: wd
 * @create: 2020-07-26 15:18
 **/

public class Main_3 {
    static int[] nums;
    static int n;
    static int[] ans;

    public static void main(String[] args) {
        Main_3 main = new Main_3();
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        ans = new int[n];

        for (int i = 0; i < n; i++) {
            int m = in.nextInt();
            PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
            for (int j = 0; j < m; j++) {
                int x = in.nextInt();
                if (x > 0) queue.add(x);
            }
            getNum(queue);
        }
    }

    static private void getNum(PriorityQueue<Integer> queue) {
        int temp = 0;
        while (queue.size() >= 3) {
            int a = queue.poll();
            int b = queue.poll();
            int c = queue.poll();
            if (c == 0) break;

            if (a > 1) queue.add(a - 1);
            if (b > 1) queue.add(b - 1);
            if (c > 1) queue.add(c - 1);
            temp++;
        }
        System.out.println(temp);
    }
}
