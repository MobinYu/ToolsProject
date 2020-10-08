package com.study.thread2;

public class ThreadLocalDemo {

    public ThreadLocal<String> value = new ThreadLocal<>();

    public void ThreadLocalTest() throws InterruptedException {
        value.set("222222222");
        
        String v = value.get();
        
        System.out.println("线程1执行之前，主线程取到的值：" + v);
        
        new Thread(()->{
            String d = value.get();
            System.out.println("线程1取到的值：" + d);
            value.set("3333333333333");
            d = value.get();
            System.out.println("重新设置之后，线程1取到的值：" + d);
            System.out.println("线程1执行结束");
        }).start();

        Thread.sleep(5000L); // 等待所有线程执行结束
        
        v = value.get();
        System.out.println("线程1执行之后，主线程取到的值：" + v);
    }

    public static void main(String[] args) throws Exception{
        new ThreadLocalDemo().ThreadLocalTest();
    }
}