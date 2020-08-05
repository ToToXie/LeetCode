package yuanfudao;

import alibaba.Test;

/**
 * @program: LeetCode
 * @description:
 * @author: wd
 * @create: 2020-07-26 15:51
 **/

public class test {
    static int fun1(int i) {
        return i <= 5 ? i : fun1(i - 1) * fun1(i - 3);
    }

    static int fun2(int i) {
        return ((i >>> 8) & 0x6597) & (((i << 8) & 0x6597) >>> 8);
    }

    public static void main(String[] args) {
        tes tes = new tes();
        tes.c();

    }

    public void a() {
        System.out.println("test.a");
    }

    public void b() {
        System.out.println("this test.b");

        this.b();
        a();
    }

    public void c() {
        System.out.println("test.c");
    }

}

class tes extends test {
    public void a() {
        System.out.println("tes.a");
    }

    public void b() {
        System.out.println("------------");
        System.out.println("this tes.b");
        System.out.println(this.getClass());
        System.out.println(super.getClass());
        System.out.println("------------");
        Test test = new Test();
        Object obj = test;
        System.out.println(obj.getClass());
        super.b();
        System.out.println("------------");

    }

    public void c() {
        System.out.println("tes.c");
    }

}
