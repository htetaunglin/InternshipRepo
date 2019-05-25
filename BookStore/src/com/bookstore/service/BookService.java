package com.bookstore.service;

import java.time.LocalDate;
import java.util.List;

import com.bookstore.domain.Book;

public interface BookService {
	void createORUpdate(Book book);

	Book findByID(int id);

	void delete(int id);

	List<Book> getAll();

	List<Book> search(String name);

	List<Book> search(LocalDate startDate, LocalDate endDate);

	List<Book> search(String name, LocalDate startDate, LocalDate endDate);

}
