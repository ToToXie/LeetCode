package util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @program: LeetCode
 * @description:
 * @author: wd
 * @create: 2020-05-08 17:40
 **/

public class RunTest<T> {
    static String method;
    static String[] methods;
    static String[] parameters;
    static Integer[] paras;
    static String parameter;
    static Class clazz;
    static Map<String, Method> methodMap = new HashMap<>();

    private static void getParameterAndMethod() {
        Scanner in = new Scanner(System.in);
        if (in.hasNext()) {
            method = in.next();
        }
        if (in.hasNext()) {
            parameter = in.next();
        }

//        method = method.split(":[")[1].split("]")[0];
//        String[] split = method.split("\",\"");
//        split[0] = split[0].substring(1);
//        split[split.length - 1] = split[split.length - 1].substring(0,split[split.length - 1].length() -1);
        String[] split = method.split("\",\"");
        int length = split.length - 1;
        int start = 0, end = 0;
        split[0] = split[0].substring(split[0].lastIndexOf("\"") + 1);
        split[length] = split[length].substring(0, split[length].indexOf("\""));
        methods = split;
        String[] split1 = parameter.split("],\\[");

        split1[0] = split1[0].substring(split1[0].lastIndexOf("[") + 1);
        split1[length] = split1[length].substring(0, split1[length].indexOf("]"));

        parameters = split1;

        System.out.println("methods = " + Arrays.toString(methods));
        System.out.println("parameters = " + Arrays.toString(parameters));

    }

    public void run(T obj) {
        getParameterAndMethod();
        int n = methods.length;
        Method[] declaredMethods = obj.getClass().getDeclaredMethods();
        for (Method m : declaredMethods) {
            methodMap.putIfAbsent(m.getName(), m);
        }
//        try {
//            Constructor constructor = clazz.getDeclaredConstructor(null);
//            Object o = constructor.newInstance(null);
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        }
        for (int i = 0; i < n; i++) {
            Method method = methodMap.get(methods[i]);
            if (method != null) {
                String[] split = parameters[i].split(",");
                Integer[] integers = Arrays.stream(split).map(it -> Integer.valueOf(it)).toArray(Integer[]::new);
                try {
                    method.invoke(obj, integers);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
