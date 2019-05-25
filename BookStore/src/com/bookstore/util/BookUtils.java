package com.bookstore.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class BookUtils {

	private static Scanner scan = new Scanner(System.in);
	private static DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy");

	private BookUtils() {
	}

	public static String readString(String message) {
		System.out.print(message);
		return scan.nextLine().trim();
	}

	public static int readInt(String message) {
		for (;;) {
			try {
				return Integer.parseInt(readString(message));
			} catch (NumberFormatException e) {
				System.out.println("Enter digit only 1 to 5.");
			}
		}
	}

	public static LocalDate readDate(String message) {
		try {
			return LocalDate.parse(readString(message), df);
		} catch (Exception e) {
			return null;
		}
		
	}
}
