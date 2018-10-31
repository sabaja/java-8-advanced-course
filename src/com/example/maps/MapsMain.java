package com.example.maps;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

/**
 * Aggiungo un valore random double a una mappa con le chiavi indicate e se
 * trova un conflitto lo somma tramite il merge bifunction
 * 
 * @author SabatiniJa
 *
 */
public class MapsMain {

	public static void main(String[] args) {
		Map<String, Double> map = new HashMap<>();
		Random rand = new Random();
		try (Scanner sc = new Scanner(System.in)) {
			System.out.println(
					"Aggiungo un valore random double a una mappa con le chiavi indicate e se trova un conflitto lo somma tramite il merge bifunction");
			do {
				String line = sc.nextLine();
				if (line.isEmpty())
					break;

				double amount = rand.nextDouble() * 100.0;
				// vedi documentazione del metod merge di Map
				map.merge(line, amount, (oldV, newV) -> oldV + newV);
				System.out.printf("Aggiunto %.2f a %s.%n", amount, line);
			} while (true);
		}
		// qui posso usare forech
		map.forEach((k, v) -> System.out.printf("%s %.2f\n", k, v));
	}

}
