package com.firnice.zuti.multithread.pk3;


import java.util.concurrent.*;

public class Task implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        Thread.sleep(1000);
        return 2;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executor = Executors.newCachedThreadPool();
        Task task = new Task();

        Future<Integer> submit = executor.submit(task);
        System.out.println(submit.get());


        FutureTask<Integer> integerFutureTask = new FutureTask<>(new Task());
        executor.submit(integerFutureTask);
        System.out.println(integerFutureTask.get());
    }
}
