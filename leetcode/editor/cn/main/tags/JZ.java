package tags;

import util.TreeNode;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @program: LeetCode
 * @description:
 * @author: wd
 * @create: 2020-07-04 09:43
 **/

public class JZ {
    static int[] ints1 = {7, 1, 5, 3, 6, 4};
    static int[] ints2 = {5, 7, 7, 8, 8, 10};
    static Integer[] integers = {4, 2, 5, 1, 3};
    //    static String str1 = "aaa";
    static String str1 = "mississippi";
    static String str2 = "mis*suvyls*p*.";
    static private Integer[][] integerss = {{7, null}, {13, 0}, {11, 4}, {10, 2}, {1, 0}};
//    static String str2 = "ab*a*c*a";

    public static void main(String[] args) {
        JZ jz = new JZ();
//        Node node = Node.buildNode(integerss);
//        TreeNode node = TreeNode.buildByLevelOrder(integers);

//        for (int i : ints2) {
//            medianFinder.addNum(i);
//            System.out.println(medianFinder.findMedian());
//        }
        System.out.println(
                jz.CheckPermutation("abc", "bda")
//                Arrays.toString(jz.constructArr(ints1))
//                Arrays.deepToString(jz.findContinuousSequence(9))

        );

    }

    /**
     * 面试题 01.02 判罚是否互为字符重排
     **/
    public boolean CheckPermutation(String s1, String s2) {
        if (s1 == null && s2 == null) return true;
        else if (!(s1 != null && s2 != null)) return false;
        if (s1.length() != s2.length()) return false;
        int[] chars = new int[128];
        for (int i = 0; i < s1.length(); i++) {
            chars[s1.charAt(i)]++;
            chars[s2.charAt(i)]--;
        }
        return !Arrays.stream(chars).anyMatch(a -> a != 0);
    }

