package com.arturheath.MyExecutorService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyExecutorService {
    public static void main(String[] args) {
        ExecutorService exs = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            exs.execute(new MyRunnaable());
        }
        exs.shutdown();
    }
}

class MyRunnaable implements Runnable{
    public void run(){
        System.out.println("hello from " + Thread.currentThread().getName());
    }
}
