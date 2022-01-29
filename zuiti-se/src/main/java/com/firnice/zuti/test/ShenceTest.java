package com.firnice.zuti.test;

import java.util.concurrent.atomic.AtomicInteger;

public class ShenceTest {


    /**
     * 两个线程 争取数字，
     * 线程1 +1
     * 线程2 +2
     * 抢10次 暂停
     * 输出结果
     *
     * @param args
     */


    //计数
    static AtomicInteger i = new AtomicInteger(0);
    static AtomicInteger j = new AtomicInteger(0);
    //
    public static void main(String[] args) throws InterruptedException {

        Thread thread1 = new Thread(() -> {
            while (true) {
                if (j.incrementAndGet() < 10) {
                    i.addAndGet(1);
                } else {
                    System.out.println(i.get());
                    return;
                }
            }

        });
        Thread thread2 = new Thread(() -> {
            while (true) {
                if (j.incrementAndGet() < 10) {
                    i.addAndGet(2);
                } else {
                    System.out.println(i.get());
                    return;
                }
            }
        });
        thread1.start();
        thread2.start();

        Thread.sleep(10000);

    }


}
