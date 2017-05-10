package cn.utopay.gblwsdk.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPool {

	private ExecutorService pool = Executors.newCachedThreadPool();

	private static final ThreadPool threadPool = new ThreadPool();

	public static ThreadPool getInstance() {
		return threadPool;
	}

	public void submitTask(Runnable task) {
		pool.execute(task);
	}
}
