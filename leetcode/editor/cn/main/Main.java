/**
 * @program: LeetCode
 * @description:
 * @author: wd
 * @create: 2020-05-21 13:35
 **/

public abstract class Main {
    public static void main(String[] args) {
        MainA mainA = new MainA();
        MainB mainB = new MainB();
        mainA.go("MainB");
        mainB.go("MainB");
    }

    public void sayhello(String name) {
        System.out.println("I am " + name);
    }

    ;

    void goBack() {

    }

    ;

    public void go(String name) {
        sayhello(name);
        goBack();
    }
}

class MainA extends Main {
    @Override
    public void goBack() {
        System.out.println("goBacy By line 11");
    }
}

class MainB extends Main {
    @Override
    public void goBack() {
        System.out.println("goBacy By line 22");
    }
}
