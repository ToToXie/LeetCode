package tags;

import util.TreeNode;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @program: LeetCode
 * @description: 二分查找
 * @author: wd
 * @create: 2020-05-29 14:43
 **/

public class BinarySearch {
    Map<Integer, Integer> map = new HashMap<>();

    public static void main(String[] args) {
        BinarySearch binarySearch = new BinarySearch();
        int[][] intss = {{1, 5, 9}, {10, 11, 13}, {12, 13, 15}};
        int[] ints = {488, 247, 151, 268, 358, 270, 366, 2, 85, 49, 209, 37, 353, 17, 287, 385, 421, 467, 32, 201, 398, 27, 108, 291, 435, 447};
        int[] ints2 = {2, 5, 6, 0, 1};
        Solution solution = binarySearch.new Solution(ints);
        System.out.println(

//                Arrays.toString(binarySearch.findClosestElements(ints,4,3))
//                Arrays.deepToString(binarySearch.findRightInterval(intss))
                binarySearch.shipWithinDays(ints, 26)

//                Integer.MAX_VALUE + " "+
//                binarySearch.superEggDrop(3, 14)

        );
//        while (true) {
//            System.out.println(solution.pickIndex());
//        }
    }

    /**
     * 在 d 天 内送达包裹的能力
     **/
    public int shipWithinDays(int[] weights, int D) {
        if (weights == null || weights.length < 1) return 0;

        int length = weights.length;
        int[] nums = new int[length];
        for (int i = 0; i < length; i++) {
            nums[i] = weights[i] + (i == 0 ? 0 : nums[i - 1]);
        }
        if (D == nums.length) return Arrays.stream(weights).max().getAsInt();
        int left = nums[length - 1] / D;
        int right = nums[length - 1];
        int mid;
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (cheak(nums, mid, D)) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private boolean cheak(int[] nums, int w, int d) {
        int cnt = 0, sum = 0;
        while (cnt < d) {
            cnt++;
            int i = Arrays.binarySearch(nums, w + sum);
            if (i == nums.length - 1) return true;
            if (i < 0) {
                i = -i - 1;
                if (i == 0) return false;
                if (i > nums.length - 1) {
                    return true;
                } else {
                    sum = nums[i - 1];
                }
            } else {
                if (i == 0) return false;
                if (i == nums.length - 1) {
                    return true;
                } else {
                    sum = nums[i];
                }
            }
        }
        return false;
    }

    /**
     * 鸡蛋掉落
     **/
    public int superEggDrop(int K, int N) {
        return dp(K, N);
    }

    private int dp(int k, int n) {
        if (!map.containsKey(n * 100 + k)) {
            int ans = 0;
            if (k == 1) {
                ans = n;
            } else if (n == 0) {
                ans = 0;
            } else {
                int left = 1, right = n, mid;
                while (left < right) {
                    mid = left + ((right - left) >> 1);
                    int t1 = dp(k - 1, mid - 1);
                    int t2 = dp(k, n - mid);
                    if (t1 < t2) {
                        left = mid + 1;
                    } else if (t1 > t2) {
                        right = mid - 1;
                    } else {
                        left = right = mid;
                    }
                }
                ans = 1 + Math.min(Math.max(dp(k - 1, left - 1), dp(k, n - left)),
                        Math.max(dp(k - 1, right - 1), dp(k, n - right)));
            }
            map.put(k + n * 100, ans);
        }
        return map.get(k + n * 100);
    }

    /**
     * 爱吃香蕉的珂珂
     **/
    public int minEatingSpeed(int[] piles, int H) {
        int max = Arrays.stream(piles).max().getAsInt();
        if (piles.length == H) return max;
        int left = 1, right = max, mid;

        while (left <= right) {
            mid = left + ((right - left) >>> 1);
            if (check(piles, mid, H)) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private boolean check(int[] nums, int x, int h) {
        int sum = 0;
        for (int num : nums) {
            sum += (num - 1) / x + 1;
            if (sum > h) return false;
        }
        return true;
    }

    /**
     * 最长重复的子数组
     **/
    public int findLength(int[] A, int[] B) {
        int[][] dp = new int[A.length + 1][B.length + 1];
        int ans = 0;
        for (int i = A.length - 1; i >= 0; i--) {
            for (int j = B.length - 1; j >= 0; j--) {
                if (A[i] == B[j]) {
                    dp[i][j] = dp[i + 1][j + 1] + 1;
                    if (dp[i][j] > ans) ans = dp[i][j];
                }
            }
        }
        return ans;
    }

    /**
     * 找到 K 个最接近的元素
     **/
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> collect = Arrays.stream(arr).boxed().collect(Collectors.toList());
        int n = collect.size();
        if (x <= collect.get(0)) {
            return collect.subList(0, k);
        }
        if (x >= collect.get(n - 1)) {
            return collect.subList(n - k, n);
        }
        int index = Collections.binarySearch(collect, x);
        if (index < 0) {
            index = -index - 1;
        }
        if (Math.abs(arr[index - 1] - x) <= arr[index] - x) {
            index--;
        }
        int left = index - 1;
        int right = index + 1;
        while (right - left - 1 < k) {
            if (left < 0) {
                right++;
            } else if (right >= n) {
                left--;
            } else if (x - arr[left] <= arr[right] - x) {
                left--;
            } else if (x - arr[left] > arr[right] - x) {
                right++;
            }
        }
        return collect.subList(left + 1, right);
    }

    /**
     * 四数相加2
     **/
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        Map<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        for (int i : A) {
            for (int i1 : B) {
                sum = i + i1;
                map.put(-sum, map.getOrDefault(-sum, 0) + 1);
            }
        }
        int ans = 0;
        for (int i : C) {
            for (int i1 : D) {
                sum = i + i1;
                ans += map.getOrDefault(sum, 0);
            }
        }
        return ans;
    }

    /**
     * 错误答案，只有两个有序数组 找 第k小的数这部分写对了，有点价值
     **/
    public int kthSmallest(int[][] matrix, int k) {
        int index = 1;
        while (k > index * index) index++;
        if (k == index * index) return matrix[index - 1][index - 1];
        k -= (index - 1) * (index - 1);
        int left = 0, up = 0, newLeft, newUp;
        while (up < index && left < index) {
            if (k == 1) return Math.min(matrix[index - 1][left], matrix[up][index - 1]);
            int half = k / 2;
            newLeft = Math.min(left + half, index) - 1;
            newUp = Math.min(up + half, index) - 1;
            if (matrix[index - 1][newLeft] <= matrix[newUp][index - 1]) {
                k -= (newLeft - left + 1);
                left = newLeft + 1;
            } else {
                k -= (newUp - up + 1);
                up = newUp + 1;
            }
        }
        return Math.min(matrix[index - 1][left], matrix[up][index - 1]);
    }

    public int kthSmallest2(int[][] matrix, int k) {
        int length = matrix.length;
        int left = matrix[0][0], right = matrix[length - 1][length - 1], mid;
        while (left <= right) {
            mid = left + (right - left) / 2;
            int kSmaller = findKSmaller(matrix, mid);
            if (kSmaller >= k) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }

        }
        return left;
    }

