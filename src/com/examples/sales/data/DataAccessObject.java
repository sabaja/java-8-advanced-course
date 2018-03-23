package com.examples.sales.data;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DataAccessObject implements AutoCloseable {
	private final Map<String, Retail> retails = new HashMap<>();
	private final Map<String, Item> items = new HashMap<>();
	private final List<Sale> sales;

	public DataAccessObject() {
		try {
			this.items.put("ITEM3", newItem("ITEM3", "MONITOR WIDE"));
			this.sales = Files.lines(new File("WS1.txt").toPath())
				.map(line -> line.split("\\t"))
				.map(this::newSale)
				.collect(Collectors.toList());
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}
	
	public List<Sale> findSales() {
		return new ArrayList<>(this.sales);
	}
	
	public List<Item> findItems() {
		return new ArrayList<>(this.items.values());
	}
	
	public List<Retail> findRetails() {
		return new ArrayList<>(this.retails.values());
	}

	private Sale newSale(String[] cols) {
		Retail r = retails.computeIfAbsent(cols[0].trim(), id -> newRetail(id, cols[1].trim()));
		LocalDate date = LocalDate.parse(cols[2]);
		Item i = items.computeIfAbsent(cols[3].trim(), id -> newItem(id, cols[4].trim()));
		int qty = Integer.parseInt(cols[5]);
		Sale s = new Sale();
		s.setRetail(r);
		s.setDate(date);
		s.setItem(i);
		s.setQuantity(qty);
		return s;
	}

	private Item newItem(String id, String desc) {
		Item r = new Item();
		r.setItemId(id);
		r.setItemDescription(desc);
		return r;
	}

	private Retail newRetail(String id, String desc) {
		Retail r = new Retail();
		r.setRetailId(id);
		r.setRetailDescription(desc);
		return r;
	}

	@Override
	public void close() {
		
	}

}
