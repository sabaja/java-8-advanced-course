package com.example.forkjoin;

import java.util.concurrent.RecursiveTask;

public class SummingTask extends RecursiveTask<Double> {
	// array su cui sto operando
	private double[] array;

	// indice minimo incluso e massimo escluso
	private int lo;
	private int hi;

	private SummingTask(double[] array, int lo, int hi) {
		super();
		// perchè costruttore è privato
		assert hi - lo > 0;
		this.array = array;
		this.lo = lo;
		this.hi = hi;
	}

	// nota bene: RecursiveTask implenta Serializable nel caso finisca la memoria si
	// serializza i thread e poi ripresi succesivamente
	private static final long serialVersionUID = -6073561175227213080L;

	public SummingTask(double[] array) {
		this.array = array;
		this.lo = 0;
		this.hi = array.length;
		// in alternativa, posso delegare l'altro costruttore
		// this(array, 0, arra.length)
	}

	@Override
	protected Double compute() {
		switch (hi - lo) {
		case 1:
			return array[lo];
		case 2:
			return array[lo] + array[lo + 1];

		default:
			break;

		}
		int mi = (lo + hi) / 2;
		SummingTask left = new SummingTask(array, lo, mi);
		SummingTask right = new SummingTask(array, mi, hi);
		left.fork();
		right.fork();

		double leftValue = left.join();
		double rightValue = right.join();

		return rightValue + leftValue;
	}

}
