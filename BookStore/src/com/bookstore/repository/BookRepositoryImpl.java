package com.bookstore.repository;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import com.bookstore.domain.Book;
import com.bookstore.util.BookStoreException;

public class BookRepositoryImpl implements BookRepository {

	private static List<Book> bookList;
	private static BookRepositoryImpl repo;

	private static int id;

	@SuppressWarnings("unchecked")
	private BookRepositoryImpl() {
		bookList = new ArrayList<>();
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("book.obj"));
			bookList = (List<Book>) ois.readObject();
			ois.close();
		} catch (Exception e) {
			bookList = new ArrayList<>();
		}
	}

	public static BookRepositoryImpl getInstance() {
		if (null == repo)
			repo = new BookRepositoryImpl();
		return repo;
	}

	@Override
	public void createORUpdate(Book book) {
		if (book.getId() > 0) {
			int index = bookList.indexOf(book);
			bookList.set(index, book);
		} else {
			book.setId(++id);
			bookList.add(book);
		}

		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("book.obj"));
			oos.writeObject(bookList);
			oos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Book findByID(int id) {
		return bookList.stream().filter(book -> id == book.getId()).findFirst().get();
	}

	@Override
	public void delete(int id) {
		Book book = findByID(id);
		if (Objects.isNull(book))
			throw new BookStoreException.BookNotFoundException("Book Not Found!");
		else
			bookList.remove(findByID(id));
	}

	@Override
	public List<Book> getAll() {
		return bookList;
	}

	@Override
	public List<Book> search(String name) {
		List<Book> ls = new ArrayList<Book>();
		bookList.forEach(book -> {
			if (book.getName().contains(name)) {
				ls.add(book);
			}
		});
		return ls;
	}

	@Override
	public List<Book> search(LocalDate startDate, LocalDate endDate) {
		List<Book> ls = new ArrayList<Book>();

		bookList.forEach(book -> {
			LocalDate d = book.getReleasedDate();
			if (d.compareTo(startDate) >= 0 && d.compareTo(endDate) <= 0) {
				ls.add(book);
			}
		});
		return ls;
	}

	@Override
	public List<Book> search(String name, LocalDate startDate, LocalDate endDate) {
		List<Book> ls = new ArrayList<Book>(bookList);
		Iterator<Book> itr = ls.iterator();

		while (itr.hasNext()) {
			Book b = itr.next();

			if (!name.isEmpty() && null != startDate && null != endDate) {
				if (!name.contains(b.getName()) && !((b.getReleasedDate().compareTo(startDate)) >= 0)
						&& ((b.getReleasedDate().compareTo(endDate)) <= 0)) {
					itr.remove();
				}
			} else {

				if (!name.isEmpty()) {
					if (!name.contains(b.getName())) {
						itr.remove();
					}
				}

				if (null != startDate) {
					if (!((b.getReleasedDate().compareTo(startDate)) >= 0)) {
						itr.remove();
					}
				}

				if (null != endDate) {
					if (!((b.getReleasedDate().compareTo(endDate)) <= 0)) {
						itr.remove();
					}
				}
			}
		}

		return ls;

	}

}
