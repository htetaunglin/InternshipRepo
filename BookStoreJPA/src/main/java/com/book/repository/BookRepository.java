package com.book.repository;

import java.util.Date;
import java.util.List;

import com.bookstore.entity.Book;

public interface BookRepository {

	void createORUpdate(Book book);

	Book findBook(int bookID);

	void deleteBook(int bookID);

	List<Book> getAllBook();

	List<Book> searchBook(String name, Date startDate, Date endDate);
}
