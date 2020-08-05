package month;

import java.util.*;

/**
 * @program: LeetCode
 * @description:
 * @author: wd
 * @create: 2020-06-05 10:47
 **/

public class LC_20_06_Month {
    /**
     * 单词接龙2
     **/
    private static final int INF = 1 << 20;
    private Map<String, Integer> wordId = new HashMap<>(); // 单词到id的映射
    private ArrayList<String> idWord = new ArrayList<>(); // id到单词的映射
    private ArrayList<Integer>[] edges; // 图的边

    public static void main(String[] args) {

//        System.out.println(Character.MAX_VALUE);
//        System.out.println(Character.MIN_VALUE);
//        System.out.println(Character.MAX_VALUE - Character.MIN_VALUE);
//        System.out.println(Math.pow(2, 16) - 1);
        LC_20_06_Month lc_20_06_month = new LC_20_06_Month();
//        System.out.println(lc_20_06_month.combinationSum2(new int[]{10,1,1,2,7,6,1,5},8));
    }

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        int id = 0;
        // 将wordList所有单词加入wordId中 相同的只保留一个 // 并为每一个单词分配一个id
        for (String word : wordList) {
            if (!wordId.containsKey(word)) {
                wordId.put(word, id++);
                idWord.add(word);
            }
        }
        // 若endWord不在wordList中 则无解
        if (!wordId.containsKey(endWord)) {
            return new ArrayList<>();
        }
        // 把beginWord也加入wordId中
        if (!wordId.containsKey(beginWord)) {
            wordId.put(beginWord, id++);
            idWord.add(beginWord);
        }

        // 初始化存边用的数组
        edges = new ArrayList[idWord.size()];
        for (int i = 0; i < idWord.size(); i++) {
            edges[i] = new ArrayList<>();
        }
        // 添加边
        for (int i = 0; i < idWord.size(); i++) {
            for (int j = i + 1; j < idWord.size(); j++) {
                // 若两者可以通过转换得到 则在它们间建一条无向边
                if (transformCheck(idWord.get(i), idWord.get(j))) {
                    edges[i].add(j);
                    edges[j].add(i);
                }
            }
        }

        int dest = wordId.get(endWord); // 目的ID
        List<List<String>> res = new ArrayList<>(); // 存答案
        int[] cost = new int[id]; // 到每个点的代价
        for (int i = 0; i < id; i++) {
            cost[i] = INF; // 每个点的代价初始化为无穷大
        }

        // 将起点加入队列 并将其cost设为0
        Queue<ArrayList<Integer>> q = new LinkedList<>();
        ArrayList<Integer> tmpBegin = new ArrayList<>();
        tmpBegin.add(wordId.get(beginWord));
        q.add(tmpBegin);
        cost[wordId.get(beginWord)] = 0;

