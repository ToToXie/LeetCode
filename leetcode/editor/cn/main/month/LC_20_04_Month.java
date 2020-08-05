package month;

import javafx.util.Pair;
import util.TreeNode;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @program: LeetCode
 * @description:
 * @author: wd
 * @create: 2020-05-08 09:52
 **/

public class LC_20_04_Month {
    private static int[] nums = {2, 2, 2, 1, 2, 2, 1, 2, 2, 2};
    private static int[] num2 = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
    private static int[][] num1 = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
    private static int[][] matrix = {{0, 0, 0}, {0, 1, 0}, {1, 1, 1}};
    List<List<Integer>> ans = new ArrayList<>();
    int[] nextIndex = {-1, 0, 1, 0, 0, -1, 0, 1};
    int X;
    int Y;
    boolean[][] vis;
    private List<Integer> ansList = new ArrayList<>();

    public static void main(String[] args) {
        LC_20_04_Month lc = new LC_20_04_Month();
//        util.RunTest<Object> runTest = new util.RunTest<>();
////        List<List<Integer>> permute = java.lang.lc.permute(nums);
////        int[][] merge = java.lang.lc.merge(num1);
////        System.out.println(permute.toString());
//        System.out.println("[");
//        month.Twitter twitter = new month.Twitter();
//        runTest.run(twitter);
//        int i = java.lang.lc.trap(num2);
//        System.out.println(i);

    }