    /**
     * 有序矩阵中d第 k 小的元素
     * <p>
     * 两个有序数组 找第k小的数
     * <p>
     * /* 主要思路：要找到第 k (k>1) 小的元素，那么就取 pivot1 = nums1[k/2-1] 和 pivot2 = nums2[k/2-1] 进行比较
     * 这里的 "/" 表示整除
     * nums1 中小于等于 pivot1 的元素有 nums1[0 .. k/2-2] 共计 k/2-1 个
     * nums2 中小于等于 pivot2 的元素有 nums2[0 .. k/2-2] 共计 k/2-1 个
     * 取 pivot = min(pivot1, pivot2)，两个数组中小于等于 pivot 的元素共计不会超过 (k/2-1) + (k/2-1) <= k-2 个
     * 这样 pivot 本身最大也只能是第 k-1 小的元素
     * 如果 pivot = pivot1，那么 nums1[0 .. k/2-1] 都不可能是第 k 小的元素。把这些元素全部 "删除"，剩下的作为新的 nums1 数组
     * 如果 pivot = pivot2，那么 nums2[0 .. k/2-1] 都不可能是第 k 小的元素。把这些元素全部 "删除"，剩下的作为新的 nums2 数组
     * 由于我们 "删除" 了一些元素（这些元素都比第 k 小的元素要小），因此需要修改 k 的值，减去删除的数的个数
     */

