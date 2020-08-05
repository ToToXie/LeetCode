import java.util.ArrayList;
import java.util.List;

/**
 * @program: LeetCode
 * @description:
 * @author: wd
 * @create: 2020-04-24 22:37
 **/

public class LC802 {
    public static void main(String[] args) {
        LC802 lc802 = new LC802();
        int[][] g = {{}, {2}, {3, 4}, {4}, {}};


        System.out.println(lc802.eventualSafeNodes(g));
    }

    /**
     * @Description: 递归做法，设置三种状态
     **/
    public List<Integer> eventualSafeNodes(int[][] graph) {
        int length = graph.length;
        List<Integer> ans = new ArrayList<>(length);
//        Set<Integer> ans = new HashSet<>(length);
        int[] vis = new int[length];
        for (int i = 0; i < length; i++) {
            if (dfs(graph, i, vis)) {
                ans.add(i);
            }
        }
        return ans;
    }

    /**
     * @Description: 有向无环图，拓扑排序
     **/
    public List<Integer> eventualSafeNodes2(int[][] graph) {
        int length = graph.length;
        List<Integer> ans = new ArrayList<>(length);
//        Set<Integer> ans = new HashSet<>(length);
        int[] vis = new int[length];
        for (int i = 0; i < length; i++) {
            if (dfs(graph, i, vis)) {
                ans.add(i);
            }
        }
        return ans;
    }

    public boolean dfs(int[][] graph, int index, int[] color) {
        if (color[index] > 0) {
            return color[index] == 2;
        }
        color[index] = 1;
        for (int it : graph[index]) {
            if (color[it] == 2) {
                continue;
            } else if (color[it] == 1 || !dfs(graph, it, color)) {
                return false;
            }
        }
        color[index] = 2;
        return true;
    }
}
