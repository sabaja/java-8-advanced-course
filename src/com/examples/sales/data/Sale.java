package com.examples.sales.data;

import java.time.LocalDate;

public class Sale {
	private Item item;
	private LocalDate date;
	private Retail retail;
	private int quantity;

	public Sale() {
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Retail getRetail() {
		return retail;
	}

	public void setRetail(Retail retail) {
		this.retail = retail;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
