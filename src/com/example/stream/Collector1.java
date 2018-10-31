package com.example.stream;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.examples.sales.data.DataAccessObject;
import com.examples.sales.data.Item;
import com.examples.sales.data.Retail;
import com.examples.sales.data.Sale;

public class Collector1 {

	public static void main(String[] args) {
		List<Sale> sales;
		List<Retail> rets;

		try (DataAccessObject dao = new DataAccessObject()) {
			sales = dao.findSales();
			rets = dao.findRetails();
		}

		// simulo il manager che vuole vedere il suo report con l'Elenco delle vendite
		// vogliamo estrarre una list di Items venduti
		Retail selectedRet = rets.get(0);
		System.out.printf("Il manager ha scelto %s.%n", selectedRet.getRetailDescription());

		List<Item> result = sales.stream()
				// il manager vuole sapre quali sono i prodotti di questo retail
				.filter(s -> s.getRetail() == selectedRet)
				// che hanno scontrino con 9 pezzi
				.filter(s -> s.getQuantity() == 9)
				// qualis sono i prodotti
				.map(sal -> sal.getItem())
				// non ripetuti
				.distinct()
				// Come findAny sum etc bisogna assegnare il result in una variabile
				// genero il risultato
				.collect(Collectors.toList());
		System.out.printf("Ho trovato %d prodotti:\n", result.size());
		result.forEach( e -> System.out.println(e.getItemDescription()));
		
	}

}
