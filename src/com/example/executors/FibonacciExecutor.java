package com.example.executors;

public class FibonacciExecutor {

	private int count;

	public FibonacciExecutor(int count) {
		super();
		if (count < 0) {
			throw new IllegalArgumentException();
		}
		this.count = count;
	}

	public Runnable createTask() {
		return () -> fibonacciTask();
	}

	private void fibonacciTask() {
		int remaining = this.count;

		if (remaining-- == 0) {
			return;
		}
		System.out.printf("Fibonacci %d%n", 0);

		if (remaining-- == 0) {
			return;
		}
		System.out.printf("Fibonacci %d%n", 1);

		long n0 = 0;
		long n1 = 1;

		do {
			long n2 = n0 + n1;
			System.out.printf("Fibonacci %d%n", n2);
			n0 = n1;
			n1 = n2;
			//viene bloccato nel momento di shutDownNow invoked
			if(Thread.currentThread().isInterrupted()) {
				break;
			}
		} while (remaining-- >= 0);
		System.out.printf("Task fibonacci completed at %d%n", System.currentTimeMillis());
	}
}
