package com.avinash.executor_service_and_thread_pool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorsExample {

	public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        Runnable task = () -> {
          System.out.println("Executing Task At " + System.nanoTime());
        };
        
        Runnable task1 = () -> {
            System.out.println("Executing Task1 At " + System.nanoTime());
          };

          Runnable task2 = () -> {
              System.out.println("Executing Task2 At " + System.nanoTime());
            };
        System.out.println("Submitting task at " + System.nanoTime() + " to be executed after 5 seconds.");
        scheduledExecutorService.schedule(task, 5, TimeUnit.SECONDS);
        scheduledExecutorService.schedule(task1, 3, TimeUnit.SECONDS);
        scheduledExecutorService.schedule(task2, 2, TimeUnit.SECONDS);
        
        scheduledExecutorService.shutdown();
    }
}
