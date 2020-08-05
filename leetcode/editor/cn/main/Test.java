import javafx.util.Pair;
import util.TreeNode;

import java.util.*;

/**
 * @program: LeetCode
 * @description:
 * @author: wd
 * @create: 2020-04-20 16:10
 **/

public class Test {
    //    public static int[] nums = {7,8,1,2,3,4,5,6};
//    public static int[] nums = {100,-23,-23,404,100,23,23,23,3,404};
    public static int[] nums = {6, 4, 14, 6, 8, 13, 9, 7, 10, 6, 12};
    public static Integer[] numss = {1, 2, 3, 4, 5};
    int[] dp;
    int[] arr;
    int d, n;
    private Map<Integer, Integer> currSumMap = new HashMap<>();
    private int target;
    private int maxDiameter = 0;

    public static void main(String[] args) {
        Test test = new Test();
//        Solution solution = new Solution();
//        List<List<Integer>> rooms = new ArrayList<>();
//        Arrays.fill(nums,Integer.MAX_VALUE);  n
        new alibaba.Test();
        boolean[] xx = new boolean[7];
        xx[0] = true;
//        int[] nums = {1,2,3};
        int[][] ans = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
//        HashMap<Integer,Integer> map = new HashMap<>();
//        map.put(1, 1);
//        map.compute(2, (k,v) -> v == null ? 5 :v + 5);
//        map.compute(1, (k,v) -> v == null ? 5 :v + 5);
//        System.out.println(map.toString());
//        TreeNode build = TreeNode.buildByLevelOrder(numss);
//        util.TreeNode treeNode = test.convertBST(build);
//        int i = test.diameterOfBinaryTree(build);
//        System.out.println(test.convertBST(build));
        test.isPerfectSquareB(16);


//
//        System.out.println("test.movingCount(2,3,1) = " + test.movingCount(2, 3, 1));;
//        System.out.println(test.search(nums, 1));
//        test.testMap();
//        System.out.println(test.maxJumps(nums,2));
    }

    public boolean isPerfectSquareB(int num) {
        if (num < 2) return true;
        long ret = num / 2;
        while (ret * ret > num) {
            ret = (ret + num / ret) / 2;
        }
        return (ret * ret == num);
    }

    public boolean isPerfectSquare(int num) {
        if (num == 1) return true;
        int left = 2, right = num / 2, mid = 0;
        long temp = 0;
        while (left <= right) {
            mid = left + (right - left) / 2;
            temp = (long) mid * mid;
            if (temp == num) {
                return true;
            } else if (temp < num) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return false;
    }

    public int movingCount(int m, int n, int k) {
        int[] rows = new int[m];
        int[] cols = new int[n];
        for (int i = 0; i < m; i++) {
            rows[i] = (i / 10 + i % 10);
        }
        for (int i = 0; i < n; i++) {
            cols[i] = (i / 10 + i % 10);
        }
        boolean[][] vis = new boolean[m][n];
        int count = 0;
        Queue<Pair<Integer, Integer>> queue = new LinkedList<>();
        queue.add(new Pair<>(0, 0));
        vis[0][0] = true;
        Pair<Integer, Integer> now;
        int x, y;
        while (!queue.isEmpty()) {
            count++;
            now = queue.poll();
            x = now.getKey();
            y = now.getValue();
            if (y + 1 < n) {
                if (!vis[x][y + 1]) {
                    if (rows[x] + cols[y + 1] <= k) {
                        vis[x][y + 1] = true;
                        queue.add(new Pair<>(x, y + 1));
                    }
                }
            }
            if (x + 1 < m) {
                if (!vis[x + 1][y]) {
                    if (rows[x + 1] + cols[y] <= k) {
                        vis[x + 1][y] = true;
                        queue.add(new Pair<>(x + 1, y));
                    }
                }
            }
        }
        return count;
    }

    public int search(int[] nums, int target) {
        return searchCore(nums, 0, nums.length, target);
    }

    public int searchCore(int[] nums, int s, int e, int target) {
        if (s == e) return -1;
        int mid = s + (e - s) / 2;
        if (nums[mid] == target) {
            return mid;
        } else if (nums[mid] > target) {
            if (nums[s] < nums[mid] && nums[s] > target) {
                return searchCore(nums, mid + 1, e, target);
            } else {
                return searchCore(nums, s, mid, target);
            }
        } else {
            if (nums[s] > nums[mid] && nums[e - 1] < target) {
                return searchCore(nums, s, mid, target);
            } else {
                return searchCore(nums, mid + 1, e, target);
            }
        }
    }

    public int minJumps(int[] arr) {
        int n = arr.length;
        int[] dis = new int[n];
        Arrays.fill(dis, Integer.MAX_VALUE);
        Map<Integer, List<Integer>> m = new HashMap<>();
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (!m.containsKey(arr[i])) {
                m.put(arr[i], new LinkedList<>());
            }
            m.get(arr[i]).add(i);
        }
        queue.add(n - 1);
        dis[n - 1] = 0;
        int next, now;
        while (!queue.isEmpty()) {
            now = queue.poll();
            next = now + 1;
            if (next >= 0 && next < n && dis[next] == Integer.MAX_VALUE) {
//                dis[next] = Math.min(dis[next],dis[now] + 1);
                dis[next] = dis[now] + 1;
                queue.add(next);
            }
            next = now - 1;
            if (next >= 0 && next < n && dis[next] == Integer.MAX_VALUE) {
//                dis[next] = Math.min(dis[next],dis[now] + 1);
                dis[next] = dis[now] + 1;
                queue.add(next);
            }
            if (m.containsKey(arr[now])) {
                for (int it : m.get(arr[now])) {
                    if (dis[it] == Integer.MAX_VALUE) {
                        dis[it] = dis[now] + 1;
                        queue.add(it);
                    }
                }
                m.remove(now);
            }
        }
        return dis[0];
    }

