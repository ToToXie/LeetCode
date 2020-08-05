package meituan;

import java.util.HashMap;
import java.util.Scanner;

/**
 * @program: LeetCode
 * @description:
 * @author: wd
 * @create: 2020-05-11 09:54
 **/

public class MainC {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        HashMap<Integer, Integer> map = new HashMap<>(m);
//        Map<Integer,Integer> map2 = new TreeMap<>();
        int a, b, c;
        for (int i = 0; i < m; i++) {
            a = in.nextInt();
            b = in.nextInt();
            if (a == 1) {
                if (!map.containsKey(b)) {
                    c = b + 1;
                    while (map.containsKey(c)) {
                        c++;
                    }
                    if (c > n) c = -1;
                    map.put(b, c);
                }
            } else if (a == 2) {
                if (!map.containsKey(b)) {
                    System.out.println(b);
                } else {
                    c = b;
                    while (map.containsKey(c)) {
                        c = map.get(c);
                    }
                    map.put(b, c);
                    System.out.println(map.get(b));
                }
            }
        }
        in.close();
    }
}
