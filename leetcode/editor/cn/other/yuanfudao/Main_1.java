package yuanfudao;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @program: LeetCode
 * @description:
 * @author: wd
 * @create: 2020-07-26 15:00
 **/

public class Main_1 {
    static int[] nums;
    static int n, k;

    public static void main(String[] args) {
        Main_1 main_1 = new Main_1();
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        k = in.nextInt();
        nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = in.nextInt();
        }
        int[] ints = getNums();
        for (int i = 0; i < ints.length; i++) {
            System.out.print(ints[i]);
            if (i < ints.length - 1) System.out.print(" ");
        }

    }

    static private int[] getNums() {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        return Arrays.stream(nums).filter(it -> map.get(it) <= k).toArray();
    }
}
