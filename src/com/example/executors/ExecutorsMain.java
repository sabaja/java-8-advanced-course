package com.example.executors;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ExecutorsMain {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService pool = Executors.newFixedThreadPool(2);
		FibonacciExecutor fib = new FibonacciExecutor(13);
		SquaresExecutor square = new SquaresExecutor(13);
		Runnable taskFib = fib.createTask();
		Runnable taskSquare = square.createTask();

		pool.submit(taskFib);
		Future<?>future = pool.submit(taskSquare);
		try {
			future.get(100, TimeUnit.MICROSECONDS);
		} catch (TimeoutException e) {
			future.cancel(true);
		}
		
		
		//chiudo la coda non posso aggiungere task
		pool.shutdown();
		System.out.printf("shutdown invoked at %d%n",  System.currentTimeMillis());
		pool.awaitTermination(1, TimeUnit.MILLISECONDS);
		System.out.printf("shutdownNow invoked at %d%n",  System.currentTimeMillis());
		//chiudo i thread e ammazzo le code
		pool.shutdownNow();
	}

}
