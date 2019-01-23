package com.example.string.operators.functions;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.function.UnaryOperator;

/**
 * java.util.function
 * 
 * *****Interfaccie funzionali convenzioni***** Predicate: Boolean di ritorno
 * Function: qualunque tipo di ritorno tranne void Consumer: ritorno void
 * 
 * 
 * *****Numero di parametri******* nessun Parametro: Supplier 1 parametro : vedi
 * sopra 2 parametri : BiFunction, BiPredicate etc..
 * 
 * Un parametro tipo: Int Long Double Boolean
 * 
 * Binary: due parametri
 * 
 * Operator: tutti i parametri e return type sono dello stesso tipo.
 * 
 * @author SabatiniJa
 *
 */
public class OperatorsMain {

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
		lists.replaceAll(StringOperators.upperCaser());
		System.out.printf("Parole In mauiscolo:\n%s", lists);

		// Operatore di prefisso
		lists.replaceAll(StringOperators.prefixer("p"));
		System.out.printf("\nParole con prefisso:\n%s", lists);

		lists.replaceAll(StringOperators.loweCaser());
		System.out.printf("\nParole In minuscolo:\n%s%n", lists);

		//Tolgo tutte le parole che contengono una a
		lists.removeIf(p -> p.contains("a"));
		
		System.out.println("forEach una riga per ogni elemento");
		lists.forEach(e -> System.out.println(e));
		// caching dell'oggetto

	}
}
