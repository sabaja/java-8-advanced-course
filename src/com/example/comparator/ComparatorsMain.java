package com.example.comparator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * java.util.function
 * 
 * *****Interfaccie funzionali convenzioni***** 
 * Predicate: Boolean di ritorno 
 * Function: qualunque tipo di ritorno tranne void 
 * Consumer: ritorno void 
 * 
 * 
 * *****Numero di parametri******* 
 * nessun Parametro: Supplier 
 * 1 parametro     : vedi sopra
 * 2 parametri     : BiFunction, BiPredicate etc..
 * 
 * Un parametro tipo:
 * Int
 * Long
 * Double
 * Boolean
 * 
 * Binary:
 * due parametri
 * 
 * Operator:
 * tutti i parametri e return type sono dello stesso tipo.
 * 
 * @author SabatiniJa
 *
 */
public class ComparatorsMain {

	public static final Comparator<String> INGNORE_CASE_COMAPATOR = (String t1, String t2) -> {
		return t1.compareTo(t2);
	};

	public static void main(String[] args) {

		// Comparator si usa per ordered (ordinato in una sequeza di cose da fare) e per
		// sortered (Oridanto secondo una logica)
		List<String> lists = new ArrayList<>();

		// try with resource
		try (Scanner sc = new Scanner(System.in);) {
			String line;
			System.out.println("Inserire una stringa invio per uscire:\n");
			do {
				line = sc.nextLine();
				// condizione di uscita invia senza scrivere nulla
				if (line.isEmpty()) {
					break;
				}
				lists.add(line);
			} while (true);
		}

		// ulizzo il mio comparator
		lists.sort(INGNORE_CASE_COMAPATOR);
		System.out.printf("Parole ordinate:\n%s", lists);
	}
}