    public void testMap() {
        String msg = "xxx";
        Map<String, String> map = new HashMap<>();
        map.put("a", "A");
        map.put("b", "B");
        String val = map.compute("b", (k, v) -> (v == null) ? msg : v.concat(msg)); // 输出 v
        System.out.println(val);
        String v1 = map.compute("c", (k, v) -> (v == null) ? msg : v.concat(msg)); // 输出 v
        System.out.println(v1);
        Map<Integer, List<Integer>> mm = new HashMap<>();

    }

    public int maxJumps(int[] arr, int d) {
        this.arr = arr;
        n = arr.length;
        dp = new int[n];
        this.d = d;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, maxJumpsCore(i));
        }
        return ans;
    }

    public int maxJumpsCore(int i) {
        if (dp[i] != 0) {
            return dp[i];
        }
        int next;
        dp[i] = 1;
        for (int k = -1; k < 2; k += 2) {
            for (int j = 1; j <= d; j++) {
                next = i + k * j;
                if (next >= 0 && next < n) {
                    if (arr[i] > arr[next]) {
                        dp[next] = maxJumpsCore(next);
                        dp[i] = Math.max(dp[i], dp[next] + 1);
                    } else {
                        break;
                    }
                }

            }
        }
        return dp[i];
    }

    public void moveZeroes(int[] nums) {
        int now = 0;
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            if (nums[i] != 0) {
                int temp = nums[now];
                nums[now] = nums[i];
                nums[i] = temp;
                now++;
            }
        }
    }

    public int pathSum(TreeNode root, int sum) {
        this.target = sum;
        currSumMap.put(0, 1);
        return pathSumCore(root, 0);
    }

    public int pathSumCore(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        int ans = 0;
        sum += root.val;

        ans += currSumMap.getOrDefault(sum - target, 0);
        currSumMap.compute(sum, (k, v) -> v == null ? 1 : v + 1);
        ans += pathSumCore(root.left, sum);
        ans += pathSumCore(root.right, sum);
        currSumMap.put(sum, currSumMap.get(sum) - 1);
//        currSumMap.compute(sum, (k,v) -> v == null ? 0 : v-1);
        return ans;
    }

    public TreeNode convertBST(TreeNode root) {
        getRightSum(root, 0);
        return root;

    }

    public int getRightSum(TreeNode root, int parentSum) {
        if (root == null) {
            return 0;
        }
        int sum = 0;
        sum += getRightSum(root.right, parentSum);
        sum += root.val;
        root.val = sum + parentSum;
        sum += getRightSum(root.left, parentSum + sum);
        return sum;
    }

    public int diameterOfBinaryTree(TreeNode root) {
        diameterOfBinaryTreeCore(root);
        return maxDiameter - 1;
    }

    public int diameterOfBinaryTreeCore(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = diameterOfBinaryTreeCore(root.left);
        int right = diameterOfBinaryTreeCore(root.right);
        int ans = left + right + 1;
        maxDiameter = Math.max(maxDiameter, ans);
        return Math.max(left, right) + 1;
    }

    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null) {
            return t2;
        }
        if (t2 == null) {
            return t1;
        }
        t1.val += t2.val;
        t1.left = mergeTrees(t1.left, t2.left);
        t1.right = mergeTrees(t1.right, t2.right);
        return t1;
    }


}
