package com.book.service;

import java.util.Date;
import java.util.List;

import com.book.repository.BookRepository;
import com.book.repository.BookRepositoryImpl;
import com.bookstore.entity.Book;

public class BookServiceImpl implements BookService {

	private static BookRepository repo;
	private static BookServiceImpl service;

	private BookServiceImpl() {
		repo = BookRepositoryImpl.getInstance();
	}

	public static BookServiceImpl getInstance() {
		if (service == null)
			service = new BookServiceImpl();
		return service;
	}

	@Override
	public void createORUpdate(Book book) {
		repo.createORUpdate(book);

	}

	@Override
	public Book findBook(int bookID) {
		return repo.findBook(bookID);
	}

	@Override
	public void deleteBook(int bookID) {
		repo.deleteBook(bookID);

	}

	@Override
	public List<Book> getAllBook() {
		return repo.getAllBook();
	}

	@Override
	public List<Book> searchBook(String name, Date startDate, Date endDate) {
		return repo.searchBook(name, startDate, endDate);
	}

}