    private int findKSmaller(int[][] matrix, int target) {
        int length = matrix.length, count = 0;
        int x = 0, y = length - 1;
        while (x < length && y >= 0) {
            if (matrix[y][x] <= target) {
                count += (y + 1);
                x++;
            } else {
                y--;
            }
        }
        return count;
    }

    /**
     * 寻找右区间
     **/
    public int[] findRightInterval(int[][] intervals) {
        if (intervals == null || intervals.length < 1) return new int[0];
        int length = intervals.length;
        int[] index = new int[length];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < length; i++) {
            map.put(intervals[i][0], i);
        }
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        int[] ans = new int[length];
        tag:
        for (int i = 0; i < length; i++) {

            int left = 0, right = length - 1, mid, target = intervals[i][1];

            while (left <= right) {
                mid = left + (right - left) / 2;
                if (intervals[mid][0] > target) {
                    right = mid - 1;
                } else if (intervals[mid][0] < target) {
                    left = mid + 1;
                } else if (intervals[mid][0] == target) {
                    ans[map.get(intervals[i][0])] = map.get(intervals[mid][0]);
                    continue tag;
                }
            }
            if (left == length) ans[map.get(intervals[i][0])] = -1;
            else ans[map.get(intervals[i][0])] = map.get(intervals[left][0]);
        }
        return ans;
    }

    /**
     * H指数
     **/
    public int hIndex(int[] citations) {
        if (citations == null || citations.length < 1) return 0;
        int left = 0, right = citations.length - 1, mid;
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (citations[mid] == (citations.length - mid)) {
                return citations.length - mid;
            } else if (citations[mid] < (citations.length - mid)) {
                left = mid + 1;
            } else if (citations[mid] > (citations.length - mid)) {
                right = mid - 1;
            }
        }
        return citations.length - left;
    }

    /**
     * 完全二叉树的节点个数
     **/
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = countLevel(root.left);
        int right = countLevel(root.right);
        if (left == right) {
            return countNodes(root.right) + (1 << left);
        } else {
            return countNodes(root.left) + (1 << right);
        }
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
     * 长度最小的子数组
     **/
    public int minSubArrayLen(int s, int[] nums) {
        if (nums == null || nums.length < 1) {
            return 0;
        }
        int left = 0, right = nums.length - 1, sum = 0, ans = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            while (sum >= s) {
                ans = Math.min(ans, i - left + 1);
                sum -= nums[left++];
            }
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }

    /**
     * 寻找峰值
     **/
    public int findPeakElement(int[] nums) {
        if (nums == null || nums.length < 1) return 0;
        int left = 0, right = nums.length - 1, mid;
        while (left < right) {
            mid = left + (right - left) / 2;
            if (mid == 0) {
                if (nums[mid] > nums[mid + 1]) return mid;
                else {
                    left++;
                    continue;
                }
            }
            if (mid == nums.length - 1) {
                if (nums[mid] > nums[mid - 1]) return mid;
                else {
                    right--;
                    continue;
                }
            }
            if (nums[mid] > nums[mid + 1]) {
                if (nums[mid] > nums[mid - 1]) {
                    return mid;
                } else if (nums[mid] < nums[mid - 1]) {
                    right = mid - 1;
                }
            } else if (nums[mid] < nums[mid + 1]) {
                left = mid + 1;
            }
        }
        return left;
    }

    /**
     * 旋转数组 找最小元素
     **/
    public int findMin(int[] nums) {
        if (nums == null || nums.length < 1) return 0;
        int left = 0, right = nums.length - 1, mid;
        while (left < right) {
            mid = left + (right - left) / 2;
            if (nums[mid] < nums[right]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return nums[left];
    }

    /**
     * 搜旋转排序数组2
     **/
    public boolean search(int[] nums, int target) {
        if (nums == null || nums.length < 1) return false;
        int left = 0, right = nums.length - 1, mid;
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return true;
            }
            if (nums[left] == nums[mid]) {
                left++;
                continue;
            }
            if (nums[mid] < target) {
                if (nums[mid] < nums[left]) {
                    if (nums[right] < target) {
                        right = mid - 1;
                    } else if (nums[right] > target) {
                        left = mid + 1;
                    }
                } else if (nums[mid] > nums[left]) {
                    left = mid + 1;
                }
            } else if (nums[mid] > target) {
                if (nums[mid] > nums[left]) {
                    if (nums[left] < target) {
                        right = mid - 1;
                    } else if (nums[left] > target) {
                        left = mid + 1;
                    }
                } else if (nums[mid] < nums[left]) {
                    right = mid - 1;
                }
            }
        }
        return false;
    }

    /**
     * 搜索二维矩阵
     **/
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length < 1 || matrix[0].length < 1) return false;
        int up = 0, down = matrix.length - 1, mid;
        while (up <= down) {
            mid = up + (down - up) / 2;
            if (matrix[mid][0] == target) return true;
            else if (matrix[mid][0] < target) {
                up = mid + 1;
            } else if (matrix[mid][0] > target) {
                down = mid - 1;
            }
        }
        if (up != 0) up--;
        int left = 0, right = matrix[0].length - 1;
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (matrix[up][mid] == target) return true;
            else if (matrix[up][mid] < target) {
                left = mid + 1;
            } else if (matrix[up][mid] > target) {
                right = mid - 1;
            }
        }
        return false;
    }

    /**
     * 两数相除
     **/
    public int divide(int dividend, int divisor) {
        if (divisor == 1) {
            return dividend;
        } else if (divisor == -1) {
            if (dividend == Integer.MIN_VALUE) return Integer.MAX_VALUE;
            return -dividend;
        }
        long divi;
        long divr;
        boolean isNegative = true;
        if (dividend < 0) {
            divi = dividend;
            if (divisor < 0) {
                isNegative = false;
                divr = divisor;
            } else {
                isNegative = true;
                divr = -divisor;
            }
        } else {
            divi = -dividend;
            if (divisor < 0) {
                isNegative = true;
                divr = divisor;
            } else {
                isNegative = false;
                divr = -divisor;
            }
        }

        int ans = 0, divisorSrc = (int) divr;
        long temp = 1;
        while (divi <= divisorSrc) {
            divr += divr;
            if (divi < divr) {
                temp += temp;
            } else {
                divi -= (divr >> 1);
                ans += temp;
                divr = divisorSrc;
                temp = 1;
            }

        }
        return isNegative ? -ans : ans;
    }

    /**
     * 山峰数组的峰顶索引
     **/
    public int peakIndexInMountainArray(int[] A) {
        int left = 0, right = A.length - 1, mid = 0;
        if (right < 2) return -1;
        while (left < right) {
            mid = left + ((right - left) >> 1);
            if (A[mid] > A[mid - 1]) {
                if (A[mid] > A[mid + 1]) {
                    return mid;
                } else {
                    left = mid;
                }
            } else {
                right = mid;
            }
        }
        return left;
    }

    /**
     * 寻找比目标字母大的最小字母
     **/
    public char nextGreatestLetter(char[] letters, char target) {
        return 'z';
    }

    /**
     *  找出给定方程的正整数解
     **/
