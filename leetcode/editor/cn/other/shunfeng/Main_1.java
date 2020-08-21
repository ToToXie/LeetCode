package shunfeng;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * @program: LeetCode
 * @description:
 * @author: wd
 * @create: 2020-08-20 20:34
 **/

public class Main_1 {
    /**
     * 克里森是一名赏金猎人，他平时需要完成一些任务赚钱。最近他收到了一批任务，但是受到时间的限制，
     * 他只能完成其中一部分。具体来说就是有n个任务，每个任务用l, r, w来表示任务开始的时间l，结束的时间r和完成任务获得的金钱。
     * <p>
     * 克里森是个贪心的人，他想知道自己在任务不冲突的情况下最多获得多少金钱。
     * <p>
     * 3
     * 1 3 5
     * 2 7 3
     * 5 10 1
     * <p>
     * 6
     **/
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int l, r, w;
        List<Node> list = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            l = in.nextInt();
            r = in.nextInt();
            w = in.nextInt();
            list.add(new Node(l, r, w));
        }

//        Collections.sort(list,( a,b) -> a.l == b.l?b.w - a.w:a.l-b.l);
        Collections.sort(list);
        int max = -1;
//        Queue<Node> queue = new PriorityQueue<>((a, b) -> a.r == b.r ? b.w - a.w : a.r - b.r);
//        for (Node node : list) {
//            if (queue.isEmpty()) {
//                queue.add(node);
//            } else if (queue.peek().r > node.l) {
//                queue.add(node);
//            } else {
//                node.w += queue.peek().w;
//                queue.poll();
//                queue.add(node);
//            }
//            max = Math.max(max, node.w);
//        }
        /**
         *  dp[i] 表示 以 第 i 个 任务结尾的 收益
         *
         **/
        int[] dp = new int[n + 1];
        for (int i = 0; i < n; i++) {
            dp[i + 1] = list.get(i).w;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                if (list.get(i - 1).l >= list.get(j - 1).r) {
                    dp[i] = Math.max(dp[i], dp[j] + list.get(i - 1).w);
                }
            }
            max = Math.max(max, dp[i]);
        }
        System.out.println(max);

    }

    static class Node implements Comparable {
        int l, r, w;

        public Node(int l, int r, int w) {
            this.l = l;
            this.r = r;
            this.w = w;
        }

        @Override
        public int compareTo(Object o) {
            Node b = (Node) o;
            if (this.l == b.l) {
//                return b.w/(b.r - b.l) -this.w/(this.r - this.l);
                return b.w - this.w;
            } else {
                return this.l - b.l;
            }
        }
    }
}
