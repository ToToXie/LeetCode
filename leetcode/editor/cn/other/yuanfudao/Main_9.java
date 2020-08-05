package yuanfudao;

import util.ListNode;

import java.util.*;

/**
 * @program: LeetCode
 * @description:
 * @author: wd
 * @create: 2020-08-01 19:53
 **/

public class Main_9 {
    /**
     * 猿辅导组织一次抽奖活动，奖券的发放方式是：某个同学拿到全部的奖券，然后自己留一张，
     * 其他的分发给他周边的同学；其他同学收到奖券后，自己留一张，再分发给周边还未收到过奖券的其他同学，
     * 以此类推，直到每个同学都收到一张奖券为止。
     * 开奖时，每张奖券会得到一个奖励值，每个同学最终奖励值除了要包含自己奖券的奖励值外，
     * 还可以额外加上从经由自己发出去的奖券中选择出一部分奖券的奖励值。但是如果不选择某张奖券，
     * 那么经由持有这张没被选择奖券的同学发出去的所有奖券都不能再选了。比如A把BCD的奖券发给了B，
     * B再把CD的奖券分发给了CD，A可以只选择自己的奖券，可以选择ABCD的奖券，也可以选择AB或ABC或ABD的奖券，但是不能只选择AC或者AD的奖券。
     * 奖励值当然是越大越好，大家一定也想知道最终大奖是多少，请你帮大家算一下吧。
     * <p>
     * 第一行输入N，表示N个同学，N <= 100000；
     * 第二行到第N+1行输入两个整数A B，其中A表示某同学持有奖券的奖励值，-1e9 <= A <= 1e9，B表示该奖券是第B行的同学发给他的；
     * B=0表示他是第一个发奖券的同学。
     * 3
     * 2 0
     * 1 2
     * -1 2
     * 3
     **/
    static int max = Integer.MIN_VALUE;

    /**
     * 使用 Map<Integer, Set<Integer>> 来保存父子结构，用数组存放真正的父子节点，
     * 使用递归来实现 自底向上遍历
     **/

    public static void main_1(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] sum = new int[n];
        Map<Integer, List<Integer>> map = new HashMap<>();
        int root = -1;
        for (int i = 0; i < n; i++) {
            sum[i] = in.nextInt();
            int index = in.nextInt() - 2;
            if (map.containsKey(index)) {
                map.get(index).add(i);
            } else {
                List<Integer> one = new ArrayList<>();
                one.add(i);
                map.put(index, one);
            }
        }
        root = map.get(-2).stream().findFirst().get();
        getSum(sum, map, root);
        System.out.println(max);
    }

    static public int getSum(int[] sum, Map<Integer, List<Integer>> map, int index) {
        if (!map.containsKey(index)) {
            return sum[index];
        } else {
            int temp = 0;
            for (Integer it : map.get(index)) {
                int val = getSum(sum, map, it);
                if (val >= 0) temp += val;
            }
            sum[index] += temp;
            max = Math.max(max, sum[index]);
            return sum[index];
        }
    }

    /**
     * 使用 ListNode child 指向孩子节点 next 指向兄弟节点
     **/
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        ListNode[] list = new ListNode[n];
        for (int i = 0; i < n; i++) {
            list[i] = new ListNode();
        }
        int root = -1;
        int a, b;
        for (int i = 0; i < n; i++) {
            a = in.nextInt();
            b = in.nextInt() - 2;
            list[i].val = a;
            if (b == -2) root = i;
            list[i] = new ListNode(a);
            if (b == -2) continue;
            ListNode child = list[i];
            ListNode parent = list[b];
            if (parent.child == null) {
                parent.child = child;
            } else {
                child.next = parent.child;
                parent.child = child;
            }
        }
        getSum(list[root]);
        System.out.println(max);
    }

    static int getSum(ListNode root) {
        if (root == null) return 0;
        else if (root.child == null) {
            return root.val;
        } else {
            int temp = 0;
            ListNode now = root.child;
            while (now != null) {
                int val = getSum(now);
                if (val > 0) temp += val;
                now = now.next;
            }
            root.val += temp;
            max = Math.max(max, root.val);
            return root.val;
        }
    }

}
