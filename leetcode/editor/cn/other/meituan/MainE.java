//package meituan;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Scanner;
//import java.util.Set;
//
///**
// * @program: LeetCode
// * @description:
// * @author: wd
// * @create: 2020-05-11 09:54
// **/
//
//public class toutiao.MainE {
//    static int[] nums ;
//    static int[] ans ;
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        int n = in.nextInt();
//        nums = new int[n];
//        ans = new int[n];
//        for (int i = 0; i < n; i++) {
//            nums[i] = in.nextInt();
//        }
//        in.close();
//        Map<Integer, Set<Integer>> map = new HashMap<>();
//        for (int i = 0; i < n; i++) {
//            if(ans[i] == 0){
//                for (int j = i+1; j < n; j++) {
//                    if(map.containsKey(nums[i])){
//                        ans[i] = 1;
//                        break;
//                    }
//                    if((nums[i] & nums[j]) == 0){
//                        ans[i] = 1;
//                        ans[j] = 1;
//                        map.putIfAbsent(nums[i],nums[j]);
//                        map.putIfAbsent(nums[j],nums[i]);
//                        break;
//                    }
//                }
//            }
//
//        }
//        for (int i = 0; i < n; i++) {
//            if(i !=0) System.out.printf(" ");
//            System.out.printf("%d",ans[i] == 1?1:-1);
//        }
//    }
//}
