package Tenngxun;

import java.util.Collections;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * @program: LeetCode
 * @description:
 * @author: wd
 * @create: 2020-08-23 21:00
 **/

public class Main_2 {
    /**
     * 输入一个字符串 s，s 由小写英文字母组成，保证 s 长度小于等于 5000 并且大于等于 1。在 s 的所有不同的子串中，输出字典序第 k 小的字符串。
     * 字符串中任意个连续的字符组成的子序列称为该字符串的子串。
     * 字母序表示英文单词在字典中的先后顺序，即先比较第一个字母，若第一个字母相同，则比较第二个字母的字典序，依次类推，则可比较出该字符串的字典序大小。
     * 不同的子串依次为：
     * a aa aab aabb ab abb b bb
     * 所以答案为aab
     * <p>
     * 给出的k 是 1 - 5 之间，所以  可能的 子串的长度 不会超过 5 ，
     * 由于不确定边界 大可以增2
     * 例如 aaaaaaaaaaaaaaaaaaaaaaaaaaa
     * 则 a aa aaa aaaa aaaaa 多余的都不考虑
     **/
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        int k = in.nextInt();
        TreeSet<String> set = new TreeSet<>(Collections.reverseOrder());
        int n = str.length();
        for (int i = 0; i < n; i++) {
            //可能的 子串的长度 不会超过 5
            for (int j = i + 1; j < i + 7; j++) {
                set.add(str.substring(i, j));
                if (set.size() > k) set.pollFirst();
            }
        }
        System.out.println(set.pollFirst());

    }
}
