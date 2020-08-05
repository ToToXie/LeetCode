package month;

import util.ListNode;

import java.util.*;

/**
 * @program: LeetCode
 * @description:
 * @author: wd
 * @create: 2020-05-14 15:39
 **/

public class LC_20_03_Month {
    static int[][] grid = {{2, 2, 2}, {2, 1, 2}, {2, 2, 2}};
    static int[] nums = {1, 2, 5};
    static char[][] rook = {{'.', '.', '.', '.', '.', '.', '.', '.'},
            {'.', '.', '.', 'p', '.', '.', '.', '.'},
            {'.', '.', '.', 'R', '.', '.', '.', 'p'},
            {'.', '.', '.', '.', '.', '.', '.', '.'},
            {'.', '.', '.', '.', '.', '.', '.', '.'},
            {'.', '.', '.', 'p', '.', '.', '.', '.'},
            {'.', '.', '.', '.', '.', '.', '.', '.'},
            {'.', '.', '.', '.', '.', '.', '.', '.'}};
    static String str1 = "ABABAB", str2 = "ABAB";
    static private String ss = "th";

    public static void main(String[] args) {
        LC_20_03_Month lc_20_03_month = new LC_20_03_Month();
//        int abccccdd = lc_20_03_month.longestPalindrome(ss);

        int abccccdd = lc_20_03_month.coinChangeB(nums, 11);
        System.out.println(abccccdd);
    }

    public static int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    /**
     * 水壶问题
     **/
    public boolean canMeasureWater(int x, int y, int z) {
        if (x + y < z) return false;
        if (x == 0 || y == 0) {
            return z == 0 || x + y == z;
        }
        return z % gcd(x, y) == 0;
    }

    /**
     * 硬币兑换
     **/
    public int coinChange(int[] coins, int amount) {
        if (coins.length < 1) {
            return 0;
        }
        int[] dp = new int[amount + 1];
//        Arrays.fill(dp, Integer.MAX_VALUE);
        return coinChangeCore(coins, amount, dp);
    }

    /**
     * 动态规划：自下而上
     **/
    public int coinChangeB(int[] coins, int amount) {
        if (coins.length < 1) {
            return 0;
        }
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;

//        for (int i = 1; i <= amount; i++) {
//            for (int coin : coins) {
//                if (i - coin >= 0) {
//                    dp[i] = Math.min(dp[i - coin]+1, dp[i]);
//                }
//            }
//        }
        Arrays.sort(coins);
        for (int i = coins.length - 1; i >= 0; i--) {
            for (int j = 0; j <= amount; j++) {
                if (j - coins[i] >= 0) {
                    dp[j] = Math.min(dp[j - coins[i]] + 1, dp[j]);
                }
            }
        }

        return dp[amount] > amount ? -1 : dp[amount];
    }

