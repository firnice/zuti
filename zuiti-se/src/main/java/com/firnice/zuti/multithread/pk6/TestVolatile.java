package com.firnice.zuti.multithread.pk6;

public class TestVolatile {

    /**
     * 如果去掉volatile
     * 只会打印
     * ThreadA: 0
     * ThreadB: 1
     * 说明ThreadA 监控不到变量的变化，ThreadB能够获取一次变量
     */
    public static volatile  int signal  = 0 ;

    static class ThreadA implements Runnable{

        @Override
        public void run() {
            while (signal<5){
                if(signal % 2 == 0){
                    System.out.println("ThreadA: "+signal);
                    signal++;
                }
            }
        }
    }

    static class ThreadB implements Runnable{

        @Override
        public void run() {
            while (signal<5){
                if(signal % 2 == 1){
                    System.out.println("ThreadB: "+signal);
                    signal++;
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new Thread(new ThreadA()).start();
        Thread.sleep(1000);
        new Thread(new ThreadB()).start();
    }
}
