package bilibili;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @program: LeetCode
 * @description:
 * @author: wd
 * @create: 2020-08-13 19:24
 **/

public class Main_2 {
    public static void main(String[] args) {
        System.out.println(IsValidExp("{[]}"));
    }

    static public boolean IsValidExp(String s) {
        // write code here
        /**
         *  "{[]}"
         **/
        char[] chars = {')', ']', '}'};
        if (s == null || s.length() < 1) return true;
        Deque<Integer> stack = new LinkedList<>();
        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.addLast(0);
            } else if (c == '[') {
                stack.addLast(1);
            } else if (c == '{') {
                stack.addLast(2);
            } else {
                if (!stack.isEmpty() && chars[stack.peekLast()] == c) {
                    stack.pollLast();
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}