    /**
     * 数学推导，等差数列求和
     **/
    static int waysToChangeC(int n) {
        int ans = 0;
        int mod = 1000000007;
        for (int i = 0; i * 25 <= n; ++i) {
            int rest = n - i * 25;
            int a = rest / 10;
            int b = rest % 10 / 5;
            ans = (ans + (a + 1) * (a + b + 1) % mod) % mod;
        }
        return ans;
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
            swap(nums, i, index);
            permuteCore(nums, index + 1);
            swap(nums, i, index);
        }
    }

    private void swap(int[] nums, int a, int b) {
        if (a != b) {
            nums[a] = nums[a] ^ nums[b];
            nums[b] = nums[a] ^ nums[b];
            nums[a] = nums[a] ^ nums[b];
        }
    }

    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length < 1) return intervals;
        List<int[]> collect = Arrays.stream(intervals).sorted((a, b) -> {
            if (a[0] == b[0]) {
                return a[1] - b[1];
            } else {
                return a[0] - b[0];
            }
        }).collect(Collectors.toList());
        int start = collect.get(0)[0], end = collect.get(0)[1];
        int[] cur;
        List<int[]> ans = new ArrayList<>(collect.size());
        for (int i = 1; i < collect.size(); i++) {
            cur = collect.get(i);
            if (end < cur[0]) {
                int[] one = {start, end};
                ans.add(one);
                start = cur[0];
                end = cur[1];
            } else {
                end = Math.max(end, cur[1]);
            }
        }
        int[] one = {start, end};
        ans.add(one);
        return ans.toArray(new int[ans.size()][2]);
    }

    public List<Integer> rightSideView(TreeNode root) {
        rightSideViewDFS(root, 1);
        return ansList;
    }

    private void rightSideViewDFS(TreeNode root, int level) {
        if (root == null) {
            return;
        }
        if (ansList.size() < level) {
            ansList.add(root.val);
        }
        if (ansList.size() <= level) rightSideViewDFS(root.right, level + 1);
        if (ansList.size() <= level) rightSideViewDFS(root.left, level + 1);
    }

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length < 1 || grid[0].length < 1) return 0;
        int count = 0;
        int n = grid.length;
        int m = grid[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == '1') {
                    numIslandsDFS(grid, i, j, n, m);
                    count++;
                }
            }
        }
        return count;
    }

    private void numIslandsDFS(char[][] grid, int x, int y, int n, int m) {
        grid[x][y] = '0';
        int newx, newy;
        for (int i = 0; i < 8; i += 2) {
            newx = x + nextIndex[i];
            newy = y + nextIndex[i + 1];
            if (newx < n && newx >= 0 && newy >= 0 && newy < m) {
                if (grid[newx][newy] == '1') {
                    numIslandsDFS(grid, newx, newy, n, m);
                }
            }
        }
    }

    public int[][] updateMatrix(int[][] matrix) {
        if (matrix == null || matrix.length < 1 || matrix[0].length < 1) return matrix;
        X = matrix.length;
        Y = matrix[0].length;
        vis = new boolean[X][Y];
        int[][] ans = new int[X][Y];
        Queue<Pair<Integer, Integer>> queue = new LinkedList<>();
        for (int i = 0; i < X; i++) {
            for (int j = 0; j < Y; j++) {
                if (matrix[i][j] == 0) {
                    queue.add(new Pair<>(i, j));
                    vis[i][j] = true;

                }
            }
        }
        int newx = -1, newy = -1, x, y;
        while (!queue.isEmpty()) {
            Pair<Integer, Integer> cur = queue.poll();
            x = cur.getKey();
            y = cur.getValue();
            for (int i = 0; i < 8; i += 2) {
                newx = x + nextIndex[i];
                newy = y + nextIndex[i + 1];
                if (newx < X && newx >= 0 && newy >= 0 && newy < Y && !vis[newx][newy]) {
                    ans[newx][newy] = ans[x][y] + 1;
                    vis[newx][newy] = true;
                    queue.add(new Pair<>(newx, newy));
                }
            }

        }
        return ans;
    }

    public int[] maxDepthAfterSplit(String seq) {
        int[] ans = new int[seq.length()];
        int count = 0, index = 0;

        for (Character c : seq.toCharArray()) {
            if (c == '(') {
                count++;
            } else count--;
            ans[index++] = count % 2;
        }
        return ans;
    }

    //最优美子数组
    public int numberOfSubarrays(int[] nums, int k) {
        if (k < 0 || nums == null) return 0;
        int length = nums.length;
        if (k > length) return 0;
        int[] dp = new int[length + 1];
        dp[0] = 1;
        int count = 0;
        for (int i = 0; i < length; i++) {
            if ((nums[i] & 1) == 1) {
                dp[++count]++;
            } else {
                dp[count]++;
            }
        }
        int ans = 0;
        for (int i = 0; i < length - k + 1; i++) {
            if (dp[i] != 0 && dp[k + i] != 0) {
                ans += (dp[i] * dp[k + i]);
            }
        }
        return ans;
    }
    //硬币

    //旋转矩阵 90度
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < (n + 1) / 2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - j - 1][i];
                matrix[n - j - 1][i] = matrix[n - i - 1][n - j - 1];
                matrix[n - i - 1][n - j - 1] = matrix[j][n - i - 1];
                matrix[j][n - i - 1] = temp;
            }
        }
    }

    /**
     * 动态规划
     **/
    public int waysToChange(int n) {
        int[] nums = {1, 5, 10, 25};
        int mod = 1000000007;
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 3; i >= 0; i--) {
            for (int j = nums[i]; j <= n; j++) {
                dp[j] = (dp[j] + dp[j - nums[i]]) % mod;
            }
        }
        return dp[n];
    }

    /**
     * 数学方法，不等式,枚举所有可能性
     **/
    public int waysToChangeB(int n) {
        int[] nums = {1, 5, 10, 25};
        int mod = 1000000007;
        int sum = 0;
        for (int i = 0; i <= n / 25; i++) {
            for (int j = 0; j <= (n - i * 25) / 10; j++) {
                /*for (int k = 0; k <= (n - i * 25 - 10 * j) / 5; k++) {
                    sum++;
                }*/
                sum += ((n - i * 25 - 10 * j) / 5) + 1;
            }
            sum %= mod;
        }
        return sum;
    }

    /**
     * 数组中两个只出现一次的数字
     * <p>
     * 关键  x = a ^ b
     * x 的二进制中的某一位 为 1，这表示 a，b 二进制的这一位 不一样
     * 利用这一点，进行分组
     **/
    public int[] singleNumbers(int[] nums) {
        int temp = 0, a = 0, b = 0;
        for (int it : nums) {
            temp ^= it;
        }
        int mask = 1;
        while ((mask & temp) == 0) mask <<= 1;
        for (int it : nums) {
            if ((it & mask) == 0) {
                a ^= it;
            } else {
                b ^= it;
            }
        }
        int[] ans = new int[2];
        ans[0] = a;
        ans[1] = b;
        return ans;
    }

    /**
     * 快乐数
     **/
    public boolean isHappy(int n) {
        int[] nums = new int[10];
        Set<Integer> vis = new HashSet<>();
        int sum, a;
        vis.add(n);
        while (n != 1) {
            sum = 0;
            while (n != 0) {
                a = n % 10;
                if (a != 0) {
                    if (nums[a] == 0) {
                        nums[a] = a * a;
                    }
                    sum += nums[a];
                }
                n /= 10;
            }
            if (vis.contains(sum)) {
                return false;
            } else {
                vis.add(sum);
            }
            n = sum;
        }
        return true;
    }

    /**
     * 接雨水
     **/
    public int trap(int[] height) {
        int n = height.length;
        int sum = 0;
        int left = 0, right = n - 1, leftHeight = 0, rightHeight = 0;
        while (left < right) {
            leftHeight = height[left];
            rightHeight = height[right];
            if (leftHeight < rightHeight) {
                left++;
                while (left < right && height[left] < leftHeight) {
                    sum += leftHeight - height[left];
                    left++;
                }
            } else {
                right--;
                while (left < right && height[right] < rightHeight) {
                    sum += rightHeight - height[right];
                    right--;
                }
            }

        }
        return sum;
    }


}