    /**
     * 67 把字符串转换成整数
     **/
    public int strToInt(String str) {
        String s = str.trim();
        long num = 0L;
        int flag = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (i == 0 && c == '-') {
                flag = -1;
            } else if (i == 0 && c == '+') {
                flag = 1;
            } else if (Character.isDigit(c)) {
                num = num * 10 + c - '0';
                if (flag >= 0 && num > Integer.MAX_VALUE) {
                    return Integer.MAX_VALUE;
                } else if (flag < 0 && num - 1 > Integer.MAX_VALUE) {
                    return Integer.MIN_VALUE;
                }
            } else break;
        }
        if (flag == -1) {
            if (num - 1 > Integer.MAX_VALUE) {
                return Integer.MIN_VALUE;
            } else {
                return -(int) num;
            }
        } else {
            return num > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int) num;
        }

    }

    /**
     * 63 股票的最大利润
     **/
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 1) {
            return 0;
        }
        int min = prices[0], max = 0;
        for (int i = 1; i < prices.length; i++) {
            max = Math.max(max, prices[i] - min);
            min = Math.min(min, prices[i]);
        }
        return max;
    }

    /**
     * 49 丑数
     **/
    public int nthUglyNumber(int n) {
        if (n == 1) return 1;
        int[] dp = new int[n];
        int a = 0, b = 0, c = 0;
        dp[0] = 1;
        for (int i = 1; i < n; i++) {
            dp[i] = Math.min(dp[a] * 2, Math.min(dp[b] * 3, dp[c] * 5));
            if (dp[i] == dp[a] * 2) {
                a++;
            }
            if (dp[i] == dp[b] * 3) {
                b++;
            }
            if (dp[i] == dp[c] * 5) {
                c++;
            }
        }
        return dp[n - 1];
    }

    /**
     * 48 最长不含重复字符的子字符串
     **/
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() < 1) {
            return 0;
        }
        Set<Character> set = new HashSet<>();
        int left = 0, right = 0, max = -1;
        while (left <= right && right < s.length()) {
            char c = s.charAt(right);
            if (set.contains(c)) {
                while (left < right && s.charAt(left) != c) {
                    set.remove(s.charAt(left));
                    left++;
                }
                set.remove(c);
            } else {
                right++;
                set.add(c);
                max = Math.max(max, set.size());
            }
        }
        return max;
    }

    /**
     * 47 礼物的最大价值
     **/
    public int maxValue(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < m || i < n; i++) {
            if (i < n) {
                dp[0][i] = dp[0][i - 1] + grid[0][i];
            }
            if (i < m) {
                dp[i][0] = dp[i - 1][0] + grid[i][0];
            }
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        return dp[m - 1][n - 1];
    }

    /**
     * 45 把数组排成最小的数
     **/
    public String minNumber(int[] nums) {
        String collect = Arrays.stream(nums).mapToObj(a -> Integer.toString(a)).sorted(
                (a, b) -> (a + b).compareTo(b + a)
        ).collect(Collectors.joining());
        return collect;
    }

    /**
     * 01.01 判断字符串是否唯一
     **/
    public boolean isUnique(String astr) {
        long high = 0, low = 0;
        for (char c : astr.toCharArray()) {
            if (c > 64) {
                long index = 1L << (c - 64);
                if ((index & high) == index) {
                    return false;
                }
                high |= index;
            } else {
                long index = 1L << c;
                if ((index & low) == index) {
                    return false;
                }
                low |= index;
            }
        }
        return true;
    }

    /**
     * 68-2
     * 二叉树的最近公共祖先
     **/
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root.val == p.val || root.val == q.val) {
            return root;
        }
        TreeNode left = lowestCommonAncestor2(root.left, p, q);
        TreeNode right = lowestCommonAncestor2(root.right, p, q);
        if (left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }
        return root;
    }

    /**
     * 68 -1
     * 二叉搜索树的最近公共祖先
     **/
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (p.val > q.val) {
            TreeNode temp = q;
            q = p;
            p = temp;

        }
        while (root != null) {
            if (root.val == p.val || root.val == q.val) {
                return root;
            } else if (root.val > q.val) {
                root = root.left;
            } else if (root.val < p.val) {
                root = root.right;
            } else {
                return root;
            }
        }
        return null;
    }

    public TreeNode lowestCommonAncestorB(TreeNode root, TreeNode p, TreeNode q) {
        if (root.val > q.val && root.val > p.val) {
            return lowestCommonAncestorB(root.left, p, q);
        } else if (root.val < q.val && root.val < p.val) {
            return lowestCommonAncestorB(root.right, p, q);
        }
        return root;
    }

    /**
     * 66 构建乘积数组
     **/
    public int[] constructArr(int[] a) {
        if (a == null || a.length < 1) {
            return a;
        }
        int[] ans = new int[a.length];
        ans[0] = a[0];
        for (int i = 1; i < a.length; i++) {
            ans[i] = a[i] * ans[i - 1];
        }
        int x = 1, y = 1;
        for (int j = a.length - 1; j >= 0; j--) {
            if (j == a.length - 1) y = 1;
            else y *= a[j + 1];
            if (j != 0) ans[j] = y * ans[j - 1];
            else ans[j] = y;
        }
        return ans;
    }

    /**
     * 59-2 队列的最大值
     **/
    class MaxQueue {
        private Deque<Integer> que;
        private Deque<Integer> max;

        public MaxQueue() {
            que = new LinkedList<>();
            max = new LinkedList<>();
        }

        public int max_value() {
            if (max.isEmpty()) {
                return -1;
            }
            return max.peek();
        }

        public void push_back(int value) {
            while (!max.isEmpty() && max.peekLast() < value) {
                max.pollLast();
            }
            max.add(value);
            que.add(value);
        }

        public int pop_front() {
            if (que.isEmpty()) {
                return -1;
            }
            int value = que.poll();
            if (max.peek() == value) {
                max.poll();
            }
            return value;
        }
    }
}
