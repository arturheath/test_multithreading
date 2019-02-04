package com.arturheath.MyCallableAndFuture;

import java.util.concurrent.*;

public class MyCallableAndFuture {
    public static void main(String[] args) {
        // 1. for use with Future interface and ExecutorService
        Callable<Integer> myCallable1 = new MyCallable(10);
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Future<Integer> future = executorService.submit(myCallable1);
        executorService.shutdown();
        try {
            System.out.println(future.get());
        } catch (InterruptedException | ExecutionException e){

        }

        // 2. for ise with FutureTask class and a single thread
        Callable<Integer> myCallable2 = new MyCallable(5);
        FutureTask<Integer> futureTask = new FutureTask<>(myCallable2);
        new Thread(futureTask).start();
        try {
            System.out.println(futureTask.get());
        } catch (InterruptedException | ExecutionException e){

        }

        // main blocks until both computations finish
        System.out.println("in main again");
    }
}

class MyCallable implements Callable<Integer> {
    int num;

    public MyCallable(int num){
        this.num = num;
    }

    @Override
    public Integer call() throws Exception {
        int result = 0;
        for (int i = 0; i < num; i++) {
            Thread.sleep(1000);
            result++;
        }
        return result;
    }
}
