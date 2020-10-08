package com.study.thread2;

/**
 * 线程依次执行（A打印123后B再打印123）
 */
public class ThreadInOrderRun {
    
    private static void printNumber(String threadName) {
        int i=0;
        while (i++ < 3) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(threadName + " print: " + i);
        }
    }

    private static void demo() {
        Thread A = new Thread(new Runnable() {
            @Override
            public void run() {
                printNumber("A");
            }
        });
        
        Thread B = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("B 开始等待 ...A执行完再开始执行");
                try {
                    A.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                
                printNumber("B");
            }
        });
        
        A.start();
        B.start();
    }
    
    public static void main(String[] args) {
        demo();
    }
}
