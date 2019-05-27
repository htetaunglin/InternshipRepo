package com.paepyotetal.util;

import java.util.Scanner;

public class TheUtils {
	private static Scanner scan = new Scanner(System.in);

	public static String readString(String message) {
		System.out.print(message);
		return scan.nextLine().trim();
	}

	public static int readInt(String message) {
		return Integer.parseInt(readString(message));
	}

}
