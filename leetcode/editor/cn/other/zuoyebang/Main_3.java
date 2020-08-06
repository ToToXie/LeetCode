package zuoyebang;

import java.util.ArrayList;
import java.util.List;

public class Main_3 {

    static char[] chars = {'A', 'B', 'C'};
    static List<List<Character>> ans;

    /**
     * 给定一个维度列表，求维度全组合。举例：
     * 输入
     * {A - 国家，B - 省份，C - 城市}
     * <p>
     * 输出
     * 全组合 {A}, {B}, {C}, {A, B}, {A, C}, {B, C}, {A, B, C}
     **/


    public static void main(String[] args) {
        System.out.println(getList(chars));
    }

    static List<List<Character>> getList(char[] chars) {
        ans = new ArrayList<>();
        if (chars == null || chars.length < 1) return ans;
        boolean[] vis = new boolean[chars.length];
        getListDFS(0, chars.length, new ArrayList<>());
        return ans;
    }

    static void getListDFS(int index, int n, List<Character> list) {
        if (index == n) {
            return;
        } else {
//            list.add(chars[index]);
//            ans.add(new ArrayList<>(list));
//            vis[index] = true;
            for (int i = index; i < n; i++) {
                list.add(chars[i]);
                ans.add(new ArrayList<>(list));
                getListDFS(i + 1, n, list);
                list.remove(list.size() - 1);
            }
//            vis[index] = false;
//            list.remove(list.size() - 1);
        }
    }


}