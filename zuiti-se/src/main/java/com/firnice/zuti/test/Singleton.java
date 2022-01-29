package com.firnice.zuti.test;

public class Singleton {

    public volatile Singleton singleton = null;

    public synchronized Singleton get() {
        if (singleton != null) {
            synchronized (singleton) {
                return new Singleton();
            }
        }
        return singleton;
    }


    public static void main(String[] args) {


    }
}
