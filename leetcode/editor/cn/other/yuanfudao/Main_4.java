package yuanfudao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @program: LeetCode
 * @description:
 * @author: wd
 * @create: 2020-07-26 22:03
 **/

public class Main_4 {
    /**
     * 中午是猿辅导水果时间，小猿会给每个同学发水果。猿辅导有一个矩形的办公区域，
     * 共有N 排，每排M个工位。平时小猿按照从第一排到最后一排的顺序发水果，
     * 某一天小猿突然发现似乎旋转着发水果是一种更有趣的方式，所以决定试试按照逆时针方向螺旋发水果。
     * 已知每个工位有一个数字，表示该工位员工的工号，每个员工的工号不同。已知小猿从（0, 0）位置开始，
     * 按照逆时针螺旋的顺序发水果，请输出收到水果的员工工号序列。
     **/
    static int[][] nums;
    static int m, n;

    public static void main(String[] args) {
        Main_4 main = new Main_4();
        Scanner in = new Scanner(System.in);
        m = in.nextInt();
        n = in.nextInt();
        nums = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                nums[i][j] = in.nextInt();
            }
        }
        List<Integer> ans = new ArrayList<>(m * n);
        int left = 0, up = 0, right = n, down = m;
        int cnt = 0;
        while (cnt < m * n) {
            for (int i = up; i < down && cnt < m * n; i++) {
                ans.add(nums[i][left]);
                cnt++;
            }
            left++;
            for (int i = left; i < right && cnt < m * n; i++) {
                ans.add(nums[down - 1][i]);
                cnt++;
            }
            down--;
            for (int i = down - 1; i >= up && cnt < m * n; i--) {
                ans.add(nums[i][right - 1]);
                cnt++;
            }
            right--;
            for (int i = right - 1; i >= left && cnt < m * n; i--) {
                ans.add(nums[up][i]);
                cnt++;
            }
            up++;
        }
        for (int i = 0; i < ans.size(); i++) {
            System.out.print(ans.get(i));
            if (i < ans.size() - 1) System.out.print(" ");
        }
    }
}
