package com.example.forkjoin;

import java.util.concurrent.ForkJoinPool;

public class ForkJoinMain {

	// Cerchiamo di riprodurre il parallel Stream con la programmazione concorrente.
	//             ----
	//     -----         ----- 
	//  ---     ---   ---    ---
	public static void main(String[] args) {

		ForkJoinPool pool = ForkJoinPool.commonPool();

		double[] array = new double[100];

		for (int i = 0; i < array.length; i++) {
			array[i] = i / 10.0;
		}

		SummingTask root = new SummingTask(array);
		pool.submit(root);

		// mettiamo in join sulla root
		double finalResult = root.join();

		System.out.printf("La somma è: %.2f\n ", finalResult);

	}

}
