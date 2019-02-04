package com.arturheath.MyLiveLock;

public class MyLiveLock {
    private static boolean tOneTaskDone = false;
    private static boolean tTwoTaskDone = false;

    public static void main(String[] args) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    System.out.println(Thread.currentThread().getName() + " still running");
                    if (tTwoTaskDone){
                        break;
                    }
                }
                System.out.println(Thread.currentThread().getName() + " stoped");
                tOneTaskDone = true;
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    System.out.println(Thread.currentThread().getName() + " still running");
                    if (tOneTaskDone){
                        break;
                    }
                }
                System.out.println(Thread.currentThread().getName() + " stoped");
                tTwoTaskDone = true;
            }
        }).start();
    }
}
