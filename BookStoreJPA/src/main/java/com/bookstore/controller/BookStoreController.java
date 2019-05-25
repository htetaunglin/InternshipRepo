package com.bookstore.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.book.service.BookService;
import com.book.service.BookServiceImpl;
import com.bookstore.entity.Book;
import com.bookstore.util.BookUtils;

public class BookStoreController {
	private BookService service;

	public BookStoreController() {
		service = BookServiceImpl.getInstance();
	}

	public void run() {
		showHearderOrFooter(true);

		do {
			BookOperation operation = BookOperation.values()[showMenu() - 1];
			doOperation(operation);
		} while ("y".equals(BookUtils.readString("Do you want to do next again (y/n)? : ")));

		showHearderOrFooter(false);
	}

	private int showMenu() {
		String[] menu = { "1. SHOW ALL", "2. ADD NEW BOOK", "3. UPDATE BOOK", "4. DELETE BOOK", "5. SEARCH BOOK" };
		for (String m : menu) {
			System.out.println(m);
		}
		System.out.println();
		return BookUtils.readInt("Choose One Option 1 to 5 : ");
	}

	private void showHearderOrFooter(boolean start) {
		System.out.println("***************************");
		if (start) {
			System.out.println("********BOOK STORE*********");
		} else {
			System.out.println("********THANK YOU**********");
		}
		System.out.println("***************************");
	}

	private void search(boolean flag) {
		if (flag) {
			showAll(service.getAllBook());
		} else {
			String name = BookUtils.readString("Enter Book Name : ");
			Date startDate = BookUtils.readDate("Enter Start Date : ");
			Date endDate = BookUtils.readDate("Enter End Date : ");
			showAll(service.searchBook(name, startDate, endDate));
		}
	}

	private void deleteBook() {
		service.deleteBook(BookUtils.readInt("Enter Book ID : "));
	}

	private void doOperation(BookOperation operation) {
		switch (operation) {
		case CREATE:
			createORUpdate(true);
			break;
		case UPDATE:
			createORUpdate(false);
			break;
		case DELETE:
			deleteBook();
			break;
		case SHOWALL:
			search(true);
			break;
		case SEARCH:
			search(false);
			break;
		default:
			break;
		}
	}

	private void createORUpdate(boolean isCreate) {
		Book book = new Book();
		if (!isCreate)
			book = service.findBook(BookUtils.readInt("Enter Book ID : "));

		book.setName(BookUtils.readString("Enter book Name : "));
		book.setReleasedDate(BookUtils.readDate("Enter released date : "));
		book.setAuthor(BookUtils.readString("Enter author : "));
		book.setPrice(BookUtils.readInt("Enter price : "));
		service.createORUpdate(book);
	}

	private void showAll(List<? extends Book> books) {
		String header = String.format("%-5s%-20s%-20s%-15s%-10s%n", "ID", "NAME", "AUTHOR", "RELEASE DATE", "PRICE");

		String data = "%-5d%-20s%-20s%-15s%-10d%n";

		System.out.printf("%30s%n", "BOOK LIST");

		String line = "";
		for (int i = 0; i < header.length(); i++) {
			line += "*";
		}

		System.out.println(line);
		System.out.print(header);
		System.out.println(line);
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

		if (null != books) {
			books.forEach(book -> {
				System.out.println(String.format(data, book.getID(), book.getName(), book.getAuthor(),
						df.format(book.getReleasedDate()), book.getPrice()));
			});
		}
		System.out.println(line);
	}
}
