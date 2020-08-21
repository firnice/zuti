package com.firnice.zuti.reflect;

public class TargetObject {

    private String value;

    public TargetObject() {
        value = "zuti";
        System.out.println(" constructor !!! ");
    }

    public void publicMethod(String s){
        System.out.println("I love "+s);
    }

    private void privateMethod(){
        System.out.println("value is "+value);
    }



}
