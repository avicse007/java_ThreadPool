package com.avinash.threadpoolexecutor.example;

import java.util.concurrent.ThreadPoolExecutor;


/*
 * ThreadPoolExecutor provides several methods using which we can find 
 * out the current state of the executor, pool size, active thread 
 * count and task count. So I have a monitor thread that will print
 * the executor information at a certain time interval.
 */

public class MyMonitorThread implements Runnable{
	
	private int delay;
	
	private ThreadPoolExecutor executor;

	private boolean run;
	
	
	public MyMonitorThread(int delay, ThreadPoolExecutor executor) {
		this.delay = delay;
		this.executor = executor;
	}
	
	@Override
	public void run() {
		if(run) {
		 System.out.println(
                 String.format("[monitor] [%d/%d] Active: %d, Completed: %d, Task: %d, isShutdown: %s, isTerminated: %s",
                     this.executor.getPoolSize(),
                     this.executor.getCorePoolSize(),
                     this.executor.getActiveCount(),
                     this.executor.getCompletedTaskCount(),
                     this.executor.getTaskCount(),
                     this.executor.isShutdown(),
                     this.executor.isTerminated()));
             try {
                 Thread.sleep(delay*1000);
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
		}
	}

	public void shutdown() {
		this.run =false;
		
	}

}
