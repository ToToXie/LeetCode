import java.util.*;
import java.util.stream.Collectors;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
//        solution.getCoinTest();
        solution.getMinDisTest();
        int[] days = {1, 2};
        Set<Integer> collect = Arrays.stream(days).boxed().collect(Collectors.toSet());
    }

    //给出一个具有N个不同值的数组A[]，找出将数组排序所需的最小交换次数。
//    找环的个数
    public int minSwapInArray(int[] nums) {
        int length = nums.length;
        int loop = 0;
        int[] sortNums = Arrays.copyOf(nums, length);
        Arrays.sort(sortNums);
        Map<Integer, Integer> index = new HashMap<>(length);
        boolean[] visted = new boolean[length];
        for (int i = 0; i < length; i++) {
            index.put(sortNums[i], i);
        }
        for (int i = 0; i < length; i++) {
            if (visted[i] == false) {
                if (nums[i] != sortNums[i]) {
                    int temp = nums[i];
                    visted[i] = true;
                    while (index.get(temp) != i) {
                        visted[index.get(temp)] = true;
                        temp = nums[index.get(temp)];

                    }
                    loop++;
                }
            }
        }
        System.out.println(loop);
        return length - loop;
    }

    public void minSwapInArrayTest(int[] nums) {
        System.out.println(Arrays.toString(nums));
        System.out.println(minSwapInArray(nums));
        System.out.println(Arrays.toString(nums));

    }

    //    Dijkstra算法,返回最短路径数组
    public int[] Dijkstra(int[][] G, int start) {
        int length = G.length;
        int[] d = new int[length];
        boolean[] vis = new boolean[length];
        Arrays.fill(d, Integer.MAX_VALUE);
        int s = start;
        d[s] = 0;
        for (int i = 0; i < length; i++) {
            int u = -1, MIN = Integer.MAX_VALUE;
            for (int j = 0; j < length; j++) {
                if (vis[j] == false && d[j] < MIN) {
                    u = j;
                    MIN = d[j];
                }
            }
            if (u == -1) break;
            vis[u] = true;
            for (int v = 0; v < length; v++) {
                if (vis[v] == false && G[u][v] != Integer.MAX_VALUE
                        && d[u] + G[u][v] < d[v]) {
                    d[v] = G[u][v] + d[u];
                }
            }
        }
        return d;
    }

    //    手动输入
    public void DijkstraTest() {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int s = in.nextInt();
        int[][] G = new int[n][n];
        Arrays.stream(G).forEach(it -> Arrays.fill(it, Integer.MAX_VALUE));
        int a, b, c;
        for (int i = 0; i < m; i++) {
            a = in.nextInt();
            b = in.nextInt();
            c = in.nextInt();
            G[a][b] = c;
        }
        int[] dijkstra = Dijkstra(G, s);
        System.out.println(Arrays.toString(dijkstra));

    }

    /**
     * @Description: 蚂蚁森林n个小动物，1~n,小动物编号越小能力越强，
     * 现在筛选国王，每个小动物都会崇拜别的小动物或者自己，但只会崇拜比自己能力强的小动物。
     * 问每个人最多可以获得多少票
     **/
    public int[] vote(int[] nums) {
        int length = nums.length;
        int vote[] = new int[length];
        for (int i = length - 1; i >= 0; i--) {
            int index = nums[i];
            vote[i]++;
            if (index != 0) vote[index - 1] += vote[i];
        }
        return vote;
    }

    public void voteTest() {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = in.nextInt();
        }
        System.out.println(Arrays.toString(vote(nums)));

    }

    /**
     * @Description: 勇士打怪兽，勇士有一个初始能力值，
     * 记为 a ,怪兽有 n 个，每个怪兽有自己的能力值，
     * 记为 i 。当 a>=i时，勇士可以打败怪兽，并获得一枚金币。
     * 且勇士在任意时刻都可以选择花费一枚金币提升自己1点能力值。
     * 勇士打怪兽的顺序随意，勇士的初始金币为0.
     * 问：
     * 勇士最大可以获得多少金币，怪兽不一定要打完。
     * 金币花完没有了，就是0
     **/
    public int getCoin(int a, int[] monsters) {
        int ans = 0, coinNow = 0;

        Arrays.sort(monsters);
        for (int i = 0; i < monsters.length; ) {
            int x = monsters[i];
            if (a >= x) {
                coinNow++;
                ans = Math.max(coinNow, ans);
                i++;
            } else if (coinNow > 0) {
                coinNow--;
                a++;
            } else break;
        }
        return ans;
    }

    public void getCoinTest() {

        Scanner in = new Scanner(System.in);
        int a = in.nextInt();
        int m = in.nextInt();
        int[] nums = new int[m];
        for (int i = 0; i < m; i++) {
            nums[i] = in.nextInt();
        }

        System.out.println(getCoin(a, nums));
    }

    /**
     * @Description: 2. 最短路径
     * <p>
     * 有n个城市，每个城市有自己的等级level，城市之间有n-1条路径。
     * 小明可以选择任意的level相同的城市作为起点和终点，并从起点走到终点。
     * 问：
     * 小明走路的最短距离是多少？
     **/
    public int getMinDis(ArrayList<LinkedList<Integer>> g
            , Map<Integer, Integer> map, boolean[] vis, int[] level) {
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < level.length; i++) {
            getMinDisCore(g, vis, level, ans, i, 0, level[i]);
        }
        return ans;
    }

    private void getMinDisCore(ArrayList<LinkedList<Integer>> g,
                               boolean[] vis, int[] levels, int ans, int now, int sum, int level) {
        vis[now] = true;
        for (Integer it : g.get(now)) {
            if (!vis[it]) {
                if (levels[it] == level) {
                    ans = Math.min(ans, sum + 1);
                    getMinDisCore(g, vis, levels, ans, it, 0, level);
                } else {
                    getMinDisCore(g, vis, levels, ans, it, sum + 1, level);
                }
            }
        }
    }

    public void getMinDisTest() {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] level = new int[n];
        boolean[] vis = new boolean[n];
        ArrayList<LinkedList<Integer>> g = new ArrayList<>(n);
        Map<Integer, Integer> map = new HashMap<>();


        for (int i = 0; i < n; i++) {
            level[i] = in.nextInt();
            map.put(level[i], i);
        }
        int x, y;
        for (int i = 1; i < n; i++) {
            x = in.nextInt() - 1;
            y = in.nextInt() - 1;
            LinkedList<Integer> integerx = g.get(x);
            if (integerx == null) {
                integerx = new LinkedList<>();
            }
            integerx.add(y);
            LinkedList<Integer> integery = g.get(y);
            if (integery == null) {
                integery = new LinkedList<>();
            }
            integery.add(x);
        }
        System.out.println(getMinDis(g, map, vis, level));
        ;

    }
}