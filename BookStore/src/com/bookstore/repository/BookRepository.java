package com.bookstore.repository;

import java.time.LocalDate;
import java.util.List;

import com.bookstore.domain.Book;

public interface BookRepository {

	void createORUpdate(Book book);

	Book findByID(int id);

	void delete(int id);

	List<Book> getAll();

	List<Book> search(String name);

	List<Book> search(LocalDate startDate, LocalDate endDate);

	List<Book> search(String name, LocalDate startDate, LocalDate endDate);

}
