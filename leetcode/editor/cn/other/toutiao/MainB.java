package toutiao;

import java.util.*;

/**
 * @program: LeetCode
 * @description:
 * @author: wd
 * @create: 2020-05-11 09:54
 **/

public class MainB {
    /**
     * @Description: 第一行表示文章，文章的长度不超过50000；
     * 第二行包含一个正整数N（2 <= N <= 50000），表示字典的单词数量；
     * 接下来N行，每行表示一个单词（单词的长度不超过20）；
     * 输出描述:
     * 一个正整数，考虑到结果数值可能非常大，输出对835672545的余数即可；
     * 如果无法翻译，请输出0；
     **/
    /*
    abcba
    5
    ab
    cb
    bc
    ba
    a
    */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        StringBuilder sb = new StringBuilder(str);
        Map<Integer, Set<String>> map = new HashMap<>();
        int m = in.nextInt();
        in.nextLine();
        int max = -1;
        for (int i = 0; i < m; i++) {
            String ss = in.nextLine().trim();
            int length = ss.length();
            if (!map.containsKey(length)) {
                map.putIfAbsent(length, new HashSet<>());
            }
            map.get(length).add(ss);
            max = Math.max(max, length);
        }
        in.close();
        System.out.println(getNum(sb, 0, map, max));

    }

    private static int getNum(StringBuilder sb, int start, Map<Integer, Set<String>> map, int n) {
        if (start == sb.length()) return 1;
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            if (start + i <= sb.length()) {
                String ss = sb.substring(start, start + i);
                if (map.containsKey(i)) {
                    if (map.get(i).contains(ss)) {
                        sum += getNum(sb, start + i, map, n);
                    }

                }
            }
        }
        return sum;
    }
}