        // 开始广度优先搜索
        while (!q.isEmpty()) {
            ArrayList<Integer> now = q.poll();
            int last = now.get(now.size() - 1); // 最近访问的点
            if (last == dest) { // 若该点为终点则将其存入答案res中
                ArrayList<String> tmp = new ArrayList<>();
                for (int index : now) {
                    tmp.add(idWord.get(index)); // 转换为对应的word
                }
                res.add(tmp);
            } else { // 该点不为终点 继续搜索
                for (int i = 0; i < edges[last].size(); i++) {
                    int to = edges[last].get(i);
                    // 此处<=目的在于把代价相同的不同路径全部保留下来
                    if (cost[last] + 1 <= cost[to]) {
                        cost[to] = cost[last] + 1;
                        // 把to加入路径中
                        ArrayList<Integer> tmp = new ArrayList<>(now);
                        tmp.add(to);
                        q.add(tmp); // 把这个路径加入队列
                    }
                }
            }
        }
        return res;
    }

    // 两个字符串是否可以通过改变一个字母后相等
    boolean transformCheck(String str1, String str2) {
        int differences = 0;
        for (int i = 0; i < str1.length() && differences < 2; i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                ++differences;
            }
        }
        return differences == 1;
    }

    /**
     * 组合总和2 同层去重
     **/
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(candidates);
        dfs(candidates, target, 0, 0, ans, new ArrayList<>());
        return ans;
    }

    public void dfs(int[] candidates, int target, int sum,
                    int index, List<List<Integer>> ans, List<Integer> list) {
        if (sum > target) {
            return;
        }
        for (int i = index; i < candidates.length; i++) {
            if (i > index && candidates[i] == candidates[i - 1]) continue;
            list.add(candidates[i]);
            if (sum + candidates[i] == target) {
                ans.add(new ArrayList<>(list));
            }
            dfs(candidates, target, sum + candidates[i], i + 1, ans, list);
            list.remove(list.size() - 1);
        }
    }

    /**
     * 组合总和
     **/
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        combinationSumCore(candidates, target, 0, 0, ans, new ArrayList<>());
        return ans;
    }

    public void combinationSumCore(int[] candidates, int target, int sum,
                                   int index, List<List<Integer>> ans, List<Integer> list) {
        if (sum > target) {
            return;
        }
        for (int i = index; i < candidates.length; i++) {
            list.add(candidates[i]);
            if (sum + candidates[i] == target) {
                ans.add(new ArrayList<>(list));
            }
            combinationSumCore(candidates, target, sum + candidates[i], i, ans, list);
            list.remove(list.size() - 1);
        }
    }

    /**
     * 在排序数组中查找元素的第一个和最后一个位置
     * {-1,-1}
     **/

    public int[] searchRange(int[] nums, int target) {
        int[] ans = new int[]{-1, -1};
        if (nums == null || nums.length < 1) return ans;
        int left = 0, right = nums.length - 1;
        int mid;
        while (left < right) {
            mid = left + (right - left) / 2;
            if (nums[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        if (left < nums.length && nums[left] == target) {
            ans[0] = left;
        }
        left = 0;
        right = nums.length - 1;
        while (left < right) {
            mid = left + (right - left) / 2;
            if (nums[mid] > target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        if (ans[0] != -1) {
            if (nums[left] == target) {
                ans[1] = left;
            } else {
                ans[1] = left - 1;
            }
        }

        return ans;
    }

    /**
     * 看了详细的二分分析后，重写
     * 用【left,right】 的写法
     **/
    public int[] searchRangeBack(int[] nums, int target) {
        int[] ans = new int[]{-1, -1};
        if (nums == null || nums.length < 1) return ans;
        int left = 0, right = nums.length - 1;
        int mid;
        //左边界
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] == target) {
                right = mid - 1;
            }
        }
        if (left < nums.length && nums[left] == target) {
            ans[0] = left;
        }

        left = 0;
        right = nums.length - 1;
        //右边界
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] == target) {
                left = mid + 1;
            }
        }
        if (left != 0 && nums[left - 1] == target) {
            ans[1] = left - 1;
        }
        return ans;
    }

    /**
     * 等式方程的可满足性
     **/
    public boolean equationsPossible(String[] equations) {
        int length = equations.length;
        int[] parent = new int[26];
        for (int i = 0; i < 26; i++) {
            parent[i] = i;
        }
        for (String str : equations) {
            if (str.charAt(1) == '=') {
                int index1 = str.charAt(0) - 'a';
                int index2 = str.charAt(3) - 'a';
                union(parent, index1, index2);
            }
        }
        for (String str : equations) {
            if (str.charAt(1) == '!') {
                int index1 = str.charAt(0) - 'a';
                int index2 = str.charAt(3) - 'a';
                if (find(parent, index1) == find(parent, index2)) {
                    return false;
                }
            }
        }
        return true;
    }

    public void union(int[] parent, int index1, int index2) {
        parent[find(parent, index1)] = find(parent, index2);
    }

    public int find(int[] parent, int index) {
        while (parent[index] != index) {
            //隔代压缩，性能比较好
            parent[index] = parent[parent[index]];
            index = parent[index];
        }
        return index;
    }

    /**
     * 把数字翻译成字符串
     **/
    public int translateNum(int num) {
        String numStr = String.valueOf(num);
        int a = 1, b = 1, c = 0;
        String str;
        for (int i = 1; i < numStr.length(); i++) {
            str = numStr.substring(i - 1, i + 1);
            int x = Integer.valueOf(str);
            if (x <= 25 && x >= 0 && str.charAt(0) != '0') {
                c = a + b;
            } else {
                c = b;
            }
            a = b;
            b = c;
        }
        return c;
    }

    /**
     * 求n！
     **/
    public int sumNums(int n) {
        boolean flag = n > 0 && (n += sumNums(n - 1)) > 0;
        return n;
    }

    /**
     * 新21点
     **/
    public double new21Game(int N, int K, int W) {
        if (K - 1 + W < N) return 1.0;
        double[] dp = new double[K + W];
        for (int i = K; i < K + W; i++) {
            dp[i] = 1;
        }
        double sum = N - K + 1;
        for (int i = K - 1; i >= 0; i--) {
            dp[i] = sum / W;
            sum += dp[i] - dp[i + W];
        }
        return dp[0];
    }

    /**
     * 拥有最多糖果的孩子
     **/
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        return null;
    }
}
