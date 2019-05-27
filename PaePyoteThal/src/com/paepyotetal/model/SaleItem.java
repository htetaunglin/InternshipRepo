package com.paepyotetal.model;

public class SaleItem {
	Item item;
	int amount;

	public SaleItem() {

	}

	public int showHeader() {
		return 0;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

}
