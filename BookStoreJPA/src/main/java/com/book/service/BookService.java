package com.book.service;

import java.util.Date;
import java.util.List;

import com.bookstore.entity.Book;

public interface BookService {

	void createORUpdate(Book book);

	Book findBook(int bookID);

	void deleteBook(int bookID);

	List<Book> getAllBook();

	List<Book> searchBook(String name, Date startDate, Date endDate);
}
