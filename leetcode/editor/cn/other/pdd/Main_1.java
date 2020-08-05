package pdd;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @program: LeetCode
 * @description:
 * @author: wd
 * @create: 2020-08-02 09:51
 **/

public class Main_1 {
    /**
     * 多多鸡有N个魔术盒子（编号1～N），其中编号为i的盒子里有i个球。
     * 多多鸡让皮皮虾每次选择一个数字X（1 <= X <= N），多多鸡就会把球数量大于等于X个的盒子里的球减少X个。
     * 通过观察，皮皮虾已经掌握了其中的奥秘，并且发现只要通过一定的操作顺序，可以用最少的次数将所有盒子里的球变没。
     * 那么请问聪明的你，是否已经知道了应该如何操作呢？
     * <p>
     * 第一行，有1个整数T，表示测试用例的组数。
     * （1 <= T <= 100）
     * 接下来T行，每行1个整数N，表示有N个魔术盒子。
     * （1 <= N <= 1,000,000,000）
     * <p>
     * 共T行，每行1个整数，表示要将所有盒子的球变没，最少需要进行多少次操作。
     **/
    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE);
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        Map<Integer, Integer> map = new HashMap<>();
        map.put(1, 1);
        map.put(2, 2);
        for (int i = 0; i < n; i++) {
            int x = in.nextInt();
            System.out.println(getTimes(map, x));
        }
    }

    static private int getTimes(Map<Integer, Integer> map, int n) {
        if (map.containsKey(n)) {
            return map.get(n);
        } else {
//            int x;
//            if( (n & 1) == 1){
//                x = (n -1)/2;
//            }else {
//                x = n/2;
//            }
            int times = getTimes(map, n / 2) + 1;
            map.put(n, times);
            return times;
        }
    }
}
