package tags;

/**
 * @program: LeetCode
 * @description:
 * @author: wd
 * @create: 2020-08-31 19:42
 **/

public class Bit {
    public static void main(String[] args) {
        System.out.println();
    }

    public int reverseBits(int n) {
        int res = 0;
        for (int i = 0; i < 32; i++) {
            res = (res << 1) + (n & 1);
            n >>= 1;
        }
        return res;
    }

    public int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            n = n & (n - 1);
            count++;
        }
        return count;
    }
}
