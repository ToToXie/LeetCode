package Test;

/**
 * @program: LeetCode
 * @description:
 * @author: wd
 * @create: 2020-06-21 19:55
 **/

public class A {
    public static void main(String[] args) {
        A a = new B();
        System.out.println(a.getNumB());
    }

    public Integer getNum() {
        return 1;
    }

    public Number getNumB() {
        return 1.0;
    }
}

class B extends A {
    public Integer getNum() {
        return 2;
    }

    public Float getNumB() {
        return 2.0F;

    }
}
