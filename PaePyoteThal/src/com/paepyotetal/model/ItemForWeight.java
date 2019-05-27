package com.paepyotetal.model;

public class ItemForWeight extends Item {

	private double smallUnit;// 5 kyat thar
	private double smallUnitPrice; // 500

	@Override
	public double getTotal(double amount) {
		return (int) (smallUnitPrice * (amount / smallUnit));
	}

	public double getSmallUnit() {
		return smallUnit;
	}

	public void setSmallUnit(double smallUnit) {
		this.smallUnit = smallUnit;
	}

	public double getSmallUnitPrice() {
		return smallUnitPrice;
	}

	public void setSmallUnitPrice(double smallUnitPrice) {
		this.smallUnitPrice = smallUnitPrice;
	}

}