class Twitter {
    //默认用户id和推文id是全局唯一的，但是不保证推文id是按时间顺序递增

    Map<Integer, Integer> timeMap;
    Map<Integer, Queue<Integer>> userTwiMap;
    Map<Integer, Set<Integer>> followerMap;
    private Integer timeId;

    /**
     * Initialize your data structure here.
     */
    public Twitter() {
        timeId = 1;
        timeMap = new HashMap<>();
        userTwiMap = new HashMap<>();
        followerMap = new HashMap<>();
    }

    /**
     * Compose a new tweet.
     */
    public void postTweet(int userId, int tweetId) {
        if (!userTwiMap.containsKey(userId)) {
            PriorityQueue<Integer> queue = new PriorityQueue<>(10, (o1, o2) -> {
                return timeMap.get(o2) - timeMap.get(o1);
            });
            userTwiMap.putIfAbsent(userId, queue);
        }
        timeMap.putIfAbsent(tweetId, timeId);
        timeId++;
        userTwiMap.get(userId).add(tweetId);
    }

    /**
     * Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent.
     */
    public List<Integer> getNewsFeed(int userId) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(10, (o1, o2) -> {
            return timeMap.get(o2) - timeMap.get(o1);
        });
        if (followerMap.containsKey(userId)) {
            for (Integer it : followerMap.get(userId)) {
                if (userTwiMap.containsKey(it)) {
                    userTwiMap.get(it).stream().forEach(a -> queue.add(a));
                }
            }
        }
        if (userTwiMap.containsKey(userId)) {
            userTwiMap.get(userId).stream().forEach(a -> queue.add(a));
        }
        List<Integer> ans = new ArrayList<>(10);
        for (int i = 0; i < 10; i++) {
            if (!queue.isEmpty()) ans.add(queue.poll());
            else break;
        }
        return ans;
    }

    /**
     * Follower follows a followee. If the operation is invalid, it should be a no-op.
     */
    public void follow(int followerId, int followeeId) {
        if (followerId != followeeId) {
            if (!followerMap.containsKey(followerId)) {
                Set<Integer> follwer = new HashSet<>();
                followerMap.putIfAbsent(followerId, follwer);
            }
            followerMap.get(followerId).add(followeeId);
        }

    }

    /**
     * Follower unfollows a followee. If the operation is invalid, it should be a no-op.
     */
    public void unfollow(int followerId, int followeeId) {
        if (followerMap.containsKey(followerId)) {
            followerMap.get(followerId).remove(followeeId);
        }
    }
}

