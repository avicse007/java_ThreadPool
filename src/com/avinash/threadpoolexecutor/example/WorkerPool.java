package com.avinash.threadpoolexecutor.example;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.avinash.WorkerThread;

/*
 * Executors class provide simple implementation of ExecutorService using ThreadPoolExecutor
 * but ThreadPoolExecutor provides much more feature than that. We can specify the number of
 * threads that will be alive when we create ThreadPoolExecutor instance and we can limit 
 * the size of thread pool and create our own RejectedExecutionHandler implementation to 
 * handle the jobs that can’t fit in the worker queue.
 * 
 */




public class WorkerPool {
	
	public static void main(String[] args) throws InterruptedException {
		
		 //RejectedExecutionHandler implementation
        RejectedExecutionHandlerImpl rejectionHandler = new RejectedExecutionHandlerImpl();
        //Get the ThreadFactory implementation to use
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        //creating the ThreadPoolExecutor
        ThreadPoolExecutor executorPool = new ThreadPoolExecutor(2, 4, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(2), threadFactory, rejectionHandler);
        //start the monitoring thread
        MyMonitorThread monitor = new MyMonitorThread(1, executorPool);
        Thread monitorThread = new Thread(monitor);
        monitorThread.start();
        //submit work to the thread pool
        for(int i=0; i<10; i++){
            executorPool.execute(new WorkerThread("cmd"+i));
        }
        
        Thread.sleep(3000);
        //shut down the pool
        executorPool.shutdown();
        //shut down the monitor thread
        Thread.sleep(5000);
        monitor.shutdown();
	}

}
