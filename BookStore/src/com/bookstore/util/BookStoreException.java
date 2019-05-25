package com.bookstore.util;

public class BookStoreException {

	private BookStoreException() {

	}

	@SuppressWarnings("serial")
	public static class BookNotFoundException extends BaseException {
		public BookNotFoundException(String message) {
			super(message);
		}
	}
}
