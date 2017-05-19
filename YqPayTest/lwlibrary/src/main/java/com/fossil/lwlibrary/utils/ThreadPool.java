package com.fossil.lwlibrary.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Kam Wong Division on 17/5/12.
 */

public class ThreadPool {

    private ExecutorService pool = Executors.newCachedThreadPool();

    private static final ThreadPool threadPool = new ThreadPool();

    public static ThreadPool getInstance() {
        return threadPool;
    }

    public void executeTask(Runnable task) {
        pool.execute(task);
    }
}
