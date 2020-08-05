package tags;

import util.ListNode;
import util.TreeNode;

import java.util.*;

/**
 * @program: LeetCode
 * @description:
 * @author: wd
 * @create: 2020-05-20 11:07
 **/

public class TencentQuestions {
    static private int[] nums = {3, 2, 1, 5, 6, 4};
    /**
     * 得到根节点到叶节点的最大路径和
     **/
    int ans = Integer.MIN_VALUE;

    public static void main(String[] args) {
        TencentQuestions tencentQuestions = new TencentQuestions();
        ListNode listNode = ListNode.bulideByLevelOrder(nums);
        int kthLargest = tencentQuestions.findKthLargest(nums, 2);
    }

    /**
     * 二叉树中的最大路径和
     **/
    public int maxPathSum(TreeNode root) {
        getRootSum(root);
        return ans;
    }

    private int getRootSum(TreeNode root) {
        if (root == null) return 0;
        int left = Math.max(getRootSum(root.left), 0);
        int right = Math.max(getRootSum(root.right), 0);
        int newPathSum = root.val + left + right;
        ans = Math.max(ans, newPathSum);
        return root.val + Math.max(left, right);
    }

    /**
     * 除自身以外数组的乘积
     **/
    public int[] productExceptSelf(int[] nums) {
        int[] ans = new int[nums.length];
        ans[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            ans[i] = ans[i - 1] * nums[i - 1];
        }
        int right = 1;
        for (int j = ans.length - 1; j >= 0; j--) {
            ans[j] = right * ans[j];
            right = right * nums[j];
        }
        return ans;
    }

    /**
     * 二叉搜索树中第k小的元素
     **/
    public int kthSmallest(TreeNode root, int k) {
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode p = root;
        while (!stack.isEmpty() || p != null) {
            while (p != null) {
                stack.addLast(p);
                p = p.left;
            }
            p = stack.removeLast();
            if (--k == 0) return p.val;
            p = p.right;
        }
        return -1;
    }

