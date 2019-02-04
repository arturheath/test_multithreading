package com.arturheath.MyCondition;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyCondition {
    public static void main(String[] args) {

        final List<Integer> data = new ArrayList<>();
        ProducerConsumer pr = new ProducerConsumer(data);

        Thread producer = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    pr.put(i);
                    pr.printDataSize();
                }
            }
        });


        Thread consumer = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {

                }
                for (int i = 0; i < 10; i++) {
                    pr.take();
                    pr.printDataSize();
                }
            }
        });

        producer.setName("Producer");
        consumer.setName("Consumer");

        producer.start();
        consumer.start();
    }
}


class ProducerConsumer {

    private List<Integer> data;
    private Lock lock = new ReentrantLock();
    private Condition notEmpty = lock.newCondition();
    private Condition notFull = lock.newCondition();

    public ProducerConsumer(List<Integer> list) {
        data = list;
    }

    public void put(int num) {
        lock.lock();
        try {
            while (data.size() > 9) {
                try {
                    notFull.await();
                } catch (InterruptedException e) {

                }
            }
            data.add(num);
            notEmpty.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void take() {
        lock.lock();
        try {
            while (data.size() <= 0) {
                try {
                    notEmpty.await();
                } catch (InterruptedException e) {

                }
            }
            System.out.println("The element " + data.remove(0) + " has been removed");
            notFull.signal();
        } finally {
            lock.unlock();
        }
    }

    public void printDataSize() {
        System.out.println(Thread.currentThread().getName() + " in progress. Size of data is " + data.size()
                + " including following elements" + data);
    }
}
