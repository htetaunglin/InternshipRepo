package com.bookstore.entity;

import java.io.Serializable;
import java.lang.String;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Book
 *
 */
@SuppressWarnings("serial")
@Entity
public class Book implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ID;
	private String name;
	@Temporal(TemporalType.DATE)
	private Date releasedDate;
	private String author;
	private int price;

	public Book() {
		super();
	}

	public int getID() {
		return this.ID;
	}

	public void setID(int ID) {
		this.ID = ID;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getReleasedDate() {
		return this.releasedDate;
	}

	public void setReleasedDate(Date releasedDate) {
		this.releasedDate = releasedDate;
	}

	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getPrice() {
		return this.price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

}
