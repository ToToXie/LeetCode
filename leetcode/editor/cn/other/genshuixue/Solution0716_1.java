package genshuixue;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * @program: LeetCode
 * @description:
 * @author: wd
 * @create: 2020-07-16 19:20
 **/

public class Solution0716_1 {
    public static void main(String[] args) {
        Solution0716_1 genshuixue = new Solution0716_1();
        System.out.println(2 + 35 - 9 / 2);
        System.out.println(3 - 10 / 8 + 5 * 2);
        System.out.println(
                "genshuixue.getValue(\"3-10/8+5*2\") = " + genshuixue.getValue("2+35-9/2"));
    }

    public static int getResult(List<Object> list) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < list.size(); i++) {
            Object o = list.get(i);
            if (o instanceof Character) {
                char c = (Character) o;
                //当遇到操作符的时候，从栈中弹出两个元素，然后根据运算符的不同做相应的运算，然后把运算结果压栈。
                int b = stack.pop();
                int a = stack.pop();
                if (c == '+') stack.push(a + b);
                else if (c == '-') stack.push(a - b);
                else if (c == '*') stack.push(a * b);
                else stack.push(a / b);
            } else {
                stack.push((Integer) o);
            }
        }
        //最终栈中肯定只剩下一个元素，就是计算的结果。
        return stack.pop();
    }

    /**
     * 把中缀表达式转为后缀表达式
     * 前提条件：操作数的范围为整数，操作符为+,-,*,/,以及()
     *
     * @param s 字符串表达式
     * @return
     */
    public static List<Object> getPost(String s) {
        Stack<Character> stack = new Stack<>(); //保存操作符
        LinkedList<Object> list = new LinkedList<>(); //保存最终的后缀表达式
        StringBuilder sb = new StringBuilder();//用来存放整数
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= '0' && c <= '9') sb.append(c); //操作数直接输出到后缀表达式中
            else if (c == '+' || c == '-' || c == '*' || c == '/') {
                //把比当前操作符c的优先级高或者等于c的优先级的操作符依次弹出栈并保存到后缀表达式中，直到遇到栈顶操作符的优先级比c低
                if (sb.length() > 0) {
                    list.addLast(Integer.parseInt(sb.toString()));
                    sb.setLength(0);
                }
                while (!stack.isEmpty()) {
                    if (compare(stack.peek()) >= compare(c)) {
                        list.add(stack.pop());
                    } else break;
                }
                stack.push(c);//当前操作符c入栈
            } else if (c == '(') {
                if (sb.length() > 0) {
                    list.addLast(Integer.parseInt(sb.toString()));
                    sb.setLength(0);
                }
                stack.push(c); //左括号直接入栈
            } else {
                if (sb.length() > 0) {
                    list.addLast(Integer.parseInt(sb.toString()));
                    sb.setLength(0);
                }
                //当遇到右括号的时候，把栈中的操作符依次弹出并追加到后缀表达式中，直到遇到左括号停止，并把左括号弹出。
                while (stack.peek() != '(') {
                    list.add(stack.pop());
                }
                stack.pop();
            }
        }
        if (sb.length() > 0) {
            list.addLast(Integer.parseInt(sb.toString()));
            sb.setLength(0);
        }
        //把栈中所有的操作符全部弹出追加到后缀表达式中
        while (!stack.isEmpty()) list.add(stack.pop());
        return list;
    }

    /**
     * 计算运算符的优先级
     *
     * @param c 运算符
     * @return
     */
    public static int compare(char c) {
        if (c == '+' || c == '-') return 1;
        else if (c == '*' || c == '/') return 2;
        else return 0;
    }

    public String findMaxSuffixStr(String strA, String strB) {
        if (strA == null || strB == null || strA.length() < 1 || strB.length() < 1) {
            return "";
        }
        int m = strA.length();
        int n = strB.length();
        while (n > 0 && m > 0) {
            if (strA.charAt(m - 1) == strB.charAt(n - 1)) {
                n--;
                m--;
            } else break;
        }
        return m > 0 ? strA.substring(m) : "";
    }

    public int getValue(String expession) {
        if (expession == null) return 0;
        List<Object> list = getPost(expession);
        return getResult(list);
    }

}
