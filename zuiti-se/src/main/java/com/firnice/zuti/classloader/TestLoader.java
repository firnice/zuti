package com.firnice.zuti.classloader;

public class TestLoader {

    public static void main(String[] args) throws ClassNotFoundException {
        ClassLoader classLoader = Class.forName("java.lang.String").getClassLoader();
        System.out.println(classLoader);

        ClassLoader classLoader2 = String.class.getClassLoader();
        System.out.println(classLoader2);

        ClassLoader classLoader1 = TestLoader.class.getClassLoader();
        System.out.println(classLoader1);
    }
}
