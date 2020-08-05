package yuanfudao;

import javafx.util.Pair;

import java.util.*;

/**
 * @program: LeetCode
 * @description:
 * @author: wd
 * @create: 2020-08-01 19:29
 **/

public class Main_8 {
    /**
     * 小猿非常热爱学习，所以他在猿辅导上购买了N节课来提升自己，每节课有一个开始时间S和结束时间E（S和E均用正整数表示）。
     * 买完课程后，粗心的小猿发现这些课程之间有些时间冲突，幸好小猿有一种“一心多用”的超能力，能同时兼顾K节课上课。
     * 当然是K越大，使用这种能力就越累。请问小猿最少需要一心几用，才能上完所有他买的课程呢
     * 4
     * 1 4
     * 1 2
     * 2 3
     * 3 4
     * <p>
     * 2
     **/
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        List<Pair<Integer, Integer>> list = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            list.add(new Pair<>(a, b));
        }
        Collections.sort(list, (a, b) -> a.getValue() - b.getValue());
        Queue<Integer> queue = new PriorityQueue<>();
        queue.add(list.get(0).getValue());
        for (int i = 1; i < list.size(); i++) {
            if (queue.peek() <= list.get(i).getKey()) {
                queue.poll();
            }
            queue.add(list.get(i).getValue());
        }
        System.out.println(queue.size());
    }
}
