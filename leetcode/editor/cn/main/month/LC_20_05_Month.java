package month;

import javafx.util.Pair;
import util.ListNode;
import util.TreeNode;

import java.util.*;

/**
 * @program: LeetCode
 * @description: 2020-05 每日一题
 * @author: wd
 * @create: 2020-05-07 17:19
 **/

public class LC_20_05_Month {
    static int[] preorder = {3, 9, 20, 15, 7};
    static int[] inorder = {9, 3, 15, 20, 7};
    private static Integer[] nums = {-2, 3, -4};
    private static int[] ints = {2, 1, 5, 6, 2, 3};
    private static int[][] numss = {{1, 0}, {2, 0}, {3, 1}, {3, 2}};
    private static int[][] numsss = {{2, 0}, {1, 0}};
    private static int[][] grid2 = {{1, 1, 0, 0, 0}, {1, 1, 0, 0, 0}, {0, 0, 0, 1, 1}, {0, 0, 0, 1, 1}};
    private static int[][] grid = {{0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
            {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
            {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}};
    /**
     * 岛屿的最大面积
     **/
    static private int[] index = {1, 0, -1, 0, 0, 1, 0, -1};
    /**
     * 二叉树的最近公共祖先
     **/
    static private TreeNode ans;
    private char[][] cg = {
            {'1', '0', '1', '0', '0'},
            {'1', '0', '1', '1', '1'},
            {'1', '1', '1', '1', '1'},
            {'1', '0', '0', '1', '0'}
    };

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        LC_20_05_Month lc_20_05_month = new LC_20_05_Month();
//        ListNode root = ListNode.bulideByLevelOrder(ints);
        int listNode = lc_20_05_month.largestRectangleArea(ints);
        System.out.println(listNode);

    }

    public static int[] getNext(String str) {
        int[] next = new int[str.length()];
        int j = -1;
        next[0] = -1;
        for (int i = 1; i < str.length(); i++) {
            while (j != -1 && str.charAt(i) != str.charAt(j + 1)) {
                j = next[j];
            }
            if (str.charAt(i) == str.charAt(j + 1)) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }

    public static boolean KMP(String str, String pattern) {
        int m = str.length(), n = pattern.length();
        int[] next = getNext(pattern);
        int j = -1;
        for (int i = 0; i < m; i++) {
            while (j != -1 && str.charAt(i) != pattern.charAt(j + 1)) {
                j = next[j];
            }
            if (str.charAt(i) == pattern.charAt(j + 1)) {
                j++;
            }
            if (j == m - 1) {
                return true;
            }
        }
        return false;
    }

    /**
     * 这个函数 表示 root 节点的子树中是否包含 p 节点或 q节点，注意 是 或
     **/
    private static boolean lowestCommonAncestorCore(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return false;
        boolean left = lowestCommonAncestorCore(root.left, p, q);
        boolean right = lowestCommonAncestorCore(root.right, p, q);
        if ((left && right) || (root.val == p.val || root.val == q.val) && (left || right)) {
            ans = root;
        }
        return left || right || root.val == p.val || root.val == q.val;
    }

    private static void lowestCommonAncestorDFS(TreeNode root, Map<Integer, TreeNode> map) {
        if (root.left != null) {
            map.putIfAbsent(root.left.val, root);
            lowestCommonAncestorDFS(root.left, map);
        }
        if (root.right != null) {
            map.putIfAbsent(root.right.val, root);
            lowestCommonAncestorDFS(root.right, map);
        }
    }

    /**
     * 柱状图中的最大矩形
     **/
    public int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int length = heights.length;
        int[] left = new int[length];
        int[] right = new int[length];
        Arrays.fill(right, length);
        for (int i = 0; i < length; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                right[stack.peek()] = i;
                stack.pop();
            }
            left[i] = (stack.isEmpty() ? -1 : stack.peek());
            stack.push(i);
        }
        int max = 0;
        for (int i = 0; i < length; i++) {
            max = Math.max(max, (right[i] - left[i] - 1) * heights[i]);
        }
        return max;
    }

