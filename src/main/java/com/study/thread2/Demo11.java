package com.study.thread2;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Demo11 {

private static Integer doTaskWithResultInWorker() {
    Callable<Integer> callable = new Callable<Integer>() {
        @Override
        public Integer call() throws Exception {
            System.out.println("子线程开始执行...");
            Thread.sleep(1000);
            int result = 0;
            for (int i=0; i<=100; i++) {
                result += i;
            }
            System.out.println("子线程执行完毕.");
            return result;
        }
    };
    FutureTask<Integer> futureTask = new FutureTask<>(callable);
    new Thread(futureTask).start();
    
    Integer ret = 0;
    try {
        System.out.println("等待子线程执行并返回 futureTask.get()...");
        ret = futureTask.get();
        System.out.println("子线程执行并返回 futureTask.get().");
    } catch (InterruptedException e) {
        e.printStackTrace();
    } catch (ExecutionException e) {
        e.printStackTrace();
    }
    
    return ret;
}

public static void main(String[] args) {
	ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>();
    System.out.println("主线程开始执行...");
    Integer ret = doTaskWithResultInWorker();
    System.out.println("子线程执行完毕并返回结果： " + ret);
    System.out.println("主线程开始执行.");
}
}