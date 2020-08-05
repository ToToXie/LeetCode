package yuanfudao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @program: LeetCode
 * @description:
 * @author: wd
 * @create: 2020-08-01 16:51
 **/

public class Main_7 {
    /**
     * 某天猿辅导 HR 组织大家去漂流，早上，参加团建的同学都到齐了，并且按到达公司的先后顺序排好队了。
     * 由于员工太多，一个大巴车坐不下，需要分多个车，车是足够的，但所有人需要按一定顺序上车，
     * 按如下规则安排上车的顺序：假设大巴车容量为 m，从队首开始，每 m 个人分成一个小组，
     * 每个小组坐一辆车。同时只有一个车打开车门供员工上车。 小组之间按从队尾到队首顺序依次上车，
     * 同一小组内先到的同学先上，求所有人上车的顺序。
     * <p>
     * 例如： 员工数 8， 车容量 3， 员工到达顺序为 1 2 3 4 5 6 7 8， 3个人一个小组，分三个小组，
     * 小组一： 1， 2， 3， 小组二： 4， 5， 6，小组三： 7，8。 小组上车顺序为： 小组三，小组二，小组一 。
     * 所有员工上车顺序为 7 8 4 5 6 1 2 3
     **/
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = in.nextInt();
        }
        List<Integer> ans = new ArrayList<>();
        int k = (n - 1) / m;
        while (k >= 0) {
            for (int i = 0; i < m; i++) {
                if (k * m + i < n) {
                    ans.add(nums[k * m + i]);
                }
            }
            k--;
        }
        for (int i = 0; i < ans.size(); i++) {
            if (i != 0) System.out.print(" ");
            System.out.print(ans.get(i));
        }
    }
}
