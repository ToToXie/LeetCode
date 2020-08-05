package Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: LeetCode
 * @description:
 * @author: wd
 * @create: 2020-05-27 15:59
 **/

public class ListTest {
    public static void main(String[] args) {
//        Map<Integer,String> map = new HashMap<>();
//        map.put(1, "1");
//
//        Map<Integer, String> integerStringMap = Collections.unmodifiableMap(map);
//        map.put(2, "2");
//        integerStringMap.put(3, "3");
        Map<Integer, Integer> map = new HashMap<>();
        int[] objects = map.entrySet().stream().
                filter(it -> it.getKey() == 0).mapToInt(it -> it.getKey()).toArray();

    }
}
