package yuanfudao;

import java.util.Scanner;

/**
 * @program: LeetCode
 * @description: [编程题]课程质量建设
 * @author: wd
 * @create: 2020-07-26 23:39
 **/

public class Main_6 {
    /**
     * 辅导课堂在推进质量建设，需要分析每堂直播课的用户报障数量。
     * 当连续多个课程的报障数量之和大于一个数s的时候，
     * 系统会发出报警。小猿想知道最长连续的没有触发报警的课程数量。
     **/
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int s = in.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = in.nextInt();

        }
        int left = 0, right = 0;
        int sum = 0, max = -1;
        while (left < n && right < n) {
            if (sum <= s) {
                sum += nums[right];
                if (sum <= s) max = Math.max(max, right - left + 1);
                right++;
            } else {
                sum -= nums[left];
                left++;
            }
        }
        System.out.println(max);
    }

}
