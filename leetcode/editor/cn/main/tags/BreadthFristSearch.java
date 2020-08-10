package tags;

import javafx.util.Pair;
import util.Node;
import util.TreeNode;
import util.UnionFind;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @program: LeetCode
 * @description:
 * @author: wd
 * @create: 2020-06-21 22:36
 **/

public class BreadthFristSearch {
    static int[] nextIndexToleftAndUp = {-1, 0, 0, -1};
    static int[] nextIndexToRightAndDown = {1, 0, 0, 1};
    /**
     * 大西洋 太平洋水流问题
     **/
    static int[] nextIndex = {-1, 0, 1, 0, 0, -1, 0, 1};
    static int[] click = {3, 0};
    //    static private int[][] intsss = {{1,1},{1,1},{1,1}};
    static private char[][] board = {{'E', 'E', 'E', 'E', 'E'}, {'E', 'E', 'M', 'E', 'E'}, {'E', 'E', 'E', 'E', 'E'}, {'E', 'E', 'E', 'E', 'E'}};
    static private int[][] intss = {{0, 1}};
    static private int[][] intsss = {{-1, -1, -1, -1, -1, -1}, {-1, -1, -1, -1, -1, -1}, {-1, -1, -1, -1, -1, -1}, {-1, 35, -1, -1, 13, -1}, {-1, -1, -1, -1, -1, -1}, {-1, 15, -1, -1, -1, -1}};
    /**
     * 单词接龙
     **/
    private Map<String, Integer> wordId = new HashMap<>(); // 单词到id的映射
    private ArrayList<String> idWord = new ArrayList<>(); // id到单词的映射
    private ArrayList<Integer>[] edges; // 图的边

    public static void main(String[] args) {
        BreadthFristSearch breadthFristSearch = new BreadthFristSearch();

        System.out.println(

                breadthFristSearch.updateBoard(board, click)

        );
    }

    /**
     * 529 扫雷游戏
     **/
    public char[][] updateBoard(char[][] board, int[] click) {
        int x = click[0];
        int y = click[1];
        if (board[x][y] == 'X' || board[x][y] == 'M') return board;
        Queue<Pair<Integer, Integer>> queue = new LinkedList<>();
        Queue<Pair<Integer, Integer>> temp = new LinkedList<>();
        queue.add(new Pair<>(x, y));
        int m = board.length;
        int n = board[0].length;
        while (!queue.isEmpty()) {
            Pair<Integer, Integer> now = queue.poll();
            x = now.getKey();
            y = now.getValue();
            if (board[x][y] == 'B') continue;
            int count = 0;
            temp.clear();
            for (int i = 0; i < 16; i += 2) {
                int toX = x + Index.nextIndexOfEight[i];
                int toY = y + Index.nextIndexOfEight[i + 1];
                if (toX < 0 || toY < 0 || toX >= m || toY >= n) {
                    continue;
                }
                if (board[toX][toY] == 'M') {
                    count++;
                } else if (board[toX][toY] == 'E') {
                    temp.add(new Pair<>(toX, toY));
                }
            }
            if (count == 0) {
                board[x][y] = 'B';
                queue.addAll(temp);
            } else {
                board[x][y] = (char) ('0' + count);
            }
        }
        return board;
    }

