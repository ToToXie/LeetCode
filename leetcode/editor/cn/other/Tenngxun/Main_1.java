package Tenngxun;

import java.util.Scanner;

/**
 * @program: LeetCode
 * @description:
 * @author: wd
 * @create: 2020-08-23 20:00
 **/

public class Main_1 {
    /**
     * 5 3
     * 1 2 3 4 5
     * <p>
     * 输出的第一行有两个正整数n, k, n表示链表的长度,k表示要删除的节点。
     * 第二行为原始单链表中依次每个节点的值val。
     * 1 <= k <= n <= 1000000,  -1000000 <= val <= 1000000
     **/
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
//        List<Integer> list = new ArrayList<>(n);
        int x;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            x = in.nextInt();
            if (i != k - 1) {
                sb.append(x);
                if (i != n - 1) sb.append(" ");
            }
        }
        System.out.println(sb.toString());
    }
}
