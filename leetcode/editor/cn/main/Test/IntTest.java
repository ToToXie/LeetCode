package Test;

/**
 * @program: LeetCode
 * @description:
 * @author: wd
 * @create: 2020-07-03 16:54
 **/

public class IntTest {
    public static void main(String[] args) {
//        limitTest();
//        Integer.valueOf(111);
        integerTest();
    }

    private static void limitTest() {
        System.out.println("Byte.MAX_VALUE = " + Byte.MAX_VALUE);
        System.out.println("Byte.MIN_VALUE = " + Byte.MIN_VALUE);
        System.out.println("Short.MAX_VALUE = " + Short.MAX_VALUE);
        System.out.println("Short.MIN_VALUE = " + Short.MIN_VALUE);
    }

    private static void integerTest() {
        Integer a = 1;
        Integer b = 333;
        add(a);
        System.out.println(a);
        add(b);
        System.out.println(b);
    }

    static void add(Integer integer) {
        integer += 1;
        System.out.println(integer);
    }
}
