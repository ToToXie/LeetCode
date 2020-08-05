package alibaba;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * @program: LeetCode
 * @description:
 * @author: wd
 * @create: 2020-07-29 09:09
 **/

public class Main_1 {
    /**
     * 小强已经开始准备放大他的n个恐龙蛋了，他把它们放在他自制的n个放大器，
     * 其中第i个放大器每天可以使放在上面的恐龙蛋增大i个尺寸，为了不偏心，
     * 他把最大的恐龙蛋放在第i个放大器上，第二大的恐龙蛋放在第i个放大器上，
     * 以此类推。他想要孵化两个一样大的恐龙蛋，所以他想要知道最早几天后有两个恐龙蛋会变成一样大？
     * 保证刚开始没有两个恐龙蛋是一样大的
     * <p>
     * 3
     * 8 4 2
     * 2
     **/
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] ints = new int[n];
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            ints[i] = in.nextInt();
            set.add(ints[i]);

        }
        /**
         *  只考虑相邻的情况
         **/
        int ans = Integer.MAX_VALUE;
        for (int i = 1; i < n; i++) {
            ans = Math.min(ans, ints[i - 1] - ints[i]);
        }
//        int ans =0;
//        while (set.size() == n){
//            for (int i = 0; i < n; i++) {
//                set.remove(ints[i]);
//                ints[i]+= (i + 1);
//                set.add(ints[i]);
//            }
//            ans++;
//        }

//        int ans =Integer.MAX_VALUE;
//        for (int i = 0; i < n; i++) {
//            int a = ints[i];
//            for (int j = i+1; j < n; j++) {
//                int b = ints[j];
//                int z = (a - b) / (j - i);
//                if(z * (j - i) == (a - b)){
//                    ans = Math.min(ans, z);
//                }
//                if(ans == 1) break;
//            }
//        }
        System.out.println(ans);
    }
}
