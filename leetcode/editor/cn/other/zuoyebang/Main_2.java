package zuoyebang;

/**
 * @program: LeetCode
 * @description:
 * @author: wd
 * @create: 2020-08-04 14:56
 **/

public class Main_2 {

    /**
     * int[] arr = {2,2,2,2,5,5,5,8}, k = 3, threshold = 4
     * <p>
     * output: 3
     **/
    public static void main(String[] args) {
        int[] arr = {2, 2, 2, 2, 5, 5, 5, 8};
        System.out.println(getNum(arr, 3, 4));
    }

    static int getNum(int[] nums, int k, int threshold) {
        if (nums == null || nums.length < 1) return 0;

        int temp = k * threshold;
        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }
        int left = 0, right = k;
        int ans = 0;
        if (sum >= temp) ans++;
        while (right < nums.length) {
            sum += nums[right];
            right++;
            sum -= nums[left];
            left++;
            if (sum >= temp) {
                ans++;
            }
        }
        return ans;
    }
}
