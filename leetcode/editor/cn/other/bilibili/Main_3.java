package bilibili;

/**
 * @program: LeetCode
 * @description:
 * @author: wd
 * @create: 2020-08-13 19:33
 **/

public class Main_3 {
    public static void main(String[] args) {
        System.out.println(GetCoinCount(1024));
    }

    static public int GetCoinCount(int N) {
        int[] nums = {1, 4, 16, 64, 1024};
        int cnt = 4;
        int count = 0;
        N = 1024 - N;
        while (N > 0) {
            if (N - nums[cnt] >= 0) {
                count++;
                N -= nums[cnt];
            } else {
                cnt--;
            }
        }
        return count;
    }
}

