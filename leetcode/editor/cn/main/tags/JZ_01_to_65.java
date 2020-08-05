package tags;

import util.ListNode;
import util.Node;
import util.TreeNode;

import java.util.*;
import java.util.stream.IntStream;

/**
 * @program: LeetCode
 * @description: 剑指offer
 * @author: wd
 * @create: 2020-06-28 14:43
 **/

public class JZ_01_to_65 {
    static int[] ints1 = {1, 3, -1, -3, 5, 3, 6, 7};
    static int[] ints2 = {5, 7, 7, 8, 8, 10};
    static Integer[] integers = {4, 2, 5, 1, 3};
    //    static String str1 = "aaa";
    static String str1 = "mississippi";
    static String str2 = "mis*suvyls*p*.";
    static private Integer[][] integerss = {{7, null}, {13, 0}, {11, 4}, {10, 2}, {1, 0}};
    //    static String str2 = "ab*a*c*a";
    static private int[] nextIndex = {-1, 0, 1, 0, 0, -1, 0, 1};
    /**
     * 36
     * 二叉搜索树和双向链表
     **/
    TreeNode pre;
    StringBuilder res;
    int nine = 0, count = 0, start, n;
    char[] chars, loop = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    public static void main(String[] args) {
        JZ_01_to_65 jz01to65 = new JZ_01_to_65();
//        Node node = Node.buildNode(integerss);
//        TreeNode node = TreeNode.buildByLevelOrder(integers);
        MedianFinder medianFinder = jz01to65.new MedianFinder();

//        for (int i : ints2) {
//            medianFinder.addNum(i);
//            System.out.println(medianFinder.findMedian());
//        }
        System.out.println(
                jz01to65.cuttingRope2(127)
//                Arrays.toString(jz.twoSum(2))
//                Arrays.deepToString(jz.findContinuousSequence(9))

        );

    }

    /**
     * 65
     * 不用加减乘除做加法
     **/
    public int add(int a, int b) {
        while (b != 0) {
            int c = (a & b) << 1;
            a ^= b;
            b = c;
        }
        return a;
    }

    /**
     * 62
     * 圆圈中剩余的数字
     **/
    public int lastRemaining(int n, int m) {
        if (n == 1) return 0;
        int[] dp = new int[n + 1];
        for (int i = 2; i <= n; i++) {
            dp[i] = (dp[i - 1] + m) % i;
        }
        return dp[n];
    }

    /**
     * 61
     * 扑克牌中的顺子
     **/
    public boolean isStraight(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int max = 0, min = 14;
        for (int num : nums) {
            if (num == 0) continue;
            if (set.contains(num)) {
                return false;
            }
            set.add(num);
            max = Math.max(max, num);
            min = Math.min(min, num);
        }
        return max - min < 5;
    }

    /**
     * 60
     * n个骰子的点数
     **/
    public double[] twoSum(int n) {
        double[] pre = {1 / 6d, 1 / 6d, 1 / 6d, 1 / 6d, 1 / 6d, 1 / 6d};
        for (int i = 2; i <= n; i++) {
            double[] temp = new double[5 * i + 1];
            for (int j = 0; j < pre.length; j++) {
                for (int k = 0; k < 6; k++) {
                    temp[j + k] += pre[j] / 6;
                }
            }
            pre = temp;
        }
        return pre;
    }

