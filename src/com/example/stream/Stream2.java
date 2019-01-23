package com.example.stream;

import java.text.Collator;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import com.examples.sales.data.DataAccessObject;
import com.examples.sales.data.Item;

public class Stream2 {
	public static void main(String[] args) {
		List<Item> items;
		// Ordine alfabetico in Italiano
		// la class java.text.Collator implementa
		// java.util.Comparator<String>
		Collator it = Collator.getInstance(Locale.ITALIAN);

		try (DataAccessObject dao = new DataAccessObject()) {
			items = dao.findItems();
		}
		LocalTime start = LocalTime.now();

		items.stream()
				// estraggo la descrizione
				.map(i -> i.getItemDescription())
				// considero ciò che NON è MOUSEPAD
				.filter(i -> !i.contains("MOUSEPAD"))
				// estraggo la prima parola
				.map(str -> str.split("\\s")[0])
				// non ripetuto distinct
				.distinct()
				// ordino secondo la lingua Italiana
				.sorted(it)
				// faccio il sysout
				.forEach(id -> System.out.printf("%s [%s]%n", id, Thread.currentThread().getName()));

		LocalTime end = LocalTime.now();
		System.out.println(
				"\nstart: " + start + " \nend: " + end + "\nmillis between: " + ChronoUnit.MILLIS.between(start, end));

		//Secondo esempio con findAny/findFirst
		System.out.println();
		// quando uso findFisrt o findAny [ritornano Optional] sono obbligato ad
		// assegnare ed eventualmente definere la variabile pri8ma dell'esspressione che
		// ne calocla il valore
		Item item;
		item = items.parallelStream()
				.filter(i -> i.getItemDescription().contains("USB"))
				.findAny()
				.orElse(null);
		
		if (Objects.nonNull(item)) {
			System.out.printf("Ho trovato: %s.%n", item.getItemDescription());
		}else {
			System.out.println("Non ho trovato nulla");
		}

		
	}
}
