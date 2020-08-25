package com.firnice.zuti.multithread.pk5;

public class TestWait {

    public static void main(String[] args) throws InterruptedException {
        blockedTest();
    }

    public static Object lock = new Object();


    public static void blockedTest() throws InterruptedException {

        Thread a = new Thread(() -> {
            try {
                System.out.println("a lock");
                synchronized (lock) {
                    lock.wait();
                }
                System.out.println("a rec notify");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "a");
        Thread b = new Thread(() -> {
            System.out.println("b try notify");

            synchronized (lock) {
                lock.notify();
            }
            System.out.println("b notify");

        }, "b");

        a.start();
        Thread.sleep(1000L); // 需要注意这里main线程休眠了1000毫秒，而testMethod()里休眠了2000毫秒
        b.start();
        Thread.sleep(2000L); // 需要注意这里main线程休眠了1000毫秒，而testMethod()里休眠了2000毫秒
        System.out.println(a.getName() + ":" + a.getState());
        System.out.println(b.getName() + ":" + b.getState());
    }

    public static synchronized void testMethod() {
        try {
            Thread.sleep(2000L);
            for (int i = 0; i < 1000000000; i++) {
                int a = i;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
