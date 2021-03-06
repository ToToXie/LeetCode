package tags;

import javafx.util.Pair;
import util.ListNode;
import util.Node;
import util.Swap;
import util.TreeNode;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @program: LeetCode
 * @description: 中等难度的题
 * @author: wd
 * @create: 2020-07-20 19:05
 **/

public class MediumQS_1 {
    public static int[] nextIndex = {-1, 0, 1, 0, 0, -1, 0, 1};
    static int[] ints = {0, 0, 1, 1, 1, 1, 2, 3, 3};
    static Integer[] integers = {1, null, 2, 3};
    static int[][] intss = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
    static char[][] board =
            {
                    {'A', 'B', 'C', 'E'},
                    {'S', 'F', 'E', 'S'},
                    {'A', 'D', 'E', 'E'}
            };
    static char[][] board2 =
            {
                    {'A'}
            };

    public static void main(String[] args) {
        MediumQS_1 main = new MediumQS_1();
        TreeNode treeNode = TreeNode.buildByLevelOrder(integers);
        System.out.println(
                main.preorderTraversal(treeNode)
        );
//        main.sortColors(ints);
//        System.out.println(Arrays.toString(ints));
    }

    /**
     * 144 二叉树前序非递归
     **/
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> ans = new LinkedList<>();
        if (root == null) return ans;
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode p = root;
        while (p != null || !stack.isEmpty()) {
            while (p != null) {
                stack.addLast(p);
                ans.add(p.val);
                p = p.left;
            }
            p = stack.pollLast();
            p = p.right;
        }
        return ans;
    }

    /**
     * 143 重排链表
     **/
    public void reorderList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) return;
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        fast = slow.next;
        slow.next = null;
        ListNode prev = null;
        while (fast != null) {
            slow = fast.next;
            fast.next = prev;
            prev = fast;
            fast = slow;
        }
        slow = head;
        fast = prev;
        while (fast != null) {
            prev = fast.next;
            fast.next = slow.next;
            slow.next = fast;
            slow = fast.next;
            fast = prev;
        }
    }

    /**
     * 139 单词拆分
     **/
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>();
        int max = -1;
        for (String it : wordDict) {
            max = Math.max(max, it.length());
            set.add(it);
        }
        int length = s.length();
        boolean[] dp = new boolean[length + 1];
        dp[0] = true;
        for (int i = 1; i <= length; i++) {
            for (int j = i; j >= 0 && (i - j + 1) <= max; j--) {
                dp[i] = dp[j] && set.contains(s.substring(j, i - 1));
            }
        }
        return dp[length];
    }

    /**
     * 138 复制带随即指针的链表
     **/
    public Node copyRandomList(Node head) {
        Node now = head;
        while (now != null) {
            Node node = new Node(now.val);
            node.next = now.next;
            now.next = node;
            now = node.next;
        }
        Node root = new Node(-1);
        Node p;
        now = head;
        while (now != null) {
            p = now.next;
            if (now.random != null) p.random = now.random.next;
            now = now.next.next;
        }
        p = root;
        now = head;
        while (now != null) {
            p.next = now.next;
            p = now.next;
            if (now.random != null) p.random = now.random.next;
            now.next = p.next;
            now = now.next;
        }
        return root.next;

    }

    /**
     * 137 只出现一次的数字2
     **/

    public int singleNumber(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for (Map.Entry<Integer, Integer> it : map.entrySet()) {
            if (it.getValue() == 1) return it.getKey();
        }
        return -1;
    }
    /**
     *  337 打家劫舍3
     **/
