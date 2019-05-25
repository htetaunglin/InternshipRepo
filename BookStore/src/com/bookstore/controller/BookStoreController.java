package com.bookstore.controller;

import java.time.LocalDate;
import java.util.List;

import com.bookstore.domain.Book;
import com.bookstore.service.BookService;
import com.bookstore.service.BookServiceImpl;
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
		} while ("y".equals(BookUtils.readString("Do you want to do next again (y/n)?")));

		showHearderOrFooter(false);
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

	private int showMenu() {
		String[] menu = { "1. SHOW ALL", "2. ADD NEW BOOK", "3. UPDATE BOOK", "4. DELETE BOOK", "5. SEARCH BOOK" };
		for (String m : menu) {
			System.out.println(m);
		}
		System.out.println();
		return BookUtils.readInt("Choose One Option 1 to 5 : ");
	}

	private void doOperation(BookOperation operation) {
		switch (operation) {
		case SHOWALL:
			search(true);
			break;
		case CREATE:
			createOrUpdate(true);
			break;
		case UPDATE:
			createOrUpdate(false);
			break;
		case DELETE:
			delete();
			break;
		case SEARCH:
			search(false);
			break;

		default:
			System.out.println("Invalid Operation");
			break;
		}
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

		if (null != books) {
			books.forEach(book -> {
				System.out.println(String.format(data, book.getId(), book.getName(), book.getAuthor(),
						book.getReleasedDate(), book.getPrice()));
			});
		}
		System.out.println(line);
	}

	private void createOrUpdate(boolean isCreate) {

		Book book = null;

		if (!isCreate) {
			book = service.findByID(BookUtils.readInt("Enter Book ID : "));
			book.setName(BookUtils.readString("Book Name : "));
			book.setAuthor(BookUtils.readString("Author Name : "));
			book.setReleasedDate(BookUtils.readDate("Released Date : "));
			book.setPrice(BookUtils.readInt("Price : "));
		} else {
			book = new Book();
			book.setName(BookUtils.readString("Book Name : "));
			book.setAuthor(BookUtils.readString("Author Name : "));
			book.setReleasedDate(BookUtils.readDate("Released Date : "));
			book.setPrice(BookUtils.readInt("Price : "));
		}
		service.createORUpdate(book);
	}

	private void delete() {
		service.delete(BookUtils.readInt("Enter Book ID : "));
	}

	private void search(boolean flag) {
		if (flag) {
			showAll(service.getAll());
		} else {

			String bookName = BookUtils.readString("Enter Book Name : ");
			LocalDate startDate = BookUtils.readDate("Enter Start Date : ");
			LocalDate endDate = BookUtils.readDate("Enter End Date : ");

			showAll(service.search(bookName, startDate, endDate));
		}
	}
}
