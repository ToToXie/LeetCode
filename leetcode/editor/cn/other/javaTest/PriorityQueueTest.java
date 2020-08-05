package javaTest;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @program: LeetCode
 * @description:
 * @author: wd
 * @create: 2020-05-08 20:58
 **/

public class PriorityQueueTest {
    public static void main(String[] args) {
        Map<Integer, Integer> map = new HashMap<>();
        int count = 0;
        for (int i = 0; i < 10; i += 2) {
            map.putIfAbsent(i, count++);
        }
        for (int i = 1; i < 10; i += 2) {
            map.putIfAbsent(i, count++);
        }
        PriorityQueue<Integer> objects = new PriorityQueue<>(8, (o1, o2) -> {
            return o2 - o1;
        });
        for (int i = 0; i < 10; i++) {
            objects.add(i);
            System.out.println("objects = " + objects);

        }
        objects.forEach(System.out::print);
        System.out.println();
        while (!objects.isEmpty()) {
            System.out.printf("%d ", objects.poll());
        }

    }
}
