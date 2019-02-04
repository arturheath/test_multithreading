package com.arturheath.MyWaitNotify;

import java.util.ArrayList;
import java.util.List;

public class WaitNotify {
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
    private final List<Integer> data;

    public ProducerConsumer(List<Integer> list) {
        data = list;
    }

    public void put(int num) {
        synchronized (data){
            while (data.size() > 9) {
                try {
                    data.wait();
                } catch (InterruptedException e) {

                }
            }
            data.add(num);
            data.notifyAll();
        }
    }

    public void take() {
        synchronized (data) {
            while (data.size() <= 0) {
                try {
                    data.wait();
                } catch (InterruptedException e) {

                }
            }
            System.out.println("The element " + data.remove(0) + " has been removed");
            data.notifyAll();
        }
    }

    public void printDataSize() {
        System.out.println(Thread.currentThread().getName() + " in progress. Size of data is " + data.size()
        + " including following elements" + data);
    }
}






















