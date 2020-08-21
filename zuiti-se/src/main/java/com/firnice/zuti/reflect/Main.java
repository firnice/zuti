package com.firnice.zuti.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
        Class<?> targetClass = Class.forName("com.firnice.zuti.reflect.TargetObject");
        TargetObject targetObject = (TargetObject) targetClass.newInstance();


        Method[] declaredMethods = targetClass.getDeclaredMethods();
        for (Method method:declaredMethods) {
            System.out.println(method.getName());
        }

        Method publicMethod = targetClass.getDeclaredMethod("publicMethod", String.class);
        publicMethod.invoke(targetObject,"firnice");


        Field value = targetClass.getDeclaredField("value");

        value.setAccessible(true);
        value.set(targetObject,"haha");

        Method privateMethod = targetClass.getDeclaredMethod("privateMethod");
        privateMethod.setAccessible(true);
        privateMethod.invoke(targetObject);


    }
}
