package tags;

import util.ListNode;

import java.util.*;

/**
 * @program: LeetCode
 * @description:
 * @author: wd
 * @create: 2020-07-09 09:31
 **/

public class InterviewQuestions {
    static private int[] ints1 = {1, 2, 3, 4};

    public static void main(String[] args) {
        InterviewQuestions main = new InterviewQuestions();
        ListNode head = ListNode.bulideByLevelOrder(ints1);
        System.out.println(
//                main.wiggleSort(ints1)

        );
        main.wiggleSort(ints1);

    }

    /**
     * 面试题 10.11 峰与谷
     **/
    public void wiggleSort(int[] nums) {
        if (nums == null) return;
        Arrays.sort(nums);
        int i = 0, j = nums.length;
        if (j % 2 == 1) i++;
        while (i < j) {
            int a = nums[i];
            nums[i] = nums[j - 1];
            nums[j - 1] = a;
            j -= 2;
            i += 2;
        }

    }

    /**
     * 面试题 02.06 回文链表
     **/
    public boolean isPalindrome(ListNode head) {
        if (head == null) return true;
        ListNode p1 = head, p2 = head;
        int count = 0;
        while (p2 != null) {
            p2 = p2.next;
            count++;
            if (p2 != null) {
                count++;
                p2 = p2.next;
            } else {
                break;
            }
            p1 = p1.next;
        }
        ListNode pre = null, temp;
        p2 = head;
        while (p2 != p1) {
            temp = p2.next;
            p2.next = pre;
            pre = p2;
            p2 = temp;
        }
        p2 = pre;
        if (count % 2 == 1) p1 = p1.next;
        while (p1 != null && p2 != null) {
            if (p1.val != p2.val) {
                return false;
            }
            p1 = p1.next;
            p2 = p2.next;
        }
        return p1 == p2;
    }

