package com.firnice.zuti.multithread.pk1;

/**
 *  多线程两种方式 Thread Runable
 */
public class Demo {


    public static void main(String[] args) {
        MyThread1 myThread = new MyThread1();
//        //函数调用了
//        myThread.run();

        //线程启动
        myThread.start();

        //还是需要Thread来运行
        new Thread(new MyThread2()).start();


        //jdk8 函数式编程
        new Thread(()-> {
            System.out.println("lambda run");
        }).start();
    }

    public static class MyThread1 extends Thread{
        @Override
        public void run() {
            System.out.println("MyThread1");
        }
    }

    public static class  MyThread2 implements Runnable{

        @Override
        public void run() {
            System.out.println("MyThread2");
        }
    }
}
