package com.example.stream;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAccessor;
import java.util.List;

import com.examples.sales.data.DataAccessObject;
import com.examples.sales.data.Retail;

public class Stream1 {
	public static void main(String[] args) {
		List<Retail> rets;

		try (DataAccessObject dao = new DataAccessObject()) {
			rets = dao.findRetails();
		}
		LocalTime start = LocalTime.now();

		rets.stream().filter(ret -> !ret.getRetailDescription().startsWith("TORINO"))
				.map(ret -> ret.getRetailDescription())
				// ordino statefull
				.sorted().map((rd) -> rd.split("\\s")[0])
				.forEach(ret -> System.out.printf("%s (%s)\n", ret, Thread.currentThread().getName()));

		System.out.println();

		rets.stream().filter(ret -> !ret.getRetailDescription().startsWith("TORINO"))
				.map(ret -> ret.getRetailDescription())
				// ordino statefull
				.sorted()
				// controllo che non sia vuoto il retail
				.filter(ret -> !ret.isEmpty())
				// Prendo il primo elemento della parola
				.map((rd) -> rd.split("\\s")[0])
				// Distinct di tutti gli elementi statefull
				.distinct().forEach(ret -> System.out.printf("%s (%s)\n", ret, Thread.currentThread().getName()));
		LocalTime end = LocalTime.now();
		System.out.println(
				"start: " + start + " \nend: " + end + "\nmillis between: " + ChronoUnit.MILLIS.between(start, end));

	}
}
