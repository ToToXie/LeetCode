import java.util.Scanner;

/**
 * @program: LeetCode
 * @description: 非leetcode的题
 * @author: wd
 * @create: 2020-04-22 08:35
 **/

public class SolutionTest {
    public static void main(String[] args) {
        SolutionTest solutionTest = new SolutionTest();
//        System.out.println(solutionTest.test1());
        solutionTest.test2();
    }

    /**
     * @Description: 有没有重复的数字 用异或运算
     **/
    public boolean test1() {
        int[] num = {0, 1, 2, 3, 4, 5, 6, 7, 8, 8};
        int temp = num[0];
        for (int i = 1; i < 10; i++) {
            temp ^= i;
        }
        for (int i : num) {
            temp ^= i;
        }
        return temp == 0 ? true : false;
    }

    public void test2() {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int[][] G = new int[n][m];
        int a;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                a = in.nextInt();
                G[i][j] = a;
            }
        }
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (G[i][j] != 0) {
                    dfs(G, i, j, n, m);
                    count++;
                }
            }
        }
        System.out.println(count);
    }

    public void dfs(int[][] nums, int row, int col, int n, int m) {
        if (row < n && col < m && nums[row][col] == 1) {
            nums[row][col] = 0;
            dfs(nums, row + 1, col, n, m);
            dfs(nums, row, col + 1, n, m);
        }
    }
}