//    public List<List<Integer>> findSolution(CustomFunction customfunction, int z) {
//        Integer x = 1,y = 1000;
//        List<List<Integer>> ans = new ArrayList<>();
//        while (x <= 1000 && 0 < y){
//            int ret = customfunction.f(x,y);
//            if(ret == z){
//                ans.add(Arrays.asList(x,y));
//                x++;
//                y--;
//            }else if(ret < z){
//                x++;
//            }else {
//                y--;
//            }
//        }
//        return ans;
//    }

    /**
     * 供暖器
     **/
    public int findRadius(int[] houses, int[] heaters) {
        return 0;
    }

    /**
     * 排列硬币
     **/
    public int arrangeCoins(int n) {
        long temp = 0;
        int left = 1, right = n, mid = 0;
        while (left < right) {
            mid = left + (right - left) / 2;
            temp = (long) mid * (mid + 1) / 2;
            if (temp == n) return mid;
            else if (temp < n) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left - 1;
    }

    /**
     * 判断子序列
     **/
    public boolean isSubsequence(String s, String t) {
        if (s == null) return true;
        int i = 0, j = 0;
        while (i < s.length() && j < t.length()) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
            }
            j++;
        }
        return i == s.length();
    }

    /**
     * 猜数字大小
     **/
    public int guessNumber(int n) {
        int left = 1, right = n, mid;
        while (left < right) {
            mid = left + (right - left) / 2;
            int guess = guess(mid);
            if (guess == 0) {
                return mid;
            } else if (guess > 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    private int guess(int num) {
        return 1;
    }

    class TimeMap {
        Map<String, List<Node>> map;

        /**
         * Initialize your data structure here.
         */
        public TimeMap() {
            map = new HashMap<>();
        }

        public void set(String key, String value, int timestamp) {
            Node e = new Node(value, timestamp);
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(e);
        }

        public String get(String key, int timestamp) {
            if (!map.containsKey(key)) {
                return "";
            } else {
                List<Node> list = map.get(key);
                Node now = new Node("xxx", timestamp);
                int index = Collections.binarySearch(list, now, (a, b) -> ((Node) a).getTime() - ((Node) b).getTime());
                if (index < 0) {
                    index = -index - 1;
                    if (index == 0) return "";
                    else return list.get(index - 1).getValue();
                } else {
                    return list.get(index).getValue();
                }
            }
        }

        class Node {
            String value;
            int time;

            public Node(String value, int time) {
                this.value = value;
                this.time = time;
            }

            public String getValue() {
                return value;
            }

            public int getTime() {
                return time;
            }
//
//            @Override
//            public int compareTo(Object o) {
//                return time - ((Node)o).getTime();
//            }
        }
    }

    class Solution {
        int[] indexs;
        int max = 0;
        Random random = new Random();

        public Solution(int[] w) {
            indexs = new int[w.length];
            for (int i = 0; i < w.length; i++) {
                max += w[i];
                indexs[i] = max;
            }
        }

        public int pickIndex() {
//            int x = random.nextInt(max) + 1;
//            int left = 0, right = indexs.length - 1, mid;
//            while (left <= right) {
//                mid = left + ((right - left) >>> 1);
//                if (x < indexs[mid]) {
//                    right = mid - 1;
//                } else if (x > indexs[mid]){
//                    left = mid + 1;
//                }else {
//                    return mid;
//                }
//            }
//            return left;
            int x = random.nextInt(max) + 1;
            int index = Arrays.binarySearch(indexs, x);
            if (index < 0) index = -index - 1;
            return index;
        }
    }
}
