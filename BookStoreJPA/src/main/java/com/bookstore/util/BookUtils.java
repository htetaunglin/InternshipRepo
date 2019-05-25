package com.bookstore.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class BookUtils {
	private static Scanner scan = new Scanner(System.in);
	private static SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");

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

	public static Date readDate(String message) {
		for (;;) {
			try {
				return df.parse(readString(message));
			} catch (Exception e) {
				return null;
			}
		}
	}
}
