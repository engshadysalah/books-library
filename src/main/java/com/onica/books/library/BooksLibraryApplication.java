package com.onica.books.library;

import com.onica.books.library.bookimp.BookFunctions;

/**
 * Represents the main entry point to initialize the application.
 *
 * @author Shady.Salah
 * @version 1.0
 */

public class BooksLibraryApplication {

	public static void main(String[] args) {

		new BookFunctions().bookManagement();
	}
}
