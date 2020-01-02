package com.study.thread.pool;

import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 线程池实现类
 * 
 * @author Super Yu
 *
 */
public class ThreadPoolManager implements ThreadPool {

    // 线程池中默认线程的个数为5
    private static Integer workerNum = 5;
    
    // 工作线程数组
    WorkThread[] workThreads;
    
    // 正在执行的线程任务数量
    private static volatile Integer executeTaskNumber = 0;
    
    // 任务队列, 作为一个缓冲
    private Queue<Runnable> taskQueue = new ConcurrentLinkedQueue<>();
    
    // 单例模式
    private static ThreadPoolManager threadPool;

    private AtomicLong threadNum = new AtomicLong();

    private ThreadPoolManager() {
        this(ThreadPoolManager.workerNum);
    }
    
    private ThreadPoolManager(int workerNum) {
        if (workerNum > 0) {
            ThreadPoolManager.workerNum = workerNum;
        }
        workThreads = new WorkThread[ThreadPoolManager.workerNum];
        for (int i = 0; i < ThreadPoolManager.workerNum; i++) {
            workThreads[i] = new WorkThread();
            Thread thread = new Thread(workThreads[i], "ThreadPool-worker-" + threadNum.incrementAndGet());
            thread.start();
            System.out.println("初始化线程总数：" + (i+1) + ",当前线程名称是：ThreadPool-worker-" + threadNum);
        }
    }

    public static ThreadPool getThreadPool() {
        return getThreadPool(workerNum);
    }

    public static ThreadPool getThreadPool(int workerNum) {
        if (workerNum > 0) {
            ThreadPoolManager.workerNum = workerNum;
        }
        if (threadPool == null) {
            threadPool = new ThreadPoolManager(ThreadPoolManager.workerNum);
        }
        return threadPool;
    }
    
    @Override
    public void execute(Runnable task) {
        synchronized (taskQueue) {
        	taskQueue.add(task);
        	taskQueue.notifyAll();
		}
    }

    @Override
    public void execute(Runnable[] tasks) {
    	execute(Arrays.asList(tasks));
    }

    @Override
    public void execute(List<Runnable> tasks) {
    	synchronized (taskQueue) {
            for (Runnable task : tasks) {
                 taskQueue.add(task);
            }
            taskQueue.notifyAll();
        }
    }

    @Override
    public int getExecuteTaskNumber() {
        return executeTaskNumber;
    }

    @Override
    public int getWaitTaskNumber() {
        return taskQueue.size();
    }

    @Override
    public int getWorkThreadNumber() {
        return workerNum;
    }

    @Override
    public void destroy() {
    	while (!taskQueue.isEmpty()) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < workThreads.length; i++) {
            workThreads[i].shutdown();
            workThreads[i] = null;
        }
        threadPool = null;
        taskQueue.clear();
    }
    
    @Override
    public String toString() {
        return "ThreadPoolManager {" +
                "当前的工作线程数量=" + getWorkThreadNumber() +
                ", 已完成的任务数=" + getExecuteTaskNumber() +
                ", 等待任务数=" + getWaitTaskNumber() +
                '}';
    }
    
    /**
     * 工作线程
     * @author Super Yu
     */
    class WorkThread implements Runnable {
        // 线程是否可用
        private boolean isRunning = true;
        
        @Override
        public void run() {
            Runnable r = null;
            while (isRunning) {
                // 队列同步机制，加锁（从队列头部取任务执行，并移除该任务）
                synchronized (taskQueue) {
                    while (isRunning && taskQueue.isEmpty()) {
                        try {
                            taskQueue.wait(20);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    if (!taskQueue.isEmpty()) {
                        r = taskQueue.poll();
                    }
                }
                if (r != null) {
                    r.run();
                }
                executeTaskNumber++ ;
                r = null;
            }
        }
        
        public void shutdown() {
            isRunning = false;
        }
    }

}
