package com.arturheath.MyDeadlock;

public class MyDeadlock {
    public static void main(String[] args) {
        final Object lock1 = new Object();
        final Object lock2 = new Object();


        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock1){
                    System.out.println(Thread.currentThread().getName() + " acquired lock 1");
                    synchronized (lock2){
                        System.out.println(Thread.currentThread().getName() + " acquired lock 2");
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock2){
                    System.out.println(Thread.currentThread().getName() + " acquired lock 2");
                    synchronized (lock1){
                        System.out.println(Thread.currentThread().getName() + " acquired lock 1");
                    }
                }
            }
        }).start();
    }
}
