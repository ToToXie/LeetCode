package yuanfudao;

import java.util.*;

/**
 * @program: LeetCode
 * @description:
 * @author: wd
 * @create: 2020-07-26 22:43
 **/

public class Main_5 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Main_5 main = new Main_5();
        int n = in.nextInt();
        int m = in.nextInt();
        Map<Integer, Teacher> sMap = new HashMap<>();
        Map<Integer, Teacher> tMap = new HashMap<>();
        Map<Integer, Integer> inMap = new HashMap<>();
        Map<Integer, Integer> out = new HashMap<>();
        List<Teacher> list = new ArrayList<>(m);
        for (int i = 0; i < m; i++) {
            int k = in.nextInt();
            int id = in.nextInt();
            String name = in.next();
            Teacher teacher = main.new Teacher(id, k, name);
            tMap.put(id, teacher);
            list.add(teacher);
            for (int j = 0; j < k; j++) {
                int sid = in.nextInt();
                sMap.put(sid, teacher);
            }
        }
        for (int i = 0; i < n; i++) {
            String a = in.next();
            int id = in.nextInt();
            int time = in.nextInt();
            if (a.equals("IN")) {
                inMap.put(id, time);
            } else {
                int start = inMap.get(id);
                if (tMap.containsKey(id)) {
                    Teacher teacher = tMap.get(id);
                    teacher.tTime += (time - start + 1);
                } else {
                    Teacher teacher = sMap.get(id);
                    teacher.sTime += (time - start + 1);
                }

            }
        }
//        for (Map.Entry<Integer, Integer> it : out.entrySet()) {
//            Integer key = it.getKey();
//            int end = it.getValue();
//            int start = inMap.get(key);
//            if(tMap.containsKey(key)){
//                Teacher teacher = tMap.get(key);
//                teacher.tTime += (end - start + 1);
//            }else {
//                Teacher teacher = sMap.get(key);
//                teacher.sTime += (end - start + 1);
//            }
//        }
        Collections.sort(list, (b, a) -> a.sTime / (a.sum * a.tTime) - a.sTime / (a.sum * a.tTime));
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i).name);
            if (i < list.size() - 1) System.out.println();
        }

    }

    class Teacher {
        public int id;
        public int sum;
        public String name;
        public int tTime = 0;
        public int sTime = 0;
        public double rank = 0D;

        public Teacher() {
        }

        public Teacher(int id, int sum, String name) {
            this.id = id;
            this.sum = sum;
            this.name = name;
        }
    }

}

