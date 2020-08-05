package huawei;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @program: LeetCode
 * @description:
 * @author: wd
 * @create: 2020-05-20 18:53
 **/

public class MainA {
    //*
//1,name1
//2,name2
//3,*
//4,name4
//5,name5
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String spit;
        StringBuilder sb = new StringBuilder();
        int count = 1;
        String str = "";
        List<String> list = new ArrayList<>();
        try {
            while (true) {
                if ((str = in.nextLine()).equals("")) {
                    break;
                } else {
                    list.add(str);
                }
            }
        } catch (Exception e) {
        }
        spit = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            str = list.get(i);
            if (spit.equals(str.substring(2))) {
                sb.deleteCharAt(sb.length() - 1);
                sb.append("\n");
                count++;
            } else {
                sb.append(str).append("|");
            }
        }
        in.close();
        if (count == 1) {
            System.out.println(0);
        } else {
            System.out.println(count);
            System.out.println(sb.deleteCharAt(sb.length() - 1).toString());
        }

    }
}
