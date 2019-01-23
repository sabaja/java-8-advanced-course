package com.example.executors;

public class SquaresExecutor {

	private int count;

	public SquaresExecutor(int count) {
		super();
		if (count < 0) {
			throw new IllegalArgumentException();
		}
		this.count = count;
	}

	public Runnable createTask() {
		return () -> squareTask();
	}
	
	private void squareTask() {
		for(int i = 0 ;  i <count; i++) {
			System.out.printf("Square %d%n", i * i);
		}
		System.out.printf("Task squares completed at %d%n", System.currentTimeMillis());
	}
}