    /**
     * 59
     * 滑动窗口的最大值
     **/
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length < 1 || k <= 0) return new int[0];
        Deque<Integer> queue = new LinkedList<>();
        for (int i = 0; i < k; i++) {
            while (!queue.isEmpty() && queue.peekLast() < nums[i]) queue.removeLast();
            queue.addLast(nums[i]);
        }
        int[] ans = new int[nums.length - k + 1];
        ans[0] = queue.peekFirst();
        for (int i = 1, j = k; j < nums.length; i++, j++) {
            if (queue.peekFirst() == nums[i - 1]) queue.removeFirst();
            while (!queue.isEmpty() && queue.peekLast() < nums[j]) queue.removeLast();
            queue.addLast(nums[j]);
            ans[i] = queue.peekFirst();
        }
        return ans;
    }

    /**
     * 57
     * 和为s的连续整数序列
     **/
    public int[][] findContinuousSequence(int target) {
        if (target <= 1) {
            return null;
        }
        List<int[]> ans = new ArrayList<>();
        int left = 1, right = 2;
        while (left < right && left <= target / 2) {
            int sum = (left + right) * (right - left + 1) / 2;
            if (sum < target) {
                right++;
            } else if (sum > target) {
                left++;
            } else {
                ans.add(IntStream.range(left, right + 1).toArray());
                left++;
            }
        }
        return ans.toArray(new int[][]{});
    }

    /**
     * 56
     * 数组中数字出现的次数
     **/
    public int[] singleNumbers(int[] nums) {
        int x = 0;
        for (int num : nums) {
            x ^= num;
        }
        int y = 1;
        while ((1 & x) != 1) {
            x >>= 1;
            y <<= 1;
        }
        int a = 0, b = 0;
        for (int num : nums) {
            if ((num & y) == y) {
                a ^= num;
            } else {
                b ^= num;
            }
        }
        return new int[]{a, b};
    }

    /**
     * 55
     * 平衡二叉树
     **/
    public boolean isBalanced(TreeNode root) {
        return depth(root) != -1;
    }

    private int depth(TreeNode root) {
        if (root == null) return 0;
        int left = depth(root.left);
        if (left == -1) return -1;
        int right = depth(root.right);
        if (right == -1) return -1;
        return Math.abs(left - right) < 2 ? Math.max(left, right) + 1 : -1;
    }

    /**
     * 53
     * 0~n-1中缺失的数字
     * 使用位运算 异或
     **/
    public int missingNumber(int[] nums) {
        int n = nums.length;
        int temp = 0;
        for (int i = 0; i <= n; i++) {
            temp ^= i;
            if (i < n) temp ^= nums[i];
        }
        return temp;
    }

    public int missingNumberB(int[] nums) {
        int left = 0, right = nums.length - 1, mid;
        while (left <= right) {
            mid = (left + right) >>> 1;
            if (nums[mid] > mid) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    /**
     * 53
     * 在排序数组中查找数字
     **/
    public int search(int[] nums, int target) {
        if (nums == null || nums.length < 1) return 0;
        int left = 0, right = nums.length - 1, mid;
        while (left <= right) {
            mid = (left + right) >>> 1;
            if (nums[mid] >= target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        if (left == -1 || left == nums.length || nums[left] != target) return 0;
        int x = left;
        right = nums.length - 1;
        while (left <= right) {
            mid = (left + right) >>> 1;
            if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left - x;
    }

    /**
     * 44
     * 数字序列中某一位的数
     **/
    public int findNthDigit(int n) {
        int digit = 1;
        long start = 1;
        long count = 9;
        while (n > count) { // 1.
            n -= count;
            digit += 1;
            start *= 10;
            count = digit * start * 9;
        }
        long num = start + (n - 1) / digit; // 2.
        return Long.toString(num).charAt((n - 1) % digit) - '0'; // 3.
    }

    /**
     * 43
     * 1-n 整数中1出现的次数
     **/
    public int countDigitOne(int n) {
        int digit = 1, res = 0;
        int high = n / 10, cur = n % 10, low = 0;
        while (high != 0 || cur != 0) {
            if (cur == 0) res += high * digit;
            else if (cur == 1) res += high * digit + low + 1;
            else res += (high + 1) * digit;
            low += cur * digit;
            cur = high % 10;
            high /= 10;
            digit *= 10;
        }
        return res;
    }

    /**
     * 42
     * 连续子数组的最大和
     **/
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length < 1) return 0;
        int dp = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (dp < 0) {
                dp = nums[i];
            } else {
                dp += nums[i];
            }
            max = Math.max(max, dp);
        }
        return max;
    }

    /**
     * 40
     * 最小的k个数
     **/
    public int[] getLeastNumbers(int[] arr, int k) {

        int left = 0, right = arr.length - 1;
        while (true) {
            int mid = partion(arr, left, right);
            if (mid == k || mid == k - 1) return Arrays.copyOfRange(arr, 0, k);
            else if (mid < k) {
                left = mid + 1;
            } else if (mid > k) {
                right = mid - 1;
            }
        }
    }

    public int partion(int[] arr, int left, int right) {
        int temp = arr[left];
        while (left < right) {
            while (left < right && arr[right] > temp) right--;
            arr[left] = arr[right];
            while (left < right && arr[left] <= temp) left++;
            arr[right] = arr[left];

        }
        arr[left] = temp;
        return left;
    }

    /**
     * 38
     * 字符串的排列
     **/
    public String[] permutation(String s) {
        List<String> ans = new ArrayList<>();
        char[] chars = s.toCharArray();
//        Arrays.sort(chars);
        permutationDFS(chars, 0, s.length() - 1, ans);
        return ans.toArray(new String[0]);
    }

    public void permutationDFS(char[] s, int left, int right, List<String> ans) {
        if (left > right) {
            ans.add(new String(s));
            return;
        }
        //去重
        Set<Character> set = new HashSet<>();
        for (int i = left; i <= right; i++) {
            if (set.contains(s[i])) continue;
            set.add(s[i]);
            swap(s, left, i);
            permutationDFS(s, left + 1, right, ans);
            swap(s, left, i);
        }
    }

    private void swap(char[] s, int x, int y) {
        char c = s[x];
        s[x] = s[y];
        s[y] = c;
    }

    /**
     * 37
     * 序列化和反序列化
     **/
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) return "[]";
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode now = queue.poll();
            if (now != null) {
                sb.append(now.val + ",");
                queue.add(now.left);
                queue.add(now.right);
            } else {
                sb.append("null,");
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("]");
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if ("[]".equals(data)) return null;
        String[] strings = data.substring(1, data.length() - 1).split(",");
        TreeNode root = new TreeNode(Integer.parseInt(strings[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int index = 1;
        while (!queue.isEmpty()) {
            TreeNode now = queue.poll();
            if (!strings[index].equals("null")) {
                now.left = new TreeNode(Integer.parseInt(strings[index]));
                queue.add(now.left);
            }
            index++;
            if (!strings[index].equals("null")) {
                now.right = new TreeNode(Integer.parseInt(strings[index]));
                queue.add(now.right);
            }
            index++;
        }
        return root;
    }

    public TreeNode treeToDoublyList(TreeNode root) {
        if (root == null) return null;
        TreeNode head = new TreeNode(-1);
        pre = head;
        treeToDoublyListDFS(root);
        pre.right = head.right;
        head.right.left = pre;
        return head.right;
    }

    public void treeToDoublyListDFS(TreeNode root) {
        if (root == null) {
            return;
        }
        treeToDoublyListDFS(root.left);
        pre.right = root;
        root.left = pre;
        pre = pre.right;
        treeToDoublyListDFS(root.right);
    }

    /**
     * 35
     * 复杂链表的复制
     * 1 迭代 ，先把复制节点放在原来的链表上，然后拆分
     **/
    public Node copyRandomListA(Node head) {
        if (head == null) return null;
        Node p1 = head, p2 = head, ans;
        while (p1 != null) {
            Node now = new Node(p1.val);
            now.next = p1.next;
            now.random = p1.random;
            p1.next = now;
            p1 = now.next;
        }
        p1 = p2.next;
        while (p1 != null) {
            if (p1.random != null) p1.random = p1.random.next;
            if (p1.next != null) p1 = p1.next.next;
            else break;
        }
        p1 = p2.next;
        ans = p2.next;
        while (p2 != null && p1 != null) {
            p2.next = p1.next;
            if (p1.next != null) p1.next = p1.next.next;
            p2 = p2.next;
            if (p2 != null) p1 = p2.next;
        }
        return ans;
    }

    /**
     * 34
     * 二叉树中和为某一值的路径
     **/
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) return ans;
        pathSumDFS(root, 0, sum, new LinkedList<>(), ans);
        return ans;
    }

    private void pathSumDFS(TreeNode root, int sum, int target, List<Integer> list, List<List<Integer>> ans) {
        if (root == null) {
            if (sum == target) {
                ans.add(new LinkedList<>(list));
            }
        } else {
            list.add(root.val);
            if (root.left != null) {
                pathSumDFS(root.left, sum + root.val, target, list, ans);
            }
            if (root.right != null) {
                pathSumDFS(root.right, sum + root.val, target, list, ans);
            }
            if (root.left == null && root.right == null) {
                pathSumDFS(root.right, sum + root.val, target, list, ans);
            }
            list.remove(list.size() - 1);
        }
    }

    /**
     * 33
     * 二叉搜索树的后序遍历序列
     **/
    public boolean verifyPostorder(int[] postorder) {
        if (postorder == null || postorder.length < 1) return true;
        return verifyPostorderDFS(postorder, 0, postorder.length - 1);

    }

    public boolean verifyPostorderDFS(int[] postorder, int left, int right) {
        if (left > right) {
            return true;
        }
        int root = postorder[right];
        int index = left;
        while (index < right && postorder[index] < root) {
            index++;
        }
        int cnt = index;
        while (cnt < right) {
            if (postorder[cnt] < root) {
                return false;
            }
            cnt++;
        }
        return verifyPostorderDFS(postorder, left, index - 1) && verifyPostorderDFS(postorder, index, right - 1);
    }

    /**
     * 34
     * 从上到下打印二叉树 3
     **/
    public List<List<Integer>> levelOrderB(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) return ans;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        boolean flag = true;
        while (!queue.isEmpty()) {
            int size = queue.size();
            LinkedList<Integer> one = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode now = queue.poll();
                if (flag) one.addLast(now.val);
                else one.addFirst(now.val);
                if (now.left != null) {
                    queue.add(now.left);
                }
                if (now.right != null) {
                    queue.add(now.right);
                }
            }
            ans.add(one);
        }
        return ans;
    }

    /**
     * 32
     * 从上到下打印二叉树
     **/
    public int[] levelOrder(TreeNode root) {
        if (root == null) return new int[0];
        List<Integer> ans = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode now = queue.poll();
            ans.add(now.val);
            if (now.left != null) {
                queue.add(now.left);
            }
            if (now.right != null) {
                queue.add(now.right);
            }

        }
        return ans.stream().mapToInt(a -> a.intValue()).toArray();
    }

    /**
     * 31
     * 栈的压入弹出序列
     **/
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        if (pushed == null || pushed.length < 1) return false;
        int length = pushed.length;
        Stack<Integer> stack = new Stack<>();
        int a = 0, b = 0;
        while (a <= length && b < length) {
            if (a < length && stack.isEmpty() || stack.peek() != popped[b]) {
                stack.add(pushed[a++]);
            } else if (stack.peek() == popped[b]) {
                stack.pop();
                b++;
            } else {
                return false;
            }
        }
        return a == b;
    }

    /**
     * 28
     * 对称二叉树
     **/
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return isSymmetricCore(root.left, root.right);
    }

    private boolean isSymmetricCore(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null) {
            return false;
        }
        return (left.val == right.val) && isSymmetricCore(left.left, right.right) && isSymmetricCore(left.right, right.left);

    }

    /**
     * 26
     * 树的子结构
     **/
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        return (A != null && B != null) && (isSubStructureCore(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B));
    }

    private boolean isSubStructureCore(TreeNode a, TreeNode b) {
        if (b == null) return true;
        if (a == null || a.val != b.val) return false;
        return isSubStructureCore(a.left, b.left) || isSubStructureCore(a.right, b.right);
    }

    /**
     * 24
     * 反转链表
     * 递归写法
     **/
    public ListNode reverseList(ListNode head) {
        //如果传入的head是NULL，则直接返回NULL (只有第一次调用传NULL才会走到，否则之前就会走到head->next==NULL)
        //如果传入head满足head->next==NULL，则head是原链表tail，需要返回
        if (head == null || head.next == null) {
            return head;
        }

        //如果没有满足上面的退出条件，下面这个递归调用会一直递归下去，直到找到tail节点，此处返回的就是tail
        ListNode tail = reverseList(head.next);

        //此处的head是每次递归调用的传入参数，以[1,2,3,4,5]为例，此处分别是4，3，2，1 注意没有5，因为5满足退出条件在前面返回了
        //head->next指向原链表中当前处理元素的next元素，即head为4时，next为5；head为3时，next为4
        //因此此处让next的next指向正在处理的元素，即让5指向4，让4指向3等等
        head.next.next = head;
        //同时正在处理的元素不能再指向以前的next，暂且将其next置空，等到处理到该元素时上面的操作会让其指向原先前面的元素
        //但是对于原链表第一个元素1，即这儿最后处理的head,因为没有下面的操作了，所以1的next为NULL，符合要求。
        head.next = null;
        //每次递归返回都是返回同一个tail，即5，同时5也是反转后链表的第一个元素。这个tail是最后一次递归从退出条件处返回的，然后每次递归返回后都返回给上一层，最后一次head为1的时候，处理结束，返回这个tail
        return tail;
    }

    /**
     * 链表的倒数第k个节点
     **/
    public ListNode getKthFromEnd(ListNode head, int k) {
        if (head == null || k <= 0) return null;
        ListNode root = new ListNode(-1);
        root.next = head;
        ListNode p1 = root, p2 = root;
        while (k != 0) {
            p1 = p1.next;
            k--;
        }
        while (p1 != null) {
            p1 = p1.next;
            p2 = p2.next;
        }
        return p2;
    }

    /**
     * 21
     * 调整数组顺序，使奇数位于偶数的前面
     **/
    public int[] exchange(int[] nums) {
        if (nums == null || nums.length < 1) return nums;
        int left = 0, right = nums.length - 1;
        while (left < right) {
            while (left < right && nums[left] % 2 == 1) left++;
            while (left < right && nums[right] % 2 == 0) right--;
            if (left < right) {
                int temp = nums[right];
                nums[right] = nums[left];
                nums[left] = temp;
            }
        }
        return nums;
    }

    /**
     * 20
     * 表示数值的字符串
     * <p>
     * "+100"、"5e2"、"-123"、"3.1416"、"0123"
     * "12e"、"1a3.14"、"1.2.3"、"+-5"、"-1E-16"及"12e+5.4"都不是
     **/
    public boolean isNumber(String s) {
        if (s == null) return false;
        s = s.trim();
        if (s.length() < 1) return false;
        boolean onwNegi = false;
        boolean twoNegi = false;
        boolean isE = false;
        boolean isDou = false;
        boolean idDigit = false;
        int index = 0;

        while (index < s.length()) {
            char c = s.charAt(index);
            if (c == '+') {
                if (index != 0) {
                    return false;
                }
            } else if (c == '-') {
                if (index == 0) {
                    onwNegi = true;
                } else if (!twoNegi && isE && index >= 1 && s.charAt(index - 1) == 'e' && index < s.length() - 1 && Character.isDigit(index + 1)) {
                    twoNegi = true;
                } else {
                    return false;
                }
            } else if (c == 'e') {
                if (index > 0 && index < s.length() - 1 && !isE && (Character.isDigit(s.charAt(index - 1)) || s.charAt(index - 1) == '.')) {
                    isE = true;
                } else {
                    return false;
                }
            } else if (c == '.') {
                if (!isE && !isDou) {
                    isDou = true;
                } else {
                    return false;
                }
            } else if (Character.isDigit(c)) {
                idDigit = true;
            } else {
                return false;
            }
            index++;
        }
        return idDigit;
    }

    public boolean isNumberB(String s) {
        Map[] states = {
                new HashMap<Character, Integer>() {{
                    put(' ', 0);
                    put('s', 1);
                    put('d', 2);
                    put('.', 4);
                }}, // 0.
                new HashMap<Character, Integer>() {{
                    put('d', 2);
                    put('.', 4);
                }},                           // 1.
                new HashMap<Character, Integer>() {{
                    put('d', 2);
                    put('.', 3);
                    put('e', 5);
                    put(' ', 8);
                }}, // 2.
                new HashMap<Character, Integer>() {{
                    put('d', 3);
                    put('e', 5);
                    put(' ', 8);
                }},              // 3.
                new HashMap<Character, Integer>() {{
                    put('d', 3);
                }},                                        // 4.
                new HashMap<Character, Integer>() {{
                    put('s', 6);
                    put('d', 7);
                }},                           // 5.
                new HashMap<Character, Integer>() {{
                    put('d', 7);
                }},                                        // 6.
                new HashMap<Character, Integer>() {{
                    put('d', 7);
                    put(' ', 8);
                }},                           // 7.
                new HashMap<Character, Integer>() {{
                    put(' ', 8);
                }}                                         // 8.
        };
        int p = 0;
        char t;
        for (char c : s.toCharArray()) {
            if (c >= '0' && c <= '9') t = 'd';
            else if (c == '+' || c == '-') t = 's';
            else if (c == '.' || c == 'e' || c == 'E' || c == ' ') t = c;
            else t = '?';
            if (!states[p].containsKey(t)) return false;
            p = (int) states[p].get(t);
        }
        return p == 2 || p == 3 || p == 7 || p == 8;
    }

    /**
     * 19
     * 正则表达式匹配
     **/
    public boolean isMatch(String s, String p) {
       /*
       s和p可能为空。空的长度就是0，但是这些情况都已经判断过了，只需要判断是否为null即可
       if(p.length()==0&&s.length()==0)
            return true;
            */
        if (s == null || p == null)
            return false;
        int rows = s.length();
        int columns = p.length();
        boolean[][] dp = new boolean[rows + 1][columns + 1];
        //s和p两个都为空，肯定是可以匹配的，同时这里取true的原因是
        //当s=a，p=a，那么dp[1][1] = dp[0][0]。因此dp[0][0]必须为true。
        dp[0][0] = true;
        for (int j = 1; j <= columns; j++) {
            //p[j-1]为*可以把j-2和j-1处的字符删去，只有[0,j-3]都为true才可以
            //因此dp[j-2]也要为true，才可以说明前j个为true
            if (p.charAt(j - 1) == '*' && dp[0][j - 2])
                dp[0][j] = true;
        }

        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= columns; j++) {
                char nows = s.charAt(i - 1);
                char nowp = p.charAt(j - 1);
                if (nows == nowp) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    if (nowp == '.')
                        dp[i][j] = dp[i - 1][j - 1];
                    else if (nowp == '*') {
                        //p需要能前移1个。（当前p指向的是j-1，前移1位就是j-2，因此为j>=2）
                        if (j >= 2) {
                            char nowpLast = p.charAt(j - 2);
                            //只有p[j-2]==s[i-1]或p[j-2]==‘.’才可以让*取1个或者多个字符：
                            if (nowpLast == nows || nowpLast == '.')
                                dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                            //不论p[j-2]是否等于s[i-1]都可以删除掉j-1和j-2处字符：
                            dp[i][j] = dp[i][j] || dp[i][j - 2];
                        }
                    } else
                        dp[i][j] = false;
                }
            }
        }
        return dp[rows][columns];
    }

    /**
     * 18
     * 删除链表的结点
     **/
    public ListNode deleteNode(ListNode head, int val) {
        if (head == null) return head;
        if (head.val == val) return head.next;
        ListNode now = head;
        while (now.next != null) {
            if (now.next.val == val) {
                now.next = now.next.next;
                break;
            }
            now = now.next;
        }
        return head;
    }

    /**
     * 17
     * 打印从1到最大的n位数
     **/
    public int[] printNumbersB(int n) {
        int end = (int) Math.pow(10, n) - 1;
        int[] res = new int[end];
        for (int i = 0; i < end; i++)
            res[i] = i + 1;
        return res;
    }

    public String printNumbers(int n) {
        this.n = n;
        res = new StringBuilder();
        chars = new char[n];
        start = n - 1;
        dfs(0);
        res.deleteCharAt(res.length() - 1);
        return res.toString();
    }

    void dfs(int x) {
        if (x == n) {
            String s = String.valueOf(chars).substring(start);
            if (!s.equals("0")) res.append(s + ",");
            if (n - start == nine) start--;
            return;
        }
        for (char i : loop) {
            if (i == '9') nine++;
            chars[x] = i;
            dfs(x + 1);
        }
        nine--;
    }

    /**
     * 16
     * 数值的整数次方
     **/
    public double myPow(double x, int n) {
        long nn = n;
        boolean isNegitive = false;
        if (x < 0 && n % 2 == 1) {
            isNegitive = true;
        }
        boolean isDao = n < 0;
        x = Math.abs(x);
        nn = Math.abs(nn);
        double ans = 1;
        while (nn != 0) {
            if (nn % 2 == 1) ans = (ans * x);
            x = (x * x);
            nn /= 2;
        }
        if (isNegitive) {
            ans = -ans;
        }

        return isDao ? 1 / ans : ans;
    }

    /**
     * 03
     * 数组中重复的数字
     **/
    public int findRepeatNumber(int[] nums) {
        int[] count = new int[nums.length];
        for (int num : nums) {
            count[num]++;
            if (count[num] > 1) return num;
        }
        return -1;
    }

    /**
     * 04
     * 二维数组中的查找
     **/
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (matrix == null || matrix.length < 1 || matrix[0].length < 1) return false;
        int m = matrix.length;
        int n = matrix[0].length;
        int x = m - 1, y = 0;
        while (x >= 0 && y < n) {
            if (matrix[x][y] == target) {
                return true;
            } else if (matrix[x][y] > target) {
                x--;
            } else if (matrix[x][y] < target) {
                y++;
            }
        }
        return false;
    }

    /**
     * 05
     * 替换空格
     **/
    public String replaceSpace(String s) {
        if (s == null || s.length() < 1) return "";
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c == ' ') {
                sb.append("%20");
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 06
     * 从未到头打印链表
     **/
    public int[] reversePrint(ListNode head) {
        if (head == null) return new int[0];
        Deque<Integer> list = new LinkedList<>();
        ListNode now = head;
        while (now != null) {
            list.addFirst(now.val);
            now = now.next;
        }
        return list.stream().mapToInt(a -> a.intValue()).toArray();
    }

    /**
     * 07
     * 重建二叉树
     * 前序，中序 建树
     **/
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (inorder == null || inorder.length < 1) return null;
        return buildTreeDFS(preorder, inorder, 0, preorder.length, 0, preorder.length);
    }

    public TreeNode buildTreeDFS(int[] preorder, int[] inorder,
                                 int preLeft, int preRight, int inLeft, int inRight) {
        if (inLeft >= inRight) return null;
        int x = preorder[preLeft];
        int mid = inLeft;
        while (inorder[mid] != x) mid++;
        TreeNode root = new TreeNode(x);
        root.left = buildTreeDFS(preorder, inorder, preLeft + 1, preLeft + 1 + (mid - inLeft), inLeft, mid);
        root.right = buildTreeDFS(preorder, inorder, preLeft + 1 + (mid - inLeft), preRight, mid + 1, inRight);
        return root;
    }

    /**
     * 10 -1
     * 斐波那契数列
     **/
    public int fib(int n) {
        if (n == 0 || n == 1) return n;
        long mod = 1000000007;
        long a = 0, b = 1;
        long ans = 0;
        for (int i = 2; i <= n; i++) {
            ans = (a + b) % mod;
            a = b;
            b = ans;
        }
        return (int) ans;
    }

    /**
     * 10-2
     * 青瓜跳台阶
     **/
    public int numWays(int n) {
        if (n == 0 || n == 1) return 1;
        long mod = 1000000007;
        long a = 1, b = 1;
        long ans = 0;
        for (int i = 2; i <= n; i++) {
            ans = (a + b) % mod;
            a = b;
            b = ans;
        }
        return (int) ans;
    }

    /**
     * 旋转数组的最小元素
     **/
    public int minArray(int[] numbers) {
        if (numbers == null || numbers.length < 1) {
            return -1;
        }
        int left = 0, right = numbers.length - 1, mid;
        while (right >= left) {
            mid = left + (right - left) / 2;
            if (numbers[mid] > numbers[right]) {
                left = mid + 1;
            } else if (numbers[mid] == numbers[right]) {
                right--;
            } else if (numbers[mid] < numbers[right]) {
                right = mid;
            }
        }

        return numbers[left];
    }

    /**
     * 12
     * 矩阵中的路径
     **/
    public boolean exist(char[][] board, String word) {
        if (word == null || word.length() < 1) return true;
        char[] chars = word.toCharArray();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (existDFS(board, chars, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean existDFS(char[][] board, char[] words, int x, int y, int index) {
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length || board[x][y] == '*' || board[x][y] != words[index])
            return false;
        if (index == words.length - 1) return true;
        char c = board[x][y];
        board[x][y] = '*';
        for (int i = 0; i < 8; i += 2) {
            int tox = x + nextIndex[i];
            int toy = y + nextIndex[i + 1];
            if (existDFS(board, words, tox, toy, index + 1)) {
                board[x][y] = c;
                return true;
            }
        }
        board[x][y] = c;
        return false;
    }

    /**
     * 13
     * 机器人的运动范围
     **/
    public int movingCount(int m, int n, int k) {
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> set = new HashSet<>();
        queue.add(0);
        set.add(0);
        int x, y, now, ans = 0;
        while (!queue.isEmpty()) {
            now = queue.poll();
            ans++;
            x = now / 100;
            y = now % 100;
            for (int i = 0; i < 8; i += 2) {
                int tox = x + nextIndex[i];
                int toy = y + nextIndex[i + 1];
                if (tox < 0 || tox >= m || toy < 0 || toy >= n || getSum(tox, toy) > k) continue;
                int sum = tox * 100 + toy;
                if (set.contains(sum)) {
                    continue;
                }
                queue.add(sum);
                set.add(sum);
            }
        }
        return ans;
    }

    public int getSum(int x, int y) {
        int sum = 0;
        while (x != 0) {
            sum += x % 10;
            x /= 10;
        }
        while (y != 0) {
            sum += y % 10;
            y /= 10;
        }
        return sum;
    }

    /**
     * 14
     * 剪绳子1
     * 15
     * 剪绳子2
     **/
    public int cuttingRope(int n) {
        final int MOD = 1000000007;
        if (n <= 3) return n - 1;
        int ret = n % 3;
        if (ret == 0) return pow(3, n / 3, MOD);
        else if (ret == 1) return pow(3, n / 3 - 1, MOD) * 4;
        else return pow(3, n / 3, MOD) * 2;
    }

    public int cuttingRope2(int n) {
        if (n <= 3) return n - 1;
        final int MOD = 1000000007;
        long rem = 1, x = 3;
        int b = n % 3;
        for (int a = n / 3 - 1; a > 0; a /= 2) {
            if (a % 2 == 1) rem = (rem * x) % MOD;
            x = (x * x) % MOD;
        }
        if (b == 0) return (int) (rem * 3 % MOD);
        else if (b == 1) return (int) (rem * 4 % MOD);
        else return (int) (rem * 6 % MOD);
    }

    /**
     * 快速幂
     **/
    public int pow(int x, int n) {
        int ans = 1;

        while (n != 0) {
            if (n % 2 == 1) ans *= x;
            x *= x;
            n /= 2;
        }
        return ans;
    }

    public int pow(int x, int n, int mod) {
        long ans = 1;
        long a = x;
        while (n != 0) {
            if (n % 2 == 1) ans = (ans * a) % mod;
            a = (a * a) % mod;
            n /= 2;
        }
        return (int) ans;
    }

    /**
     * 15
     * 二进制中1的个数
     **/
    public int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            n = (n - 1) & n;
            count++;
        }
        return count;
    }

    /**
     * 41
     * 数据流中的中位数
     **/
    class MedianFinder {
        private PriorityQueue<Integer> small;
        private PriorityQueue<Integer> big;

        /**
         * initialize your data structure here.
         */
        public MedianFinder() {
            small = new PriorityQueue<>();
            big = new PriorityQueue<>(Collections.reverseOrder());
        }

        public void addNum(int num) {
            if (big.size() == small.size()) {
                small.add(num);
                big.add(small.poll());
            } else {
                big.add(num);
                small.add(big.poll());
            }

        }

        public double findMedian() {
            if (big.size() == small.size()) {
                return ((double) big.peek() + small.peek()) / 2;
            } else return (double) big.peek();
        }
    }

    /**
     * 09
     * 两个栈实现队列
     **/
    class CQueue {
        Deque<Integer> input, output;

        public CQueue() {
            input = new ArrayDeque<>();
            output = new ArrayDeque<>();
        }

        public void appendTail(int value) {
            input.addLast(value);
        }

        public int deleteHead() {
            if (!output.isEmpty()) {
                return output.pollLast();
            } else if (!input.isEmpty()) {
                while (!input.isEmpty()) {
                    output.addLast(input.pollLast());
                }
                return output.pollLast();
            } else {
                return -1;
            }
        }

    }


}

