package Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * @program: LeetCode
 * @description:
 * @author: wd
 * @create: 2020-05-13 22:16
 **/

public class StringTest {
    public static void main(String[] args) {
//        System.out.println(888);
//        String string = new String();
//        Class<? extends String> aClass = string.getClass();
//        System.out.println(aClass.getName());
////        System.out.println(string.print());
//        parttern();
        scanner();
    }


    static public void parttern() {
        Scanner in = new Scanner(System.in);
        while (true) {
            String partern = in.nextLine();
            String str = in.nextLine();
            boolean matches = Pattern.matches(partern, str);
            System.out.println(partern + " ==== " + str + " ==== " + matches);
        }
    }

    static public void scanner() {
        Scanner in = new Scanner(System.in);
//        Scanner in = new Scanner(new String("sfs\nsfdsf\nsfsf"));
//        while (in.hasNextLine()){
//            System.out.println(in.nextLine());
//        }
        String str = "";
        List<String> list = new ArrayList<>();
        try {
            while (true) {
                if ((str = in.nextLine()).equals("")) {
                    break;
                } else {
                    list.add(str);
                    System.out.println(str);
                }
            }
        } catch (Exception e) {
        }
        System.out.println("over");
        System.out.println(list.toString());
    }

    static public void scannerB() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String nextLine = scanner.nextLine();
            if (nextLine == null || nextLine.trim().length() == 0) {
                System.out.println("empty, break.");
                break;
            }
            System.out.println(nextLine);
        }
    }

}