    /**
     * 动态规划-自上而下 [通过]
     **/
    private int coinChangeCore(int[] coins, int amount, int[] dp) {
        if (amount == 0) return 0;
        if (amount < 0) return -1;
        if (dp[amount] != 0) {
            return dp[amount];
        }
        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            int ret = coinChangeCore(coins, amount - coin, dp);
            if (ret >= 0 && ret < min) {
                min = ret;
            }
        }
        dp[amount] = min == Integer.MAX_VALUE ? -1 : min + 1;
        return dp[amount];
    }

    /**
     * 最长上升子序列
     **/
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        int max = -1;
        for (int i = 0; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);

                }
            }
            max = Math.max(dp[i], max);
        }
        return max;
    }

    /**
     * 最长上升子序列
     * 贪心 + 二分 nlogn
     **/
    public int lengthOfLISB(int[] nums) {

        int length = nums.length;
        int[] dp = new int[length + 1];
        int len = 1;
        dp[len] = nums[0];
        for (int i = 1; i < length; i++) {
            if (nums[i] > dp[len]) {
                dp[++len] = nums[i];
            } else {
                int l = 0, pos = 0, r = len;
                while (l <= r) {
                    int mid = (l + r) >> 1;
                    if (nums[i] > dp[mid]) {
                        pos = mid;
                        l = mid + 1;
                    } else {
                        r = mid - 1;
                    }
                }
                dp[pos + 1] = nums[i];

            }

        }
        return len;
    }

    /**
     * 反转单链表
     **/
    public ListNode reverseList(ListNode head) {
        ListNode newHead = null;
        ListNode cur = head, next;
        while (cur != null) {
            next = cur.next;
            cur.next = newHead;
            newHead = cur;
            cur = next;
            if (cur != null) next = cur.next;
        }
        return newHead;
    }

    /**
     * 最长回文串
     * 给定一个包含大写字母和小写字母的字符串，找到通过这些字母构造成的最长的回文串。
     **/
    public int longestPalindrome(String s) {
        Map<Character, Integer> cMap = new HashMap<>();
        for (Character c : s.toCharArray()) {
            cMap.compute(c, (k, v) -> v == null ? 1 : v + 1);
        }
        int max = 0, sum = 0;
        for (Map.Entry<Character, Integer> it : cMap.entrySet()) {
            if ((it.getValue() & 1) == 1) {
                sum += it.getValue() - 1;
                if (max == 0 && it.getValue() == 1) {
                    max = 1;
                }
            } else {
                sum += it.getValue();
            }

        }
        return sum + max;
    }

    /**
     * 矩形重叠
     **/
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
//        int x1 = rec1[0],y1 = rec1[1],x2 = rec2[2],y2 = rec2[3];
//        int x1 = rec2[0],y1 = rec2[1],x2 = rec2[2],y2 = rec2[3];
        return !(rec1[2] <= rec2[0] ||   // left
                rec1[3] <= rec2[1] ||   // bottom
                rec1[0] >= rec2[2] ||   // right
                rec1[1] >= rec2[3]);    // top
    }

    /**
     * 给定一个带有头结点 head 的非空单链表，返回链表的中间结点。
     * <p>
     * 如果有两个中间结点，则返回第二个中间结点。
     **/
    public ListNode middleNode(ListNode head) {
        ListNode p = head, q = head;
        while (q != null) {
            q = q.next;
            if (q != null) {
                q = q.next;
            } else {
                break;
            }
            p = p.next;
        }
        return p;
    }

    /**
     * 三维形体的表面积
     **/
    public int surfaceArea(int[][] grid) {
        int sum = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] > 0) {
                    sum += 4 * (grid[i][j]) + 2;
                }
                if (i > 0) {
                    sum -= 2 * Math.min(grid[i][j], grid[i - 1][j]);
                }
                if (j > 0) {
                    sum -= 2 * Math.min(grid[i][j], grid[i][j - 1]);
                }
            }
        }
        return sum;
    }

    /**
     * 卡牌分组
     **/
    public boolean hasGroupsSizeX(int[] deck) {
        Map<Integer, Integer> times = new HashMap<>();
        int max = -1;
        for (int it : deck) {
            times.compute(it, (k, v) -> v == null ? 1 : v + 1);
            max = Math.max(max, times.get(it));
        }
        for (int i = 2; i <= max; i++) {
            boolean flag = true;
            for (Integer it : times.values()) {
                if (it % 2 != 0) {
                    flag = true;
                    break;
                }
            }
            if (flag) return true;
        }
        return false;
    }

    /**
     * 可以被捕获的棋子数
     **/
    public int numRookCaptures(char[][] board) {
        int n = board.length;
        int m = board[0].length;
        int x = -1, y = -1;
        for (int i = 0; i < n; i++) {
            if (x != -1) break;
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 'R') {
                    x = i;
                    y = j;
                    break;
                }
            }
        }
        int ans = 0;
        int[] index = {1, 0, -1, 0, 0, 1, 0, -1};
        for (int i = 0; i < 8; i += 2) {
            for (int j = 1; j < n; j++) {
                int newx = x + index[i] * j;
                int newy = y + index[i + 1] * j;
                if (newx >= 0 && newy >= 0 && newx < n && newy < m) {
                    if (board[newx][newy] == 'p') {
                        ans++;
                        break;
                    } else if (board[newx][newy] == 'P'
                            || board[newx][newy] == 'B'
                            || board[newx][newy] == 'b'
                            || board[newx][newy] == 'r'
                            || board[newx][newy] == 'R') {
                        break;
                    }
                }
            }
        }

        return ans;
    }

    /**
     * 将数组分成和相等的三个部分
     **/
    public boolean canThreePartsEqualSum(int[] A) {
        int sum = 0, curSum = 0;
        for (int it : A) {
            sum += it;
        }
        curSum = sum / 3;
        if (curSum * 3 != sum) return false;
        int temp = 0;
        for (int a : A) {
            temp += a;
            if (temp == curSum) temp = 0;
        }
        return temp == 0;
    }

    /**
     * 字符串的最大公因子
     **/
    public String gcdOfStrings(String str1, String str2) {
        String temp;
        if (str1.length() < str2.length()) {
            temp = str1;
            str1 = str2;
            str2 = temp;
        }
        for (int i = 1; i <= str2.length(); i++) {
            int l = str2.length() / i;
            String substring = str2.substring(0, l);
            if (match(str1, str2, substring)) {
                return substring;
            }

        }
        return "";
    }

    private boolean match(String s1, String s2, String partern) {
        int n = partern.length();
        if (s1.length() % n != 0 || s2.length() % n != 0) {
            return false;
        }
        for (int i = 0; i < s1.length(); i += n) {
            if (!partern.equals(s1.substring(i, i + n))) {
                return false;
            }
        }
        for (int i = 0; i < s2.length(); i += n) {
            if (!partern.equals(s2.substring(i, i + n))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 分糖果
     **/
    public int[] distributeCandies(int candies, int num_people) {
        int n = num_people;
        // how many people received complete gifts
        int p = (int) (Math.sqrt(2 * candies + 0.25) - 0.5);
        int remaining = (int) (candies - (p + 1) * p * 0.5);
        int rows = p / n, cols = p % n;

        int[] d = new int[n];
        for (int i = 0; i < n; ++i) {
            // complete rows
            d[i] = (i + 1) * rows + (int) (rows * (rows - 1) * 0.5) * n;
            // cols in the last row
            if (i < cols) d[i] += i + 1 + rows * n;
        }
        // remaining candies
        d[cols] += remaining;
        return d;
    }

    /**
     * 字符串压缩
     **/
    public String compressString(String S) {
        StringBuilder sb = new StringBuilder();
        int index = 0, count = 0;
        while (index < S.length()) {
            char c = S.charAt(index);
            count = 0;
            while (c == S.charAt(index)) {
                index++;
                count++;
            }
            sb.append(c).append(count);
        }
        return S.length() <= sb.length() ? S : sb.toString();
    }

    /**
     * 合并排序的数组
     **/
    public void merge(int[] A, int m, int[] B, int n) {
        int indexA = m - 1;
        int indexB = n - 1;
        int index = m + n - 1;
        while (indexA >= 0 && indexB >= 0) {
            if (A[indexA] > B[indexB]) {
                A[index] = A[indexA--];
            } else {
                A[index] = B[indexB--];
            }
            index--;
        }
        while (indexB >= 0) {
            A[index--] = B[indexB--];
        }

    }

    /**
     * 和为k的子数组
     **/
    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int sum = 0, ans = 0;
        map.put(0, 1);
        for (int it : nums) {
            sum += it;
            if (map.containsKey(sum - k)) {
                ans += map.get(sum - k);
            }
            map.compute(sum, (a, v) -> v == null ? 1 : v + 1);
        }
        return ans;
    }

    /**
     * 单词的压缩编码
     **/
    public int minimumLengthEncoding(String[] words) {
        TrieNode trie = new TrieNode();
        Map<TrieNode, Integer> nodes = new HashMap();

        for (int i = 0; i < words.length; ++i) {
            String word = words[i];
            TrieNode cur = trie;
            for (int j = word.length() - 1; j >= 0; --j)
                cur = cur.get(word.charAt(j));
            nodes.put(cur, i);
        }

        int ans = 0;
        for (TrieNode node : nodes.keySet()) {
            if (node.count == 0)
                ans += words[nodes.get(node)].length() + 1;
        }
        return ans;

    }


}

/**
 * 用队列来模拟栈
 **/
class MyStack {
    private Queue<Integer> q1, q2;
    private Integer top;

    /**
     * Initialize your data structure here.
     */
    public MyStack() {
        q1 = new LinkedList<>();
        q2 = new LinkedList<>();
    }

    /**
     * Push element x onto stack.
     */
    public void push(int x) {
        top = x;
        q1.add(x);
    }

    /**
     * Removes the element on top of the stack and returns that element.
     */
    public int pop() {
        while (q1.size() != 1) {
            top = q1.poll();
            q2.add(top);
        }
        Queue<Integer> temp = q2;
        q2 = q1;
        q1 = temp;
        return q2.poll();
    }

    /**
     * Get the top element.
     */
    public int top() {
        return top;
    }

    /**
     * Returns whether the stack is empty.
     */
    public boolean empty() {
        return q1.isEmpty();
    }
}

/**
 * 前缀树
 **/
class TrieNode {
    TrieNode[] children;
    // 0 叶子节点 1 非叶子节点
    int count;

    TrieNode() {
        children = new TrieNode[26];
        count = 0;
    }

    public TrieNode get(char c) {
        if (children[c - 'a'] == null) {
            children[c - 'a'] = new TrieNode();
            count++;
        }
        return children[c - 'a'];
    }
}
