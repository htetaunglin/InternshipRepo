package com.rebotic.util;

import java.util.Scanner;

public class TheUtils {

	private TheUtils() {
	}

	private static Scanner scanner = new Scanner(System.in);

	public static String teachString(String message) {
		System.err.print(message);
		return scanner.nextLine();
	}

	public static String readString(String message) {
		System.out.print(message);
		return scanner.nextLine();
	}

}