    /**
     * 每个元音包含偶数次的最长子字符串  字符串版本
     **/
    public int findTheLongestSubstring(String s) {
        StringBuilder sb = new StringBuilder("00000");
        char[] chars = {'a', 'e', 'i', 'o', 'u'};
        int max = 0;

        Map<String, Integer> map = new HashMap<>(32);
        map.put(sb.toString(), -1);
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int index = isVowelCharacterAndIndex(chars, c);
            if (index != -1) {
                char cc = sb.charAt(index);
                cc = (char) (((cc - '0') + 1) % 2 + '0');
                sb.setCharAt(index, cc);
            }
            String str = sb.toString();
            if (map.containsKey(str)) {
                max = Math.max(max, i - map.get(str));
            } else {
                map.put(str, i);
            }

        }
        return max;
    }

    /**
     * 每个元音包含偶数次的最长子字符串  字使用位图 BitSet 版本
     **/
    public int findTheLongestSubstringBIT(String s) {
        char[] chars = {'a', 'e', 'i', 'o', 'u'};
        int max = 0;
        BitSet bitSet = new BitSet(5);
        Map<String, Integer> map = new HashMap<>(32);
        map.put(bitSet.toString(), -1);
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int index = isVowelCharacterAndIndex(chars, c);
            if (index != -1) {
                bitSet.flip(index);
            }
            String str = bitSet.toString();
            if (map.containsKey(str)) {
                max = Math.max(max, i - map.get(str));
            } else {
                map.put(str, i);
            }

        }
        return max;
    }

    private int isVowelCharacterAndIndex(char[] chars, char c) {
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == c) return i;
        }
        return -1;
    }

    /**
     * 字符串解码
     **/

    public String decodeString(String s) {
        int cnt = 0;
        Deque<String> stack = new LinkedList<>();
        LinkedList<Integer> nums = new LinkedList<>();
        while (cnt < s.length()) {
            char c = s.charAt(cnt);
            if (Character.isDigit(c)) {
                cnt = addNums(s, cnt, nums);
            } else if (Character.isLetter(c) || c == '[') {
                stack.addLast(String.valueOf(c));
                cnt++;
            } else if (c == ']') {
                int num = nums.pollLast();
                Deque<String> one = new LinkedList<>();
                StringBuilder sb = new StringBuilder();
                while (!stack.peekLast().equals("[")) {
                    one.addFirst(stack.pollLast());
                }
                stack.pollLast();
                for (String s1 : one) {
                    sb.append(s1);
                }
                String str = sb.toString();
                sb.setLength(0);
                for (int i = 0; i < num; i++) {
                    sb.append(str);
                }
                stack.addLast(sb.toString());
                cnt++;
            }
        }
        StringBuilder ans = new StringBuilder();
        for (String s1 : stack) {
            ans.append(s1);
        }
        return ans.toString();
    }

    private int addNums(String str, int cnt, List<Integer> nums) {
        int temp = cnt;
        while (Character.isDigit(str.charAt(temp))) temp++;
        nums.add(Integer.valueOf(str.substring(cnt, temp)));
        return temp;
    }

    /**
     * 将字符串s，解析，并且重复times次
     **/
    public String decodeStringCore(String s, int times) {
        StringBuilder ans = new StringBuilder();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c <= '9' && c >= '0') {
                sb.append(decodeStringCore(s.substring(i + 2), Integer.valueOf(c)));
            } else if (c <= 'z' && c >= 'a') {
                sb.append(c);
            } else if (c == ']') {
                for (int j = 0; j < times; j++) {
                    ans.append(sb.toString());
                }
                return ans.toString();
            }
        }
        return ans.reverse().toString();
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int length1 = nums1.length, length2 = nums2.length;
        int totalLength = length1 + length2;
        if (totalLength % 2 == 1) {
            int midIndex = totalLength / 2;
            double median = getKthElement(nums1, nums2, midIndex + 1);
            return median;
        } else {
            int midIndex1 = totalLength / 2 - 1, midIndex2 = totalLength / 2;
            double median = (getKthElement(nums1, nums2, midIndex1 + 1) + getKthElement(nums1, nums2, midIndex2 + 1)) / 2.0;
            return median;
        }
    }

    /**
     * 两个有序数组，二分找到里面
     * 找到第k大的数
     **/
    public int getKthElement(int[] nums1, int[] nums2, int k) {
        /* 主要思路：要找到第 k (k>1) 小的元素，那么就取 pivot1 = nums1[k/2-1] 和 pivot2 = nums2[k/2-1] 进行比较
         * 这里的 "/" 表示整除
         * nums1 中小于等于 pivot1 的元素有 nums1[0 .. k/2-2] 共计 k/2-1 个
         * nums2 中小于等于 pivot2 的元素有 nums2[0 .. k/2-2] 共计 k/2-1 个
         * 取 pivot = min(pivot1, pivot2)，两个数组中小于等于 pivot 的元素共计不会超过 (k/2-1) + (k/2-1) <= k-2 个
         * 这样 pivot 本身最大也只能是第 k-1 小的元素
         * 如果 pivot = pivot1，那么 nums1[0 .. k/2-1] 都不可能是第 k 小的元素。把这些元素全部 "删除"，剩下的作为新的 nums1 数组
         * 如果 pivot = pivot2，那么 nums2[0 .. k/2-1] 都不可能是第 k 小的元素。把这些元素全部 "删除"，剩下的作为新的 nums2 数组
         * 由于我们 "删除" 了一些元素（这些元素都比第 k 小的元素要小），因此需要修改 k 的值，减去删除的数的个数
         */
        int length1 = nums1.length, length2 = nums2.length;
        int index1 = 0, index2 = 0;
        int kthElement = 0;

        while (true) {
            // 边界情况
            if (index1 == length1) {
                return nums2[index2 + k - 1];
            }
            if (index2 == length2) {
                return nums1[index1 + k - 1];
            }
            if (k == 1) {
                return Math.min(nums1[index1], nums2[index2]);
            }

            // 正常情况
            int half = k / 2;
            int newIndex1 = Math.min(index1 + half, length1) - 1;
            int newIndex2 = Math.min(index2 + half, length2) - 1;
            int pivot1 = nums1[newIndex1], pivot2 = nums2[newIndex2];
            if (pivot1 <= pivot2) {
                k -= (newIndex1 - index1 + 1);
                index1 = newIndex1 + 1;
            } else {
                k -= (newIndex2 - index2 + 1);
                index2 = newIndex2 + 1;
            }
        }
    }

    /**
     * 寻找重复数
     **/
    public int findDuplicate(int[] nums) {
        int temp = 1;
        int slow = 0, fast = 0;
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);
        slow = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }

    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length < 1) {
            return 0;
        }
        int max = -1;
        int n = grid.length, m = grid[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    max = Math.max(max, maxAreaOfIslandDFS(grid, i, j, n, m));
                }
            }
        }
        return max;
    }

    private int maxAreaOfIslandBFS(int[][] grid) {
        if (grid == null || grid.length < 1) {
            return 0;
        }
        Queue<Pair<Integer, Integer>> queue = new LinkedList<>();
        int max = 0;
        int n = grid.length, m = grid[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    queue.add(new Pair<>(i, j));
                    int sum = 0;
                    while (!queue.isEmpty()) {

                        Pair<Integer, Integer> poll = queue.poll();
                        int x = poll.getKey();
                        int y = poll.getValue();
                        if (grid[x][y] == 2) continue;
                        sum++;
                        grid[x][y] = 2;
                        for (int k = 0; k < 8; k += 2) {
                            int nx = x + index[k];
                            int ny = y + index[k + 1];
                            if (nx < n && nx >= 0 && ny < m && ny >= 0 && grid[nx][ny] == 1) {
                                queue.add(new Pair<>(nx, ny));
                            }
                        }
                    }
                    max = Math.max(max, sum);
                }
            }
        }
        return max;
    }

    private int maxAreaOfIslandDFS(int[][] grid, int x, int y, int n, int m) {
        if (grid[x][y] == 0 || grid[x][y] == 2) {
            return 0;
        }
        int sum = 1;
        grid[x][y] = 2;
        for (int i = 0; i < 8; i += 2) {
            int nx = x + index[i];
            int ny = y + index[i + 1];
            if (nx < n && nx >= 0 && ny < m && ny >= 0) {
                sum += maxAreaOfIslandDFS(grid, nx, ny, n, m);
            }
        }
        return sum;
    }

    /**
     * 最小覆盖子串
     **/
    public String minWindow(String s, String t) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : t.toCharArray()) {
            map.compute(c, (k, v) -> v == null ? 1 : v + 1);
//            map.put(c,map.getOrDefault(c, 0)+1);
        }
        String ans = "";
        int count = 0;
        int l = 0, r = 0, min = Integer.MAX_VALUE, length = s.length();
        while (r < length) {
            char c = s.charAt(r);
            if (map.containsKey(c)) {
                int num = map.get(c);
                if (num > 0) {
                    count++;
                }
                map.put(c, num - 1);
                while (count == t.length() && r < length) {
                    if (min > (r - l + 1)) {
                        min = (r - l + 1);
                        ans = s.substring(l, r + 1);
                    }
                    c = s.charAt(l);
                    if (map.containsKey(c)) {
                        num = map.get(c);
                        if (num >= 0) {
                            count--;
                        }
                        map.put(c, num + 1);
                    }
                    l++;
                }
            }
            r++;
        }
        return ans;
    }

    /**
     * 前序中序建树
     * 3,9,20,15,7]
     * 中序遍历 inorder = [9,3,15,20,7]
     **/
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int endPre = preorder.length - 1;
        TreeNode treeNode = buildTreeCore(preorder, 0, endPre, inorder, 0, endPre);
        return treeNode;
    }

    private TreeNode buildTreeCore(int[] preorder, int startPre, int endPre, int[] inorder, int startIn, int endIn) {
        if (startPre > endPre) {
            return null;
        }
        TreeNode treeNode = new TreeNode(preorder[startPre]);
        int temp = startIn;
        while (inorder[temp] != preorder[startPre]) {
            temp++;
        }
        int k = temp - startIn;
        treeNode.left = buildTreeCore(preorder, startPre + 1, startPre + k, inorder, startIn, temp - 1);
        treeNode.right = buildTreeCore(preorder, startPre + k + 1, endPre, inorder, temp + 1, endIn);
        return treeNode;
    }

    /**
     * 验证回文字符串2
     * 最多删除一个字符，能否成为回文串
     **/
    public boolean validPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return isPalindrome(s.substring(left + 1, right + 1))
                        || isPalindrome(s.substring(left, right));
            } else {
                left++;
                right--;
            }
        }
        return true;
    }

    /**
     * 课程表2
     * <p>
     * 求拓扑排序
     * <p>
     * 有 dfs 使用栈，不容易理解 逆向思维
     * bfs  容易理解，使用 入度数组 正向思维
     * 两种
     * 使用邻接表来存储图
     **/

    public boolean isPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            } else {
                left++;
                right--;
            }
        }
        return true;
    }

    /**
     * 乘积最大子数组
     **/
    public int maxProduct(int[] nums) {
        if (nums == null) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int[] dpMax = new int[nums.length];
        int[] dpMin = new int[nums.length];
        dpMax[0] = nums[0];
        dpMin[0] = nums[0];
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dpMax[i] = Math.max(dpMax[i - 1] * nums[i], Math.max(dpMin[i - 1] * nums[i], nums[i]));
            dpMin[i] = Math.min(dpMax[i - 1] * nums[i], Math.min(dpMin[i - 1] * nums[i], nums[i]));
            max = Math.max(dpMax[i], Math.max(dpMin[i], max));
        }
        return max;
    }

    /**
     * 优化空间
     **/
    public int maxProductB(int[] nums) {
        if (nums == null) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int dpMax = nums[0];
        int dpMin = nums[0];
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int minn = dpMin, maxx = dpMax;
            dpMax = Math.max(maxx * nums[i], Math.max(minn * nums[i], nums[i]));
            dpMin = Math.min(maxx * nums[i], Math.min(minn * nums[i], nums[i]));
            max = Math.max(dpMax, Math.max(dpMin, max));
        }
        return max;
    }

    /**
     * 翻转链表 每k个翻转一次
     **/
    public ListNode reverseKGroup(ListNode head, int k) {
        if (k < 2 || head == null || head.next == null) return head;
        ListNode root = new ListNode(0);
        root.next = head;
        ListNode pre = root, next, end;
        while (head != null) {
            end = pre;
            for (int i = 0; i < k; i++) {
                end = end.next;
                if (end == null) {
                    return root.next;
                }
            }
            next = end.next;
            Pair<ListNode, ListNode> pair = reverseKGroupCore(head, end);
            pre.next = pair.getKey();
            pair.getValue().next = next;
            pre = pair.getValue();
            head = pre.next;
        }

        return root.next;
    }

    /**
     * 翻转一段链表，返回新的头尾节点
     **/
    private Pair<ListNode, ListNode> reverseKGroupCore(ListNode head, ListNode end) {
        ListNode pre = end.next;
        ListNode p = head, next;
        while (pre != end) {
            next = p.next;
            p.next = pre;
            pre = p;
            p = next;
        }
        return new Pair<>(end, head);
    }

    /**
     * bfs
     **/
    public int[] findOrderBFS(int numCourses, int[][] prerequisites) {
        List<Integer> ans = new ArrayList<>();
        int[] input = new int[numCourses];
        List<LinkedList<Integer>> grap = new ArrayList<>(numCourses);
        for (int i = 0; i < numCourses; i++) {
            grap.add(new LinkedList<>());
        }
        for (int[] it : prerequisites) {
            grap.get(it[1]).add(it[0]);
            input[it[0]]++;
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (input[i] == 0) {
                queue.add(i);
            }
        }
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            ans.add(cur);
            for (int it : grap.get(cur)) {
                input[it]--;
                if (input[it] == 0) {
                    queue.add(it);
                }
            }
        }
        return ans.size() == numCourses ?
                ans.stream().mapToInt(Integer::intValue).toArray() : new int[0];

    }

    /**
     * dfs
     **/
    public int[] findOrderDFS(int numCourses, int[][] prerequisites) {
        List<Integer> ans = new ArrayList<>();
        int[] vis = new int[numCourses];
        Map<Integer, LinkedList<Integer>> grap = new HashMap<>();
        for (int[] it : prerequisites) {
            if (!grap.containsKey(it[1])) {
                grap.put(it[1], new LinkedList<>());
            }
            grap.get(it[1]).add(it[0]);
        }
        Boolean isValid = false;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < numCourses; i++) {
            if (vis[i] == 0) {
                findOrderDFSCore(grap, vis, isValid, stack, i);
                if (isValid) {
                    return new int[0];
                }
            }
        }
        Collections.reverse(stack);
        return stack.stream().mapToInt(Integer::intValue).toArray();

    }

    private void findOrderDFSCore(Map<Integer, LinkedList<Integer>> grap, int[] vis,
                                  Boolean isValid, Stack<Integer> stack, int u) {
        vis[u] = 1;
        if (grap.containsKey(u)) {
            for (Integer integer : grap.get(u)) {
                if (vis[integer] == 0) {
                    findOrderDFSCore(grap, vis, isValid, stack, integer);
                    if (isValid) {
                        return;
                    }
                }
                if (vis[integer] == 1) {
                    isValid = true;
                    return;
                }
            }
        }
        vis[u] = 2;
        stack.add(u);
    }

    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null) return false;
        return isSubtreeCore(s, t) || isSubtree(s.left, t) || isSubtree(s.right, t);

    }

    public boolean isSubtreeCore(TreeNode s, TreeNode t) {
        if (s == null && t == null) return true;
        if (!(s != null && t != null)) return false;
        if (s.val != t.val) return false;
        return isSubtreeCore(s.left, t.left) && isSubtreeCore(s.right, t.right);
    }

    /**
     * @Description: 递归方式
     **/
    public boolean isValidBST(TreeNode root) {
        return helper(root, null, null);
    }

    public boolean helper(TreeNode root, Integer lower, Integer upper) {
        if (root == null) return true;
        if ((lower != null && root.val <= lower)
                || (upper != null && root.val >= upper)) return false;
        return helper(root.left, lower, root.val)
                && helper(root.right, root.val, upper);
    }

    /**
     * @Description: 中序遍历方式
     **/
    public boolean isValidBST_B(TreeNode root) {
        if (root == null) return true;
        Integer last = null;
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.add(root);
                root = root.left;
            }
            root = stack.pop();
            if (last != null && last > root.val) {
                return false;
            }
            last = root.val;
            root = root.right;

        }
        return true;
    }
    //Pow（x,n）

    //官方题解
    public int maximalSquareB(char[][] matrix) {
        if (matrix == null || matrix.length < 1 || matrix[0].length < 1) return 0;
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] dp = new int[n][m];
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] != '0') {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                    }
                    ans = Math.max(ans, dp[i][j]);
                }
            }
        }
        return ans * ans;
    }

    /**
     * 最大正方形
     **/
    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length < 1 || matrix[0].length < 1) return 0;
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] dp = new int[n][m];
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] != '0') {
                    ans = Math.max(ans, maximalSquareCore(matrix, dp, i, j));
                }
            }
        }
        return ans * ans;
    }

    private int maximalSquareCore(char[][] matrix, int[][] dp, int x, int y) {
        if (matrix[x][y] == '0') return 0;
        if (dp[x][y] != 0) return dp[x][y];
        if (x == 0 || y == 0) {
            dp[x][y] = 1;
            return 1;
        }
        int temp = 0;
        temp = maximalSquareCore(matrix, dp, x - 1, y - 1);
        if (temp == 0) {
            dp[x][y] = 1;
            return 1;
        } else {
            for (int i = 0; i < temp; i++) {
                if (matrix[x - 1 - i][y] == '0' || matrix[x][y - 1 - i] == '0') {
                    dp[x][y] = i + 1;
                    return i + 1;
                }
            }
            dp[x][y] = temp + 1;
            return dp[x][y];
        }

    }

    /**
     * @Description: -100.0 < x < 100.0
     * n 是 32 位有符号整数，其数值范围是 [−231, 231 − 1] 。
     **/
    public double myPow(double x, int n) {
        return n > 0 ? quickMul(x, n) : (1 / quickMul(x, -n));
    }

    /**
     * 递归 快速幂
     **/
    private double quickPow(double x, int n) {
        if (n == 0) {
            return 1.0;
        }
        double y = quickPow(x, n / 2);
        if ((n & 1) == 1) {
            return y * y * x;
        } else {
            return y * y;
        }
    }

    /**
     * 迭代 快速幂
     * x^(1+4+8+64) = x^77
     * 。而1+4+8+64 它们都是 2^i 的幂次，
     * 这是因为每个额外乘的 xx 在之后都会被平方若干次。
     * 而这些指数 11，44，88 和 6464，
     * 恰好就对应了 7777 的二进制表示 (1001101)
     **/
    private double quickMul(double x, int N) {
        double ans = 1.0;
        // 贡献的初始值为 x
        double x_contribute = x;
        // 在对 N 进行二进制拆分的同时计算答案
        while (N > 0) {
            if (N % 2 == 1) {
                // 如果 N 二进制表示的最低位为 1，那么需要计入贡献
                ans *= x_contribute;
            }
            // 将贡献不断地平方
            x_contribute *= x_contribute;
            // 舍弃 N 二进制表示的最低位，这样我们每次只要判断最低位即可
            N /= 2;
        }
        if (N < 0 || Double.isInfinite(ans)) return 0.0;
        return ans;
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        lowestCommonAncestorCore(root, p, q);
        return ans;
    }

    /**
     * 存储每个节点的父节点，然后倒着遍历，先遍历q，在遍历p，
     * 同时存储 访问状态，遇到已经访问过的，就是最近公共祖先
     **/
    public TreeNode lowestCommonAncestorB(TreeNode root, TreeNode p, TreeNode q) {
        Map<Integer, TreeNode> toParent = new HashMap<>();
        Set<Integer> vis = new HashSet<>();
        toParent.putIfAbsent(root.val, null);
        lowestCommonAncestorDFS(root, toParent);
        while (q != null) {
            vis.add(q.val);
            q = toParent.get(q.val);
        }
        while (p != null) {
            if (vis.contains(p.val)) return p;
            vis.add(p.val);
            p = toParent.get(p.val);
        }
        return null;
    }

    /**
     * 二叉树的层序遍历
     * 使用二元组
     **/
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) return ans;
        Queue<Pair<Integer, TreeNode>> queue = new LinkedList<>();
        Pair<Integer, TreeNode> now = new Pair<>(1, root);
        queue.add(now);
        int level;
        TreeNode cur;
        while (!queue.isEmpty()) {
            now = queue.poll();
            level = now.getKey();
            cur = now.getValue();
            if (ans.size() < level) {
                ans.add(new ArrayList<>((int) Math.pow(2, level - 1)));
            }
            ans.get(level - 1).add(cur.val);
            if (cur.left != null) {
                queue.add(new Pair<>(level + 1, cur.left));
            }
            if (cur.right != null) {
                queue.add(new Pair<>(level + 1, cur.right));
            }
        }
        return ans;
    }

    /**
     * 不使用二元组
     * 每次遍历时，先确定个数n，然后一次遍历n个
     **/

    public List<List<Integer>> levelOrderB(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) return ans;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        TreeNode cur;
        int n = 0;
        List<Integer> one;
        while (!queue.isEmpty()) {
            n = queue.size();
            one = new ArrayList<>(n);
            while (n != 0) {
                cur = queue.poll();
                one.add(cur.val);
                n--;
                if (cur.left != null) {
                    queue.add(cur.left);
                }
                if (cur.right != null) {
                    queue.add(cur.right);
                }
            }
            ans.add(one);
        }
        return ans;
    }

}
