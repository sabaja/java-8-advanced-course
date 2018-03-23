package com.example.stream;

import java.util.List;

import com.examples.sales.data.DataAccessObject;
import com.examples.sales.data.Item;
import com.examples.sales.data.Sale;

public class Stream3 {

	public static void main(String[] args) {
		List<Sale> sales;

		try (DataAccessObject dao = new DataAccessObject()) {
			sales = dao.findSales();
		}

		// estraiamo l'informazione quantity
		// sommiamo (totale del venduto per il primo trimestre 2017)
		// come per findFirst e findFirst, anche sum restituisce un valore
		// questo mi obbliga ad assegnare a una variabile prima di dell'espressione

		long quarterTotal = sales.stream().mapToInt(sal -> sal.getQuantity()).sum();
		System.out.printf("quarter Total sales: %d%n", quarterTotal);

		// massimo di pezzi di un singolo prodotto in un unico scontrino
		int maxQuant = sales.stream().mapToInt(sal -> sal.getQuantity()).max().orElse(0);

		System.out.printf("Max qunatity sales: %d%n", maxQuant);

		// secondo esempio
		System.out.println();
		int[] i = {0};
		i[0] = 1;
		sales.stream()
				// seleziono quindi gli elementi sales che hanno quantità uguale
				// alla maxquantity trovata
				.filter(sal -> sal.getQuantity() == maxQuant)
				// estraggo l'informazione item
				.map(sal -> sal.getItem())
				// non mi interessa avere elementi ripetuti
				.distinct()
				// estraggo l'informazione descrizione
				.map(info -> info.getItemDescription())
				// le preferisco in ordine alfabetico
				.sorted()
				// faccio il dump
				.forEach(des -> System.out.printf("[%d] %s%n", i[0]++, des));

	}
}
