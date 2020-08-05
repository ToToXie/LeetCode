package toutiao;

import java.util.HashMap;
import java.util.Scanner;

/**
 * @program: LeetCode
 * @description:
 * @author: wd
 * @create: 2020-05-11 09:54
 **/

public class MainC {
    /**
     * @Description: 所有读入均为整型数。
     * 第一行输入两个整数n和m，n是袜子数量，m是天数，n，m均不大于100,000。第二行输入n个整数，第i个数c_i表示第i只袜子颜色,保证c_i不大于100,000.
     * 接下来m行每行两个整数a_i和b_i，表示小H计划第i天穿的袜子标号。
     * <p>
     * 小H有n只纯色袜子，由于颜色的种类繁多，对标号为i的袜子，定义它的颜色为c_i，并且袜子不区分左右脚。接下来的m天中，小H每天需要穿两只袜子出门，并且他已经做好了计划，第i天他会选择穿标号为a_i和b_i的两只袜子。
     * 众所周知，每天穿的两只袜子颜色要是一样的，小H同样深知这个道理。为了保证他的计划不被打乱，并且每天穿的两只袜子颜色一致，他事先会对所有的袜子重新染色，现在小H希望自己染色的袜子数尽可能少，你能帮帮他吗
     **/

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        HashMap<Integer, Integer> colorsMap = new HashMap<>();

        int[] colors = new int[n];
        int[] left = new int[m];
        int[] right = new int[m];
        int max = -1;
        for (int i = 0; i < n; i++) {
            colors[i] = in.nextInt();
            max = Math.max(max, colors[i]);
        }


        int a, b;
        for (int i = 0; i < m; i++) {
            left[i] = in.nextInt() - 1;
            right[i] = in.nextInt() - 1;

            colorsMap.put(colors[left[i]], colorsMap.getOrDefault(colors[left[i]], 0) + 1);
            colorsMap.put(colors[right[i]], colorsMap.getOrDefault(colors[right[i]], 0) + 1);
        }
        int ans = 0;
        for (int i = 0; i < m; i++) {
            a = left[i];
            b = right[i];
            if (colors[a] == colors[b]) {
                continue;
            }
            if (colorsMap.get(colors[a]) > colorsMap.get(colors[a])) {
                colorsMap.put(colors[a], colorsMap.get(colors[a]) + 1);
                colorsMap.put(colors[b], colorsMap.get(colors[b]) - 1);
                colors[b] = colors[a];
                ans++;
            } else {
                colorsMap.put(colors[a], colorsMap.get(colors[a]) - 1);
                colorsMap.put(colors[b], colorsMap.get(colors[b]) + 1);
                colors[a] = colors[b];
                ans++;
            }
        }
        System.out.println(ans);
    }
//public static void mains(String[] args) {
//    Scanner in = new Scanner(System.in);
//    int n = in.nextInt();
//    int m = in.nextInt();
//    HashMap<Integer,Integer> colorsMap = new HashMap<>();
//    int[] colors = new int[n];
//    for (int i = 0; i < n; i++) {
//        colors[i] = in.nextInt();
//        colorsMap.put(colors[i],colorsMap.getOrDefault(colors[i],0)+1);
//    }
//    int[] col = new int[n];
//
//
//    int a,b;
//    int ans = 0;
//    for (int i = 0; i < m; i++) {
//        a = in.nextInt() -1;
//        b = in.nextInt() -1;
//        if(colors[a] == colors[b]){
//            continue;
//        }
//        if(colorsMap.get(colors[a]) > colorsMap.get(colors[a])){
//            colorsMap.put(colors[a],colorsMap.get(colors[a]) + 1);
//            colorsMap.put(colors[b],colorsMap.get(colors[b]) - 1);
//            colors[b] = colors[a];
//            ans++;
//        }else {
//            colorsMap.put(colors[a],colorsMap.get(colors[a]) - 1);
//            colorsMap.put(colors[b],colorsMap.get(colors[b]) + 1);
//            colors[a] = colors[b];
//            ans++;
//        }
//
//    }
//
//
//    System.out.println(ans);
//}
}
