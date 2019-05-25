package com.bookstore.service;

import java.time.LocalDate;

import java.util.List;

import com.bookstore.domain.Book;
import com.bookstore.repository.BookRepository;
import com.bookstore.repository.BookRepositoryImpl;

public class BookServiceImpl implements BookService {

	private static BookRepository repo;
	private static BookServiceImpl service;

	private BookServiceImpl() {
		repo = BookRepositoryImpl.getInstance();
	}

	public static BookServiceImpl getInstance() {
		if (null == service)
			service = new BookServiceImpl();
		return service;
	}

	@Override
	public void createORUpdate(Book book) {
		repo.createORUpdate(book);
	}

	@Override
	public Book findByID(int id) {
		return repo.findByID(id);
	}

	@Override
	public void delete(int id) {
		repo.delete(id);
	}

	@Override
	public List<Book> getAll() {
		return repo.getAll();
	}

	@Override
	public List<Book> search(String name) {
		return repo.search(name);
	}

	@Override
	public List<Book> search(LocalDate startDate, LocalDate endDate) {
		return repo.search(startDate, endDate);
	}

	@Override
	public List<Book> search(String name, LocalDate startDate, LocalDate endDate) {

		return repo.search(name, startDate, endDate);
	}

}
