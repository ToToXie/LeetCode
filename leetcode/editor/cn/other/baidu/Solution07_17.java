package baidu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @program: LeetCode
 * @description:
 * @author: wd
 * @create: 2020-07-17 21:06
 **/

public class Solution07_17 {
    static int[] tree = {10, 5, 4, 1, 2, 3};

    public static void main(String[] args) {
        Solution07_17 solution07_17 = new Solution07_17();
        solution07_17.sort(tree);
    }

    private List<Integer> sort(int[] nums) {
        PriorityQueue<Integer> que = new PriorityQueue<>();
        Arrays.stream(nums).forEach(it -> que.add(it));
        ArrayList<Integer> list = new ArrayList<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            list.add(que.poll());
        }
        return list;
    }
}
