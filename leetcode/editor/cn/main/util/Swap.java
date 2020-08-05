package util;

/**
 * @program: LeetCode
 * @description: 交换使用
 * @author: wd
 * @create: 2020-07-17 14:03
 **/

public class Swap {
    static public void swap(int[] nums, int a, int b) {
        if (a != b) {
            nums[a] = nums[a] ^ nums[b];
            nums[b] = nums[a] ^ nums[b];
            nums[a] = nums[a] ^ nums[b];
        }
    }

    static public void swap(int[][] nums, int x1, int y1, int x2, int y2) {
        if (x1 != x2 || y1 != y2) {
            int temp = nums[x1][y1];
            nums[x1][y1] = nums[x2][y2];
            nums[x2][y2] = temp;
        }
    }

    static public void swap(char[] s, int x, int y) {
        if (x != y) {
            char c = s[x];
            s[x] = s[y];
            s[y] = c;
        }
    }
}
