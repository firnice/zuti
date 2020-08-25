package com.firnice.zuti.multithread.pk4;

public class TestState {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
        });

        System.out.println(thread.getState());

        blockedTest();
    }



    public static void blockedTest() throws InterruptedException {

        Thread a = new Thread(new Runnable() {
            @Override
            public void run() {
                testMethod();
            }
        },"a");
        Thread b = new Thread(new Runnable() {
            @Override
            public void run() {
                testMethod();
            }
        },"b");

        a.start();
        Thread.sleep(1000L); // 需要注意这里main线程休眠了1000毫秒，而testMethod()里休眠了2000毫秒
        b.start();
        System.out.println(a.getName()+":"+a.getState());
        System.out.println(b.getName()+":"+b.getState());
    }

    public static synchronized void testMethod(){
        try {
            Thread.sleep(2000L);
            for (int i = 0; i <1000000000 ; i++) {
                int a = i;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
