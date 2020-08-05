package tags;

import util.ListNode;

import java.util.*;

/**
 * @program: LeetCode
 * @description: 随即每日一题
 * @author: wd
 * @create: 2020-07-13 10:30
 **/

public class RandomQuestion {
    static String[] strs = {"a", "b", "ba", "bca", "bda", "bdca"};

    public static void main(String[] args) {
        RandomQuestion main = new RandomQuestion();
        System.out.println(
                main.longestStrChain(strs)
        );
    }

    /**
     * 49 字母异位词分组
     **/
    public List<List<String>> groupAnagrams(String[] strs) {
        char[] chars = new char[26];
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            Arrays.fill(chars, '0');
            for (char c : str.toCharArray()) {
                chars[c - 'a']++;
            }
            String s = new String(chars);
            if (!map.containsKey(s)) {
                map.put(s, new ArrayList<>());
            }
            map.get(s).add(str);
        }
        return new ArrayList<>(map.values());
    }

    /**
     * 328 奇偶链表
     **/
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode p1 = new ListNode(-1);
        ListNode p2 = new ListNode(-1);
        ListNode odd = p1, even = p2;

        while (head != null) {
            p1.next = head;
            p1 = p1.next;
            if (head.next != null) head = head.next;
            else break;

            p2.next = head;
            p2 = p2.next;
            head = head.next;
        }
        p1.next = even.next;
        return odd.next;
    }

    /**
     * 1048 最长字符串链
     **/
    public int longestStrChain(String[] words) {
        if (words == null || words.length < 1) return 0;
        Arrays.sort(words, (a, b) -> a.length() - b.length());
        int[] dp = new int[words.length];
        int ans = 0;
        Arrays.fill(dp, 1);
        for (int i = 0; i < words.length; i++) {
            String a = words[i];
            for (int j = i + 1; j < words.length; j++) {
                if (a.length() == words[j].length()) continue;
                if (a.length() + 1 < words[j].length()) break;
                if (isStrChain(a, words[j])) {
                    dp[j] = dp[i] + 1;
                    ans = Math.max(ans, dp[j]);
                }
            }
        }
        return ans;
    }

    private boolean isStrChain(String a, String b) {
        int i = 0, j = 0;
        boolean flag = false;
        while (i < a.length() && j < b.length()) {
            if (a.charAt(i) == b.charAt(j)) {
                i++;
                j++;
            } else {
                if (flag) return false;
                flag = true;
                j++;
            }
        }
        return i == a.length();
    }

    /**
     * 1047 删除字符串中的所有相邻重复项
     **/
    public String removeDuplicates(String S) {
        if (S == null || S.length() < 2) {
            return S;
        }
        Deque<Character> stack = new LinkedList<>();
        for (char c : S.toCharArray()) {
            if (stack.isEmpty()) {
                stack.addLast(c);
            } else {
                if (stack.peekLast() == c) {
                    stack.pollLast();
                } else {
                    stack.addLast(c);
                }
            }
        }
        StringBuilder ans = new StringBuilder();
        stack.stream().forEach(it -> ans.append(it));
        return ans.toString();
    }

    /**
     * 881 救生艇
     **/
    public int numRescueBoats(int[] people, int limit) {
        if (people == null || limit < 1) return 0;
        Arrays.sort(people);
        int left = 0, right = people.length;
        int count = 0;

        while (left < right) {
            if (people[right - 1] + people[left] <= limit) {
                left++;
                right--;
                count++;
            } else {
                count++;
                right--;
            }
        }
        return count;
    }
}
