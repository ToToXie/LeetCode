package yuanfudao;

import javafx.util.Pair;
import util.ListNode;
import util.TreeNode;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @program: LeetCode
 * @description:
 * @author: wd
 * @create: 2020-07-27 17:05
 **/

public class bishiti {
    /**
     * 1038 从二叉搜索树到最大和树
     **/
    int sum = 0;
    /**
     * 面试题 08.12 8皇后
     **/
    StringBuilder sb;
    int Queens_Num;

    public static void main(String[] args) {
        bishiti main = new bishiti();
        int[] ints = {1, 3, 5, 4, 7};
        Integer[] integers = {1, 2, 3, 4, 5, 6, null};
        TreeNode treeNode = TreeNode.buildByLevelOrder(integers);


//        System.out.println(
//                main.getMisPermutionNum(6)
//
//        );
        System.out.println(
                main.findNumberOfLIS(ints)
        );
    }

    /**
     * 673 最长不连续递增子序列的个数
     **/
    public int findNumberOfLIS(int[] nums) {
        if (nums == null || nums.length < 1) return 0;
        int[] lengths = new int[nums.length];
        int[] counts = new int[nums.length];
        Arrays.fill(counts, 1);
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    if (lengths[j] >= lengths[i]) {
                        lengths[i] = lengths[j] + 1;
                        counts[i] = counts[j];
                    } else if (lengths[j] + 1 == lengths[i]) {
                        counts[i] += counts[j];
                    }
                }
            }
        }
        int max = -1, ans = 0;
        for (int length : lengths) {
            max = Math.max(max, length);
        }
        for (int i = 0; i < nums.length; i++) {
            if (lengths[i] == max) {
                ans += counts[i];
            }
        }
        return ans;
    }

    /**
     * 23 合并k个排序链表
     **/
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length < 1) return null;
        Queue<ListNode> queue = new PriorityQueue<>((a, b) -> a.val - b.val);
        for (ListNode list : lists) {
            if (list != null) queue.add(list);
        }
        ListNode root = new ListNode();
        ListNode p = root;
        while (!queue.isEmpty()) {
            ListNode node = queue.poll();
            p.next = node;
            p = p.next;
            if (node.next != null) queue.add(node.next);
        }
        return root.next;
    }

    public ListNode mergeKListsB(ListNode[] lists) {
        if (lists == null || lists.length < 1) return null;
        Queue<ListNode> queue = new LinkedList<>();
        for (ListNode list : lists) {
            if (list != null) queue.add(list);
        }
        if (queue.size() < 1) return null;
        else if (queue.size() < 2) return queue.poll();
        while (queue.size() > 1) {
            ListNode a = queue.poll();
            ListNode b = queue.poll();
            queue.add(merge(a, b));
        }
        return queue.poll();
    }

    private ListNode merge(ListNode a, ListNode b) {
        ListNode root = new ListNode();
        ListNode now = root;
        while (a != null && b != null) {
            if (a.val < b.val) {
                now.next = a;
                a = a.next;
            } else {
                now.next = b;
                b = b.next;
            }
            now = now.next;
        }
        if (a != null) {
            now.next = a;
        }
        if (b != null) {
            now.next = b;
        }
        return root.next;
    }

    /**
     * 错位全排列
     **/
    int getMisPermutionNum(int n) {
        if (n == 1) return 0;
        if (n == 2) return 1;
        int a = 0, b = 1, c;
        for (int i = 3; i <= n; i++) {
            c = (i - 1) * (a + b);
            a = b;
            b = c;
        }
        return b;
    }

    /**
     * 47 全排列2
     **/
    public List<List<Integer>> permuteUnique(int[] nums) {
        int len = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        if (len == 0) {
            return res;
        }

        // 排序（升序或者降序都可以），排序是剪枝的前提
        Arrays.sort(nums);

        boolean[] used = new boolean[len];
        // 使用 Deque 是 Java 官方 Stack 类的建议
        Deque<Integer> path = new ArrayDeque<>(len);
        dfs(nums, len, 0, used, path, res);
        return res;
    }

    private void dfs(int[] nums, int len, int depth, boolean[] used, Deque<Integer> path, List<List<Integer>> res) {
        if (depth == len) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < len; ++i) {
            if (used[i]) {
                continue;
            }

            // 剪枝条件：i > 0 是为了保证 nums[i - 1] 有意义
            // 写 !used[i - 1] 是因为 nums[i - 1] 在深度优先遍历的过程中刚刚被撤销选择
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }

            path.addLast(nums[i]);
            used[i] = true;

            dfs(nums, len, depth + 1, used, path, res);
            // 回溯部分的代码，和 dfs 之前的代码是对称的
            used[i] = false;
            path.removeLast();
        }
    }

    private List<List<Integer>> getMisPermution(int n) {
        List<Integer> nums = new ArrayList<>(n + 1);
        for (int i = 0; i <= n; i++) {
            nums.add(i);
        }
        List<List<Integer>> ans = new ArrayList<>();
        getMisPermutionDFS(ans, nums, 1);
        return ans;
    }

    private void getMisPermutionDFS(List<List<Integer>> ans, List<Integer> nums, int index) {
        if (index == nums.size() - 1) {
            if (nums.get(index) != index) ans.add(new ArrayList<Integer>(nums.subList(1, nums.size())));
            return;
        }
        for (int i = index; i < nums.size(); i++) {
            if (nums.get(i) == index) continue;
            Collections.swap(nums, index, i);
            getMisPermutionDFS(ans, nums, index + 1);
            Collections.swap(nums, index, i);
        }
    }

    public TreeNode bstToGst(TreeNode root) {
        bstToGstDFS(root);
        return root;
    }

    public void bstToGstDFS(TreeNode root) {
        if (root == null) return;
        bstToGstDFS(root.right);
        sum += root.val;
        root.val = sum;
        bstToGstDFS(root.left);
    }

    /**
     * 113 路径总和2
     **/
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        pathSumDFS(root, sum, ans, new ArrayList<>());
        return ans;
    }

    private void pathSumDFS(TreeNode root, int sum, List<List<Integer>> ans, List<Integer> list) {
        if (sum < 0) return;
        if (root == null) {
            if (sum == 0) ans.add(new ArrayList<>(list));
            return;
        }
        sum -= root.val;
        list.add(root.val);
        if (root.right == null) pathSumDFS(root.left, sum, ans, list);
        if (root.left == null) pathSumDFS(root.right, sum, ans, list);
        sum += root.val;
        list.remove(list.size() - 1);
    }

    /**
     * 222 完全二叉树的节点个数
     **/
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int d = countLevel(root);
        int left = 0, right = (int) Math.pow(2, d - 1);
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (exists(mid, d - 1, root)) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return (int) Math.pow(2, d - 1) - 1 + left;

    }

    private boolean exists(int index, int d, TreeNode root) {
        int left = 0, right = (int) Math.pow(2, d);
        for (int i = 0; i < d; i++) {
            int mid = left + (right - left) / 2;
            if (index < mid) {
                root = root.left;
                right = mid;
            } else {
                root = root.right;
                left = mid;
            }
        }
        return root != null;
    }

    private int countLevel(TreeNode root) {
        int level = 0;
        while (root != null) {
            level++;
            root = root.left;
        }
        return level;
    }

    /**
     * 314 二叉树的垂直遍历
     **/
    private List<List<Integer>> verticalTraversal(TreeNode root) {
        Queue<Pair<Integer, TreeNode>> queue = new LinkedList<>();
        TreeMap<Integer, List<Integer>> map = new TreeMap<>();
        queue.add(new Pair<>(0, root));
        while (!queue.isEmpty()) {
            Pair<Integer, TreeNode> now = queue.poll();
            int index = now.getKey();
            TreeNode value = now.getValue();
            List<Integer> list = map.getOrDefault(index, new ArrayList<>());
            list.add(value.val);
            map.put(index, list);
//            map.compute(index,  )
            if (value.left != null) {
                queue.add(new Pair<>(index - 1, value.left));
            }
            if (value.right != null) {
                queue.add(new Pair<>(index + 1, value.right));
            }
        }
        return map.values().stream().collect(Collectors.toList());
    }

    /**
     * O(n) 取得数组中每个元素右边第一个比它大的元素
     **/
    private int[] greateThanTheRight(int[] nums) {
        if (nums == null) return nums;
        int n = nums.length;
        int[] ans = new int[n];
        Deque<Integer> stack = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (stack.isEmpty()) {
                stack.addLast(i);
            } else {
                while (!stack.isEmpty() && nums[stack.peekLast()] < nums[i]) {
                    ans[stack.pollLast()] = nums[i];
                }
                stack.addLast(i);
            }
        }
        while (!stack.isEmpty()) {
            ans[stack.pollLast()] = -1;
        }
        return ans;
    }

    public List<List<String>> solveNQueens(int n) {
        Queens_Num = n;
        sb = new StringBuilder();
        for (int i = 0; i < Queens_Num; i++) {
            sb.append('.');
        }
        int[] nums = new int[Queens_Num];
        List<List<String>> ans = new ArrayList<>();
        solveNQueensDFS(nums, 0, ans);
        return ans;
    }

    public void solveNQueensDFS(int[] nums, int index, List<List<String>> ans) {
        if (index == Queens_Num) {
            addResult(nums, ans);
        } else {
            for (int i = 0; i < Queens_Num; i++) {
                nums[index] = i;
                if (check(nums, index)) {
                    solveNQueensDFS(nums, index + 1, ans);
                }
            }
        }

    }

    private boolean check(int[] nums, int col) {
        for (int i = 0; i < col; i++) {
            if (nums[i] == nums[col] ||
                    Math.abs(nums[col] - nums[i]) == (col - i)) {
                return false;
            }
        }
        return true;
    }

    private void addResult(int[] nums, List<List<String>> ans) {
        List<String> one = new ArrayList<>();
        for (int i = 0; i < Queens_Num; i++) {
            sb.setCharAt(nums[i], 'Q');
            one.add(sb.toString());
            sb.setCharAt(nums[i], '.');
        }
        ans.add(one);
    }

    /**
     * 92 反转链表2
     **/
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null || head.next == null) return head;
        ListNode root = new ListNode();
        root.next = head;
        ListNode now = root, p = null;
        int k = n - m + 1;
        while (m > 0) {
            p = now;
            now = now.next;
            m--;
        }
        ListNode pre = null, next = null, q = now;
        while (k > 0) {
            next = now.next;
            now.next = pre;
            pre = now;
            now = next;
            k--;
        }
        p.next = pre;
        q.next = now;
        return root.next;
    }

    /**
     * 662 二叉树的最大宽度
     **/
    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) return 0;
        Deque<Pair<TreeNode, Integer>> queue = new LinkedList<>();
        queue.add(new Pair<>(root, 1));
        int max = 0;
        while (!queue.isEmpty()) {
            int n = queue.size();
            max = Math.max(max, queue.peekLast().getValue() - queue.peekFirst().getValue() + 1);
            for (int i = 0; i < n; i++) {
                Pair<TreeNode, Integer> now = queue.pollFirst();
                if (now.getKey().left != null) {
                    queue.addLast(new Pair<>(now.getKey().left, now.getValue() * 2));
                }
                if (now.getKey().right != null) {
                    queue.addLast(new Pair<>(now.getKey().right, now.getValue() * 2 + 1));
                }
            }
        }
        return max;
    }

    /**
     * 674 最长连续递增序列
     **/
    public int findLengthOfLCIS(int[] nums) {
        if (nums == null || nums.length < 1) {
            return 0;
        }
        int now = 1, max = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                now++;
                max = Math.max(max, now);
            } else {
                now = 1;
            }
        }
        return max;
    }
}
