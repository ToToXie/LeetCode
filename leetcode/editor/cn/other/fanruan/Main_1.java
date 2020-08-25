package fanruan;

import java.io.Serializable;
import java.util.*;

/**
 * @program: LeetCode
 * @description:
 * @author: wd
 * @create: 2020-08-22 20:05
 **/

public class Main_1 {
    /**
     * 3 3 4 3
     * 1:5 3:8 5:1
     * 1:2 2:2 4:3  5:2
     * 2:3 4:4 6:3
     **/
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] nums = new int[n];
        List<Queue<Pair<Integer, Long>>> list = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            nums[i] = in.nextInt();
        }
        String str;
        int a;
        long b;
        for (int i = 0; i < n; i++) {
            Queue<Pair<Integer, Long>> queue = new LinkedList<>();
            for (int j = 0; j < nums[i]; j++) {
                str = in.next();
                String[] split = str.split(":");
                a = Integer.parseInt(split[0]);
                b = Integer.parseInt(split[1]);
                queue.add(new Pair<>(a, b));
            }
            list.add(queue);
        }
        for (int i = 0; i < n; i++) {
            if (list.get(i).size() == 0) list.set(i, null);
        }
        Queue<Pair<Integer, Long>> ans = new LinkedList<>();
        while (true) {
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                if (list.get(i) != null) min = Math.min(min, list.get(i).peek().getKey());
            }
            if (min == Integer.MAX_VALUE) break;
            long sum = 0;
            for (int i = 0; i < n; i++) {
                if (list.get(i) != null && min == list.get(i).peek().getKey()) {
                    sum += list.get(i).poll().getValue();
                    if (list.get(i).size() == 0) {
                        list.set(i, null);
                    }
                }
            }
            ans.add(new Pair<>(min, sum));
        }
        int i = 0;
        for (Pair<Integer, Long> it : ans) {
            System.out.print(it.getKey() + ":" + it.getValue());
            if (i++ < ans.size() - 1) System.out.print(" ");
        }
    }

    static class Pair<K, V> implements Serializable {


        private K key;
        private V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        @Override
        public String toString() {
            return key + "=" + value;
        }
    }
}
