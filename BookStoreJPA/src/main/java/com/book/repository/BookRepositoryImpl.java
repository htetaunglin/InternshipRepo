package com.book.repository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.bookstore.entity.Book;

public class BookRepositoryImpl implements BookRepository {

	private static BookRepositoryImpl repo;

	private EntityManagerFactory emf;
	private EntityManager em;

	// singleton pattern
	private BookRepositoryImpl() {
		emf = Persistence.createEntityManagerFactory("BookStoreJPA");
		em = emf.createEntityManager();
	}

	public static BookRepositoryImpl getInstance() {
		if (repo == null)
			repo = new BookRepositoryImpl();
		return repo;
	}

	@Override
	public void createORUpdate(Book book) {

		Book b = findBook(book.getID());
		if (b == null) {
			// create new book
//			System.out.println("HERE " + book.getName());
			em.getTransaction().begin();
			em.persist(book);
			em.getTransaction().commit();
		} else {
			// update existing book
			b.setName(book.getName());
			b.setAuthor(book.getAuthor());
			b.setReleasedDate(book.getReleasedDate());
			b.setPrice(book.getPrice());
		}
//		em.close();
//		emf.close();
	}

	@Override
	public Book findBook(int bookID) {
		return em.find(Book.class, bookID);
	}

	@Override
	public void deleteBook(int bookID) {
		em.getTransaction().begin();
		em.remove(findBook(bookID));
		em.getTransaction().commit();
	}

	@Override
	public List<Book> getAllBook() {
		return em.createQuery("SELECT b FROM Book b", Book.class).getResultList();
	}

	@Override
	public List<Book> searchBook(String name, Date startDate, Date endDate) {
		String query = "SELECT b FROM Book b WHERE ";

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String sDate = "";
		String eDate = "";
		if (startDate != null) {
			sDate = df.format(endDate).toString();
		}

		if (endDate != null) {
			eDate = df.format(endDate).toString();
		}

		if (name.isEmpty()) {
			if (startDate == null) {
				// only endDate valid
				query += "releasedDate >= " + eDate + "";
			} else if (endDate == null) {
				// only startDate valid
				query += "releasedDate <= " + sDate + "";
			} else {
				// startDate and endDate are valid
				query += "releasedDate BETWEEN " + sDate + " AND " + sDate;
			}
		} else if (startDate == null) {
			if (endDate == null) {
				// only name valid
				query += "name LIKE '%" + name + "%'";
			} else {
				// name and endDate are valid
				query += "name LIKE '%" + name + "%' AND releasedDate >= " + eDate;
			}
		} else if (endDate == null) {
			// name and startDate are valid
			query += "name LIKE '%" + name + "%' AND releasedDate <= " + sDate;
		} else {
			// All are valid
			query += "name LIKE '%" + name + "%' AND releasedDate BETWEEN " + sDate + " AND " + sDate;
		}
		return em.createQuery(query, Book.class).getResultList();
	}
}
