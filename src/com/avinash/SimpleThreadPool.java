package com.avinash;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimpleThreadPool {

	public static void main(String[] args) {
		
		//Create a fiexed thread pool of 5 threads using Executors static function
		ExecutorService executor = Executors.newFixedThreadPool(5); 
		
		// Lets create 10 worker threads 
		for(int i=1;i<=10;i++) {
			Runnable worker = new WorkerThread("Command::"+i);
			executor.execute(worker);
		}
		
		//Now shutdown the executor 
		executor.shutdown();
		
		//While executor is not shutting down wait
		while(executor.isTerminated()) {
			int i=1;
			System.out.println("Waiting ....."+i);
		}
		
		

	}
	
	

}