    /**
     * 在每个树行中找到最大值
     **/
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) return ans;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int max = Integer.MIN_VALUE;
        while (!queue.isEmpty()) {
            int size = queue.size();
            max = Integer.MIN_VALUE;
            for (int i = 0; i < size; i++) {
                TreeNode now = queue.poll();
                max = Math.max(max, now.val);
                if (now.left != null) {
                    queue.add(now.left);
                }
                if (now.right != null) {
                    queue.add(now.right);
                }
            }
            ans.add(max);
        }
        return ans;
    }

    /**
     * 找树左下角的值
     **/
    public int findBottomLeftValue(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode now = root, ans = root;
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                now = queue.poll();
                if (i == 0) ans = now;
                if (now.left != null) queue.add(now.left);
                if (now.right != null) queue.add(now.right);
            }
        }
        return ans.val;
    }

    /**
     * 蛇梯棋
     **/
    public int snakesAndLadders(int[][] board) {
        int N = board.length;

        Map<Integer, Integer> dist = new HashMap();
        dist.put(1, 0);

        Queue<Integer> queue = new LinkedList();
        queue.add(1);

        while (!queue.isEmpty()) {
            int s = queue.remove();
            if (s == N * N) return dist.get(s);

            for (int s2 = s + 1; s2 <= Math.min(s + 6, N * N); ++s2) {
                int rc = get(s2, N);
                int r = rc / N, c = rc % N;
                int s2Final = board[r][c] == -1 ? s2 : board[r][c];
                if (!dist.containsKey(s2Final)) {
                    dist.put(s2Final, dist.get(s) + 1);
                    queue.add(s2Final);
                }
            }
        }
        return -1;
    }

    public int get(int s, int N) {
        // Given a square num s, return board coordinates (r, c) as r*N + c
        int quot = (s - 1) / N;
        int rem = (s - 1) % N;
        int row = N - 1 - quot;
        int col = row % 2 != N % 2 ? rem : N - 1 - rem;
        return row * N + col;
    }

    public List<List<Integer>> pacificAtlantic(int[][] matrix) {
        List<List<Integer>> ans = new ArrayList<>();
        if (matrix == null || matrix.length < 1 || matrix[0].length < 1) return ans;
        int m = matrix.length, n = matrix[0].length;
        int[][] waters = new int[matrix.length][matrix[0].length];
        Queue<Pair<Integer, Integer>> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            queue.add(new Pair<>(0, i));
            waters[0][i] = 1;
        }
        for (int i = 1; i < m; i++) {
            queue.add(new Pair<>(i, 0));
            waters[i][0] = 1;
        }
        while (!queue.isEmpty()) {
            Pair<Integer, Integer> now = queue.poll();
            int x = now.getKey(), y = now.getValue();
            for (int i = 0; i < 8; i += 2) {
                int xx = x + nextIndex[i];
                int yy = y + nextIndex[i + 1];
                if (xx < 0 || xx >= m || yy < 0 || yy >= n) continue;
                if (waters[xx][yy] != 0) continue;
                if (matrix[xx][yy] >= matrix[x][y]) {
                    queue.add(new Pair<>(xx, yy));
                    waters[xx][yy] += 1;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            queue.add(new Pair<>(m - 1, i));
            waters[m - 1][i] += 2;
            if (waters[m - 1][i] == 3) {
                ans.add(Arrays.asList(m - 1, i));
            }
        }
        for (int i = 0; i < m - 1; i++) {
            queue.add(new Pair<>(i, n - 1));
            waters[i][n - 1] += 2;
            if (waters[i][n - 1] == 3) {
                ans.add(Arrays.asList(i, n - 1));
            }
        }
        while (!queue.isEmpty()) {
            Pair<Integer, Integer> now = queue.poll();
            int x = now.getKey(), y = now.getValue();
            for (int i = 0; i < 8; i += 2) {
                int xx = x + nextIndex[i];
                int yy = y + nextIndex[i + 1];
                if (xx < 0 || xx >= m || yy < 0 || yy >= n) continue;
                if (waters[xx][yy] == 2 || waters[xx][yy] == 3) continue;
                if (matrix[xx][yy] >= matrix[x][y]) {
                    queue.add(new Pair<>(xx, yy));
                    waters[xx][yy] += 2;
                    if (waters[xx][yy] == 3) {
                        ans.add(Arrays.asList(xx, yy));
                    }
                }
            }
        }
        return ans;
    }

    public void pacificAtlanticBFS(int[][] matrix, int[][] waters, int x, int y, int increament) {
        Queue<Pair<Integer, Integer>> queue = new LinkedList<>();

    }

    /**
     * 最小高度树
     * <p>
     * 解空间一定是叶子节点
     * 对当前的图(初始的图或者删掉了前几层叶子节点的图)，我们要找的满足题设的根节点所在位置只有两种可能，
     * 一种在当前图的叶子节点(即度为1的节点)，一种为内部节点，
     * 若选择某叶子节点1，则该叶子节点1与任意其他叶子节点2的距离必定为叶子1-内部节点x-叶子2，深度为这三段边之和，
     * 必大于等于max(内部x-叶子1，内部x-叶子2)，所以相比于叶子节点，解空间只能出现在内部节点，
     * 每层情况都是这样，所以我们要剥开叶子节点直到再无可分的内部节点为止。
     **/
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> res = new ArrayList<>();
        /*如果只有一个节点，那么他就是最小高度树*/
        if (n == 1) {
            res.add(0);
            return res;
        }
        /*建立各个节点的出度表*/
        int[] degree = new int[n];
        /*建立图关系，在每个节点的list中存储相连节点*/
        List<List<Integer>> map = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            map.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            degree[edge[0]]++;
            degree[edge[1]]++;/*出度++*/
            map.get(edge[0]).add(edge[1]);/*添加相邻节点*/
            map.get(edge[1]).add(edge[0]);
        }
        /*建立队列*/
        Queue<Integer> queue = new LinkedList<>();
        /*把所有出度为1的节点，也就是叶子节点入队*/
        for (int i = 0; i < n; i++) {
            if (degree[i] == 1) queue.offer(i);
        }
        /*循环条件当然是经典的不空判断*/
        while (!queue.isEmpty()) {
            /*这个地方注意，我们每层循环都要new一个新的结果集合，这样最后保存的就是最终的最小高度树了*/
//            res = new ArrayList<>();
            res.clear();
            int size = queue.size();/*这是每一层的节点的数量*/
            for (int i = 0; i < size; i++) {
                int cur = queue.poll();
                res.add(cur);/*把当前节点加入结果集，不要有疑问，为什么当前只是叶子节点为什么要加入结果集呢?
                因为我们每次循环都会新建一个list，所以最后保存的就是最后一个状态下的叶子节点，
                这也是很多题解里面所说的剪掉叶子节点的部分，你可以想象一下图，每层遍历完，
                都会把该层（也就是叶子节点层）这一层从队列中移除掉，
                不就相当于把原来的图给剪掉一圈叶子节点，形成一个缩小的新的图吗*/
                List<Integer> neighbors = map.get(cur);
                /*这里就是经典的bfs了，把当前节点的相邻接点都拿出来，
                 * 把它们的出度都减1，因为当前节点已经不存在了，所以，
                 * 它的相邻节点们就有可能变成叶子节点*/
                for (int neighbor : neighbors) {
                    degree[neighbor]--;
                    if (degree[neighbor] == 1) {
                        /*如果是叶子节点我们就入队*/
                        queue.offer(neighbor);
                    }
                }
            }
        }
        return res;/*返回最后一次保存的list*/
    }

    /**
     * 完全平方数
     **/
    public int numSquares(int n) {
        if (n < 2) return n;
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i < n; i++) {
            if (i * i <= n) {
                list.add(i * i);
            } else {
                break;
            }
        }
        int[] dp = new int[n + 1];
        Arrays.fill(dp, n);
        dp[0] = 0;
        for (Integer integer : list) {
            for (int i = 1; i <= n; i++) {
                if (i - integer >= 0) {
                    dp[i] = Math.min(dp[i - integer] + 1, dp[i]);
                }
            }
        }
        return dp[n];
    }

    /**
     * 课程表
     **/
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (numCourses <= 0) return false;
        List<List<Integer>> g = new ArrayList<>(numCourses);
        for (int i = 0; i < numCourses; i++) {
            g.add(new LinkedList<>());
        }
        int[] input = new int[numCourses];
        for (int[] it : prerequisites) {
            g.get(it[1]).add(it[0]);
            input[it[0]]++;
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < input.length; i++) {
            if (input[i] == 0) {
                queue.add(i);
            }
        }
        while (!queue.isEmpty()) {
            Integer now = queue.poll();
            for (Integer to : g.get(now)) {
                input[to]--;
                if (input[to] == 0) {
                    queue.add(to);
                }
            }
        }
        return Arrays.stream(input).allMatch(a -> a == 0);
    }

    /**
     * 克隆图
     **/
    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }
        if (node.children == null) return new Node(node.val, null);

        Queue<Node> queue = new LinkedList<>();
        Map<Integer, Node> map = new HashMap<>();
        Set<Integer> set = new HashSet<>();
        queue.add(node);
        Node root = new Node();
        root.val = node.val;
        root.children = new ArrayList<>();
        map.put(node.val, root);
        Node now;
        while (!queue.isEmpty()) {
            now = queue.poll();
            for (Node child : now.children) {
                if (set.contains(child.val)) continue;
                if (!map.containsKey(child.val)) {
                    Node node1 = new Node();
                    node1.val = child.val;
                    node1.children = new ArrayList<>();
                    map.put(child.val, node1);
                }
                map.get(now.val).children.add(map.get(child.val));
                map.get(child.val).children.add(map.get(now.val));
                queue.add(child);
            }
            set.add(now.val);
        }
        return root;
    }

    /**
     * 被围绕的区域
     **/
    public void solve(char[][] board) {
        if (board == null || board.length < 1) {
            return;
        }
        int row = board.length;
        int col = board[0].length;
        int root = row * col;
        UnionFind unionFind = new UnionFind(root + 1);
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == 'O') {
                    int temp = node(i, j, col);
                    if (i == 0 || i == row - 1 || j == 0 || j == col - 1) {
//                        unionFind.union(root, temp);
                        unionFind.union(temp, root);
                    } else {
                        if (i > 0 && board[i - 1][j] == 'O') {
                            unionFind.union(temp, node(i - 1, j, col));
                        }
                        if (i < row - 1 && board[i + 1][j] == 'O') {
                            unionFind.union(temp, node(i + 1, j, col));
                        }
                        if (j > 0 && board[i][j - 1] == 'O') {
                            unionFind.union(temp, node(i, j - 1, col));
                        }
                        if (j < col - 1 && board[i][j + 1] == 'O') {
                            unionFind.union(temp, node(i, j + 1, col));
                        }
                    }
                }
            }
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (unionFind.isConnected(node(i, j, col), root)) {
                    // 和dummyNode 在一个连通区域的,那么就是O；
                    board[i][j] = 'O';
                } else {
                    board[i][j] = 'X';
                }
            }
        }
    }

    public int node(int i, int j, int col) {
        return i * col + j;
    }

    /**
     * 二叉树的锯齿形层次遍历
     **/
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) return ans;
        Deque<Integer> stack = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        TreeNode now;
        boolean flag = false;
        while (!queue.isEmpty()) {
            int n = queue.size();
            flag = !flag;
            for (int i = 0; i < n; i++) {
                now = queue.poll();
                if (now.left != null) {
                    queue.add(now.left);

                }
                if (now.right != null) {
                    queue.add(now.right);
                }
                if (!flag) {
                    stack.addLast(now.val);
                } else {
                    stack.addFirst(now.val);
                }
            }
            ans.add(stack.stream().collect(Collectors.toList()));
            stack.clear();
        }
        return ans;
    }

    public int findLadders(String beginWord, String endWord, List<String> wordList) {
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
            return 0;
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
        boolean[] vis = new boolean[idWord.size()];

        // 将起点加入队列 并将其cost设为0
        Queue<Integer> q = new LinkedList<>();
        q.add(wordId.get(beginWord));
        int ans = 0, now, n, level = 0;

        // 开始广度优先搜索
        while (!q.isEmpty()) {
            n = q.size();
            level++;
            for (int i = 0; i < n; i++) {
                now = q.poll();
                vis[now] = true;
                if (now == dest) { // 若该点为终点则将其存入答案res中
                    return level;
                } else { // 该点不为终点 继续搜索
                    for (int j = 0; j < edges[now].size(); j++) {
                        int to = edges[now].get(j);
                        if (!vis[to]) {
                            q.add(to);
                        }
                    }
                }
            }

        }
        return ans;
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
     * 二叉树的堂兄弟节点
     **/
    public boolean isCousins(TreeNode root, int x, int y) {
        if (root == null) return false;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        TreeNode now;
        int parenta, parentb;
        while (!queue.isEmpty()) {
            int n = queue.size();
            parenta = -1;
            parentb = -1;
            for (int i = 0; i < n; i++) {
                now = queue.poll();
                if (now.left != null) {
                    queue.add(now.left);
                    if (now.left.val == x) {
                        parenta = now.val;
                    } else if (now.left.val == y) {
                        parentb = now.val;
                    }
                }
                if (now.right != null) {
                    queue.add(now.right);
                    if (now.right.val == x) {
                        parenta = now.val;
                    } else if (now.right.val == y) {
                        parentb = now.val;
                    }
                }
            }
            if (parenta != -1 && parentb != -1 && parenta != parentb) return true;
        }
        return false;
    }

    /**
     * N叉树的最大深度
     **/
    public int maxDepth(Node root) {
        if (root == null) return 0;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        Node now;
        int level = 0;
        while (!queue.isEmpty()) {
            int n = queue.size();
            level++;
            for (int i = 0; i < n; i++) {
                now = queue.poll();
                for (Node child : now.children) {
                    queue.add(child);
                }
            }
        }
        return level;
    }
}
