package pdd;

import java.util.Scanner;

/**
 * @program: LeetCode
 * @description:
 * @author: wd
 * @create: 2020-08-02 14:55
 **/

public class Main_2 {
    /**
     * 在一块长为n，宽为m的场地上，有n✖️m个1✖️1的单元格。每个单元格上的数字就是按照从1到n和1到m中的数的乘积。具体如下
     * <p>
     * n = 3, m = 3
     * 1   2   3
     * 2   4   6
     * 3   6   9
     * <p>
     * 给出一个查询的值k，求出按照这个方式列举的的数中第k大的值v。
     * 例如上面的例子里，
     * 从大到小为(9, 6, 6, 4, 3, 3, 2, 2, 1)
     * k = 1, v = 9
     * k = 2, v = 6
     * k = 3, v = 6
     * ...
     * k = 8, v = 2
     * k = 9, v = 1
     **/
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int m = in.nextInt();
        int n = in.nextInt();
        int k = in.nextInt();
        int left = 1;
        int right = m * n + 1;
        k = right - k;
        int cnt = 0;
        while (left < right) {
            int mid = left + (right - left) / 2;
            int row = mid / n;
            cnt = 0;
            cnt += row * n;
            for (int i = row + 1; i <= m; i++) {
                cnt += mid / i;
            }
            if (cnt < k) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        System.out.println(left);
    }
}
