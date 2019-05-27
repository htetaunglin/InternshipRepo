package com.paepyotetal.model;

public class ItemForCount extends Item {
	private double unitPrice;

	@Override
	public double getTotal(double amount) {
		return unitPrice * amount;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

}
