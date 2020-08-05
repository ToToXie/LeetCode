package pdd;

import javafx.util.Pair;

import java.util.*;

/**
 * @program: LeetCode
 * @description:
 * @author: wd
 * @create: 2020-05-06 15:31
 **/

public class MainD {
    /**
     * 题意：
     * <p>
     * 输入一个n（n≤100000）个元素的正整数序列，求一个连续子序列，使得该序列中所有元素的最大公约数与序列长度的乘积最大。
     * 例如，5个元素的序列30, 60, 20, 20, 20的最优解为{60, 20, 20, 20}，乘积为gcd(60,20,20,20)*4=80。
     * <p>
     * 分析：
     * <p>
     * 从左到右枚举序列的右边界j，然后快速求出左边界i≤j，使得MGCD(i,j)最大。
     * 其中MGCD(i,j)定义为gcd(a[i],a[i+1],...,a[j])*(j-i+1)。
     * 考虑序列5, 8, 6, 2, 6, 8，当j=5时需要比较i=1, 2, 3, 4, 5时的MGCD(i,j)，如下表所示：
     * <p>
     * i=1，gcd表达式=gcd(5,8,6,2,6)，gcd值=1，序列长度=5。
     * i=2，gcd表达式=gcd(8,6,2,6)，gcd值=2，序列长度=4。
     * i=3，gcd表达式=gcd(6,2,6)，gcd值=2，序列长度=3。
     * i=4，gcd表达式=gcd(2,6)，gcd值=2，序列长度=2。
     * i=5，gcd表达式=gcd(6)，gcd值=6，序列长度=1。
     * <p>
     * 从下往上看，gcd表达式里每次多一个元素，有时gcd不变，有时会变小，而且每次变小时一定是变成了它的某个约数。
     * 换句话说，不同的gcd值最多只有logj种！当gcd值相同时，序列长度越大越好，所以可以把表简化一下：
     * <p>
     * gcd值=1，i=1。
     * gcd值=2，i=2。
     * gcd值=6，i=5。
     * <p>
     * 因为表里只有logj个元素，所以可以依次比较每一个i对应的MGCD(i,j)，时间复杂度为O(logj)。
     * 下面考虑j从5变成6时，这个表会发生怎样的变化。
     * 首先，上述所有gcd值都要再和a6=8取gcd，然后要加入i=6的项目，gcd值为8。
     * 由于相同的gcd值只需要保留i的最小值，所以i=5被删除，最终得到如下表所示结果。
     * <p>
     * gcd值=1，i=1。
     * gcd值=2，i=2。
     * gcd值=8，i=6。
     * <p>
     * 总时间复杂度为O(nlogn)。
     **/
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int num, ans = -1;
        List<Pair<Integer, Integer>> oldDp = new ArrayList<>();
        List<Pair<Integer, Integer>> newDp = new ArrayList<>();
        Map<Integer, Integer> oldMap = new HashMap<>();
        Map<Integer, Integer> newMap = new HashMap<>();
        Map<Integer, Integer> temp;
        for (int i = 0; i < n; i++) {
            num = in.nextInt();
            for (Map.Entry<Integer, Integer> it : oldMap.entrySet()) {
                int oldGcd = it.getKey();
                int oldIndex = it.getValue();
                int newGcd = gcd(oldGcd, num);
                newMap.putIfAbsent(newGcd, oldIndex);
                ans = Math.max(ans, newGcd * (i - oldIndex + 1));
            }

            newMap.putIfAbsent(num, i);
            oldMap.clear();
            temp = oldMap;
            oldMap = newMap;
            newMap = temp;
            ans = Math.max(ans, num);
        }
        in.close();
        System.out.println(ans);

    }

    private static int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }
}
