package com.paepyotetal.model;

public enum ItemCategory {
	PYEPYOTE("Pyae Pyote"), NANPYAR("Nan Pyar"), EKYARKWAY("Ekyar Kway");

	private String value;

	private ItemCategory(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return value;
	}
}
