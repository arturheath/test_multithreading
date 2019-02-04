package com.arturheath.MyCountDownLatch;

import java.util.concurrent.CountDownLatch;

public class MyCountdownLatchExample {
    public static void main(String[] args) {

        CountDownLatch countDownLatch = new CountDownLatch(10);

        for (int i = 0; i < 10; i++) {
            new Thread(new MyThread(countDownLatch, i)).start();
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e){

        }
        System.out.println("In main again");

    }
}

class MyThread implements Runnable {

    private CountDownLatch countDownLatch;
    private int id;

    public MyThread(CountDownLatch countDownLatch, int id){
        this.countDownLatch = countDownLatch;
        this.id = id;
    }

    public void run(){
        System.out.println("Thread # " + id + " in progress");
        countDownLatch.countDown();
    }
}



