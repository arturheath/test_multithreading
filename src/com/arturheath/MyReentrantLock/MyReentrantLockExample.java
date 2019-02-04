package com.arturheath.MyReentrantLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyReentrantLockExample {
    public static void main(String[] args) {
        CounterLock counterLock = new CounterLock(0);
        Lock lock = new ReentrantLock();

        MeThread one = new MeThread(counterLock, lock);
        MeThread two = new MeThread(counterLock, lock);
        MeThread three = new MeThread(counterLock, lock);

        one.start();
        two.start();
        three.start();

        try {
            one.join();
            two.join();
            three.join();
        } catch (InterruptedException e){

        }

        System.out.println("c = " + counterLock.getC());
    }
}

class MeThread extends Thread {
    private CounterLock counter;
    private Lock lock;

    public MeThread(CounterLock counterLock, Lock lock){
        counter = counterLock;
        this.lock = lock;
    }

    public void run(){
        lock.lock();
        try {
            for (int i = 0; i < 100; i++) {
                counter.incr();
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e){

                }
                counter.decr();
            }
        } finally {
            lock.unlock();
        }
    }
}

class CounterLock {
    private int c;

    public CounterLock(int c){
        this.c = c;
    }

    public void incr(){
        c++;
    }

    public void decr(){
        c--;
    }

    public int getC(){
        return c;
    }
}
