package yuanfudao;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * @program: LeetCode
 * @description:
 * @author: wd
 * @create: 2020-07-26 10:12
 **/

public class Solution_3 {
    /**
     * 猿辅导APP需要下发一些宣传文本给学生，工程师们使用了一种字符压缩算法，为简单起见，
     * 假设被压缩的字符全部为大写字母序列，A,B,C,D....Z,压缩规则如下：
     * 1.AAAB可以压缩为A3B (单字符压缩不加括号)
     * 2.ABABA可以压缩为(AB)2A （多字符串压缩才加括号）
     * <p>
     * 输入数据保证不会出现冗余括号，且表示重复的数字一定合法且大于1，即不会出现：
     * 1.（A)2B   ------- （应为：A2B）
     * 2.  ((AB))2C,-----(应为：（AB)2C  )
     * 3. （A)B  ----- （应为：AB）
     * 4.   A1B，（AB)1C，（应为 AB，ABC）
     * <p>
     * 注意：数字可能出现多位数即A11B或者(AB)10C或者A02这种情况。
     * A11B = AAAAAAAAAAAB
     * (AB)10C = ABABABABABABABABABABC
     * A02 = AA
     * <p>
     * 数据分布：
     * 对于60%的数据，括号不会出现嵌套，即不会有 ((AB)2C)2这种结构。
     * 对于80%的数据，括号最多只嵌套一层，即不会有 (((AB)2C)2D)99 这种结构。
     * 对于100%的数据，括号可以嵌套任意层。
     * <p>
     * 5
     * A11B
     * (AA)2A
     * ((A2B)2)2G
     * (YUANFUDAO)2JIAYOU
     * A2BC4D2
     * <p>
     * AAAAAAAAAAAB
     * AAAAA
     * AABAABAABAABG
     * YUANFUDAOYUANFUDAOJIAYOU
     * AABCCCCDD
     **/
    public static void main(String[] args) {
        Solution_3 main = new Solution_3();
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        String s = in.nextLine();
        System.out.println(s);
        for (int i = 0; i < n; i++) {
            String str = in.next();
            System.out.print(
                    main.getString(str)
            );
            if (i != (n - 1)) System.out.println();
        }

    }

    private String getString(String str) {
        StringBuilder ans = new StringBuilder();
        Deque<String> stack = new LinkedList<>();
        String temp = "";
        for (int i = 0; i < str.toCharArray().length; ) {
            char c = str.charAt(i);
            if (c == '(') {
                stack.addLast(Character.toString(c));
                i++;
            } else if (Character.isLetter(c)) {
                stack.addLast(Character.toString(c));
                i++;
            } else if (Character.isDigit(c)) {
                int j = i + 1;
                while (j < str.length() && Character.isDigit(str.charAt(j))) j++;
                int k = Integer.parseInt(str.substring(i, j));
                temp = stack.pollLast();
                while (k > 0) {
                    k--;
                    ans.append(temp);
                }
                stack.addLast(ans.toString());
                ans.setLength(0);
                i = j;
                temp = "";
            } else if (c == ')') {
                while (!stack.isEmpty() && !stack.peekLast().equals("(")) {
                    temp = stack.pollLast() + temp;
                }
                stack.pollLast();
                stack.addLast(temp);
                temp = "";
                i++;
            }
        }
        while (!stack.isEmpty()) {
            temp = stack.pollLast() + temp;
        }
        return temp;
    }
}
