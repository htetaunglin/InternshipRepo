package com.paepyotetal.util;

public class ItemException {

	private ItemException() {

	}

	@SuppressWarnings("serial")
	public static class ItemListDuplicateException extends BaseException {
		public ItemListDuplicateException(String message) {
			super(message);
		}
	}
}
