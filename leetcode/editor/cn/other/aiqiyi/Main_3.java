package aiqiyi;

import java.util.*;

/**
 * @program: LeetCode
 * @description:
 * @author: wd
 * @create: 2020-08-23 15:24
 **/

public class Main_3 {
    /**
     * '('，')'，'{'，'}'，'['，']'
     **/
    static char[] chars = {'(', '[', '{'};
    char[] indexs = {'(', '[', '{'};

    public static void main(String[] args) {
        Map<Character, Integer> mapToIndex = new HashMap<>();
        mapToIndex.put(')', 0);
        mapToIndex.put(']', 1);
        mapToIndex.put('}', 2);
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();

        Deque<Character> stack = new LinkedList<>();
        boolean flag = true;
        for (char c : str.toCharArray()) {
            int cnt = mapToIndex.getOrDefault(c, -1);
            if (cnt == -1) {
                stack.addLast(c);
            } else {
                if (stack.pollLast() != chars[cnt]) {
                    flag = false;
                    break;
                }
            }
        }
        System.out.println(flag ? "True" : "False");
    }
}
