package com.onica.books.library.model;

/**
 * Represents book model.
 *
 * @author Shady.Salah
 * @version 1.0
 */

public class Book {

	public int id;
	public String title;
	public String author;
	public String category;
	public String description;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return id + " " + title + " " + author + " " + category + " " + description;
	}

}
