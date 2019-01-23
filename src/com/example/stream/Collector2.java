package com.example.stream;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.examples.sales.data.DataAccessObject;
import com.examples.sales.data.Item;
import com.examples.sales.data.Retail;
import com.examples.sales.data.Sale;

public class Collector2 {

	public static void main(String[] args) {
		List<Sale> sales;
		Map<Item, Integer> mapItems;

		try (DataAccessObject dao = new DataAccessObject()) {
			sales = dao.findSales();
		}

		// Con il metodo merge delle lambda function si popola un a Map<String, Double>
		// con Stream sulla list sales possiamo fare una cosa simile

		mapItems = sales.stream()
				// la sintassi del Collectors.toMap è simile al merge di Map; la differenza è
				// che non ho direttamente la chiave e il valore , ma ho 2 regole che sono
				// scritte come lambda expression per ricavare la key e value a partire da un
				// elemento dello stream
				.collect(Collectors.toMap(sal -> sal.getItem(), sal -> sal.getQuantity(), (oldv, newv) -> oldv + newv));

		mapItems.forEach((k, v) -> System.out.printf("Il prodotto %s ha venduto %d%n", k.getItemDescription(), v));

		// secondo esempio
		System.out.println();
		Map<Retail, Integer> mapRetails;

		mapRetails = sales.stream()
				// Eventuali filtri si possono mettere qui ex: cis si può limitare a un solo
				// prodotto o a un insieme di prodotti
				.filter(sal -> true)
				.collect(
						Collectors.toMap(
								sal -> sal.getRetail(), 
								sal -> sal.getQuantity(), 
								(oldv, newv) -> oldv + newv));
		
		mapRetails.forEach((k, v) -> System.out.printf("Il punto vendita %s ha venduto %d pezzi%n", k.getRetailDescription(), v));
	}

}
