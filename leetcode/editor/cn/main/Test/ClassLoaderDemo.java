package Test;

import java.util.Collections;

public class ClassLoaderDemo {
    public static void main(String[] args) {
        System.out.println("Collections.class.getClassLoader() = " + Collections.class.getClassLoader());
        System.out.println("String.class.getClassLoader() = " + String.class.getClassLoader());
        System.out.println("ClassLodarDemo's ClassLoader is " + ClassLoaderDemo.class.getClassLoader());
        System.out.println("The Parent of ClassLodarDemo's ClassLoader is " + ClassLoaderDemo.class.getClassLoader().getParent());
        System.out.println("The GrandParent of ClassLodarDemo's ClassLoader is " + ClassLoaderDemo.class.getClassLoader().getParent().getParent());
    }
}