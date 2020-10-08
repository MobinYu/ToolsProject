package com.study.thread2;

/**
 * 线程交替执行（A打印1后B打印123，A再打印23）
 */
public class ThreadAlternateRun {

private static void demo() {
    Object lock = new Object(); //对象锁
    
    Thread A = new Thread(new Runnable() {
        @Override
        public void run() {
            for (int i = 1; i <= 3; i++) {
                synchronized (lock) {
                    try {
                        System.out.println(Thread.currentThread().getName() + " print: " + i);
                        lock.wait(); // 进入等待状态,并释放锁 (无线等待状态)
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }, "A");
    
    Thread B = new Thread(new Runnable() {
        @Override
        public void run() {
            for (int i = 1; i <= 3; i++) {
                synchronized (lock) {
                    System.out.println(Thread.currentThread().getName() + " print: " + i);
                    lock.notify(); //唤醒线程
                }
                
                //线程A和线程B交换执行关键所在
                /*
                 * 为什么不能放在synchronized块中notify()后面的位置，
                 * 因为静态代码块执行完毕，虽然等待了1毫秒，但是还不会释放锁(sleep方法不会释放锁的)，
                 * 只有等静态代码块执行完毕的时候才开始释放锁；释放完锁，线程A和线程B会去争夺锁，一定是A获得到锁，所以不会出现交替打印的效果；
                 * 所以将这段代码写在锁释放完后面，让B失去获取锁的机会，然后开始重复交替打印
                 */
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }, "B");
    
    A.start();
    B.start();
}
    
    public static void main(String[] args) {
        demo();
    }
}
