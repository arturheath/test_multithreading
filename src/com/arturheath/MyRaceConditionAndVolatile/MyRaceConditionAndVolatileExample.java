package com.arturheath.MyRaceConditionAndVolatile;

public class MyRaceConditionAndVolatileExample {
    public static void main(String[] args) {

        Counter counter = new Counter(0);

        Counter.foo();
        System.out.println("first call of getC()" + counter.getC());

        Thread t1 = new Thread(new MyThreadCounter(counter));
        Thread t2 = new Thread(new MyThreadCounter(counter));
        Thread t3 = new Thread(new MyThreadCounter(counter));

        t1.start();
        t2.start();
        t3.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e){

        }
        System.out.println("main is awake");
        System.out.println("first call of getC()" + counter.getC());

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {

        }

        System.out.println(counter.getC());
    }
}

class Counter {
    private int c;

    public static synchronized void foo(){
        System.out.println("static methos is called");
        while (true){

        }
    }

    public Counter(int c) {
        System.out.println("some thread called decr()");
        this.c = c;
    }

    public synchronized void incr() {
        System.out.println("some thread called incr(). Now sleep for 50 sec");
        try{
            Thread.sleep(50000);
        } catch (InterruptedException e){

        }
        c++;
    }

    public synchronized void decr() {
        c--;
    }

    public int getC() {
        return c;
    }
}

class MyThreadCounter implements Runnable {

    private Counter counter;

    public MyThreadCounter(Counter counter) {
        this.counter = counter;
    }

    public void run() {
        for (int i = 0; i < 100; i++) {
            counter.incr();
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {

            }
            counter.decr();
        }
    }
}