    /**
     * 面试题 04.01 节点间通路
     **/
    public boolean findWhetherExistsPath(int n, int[][] graph, int start, int target) {
        if (n < 1 || graph == null) return false;
        boolean[] vis = new boolean[n];
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int[] ints : graph) {
            int a = ints[0];
            int b = ints[1];
            if (map.containsKey(a)) {
                if (a != b) map.get(a).add(b);
            } else {
                Set<Integer> set = new HashSet<>();
                if (a != b) set.add(b);
                map.put(a, set);
            }
        }
        return findWhetherExistsPathDFS(map, start, target, vis);
    }

    public boolean findWhetherExistsPathDFS(Map<Integer, Set<Integer>> map, int start, int end, boolean[] vis) {
        if (start == end) return true;

        vis[start] = true;
//        boolean flag = false;
        if (map.get(start) == null || map.get(start).size() < 1) return false;
        for (int to : map.get(start)) {
//            if(!vis[to]) flag |= findWhetherExistsPathDFS( map, to, end, vis);
            if (!vis[to]) {
                if (findWhetherExistsPathDFS(map, to, end, vis)) {
                    return true;
                }
            }
        }
        vis[start] = false;
        return false;
    }

    /**
     * 面试题 02.08 环路检测
     **/
    public ListNode detectCycle(ListNode head) {
        if (head == null) return null;
        //p1 == p2 恒成立了
        ListNode p1 = head, p2 = head;
        while (p1 != null && p2 != null) {
            p1 = p1.next;
            p2 = p2.next;
            if (p2 != null) p2 = p2.next;
            if (p1 == p2) break;
        }
        if (p1 == null || p2 == null) return null;
        p1 = head;
        while (p1 != p2) {
            p1 = p1.next;
            p2 = p2.next;
        }
        return p1;
    }

    /**
     * 面试题 02.03 删除中间节点
     **/
    public void deleteNode(ListNode node) {
        if (node == null) return;
        ListNode next = node.next;
        node.val = next.val;
        node.next = next.next;
    }

    /**
     * 面试题 02.02 返回倒数第k个节点
     **/
    public int kthToLast(ListNode head, int k) {
        if (head == null || k < 1) return -1;
        ListNode root = new ListNode(-1);
        root.next = head;
        ListNode p1 = root, p2 = root;

        while (p1 != null && k > 0) {
            p1 = p1.next;
            k--;
        }
        while (p1 != null) {
            p1 = p1.next;
            p2 = p2.next;
        }
        return p2.val;
    }

    /**
     * 面试题 02.05 链表求和
     * 反向存放
     **/
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0;
        ListNode root = new ListNode(-1);
        ListNode now = root;
        int a = 0, b = 0, c = 0;
        while (l1 != null || l2 != null) {
            a = (l1 == null ? 0 : l1.val);
            b = (l2 == null ? 0 : l2.val);
            now.next = new ListNode((a + b + carry) % 10);
            now = now.next;
            carry = (a + b + carry) / 10;
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }
        if (carry != 0) {
            now.next = new ListNode(carry);
            now = now.next;
        }
        now.next = null;
        return root.next;
    }

    /**
     * 面试题 02.04 分割链表
     **/
    public ListNode partition(ListNode head, int x) {
        ListNode s = new ListNode(-1);
        ListNode g = new ListNode(-1);
        ListNode p = s, q = g, now = head;
        while (now != null) {
            if (now.val < x) {
                p.next = now;
                p = p.next;
            } else {
                q.next = now;
                q = q.next;
            }
            now = now.next;
        }
        p.next = g.next;
        q.next = null;
        return s.next;

    }

    /**
     * 面试题 02.01 移除重复节点
     **/
    public ListNode removeDuplicateNodes(ListNode head) {
        Set<Integer> set = new HashSet<>();
        ListNode root = new ListNode(-1);
        ListNode now = root, p = head;
        while (p != null) {
            if (!set.contains(p.val)) {
                now.next = p;
                now = now.next;
                set.add(p.val);
            }
            p = p.next;
        }
        now.next = null;
        return root.next;
    }

    /**
     * 面试题01.09 字符串轮转
     **/
    public boolean isFlipedString(String s1, String s2) {
        if (s1 == null && s2 == null) return true;
        if (s1.length() == 0 && s2.length() == 0) return true;
        if (s1.length() != s2.length()) return false;
        for (int i = 0; i < s2.length(); i++) {
            if (s1.substring(0, s1.length() - i).equals(s2.substring(i))
                    && s1.substring(s1.length() - i).equals(s2.substring(0, i))) {
                return true;
            }
        }
        return false;
    }

    /**
     * s1 + s1 必定拼出来子串是s2
     **/
    public boolean isFlipedStringB(String s1, String s2) {
        if (s1.length() != s2.length()) return false;
        if (s1.equals(s2)) return true;
        s1 += s1;
        return s1.contains(s2);
    }

    /**
     * 面试题 01.08 零矩阵
     **/
    public void setZeroes(int[][] matrix) {
        if (matrix == null || matrix.length < 1) return;
        List<Integer> list = new LinkedList<>();
        int m = matrix.length, n = matrix[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    list.add(i);
                    list.add(j);
                }
            }
        }
        for (int i = 0; i < list.size(); i += 2) {
            int x = list.get(i);
            int y = list.get(i + 1);
            Arrays.fill(matrix[x], 0);
            for (int j = 0; j < m; j++) {
                matrix[j][y] = 0;
            }
        }

    }

    /**
     * 面试题01.05 一次编辑
     **/
    public boolean oneEditAway(String first, String second) {
        if (first == null && second == null) return true;
        if (Math.abs(first.length() - second.length()) > 1) return false;
        boolean flag = false;
        if (first.length() == second.length()) {
            int left = 0, right = first.length();
            while (left < right) {
                if (first.charAt(left) == second.charAt(left)) left++;
                else {
                    if (flag) return false;
                    else {
                        flag = true;
                        left++;
                    }
                }
            }
        } else {
            if (first.length() < second.length()) {
                String temp = second;
                second = first;
                first = temp;
            }

            for (int i = 0, j = 0; i < first.length() && j < second.length(); ) {
                if (first.charAt(i) == second.charAt(j)) {
                    j++;
                    i++;
                } else {
                    if (flag) return false;
                    else {
                        i++;
                        flag = true;
                    }
                }
            }
        }
        return true;
    }

    /**
     * 面试题 01.04 回文排列
     **/
    public boolean canPermutePalindrome(String s) {
        boolean[] vis = new boolean[128];
        for (char c : s.toCharArray()) {
            vis[c] = !vis[c];
        }
        boolean flag = false;
        for (boolean vi : vis) {
            if (flag && vi) {
                return false;
            }
            if (vi) {
                flag = true;
            }
        }
        return true;
    }

    /**
     * 面试题 01.03 URL化
     **/
    public String replaceSpaces(String S, int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            if (S.charAt(i) == ' ') {
                sb.append("%20");
            } else {
                sb.append(S.charAt(i));
            }
        }
        return sb.toString();
    }
}
