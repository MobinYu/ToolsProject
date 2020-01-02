package com.study.thread.pool;

import java.util.List;

/**
 * 线程池接口
 * 
 * @author Super Yu
 *
 */
public interface ThreadPool {
	
	// 执行单个线程任务
    void execute(Runnable task);

    // 执行多个任务
    void execute(Runnable[] tasks);

    // 执行多个任务
    void execute(List<Runnable> tasks);

    // 返回已经执行的任务个数
    int getExecuteTaskNumber();

    // 返回等待被处理的任务个数，队列的长度
    int getWaitTaskNumber();

    // 返回正在工作的线程的个数
    int getWorkThreadNumber();

    // 关闭线程池
    void destroy();
}
