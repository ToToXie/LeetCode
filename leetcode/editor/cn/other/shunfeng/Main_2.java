package shunfeng;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * @program: LeetCode
 * @description:
 * @author: wd
 * @create: 2020-08-20 20:57
 **/

public class Main_2 {
    /**
     * 小A的购买了一批服务器，他准备将这些服务器租用出去。每一个服务器有一个固定的带宽，
     * 人们根据自己需要的带宽来租用这些服务器。一台服务器只能租给一个人。
     * <p>
     * 小A现在有n个空闲的服务器，第 i 个服务器拥有ai的带宽。有m个客户来租服务器，
     * 第 i 位客户需要带宽至少为bi的服务器，并且愿意花ci元作为预算。（服务器带宽独立不可叠加，若不能成功租出则输出0）
     * 小A可以选择拒绝一些人，现在，小A想知道自己的服务器最多能租多少钱？
     * <p>
     * 3 4
     * 1 2 3
     * 2 1
     * 3 2
     * 3 3
     * 1 1
     * <p>
     * 5
     * <p>
     * 直接 n2 的解法 也挺好，没必要追求最优 ，先拿分才行
     **/
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        List<Integer> list = new ArrayList<>(n);
        int max = -1;
        for (int i = 0; i < n; i++) {
            int x = in.nextInt();
            list.add(x);
            max = Math.max(max, x);
        }
        List<Pair<Integer, Integer>> cost = new ArrayList<>(m);
        int c, d;
        for (int i = 0; i < m; i++) {
            c = in.nextInt();
            d = in.nextInt();
            if (c <= max) cost.add(new Pair<>(c, d));
        }
        m = cost.size();
        Collections.sort(list);
        Collections.sort(cost, (a, b) -> b.getValue() - a.getValue());
        boolean[] vis = new boolean[m];
        int index = 0;
        int maxCost = -1;
        int ans = 0;

        for (Integer it : list) {
            maxCost = -1;
            index = -1;
            for (int i = 0; i < m; i++) {
                Pair<Integer, Integer> pair = cost.get(i);
                if (pair.getKey() <= it && !vis[i]) {
                    if (maxCost < pair.getValue()) {
                        maxCost = pair.getValue();
                        index = i;
                    }
                }
            }
            if (index != -1) {
                ans += maxCost;
                vis[index] = true;
            }
        }
        System.out.println(ans);
    }
}