//    public int rob(TreeNode root) {
//        if(root == null) return 0;
//        if(root.left == null && root.right == null) return root.val;
//        Map<TreeNode,Integer> fMap = new HashMap<>();
//        Map<TreeNode,Integer> gMap = new HashMap<>();
//        myRob3(root, fMap, gMap);
//        return Math.max(fMap.get(root), gMap.get(root));
//    }
//    public void myRob3(TreeNode root,Map<TreeNode,Integer> fMap,Map<TreeNode,Integer> gMap){
//        if(root == null){
//            return;
//        }
//        myRob3(root.left, fMap, gMap);
//        myRob3(root.right, fMap, gMap);
//        fMap.put(root, root.val + gMap.getOrDefault(root.left,0) + gMap.getOrDefault(root.right,0));
//        gMap.put(root,
//                Math.max(fMap.getOrDefault(root.left, 0),
//                        gMap.getOrDefault(root.left,0)) +
//                Math.max(fMap.getOrDefault(root.right, 0),
//                        gMap.getOrDefault(root.right,0))
//                );
//    }

    /**
     * 337 打家劫舍3 优化
     **/
    public int rob(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return root.val;
        Pair<Integer, Integer> ans = myRob3(root);
        return Math.max(ans.getKey(), ans.getValue());
    }
    //Pair<Integer,Integer> key 表示 f（root） value g（root）

    public Pair<Integer, Integer> myRob3(TreeNode root) {
        if (root == null) {
            return new Pair<>(0, 0);
        }
        Pair<Integer, Integer> left = myRob3(root.left);
        Pair<Integer, Integer> right = myRob3(root.right);
        // key 表示 f（root）
        // value 表示 g（root）
        int f = root.val + left.getValue() + right.getValue();
        int g = Math.max(left.getKey(), left.getValue()) + Math.max(right.getKey(), right.getValue());
        return new Pair<>(f, g);
    }

    /**
     * 213 打家劫舍2
     **/
    public int rob(int[] nums) {
        if (nums == null || nums.length < 1) return 0;
        int n = nums.length;
        if (n == 1) return nums[0];
        int a = myRob2(Arrays.copyOfRange(nums, 1, n));
        int b = myRob2(Arrays.copyOfRange(nums, 0, n - 1));
        return Math.max(a, b);

    }

    public int myRob2(int[] nums) {
        int prev = 0, now = 0;
        for (int num : nums) {
            int temp = now;
            now = Math.max(prev + num, now);
            prev = temp;
        }
        return Math.max(now, prev);
    }


    /**
     * 216 组合总和3
     **/
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> ans = new ArrayList<>();
        combinationSum3DFS(k, n, 1, ans, new ArrayList<>());
        return ans;
    }

    public void combinationSum3DFS(int k, int sum, int index, List<List<Integer>> ans, List<Integer> list) {
        if (sum <= 0 || k <= 0) {
            if (k == 0 && sum == 0) {
                ans.add(new ArrayList<>(list));
            }
        } else {
            for (int i = index; i < 10; i++) {
                if (k == 0) break;
                list.add(i);
                combinationSum3DFS(k - 1, sum - i, i + 1, ans, list);
                list.remove(list.size() - 1);
            }
        }
    }

    /**
     * 76 组合
     **/
    int n;
    int k;
    List<List<Integer>> ansList = new ArrayList<>();
    /**
     * 47 全排列2
     **/
    List<List<Integer>> ans = new ArrayList<>();


    /**
     * 86 分隔链表
     **/
    public ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null) return head;
        ListNode left = new ListNode();
        ListNode right = new ListNode();
        ListNode p1 = left, p2 = right;
        while (head != null) {
            if (head.val < x) {
                p1.next = head;
                p1 = p1.next;
            } else {
                p2.next = head;
                p2 = p2.next;
            }
            head = head.next;
        }
        p2.next = null;
        p1.next = right.next;
        return left.next;
    }

    /**
     * 82 删除排序链表中的重复元素 2
     **/
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode root = new ListNode(-1);
        ListNode now = root, left = head, right = head.next;
        int cnt = 1;
        while (right != null) {
            if (left.val != right.val) {
                if (cnt == 1) {
                    now.next = left;
                    now = now.next;
                }
                left = right;
                right = right.next;
                cnt = 1;
            } else {
                right = right.next;
                cnt++;
            }
        }
        if (left.next == null) {
            now.next = left;
            now = now.next;
        }
        now.next = null;
        return root.next;
    }

    /**
     * 80 删除排序数组中的重复项 2
     **/
    public int removeDuplicates(int[] nums) {
        if (nums == null) return 0;
        if (nums.length < 3) return nums.length;
        int i = 0, j = 0;
        int count = 0;
        while (j < nums.length) {
            if (j == 0 || nums[j] != nums[j - 1]) {
                nums[i++] = nums[j++];
                count = 1;
            } else if (nums[j] == nums[j - 1]) {
                if (count < 2) {
                    nums[i++] = nums[j++];
                    count++;
                } else {
                    j++;
                }
            }
        }
        return i;
    }

    /**
     * 79 单词搜索
     **/
    public boolean exist(char[][] board, String word) {
        if (board == null || board.length < 0) return false;
        int m = board.length;
        int n = board[0].length;
        boolean[] vis = new boolean[m * n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == word.charAt(0)) {
                    Arrays.fill(vis, false);
                    if (existDFS(board, i, j, vis, 0, word, n)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean existDFS(char[][] board, int x, int y, boolean[] vis, int i, String word, int n) {
        if (i == word.length() - 1) {
            return true;
        }
        vis[x * n + y] = true;
        boolean flag = false;
        for (int k = 0; k < 8; k += 2) {
            int toX = x + Index.nextIndexOfFour[k];
            int toY = y + Index.nextIndexOfFour[k + 1];
            if (toX < 0 || toY < 0 || toX >= board.length || toY >= n) continue;
            if (!vis[toX * n + toY] && board[toX][toY] == word.charAt(i + 1)) {
//                System.out.println(board[toX][toY] +"  ---  i : " + (i +1) );
                flag = flag | existDFS(board, toX, toY, vis, i + 1, word, n);
            }
        }
        vis[x * n + y] = false;
        return flag;
    }

    public List<List<Integer>> combine(int n, int k) {
        this.n = n;
        this.k = k;
        combineCore(1, new LinkedList<>());
        return ansList;
    }

    public void combineCore(int frist, List<Integer> list) {
        if (list.size() == k) {
            ansList.add(new LinkedList<>(list));
        } else {
            for (int i = frist; i <= n; i++) {
                list.add(i);
                combineCore(i + 1, list);
                list.remove(list.size() - 1);
            }
        }
    }

    /**
     * 75 颜色分类
     **/
    public void sortColors(int[] nums) {
        int p0 = 0, p2 = nums.length - 1;
        int cur = 0;
        while (cur <= p2) {
            if (nums[cur] == 2) {
                Swap.swap(nums, cur, p2);
                p2--;
            } else if (cur != p0 && nums[cur] == 0) {
                Swap.swap(nums, cur, p0);
                p0++;
            } else cur++;
        }
    }

    /**
     * 71 简化路径
     **/
    public String simplifyPath(String path) {
        if (path == null || path.length() < 1) return "/";
        String[] split = path.split("/");
        Stack<String> stack = new Stack<>();
        for (String s : split) {
            if (s.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else if (!(s.equals("") || s.equals("."))) {
                stack.add(s);
            }
        }
        if (stack.isEmpty()) return "/";
        StringBuilder ans = new StringBuilder();
        for (String s : stack) {
            ans.append("/");
            ans.append(s);
        }
        return ans.toString();
    }

    /**
     * 64 最小路径和
     **/
    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length < 1) return 0;
        int m = grid.length;
        int n = grid[0].length;

        int[] dp = new int[n];
        dp[0] = grid[0][0];
        for (int i = 1; i < n; i++) {
            dp[i] = dp[i - 1] + grid[0][i];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (j == 0) {
                    dp[j] = dp[j] + grid[i][j];
                } else {
                    dp[j] = Math.min(dp[j], dp[j - 1]) + grid[i][j];
                }
            }
        }
        return dp[n - 1];
    }

    /**
     * 62 不同路径2
     **/
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid[0].length < 1) return 0;
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        if (obstacleGrid[m - 1][n - 1] == 1) return 0;
        if (obstacleGrid[0][0] == 1) return 0;
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            if (obstacleGrid[0][i] != 1) {
                dp[i] = 1;
            } else {
                break;
            }
        }
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[j] = 0;
                } else {
                    dp[j] = dp[j] + (j > 0 ? dp[j - 1] : 0);
                }
            }
        }
        return dp[n - 1];
    }

    /**
     * 60 第k个排列
     **/
    public String getPermutation(int n, int k) {
        int[] nums = new int[n];//生成nums数组
        for (int i = 0; i < n; i++) nums[i] = i + 1;
        boolean[] used = new boolean[n];//记录当前的索引的数是否被使用过
        return dfs(nums, new ArrayList<String>(), used, 0, n, k);
    }

    /**
     * @param nums      源数组
     * @param levelList 每一层的选择
     * @param used      数组的元素是否被使用过
     * @param depth     深度，也就是当前使用的元素的索引
     * @param n         上限值
     * @param k         第k个
     * @return 第k个排列
     */
    private String dfs(int[] nums, List<String> levelList, boolean[] used, int depth, int n, int k) {
        if (depth == n) {//触发出口条件，开始收集结果集
            StringBuilder res = new StringBuilder();
            for (String s : levelList) res.append(s);
            return res.toString();
        }
        int cur = factorial(n - depth - 1);//当前的depth也就是索引，有多少排列数
        for (int i = 0; i < n; i++) {
            if (used[i]) continue;//当前元素被使用过，做剪枝
            if (cur < k) {//当前的排列组合数小于k，说明就算这一层排完了，也到不了第k个，剪枝
                k -= cur;
                continue;
            }
            levelList.add(nums[i] + "");//list收的是string类型
            used[i] = true;//当前元素被使用过标记
            return dfs(nums, levelList, used, depth + 1, n, k);
        }
        return null;
    }

    //返回n的阶乘，如3!=3*2*1=6
    private int factorial(int n) {
        int res = 1;
        while (n > 0) {
            res *= n--;
        }
        return res;
    }

    public List<List<Integer>> permute(int[] nums) {
        permuteCore(nums, 0);
        return ans;
    }

    public void permuteCore(int[] nums, int index) {
        if (index == nums.length - 1) {
            ans.add(Arrays.stream(nums).boxed().collect(Collectors.toList()));
            return;
        }
        for (int i = index; i < nums.length; i++) {
            if (isRepeat(nums, index, i)) {
                continue;
            }
            swap(nums, i, index);
            permuteCore(nums, index + 1);
            swap(nums, i, index);
        }
    }

    public boolean isRepeat(int[] nums, int start, int end) {
        while (start < end) {
            if (nums[start] == nums[end]) {
                return true;
            }
            start++;
        }
        return false;
    }

    private void swap(int[] nums, int a, int b) {
        if (a != b) {
            nums[a] = nums[a] ^ nums[b];
            nums[b] = nums[a] ^ nums[b];
            nums[a] = nums[a] ^ nums[b];
        }
    }

}