    /**
     * 数组中的第k大的元素
     **/
    public int findKthLargest(int[] nums, int k) {
        if (nums == null || nums.length < 1) return -1;
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int partition = partition(nums, left, right);
            if (partition == (k - 1)) return nums[k - 1];
            else if (partition < (k - 1)) {
                left = partition + 1;
            } else {
                right = partition - 1;
            }
        }
        return -1;
    }

    private int partition(int[] nums, int left, int right) {
        int temp = nums[left];
        while (left < right) {
            while (left < right && nums[right] < temp) right--;
            nums[left] = nums[right];
            while (left < right && nums[left] >= temp) left++;
            nums[right] = nums[left];
        }
        nums[left] = temp;
        return left;
    }

    /**
     * 排序一个链表
     **/
    public ListNode sortList(ListNode head) {
        ListNode p = head;
        int size = 0;
        while (p != null) {
            size++;
            p = p.next;
        }
        ListNode first = new ListNode(-1);
        ListNode second = new ListNode(-1);
        ListNode current, tail;
        ListNode root = new ListNode(-1);
        root.next = head;
        for (int i = 1; i < size; i *= 2) {
            tail = root;
            current = root.next;
            while (current != null) {
                second.next = cut(current, i);
                first.next = current;
                current = cut(second.next, i);
                tail.next = merge(first, second);
                while (tail.next != null) {
                    tail = tail.next;
                }
            }
        }
        return root.next;
    }

    /**
     * 将原链表截断n个节点，返回截断后的新节点
     **/
    private ListNode cut(ListNode head, int n) {
        ListNode p = head;
        while (p != null && n > 1) {
            p = p.next;
            n--;
        }
        if (p == null) return null;
        head = p.next;
        p.next = null;
        return head;
    }

    /**
     * left,right 都是有头节点的
     **/
    private ListNode merge(ListNode left, ListNode right) {
        ListNode p1 = left.next, p2 = right.next;
        ListNode root = left, cur = root;
        while (p1 != null && p2 != null) {
            if (p1.val < p2.val) {
                cur.next = p1;
                p1 = p1.next;
            } else {
                cur.next = p2;
                p2 = p2.next;
            }
            cur = cur.next;
        }
        if (p2 != null) cur.next = p2;
        if (p1 != null) cur.next = p1;
        return root.next;
    }

    /**
     * 环形链表2
     **/
    public ListNode detectCycle(ListNode head) {
        ListNode fast = head, slow = head;
        while (true) {
            if (fast == null && fast.next == null) {
                return null;
            }
            slow = slow.next;
            fast = fast.next.next;
            if (fast == slow) {
                break;
            }

        }
        slow = head;
        while (fast != slow) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    /**
     * 格雷编码
     **/
    public List<Integer> grayCode(int n) {
        List<Integer> ans = new ArrayList<>((int) Math.pow(2, n));
        ans.add(0);
        int head = 1;
        for (int i = 0; i < n; i++) {
            for (int size = ans.size(); size > 0; size--) {
                ans.add(head + ans.get(size - 1));
            }
            head <<= 1;

        }
        return ans;
    }

    public int getA() {
        return 0;
    }

    public Number getA(int a) {
        return Double.valueOf(0.77d);
    }

    /**
     * 子集
     **/
    public List<List<Integer>> subsets(int[] nums) {
        int n = nums.length;
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = (int) Math.pow(2, n); i < (int) Math.pow(2, n + 1); i++) {
            String substring = Integer.toBinaryString(i).substring(1);
            List<Integer> one = new ArrayList<>();
            for (int i1 = 0; i1 < substring.length(); i1++) {
                if (substring.charAt(i1) == '1') {
                    one.add(nums[i1]);
                }
            }
            ans.add(one);
        }
        return ans;
    }

    /**
     * 不同路径
     **/
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) dp[i][j] = 1;
                else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    /**
     * 仿照01背包
     * 优化空间
     **/
    public int uniquePathsB(int m, int n) {
        int[] dp = new int[m];
        Arrays.fill(dp, 1);
        for (int i = 1; i < n; i++) {
            for (int i1 = 0; i1 < m; i1++) {
                if (i1 == 0) dp[i1] = 1;
                else dp[i1] = dp[i1] + dp[i1 - 1];
            }
        }
        return dp[m - 1];
    }

    /**
     * 螺旋矩阵3
     **/
    public int[][] spiralMatrixIII(int R, int C, int r0, int c0) {
        int[][] ans = new int[R * C][2];
        int x = r0, y = c0, nx, ny, count = 0, k = 1, cnt = 0;

        int[] index = {0, 1, 1, 0, 0, -1, -1, 0};
        while (count < R * C) {
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < k; j++) {
                    if (x < R && x >= 0 && y < C && y >= 0) {
                        int[] one = new int[]{x, y};
                        ans[count] = one;
                        count++;
                    }
                    x = x + index[cnt];
                    y = y + index[cnt + 1];
                }
                cnt = (cnt + 2) % 8;
            }
            k++;

        }
        return ans;
    }

    /**
     * 螺旋矩阵2
     **/
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int cur = 1, max = n * n;
        int[] index = {0, 1, 1, 0, 0, -1, -1, 0};
        int x = 0, y = 0, nx, ny, count = 0;
        while (cur <= max) {
            if (matrix[x][y] == 0) {
                matrix[x][y] = cur++;
            } else {
                nx = x + index[count];
                ny = y + index[count + 1];
                if (nx < n && ny < n && nx >= 0 && ny >= 0 && matrix[nx][ny] == 0) {
                    x = nx;
                    y = ny;
                } else {
                    count = (count + 2) % 8;
                }
            }
        }
        return matrix;
    }

    /**
     * 螺旋矩阵
     **/
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> ans = new ArrayList<>();
        if (matrix == null || matrix.length < 1 || matrix[0].length < 1) return ans;
        int N = matrix.length - 1;
        int M = matrix[0].length - 1;
        int left = 0, up = 0, right = M, down = N;
        while (left <= right && up <= down) {
            for (int i = left; i <= right; i++) {
                ans.add(matrix[up][i]);
            }
            for (int i = up + 1; i <= down; i++) {
                ans.add(matrix[i][right]);
            }
            if (left < right && up < down) {
                for (int i = right - 1; i >= left; i--) {
                    ans.add(matrix[down][i]);
                }
                for (int i = down - 1; i > up; i--) {
                    ans.add(matrix[i][left]);
                }
            }
            up++;
            down--;
            left++;
            right--;
        }
        return ans;
    }

    /**
     * 字符串相乘
     **/
    public String multiply(String num1, String num2) {
        if (num1.charAt(0) == '0' || num2.charAt(0) == '0') {
            return "0";
        }
        int sum = 0, n1 = 0, n2 = 0, n3 = 0;
        StringBuilder sb = new StringBuilder();
        int length1 = num1.length();
        for (int i = 0; i < length1; i++) {
            n1 = num1.charAt(length1 - i - 1) - '0';
            int length2 = num2.length();
            for (int j = 0; j < length2; j++) {
                n2 = num2.charAt(length2 - 1 - j) - '0';
                if (sb.length() < i + j + 1) {
                    sb.append(0);
                }
                if (sb.length() < i + j + 2) {
                    sb.append(0);
                }
                n3 = sb.charAt(i + j) - '0';
                sum = n1 * n2 + n3;
                sb.setCharAt(i + j, (char) (sum % 10 + '0'));
                sb.setCharAt(i + j + 1, (char) (sb.charAt(i + j + 1) + sum / 10));
            }
        }
        if (sb.charAt(sb.length() - 1) == '0') sb.deleteCharAt(sb.length() - 1);
        return sb.reverse().toString();

    }

    /**
     * 字符串相加
     **/
    public String addStrings(String num1, String num2) {
        if (num1.charAt(0) == 0) return num2;
        if (num2.charAt(0) == 0) return num1;
        StringBuilder ans = new StringBuilder();
        int carry = 0, n1 = 0, n2 = 0;
        for (int i = num1.length() - 1, j = num2.length() - 1; i >= 0 || j >= 0; i--, j--) {
            n1 = (i >= 0 ? num1.charAt(i) - '0' : 0);
            n2 = (j >= 0 ? num2.charAt(j) - '0' : 0);
            ans.append((n1 + n2 + carry) % 10);
            carry = (n1 + n2 + carry) / 10;
        }
        if (carry != 0) ans.append(carry);
        return ans.reverse().toString();
    }

    /**
     * 反转单词顺序
     **/
    public String reverseWords(String s) {
        if (s == null || s.length() < 1) return s;
        StringBuilder ans = new StringBuilder();
        StringBuilder temp = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c == ' ') {
                ans.append(c);
                ans.append(temp.reverse());
                temp.setLength(0);
            } else {
                temp.append(c);
            }
        }
        if (temp.length() != 0) {
            ans.append(' ');
            ans.append(temp.reverse());
        }
        return ans.deleteCharAt(0).toString();
    }

    /**
     * 反转字符串
     **/
    public void reverseString(char[] s) {
        if (s == null || s.length < 2) {
            return;
        }
        char c = '0';
        int length = s.length;
        for (int i = 0; i < length / 2; i++) {
            c = s[i];
            s[i] = s[length - i - 1];
            s[length - i - 1] = c;
        }

    }

    /**
     * 删除节点
     * 不是末尾，所以复制一下值，然后删除下一个节点就行
     * 不是末尾，所以复制一下值，然后删除下一个节点就行
     **/
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

    /**
     * 求公共祖先节点，二叉搜索树
     **/
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        int parent = root.val;
        int pval = p.val;
        int qval = q.val;
        if (parent > pval && parent > qval) {
            return lowestCommonAncestor(root.left, p, q);
        } else if (parent < pval && parent < qval) {
            return lowestCommonAncestor(root.right, p, q);
        }
        return root;
    }

    /**
     * 迭代版本
     **/
    public TreeNode lowestCommonAncestorB(TreeNode root, TreeNode p, TreeNode q) {
        int parent;
        int pval = p.val;
        int qval = q.val;
        while (root != null) {
            parent = root.val;
            if (parent > pval && parent > qval) {
                root = root.left;
            } else if (parent < pval && parent < qval) {
                root = root.right;
            } else {
                return root;
            }
        }
        return null;
    }

    /**
     * 判断一个数是否是2的幂次方
     * n &（n - 1） 可以清楚最右边的一位1，该位置前的高位不变，地位是0
     * n & (-n) 可以得到最右边的1，其他位置为0
     **/
    public boolean isPowerOfTwo(int n) {
        if (n == 0) {
            return false;
        }
        long x = n;
        x = x & (x - 1);
        return x == 0 ? true : false;
    }

    /**
     * 数组中是否存在重复元素
     **/
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (set.contains(num)) {
                return true;
            } else {
                set.add(num);
            }
        }
        return false;
    }

    public boolean search(int[] nums, int x) {
        int s = 0, e = nums.length - 1;
        int mid = 0;
        while (s < e) {
            mid = s + (e - s) / 2;
            if (nums[mid] < x) {
                s = mid;
            } else if (nums[mid] > x) {
                e = mid;
            } else {
                return true;
            }
        }
        return false;
    }


}

