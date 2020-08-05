package Test;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Per implements Comparable {
    public static int AGE = 20;
    private static int cnt = 0;
    public int age;
    public int id;

    public Per(int age) {
        this.age = age;
        this.id = cnt++;
    }

    public static void main(String[] args) {
        List<Per> list = new LinkedList<>();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            list.add(new Per(random.nextInt(100)));
        }
        list.add(new Per(20));
        System.out.println(list);
        Collections.sort(list);
        System.out.println(list);

    }

    @Override
    public int compareTo(Object o) {
        Per per = (Per) o;
        if (this.age == AGE) {
            if (per.age == AGE) {
                return this.id - per.id;
            } else {
                return -1;
            }
        } else {
            if (per.age != AGE) {
                return this.id - per.id;
            } else {
                return 1;
            }
        }
    }

    @Override
    public String toString() {
        return "Per{" +
                "age=" + age +
                ", id=" + id +
                '}';
    }
}