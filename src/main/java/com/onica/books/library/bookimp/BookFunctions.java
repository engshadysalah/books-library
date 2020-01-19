package com.onica.books.library.bookimp;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import com.onica.books.library.model.Book;
import com.onica.books.library.util.CSVBookUtil;

/**
 * Represents the container for book functions.
 *
 * @author Shady.Salah
 * @version 1.0
 */

public class BookFunctions {

	/**
	 * Represents a list that contains all books.
	 */
	static List<Book> allBooks;

	private Scanner scanner;

	static final String EDIT_BOOK = "EDIT_BOOK";
	static final String FIND_BOOK = "FIND_BOOK";
	static final String VIEW_BOOK_DETAILS = "VIEW_BOOK_DETAILS";

	/**
	 * Load the list of books from disk, at application start time then execute the
	 * main functions of book.
	 * 
	 */
	public void bookManagement() {

		allBooks = CSVBookUtil.readAllBooksFromCSV();

		if (allBooks.isEmpty()) {
			System.out.println("\n There is no any books to load");
		} else {
			System.out.println("\n Loaded " + allBooks.size() + " books into the library");
		}

		mainBookFuncations();

	}

	/**
	 * Allow the user, through a command line interface, to view, add, find and edit
	 * book entries.
	 * 
	 * @throws java.util.InputMismatchException
	 */
	public void mainBookFuncations() {

		System.out.print("\n ==== Book Manager ==== \n\n" + "	1) View all books \n" + "	2) Add a book \n"
				+ "	3) Edit a book \n" + "	4) Search for a book \n" + "	5) Save and exit\n\n" + "Choose [1-5]: ");

		try {
			scanner = new Scanner(System.in);

			int choice = scanner.nextInt();

			switch (choice) {

			case 1:
				viewAllBooks();
				break;

			case 2:
				addNewBook();
				break;

			case 3:
				mainEditBook();
				break;

			case 4:
				mainFindBook();
				break;

			case 5:
				saveAndExit();
				break;

			default:
				System.out.println("Rong choice, Please try your process again.");
				mainBookFuncations();
			}

		} catch (InputMismatchException ex) {

			System.out.println("\n Your Input not valid, Please try your process again.");
			mainBookFuncations();

		}
	}

	/**
	 * Allow the user, through a command line interface, to view all books in the
	 * file in case isn't empty as List the ID and title of each book and Allow the
	 * user to see details of a particular book or execute the mainBookFuncations()
	 * to allow the user to perform another process.
	 */
	private void viewAllBooks() {

		if (allBooks.isEmpty()) {

			System.out.println("\n There is no any books for viewing");
			mainBookFuncations();

		} else {

			System.out.println("\n ==== View Books ==== \n");

			displayAllBooks();

			repeatBookFuncation("\n To view details enter the book ID, to return press <Enter>.", VIEW_BOOK_DETAILS);

		}
	}

	/**
	 * Allow the user, through a command line interface, to add new book, by
	 * entering the book title, author, category and description then execute the
	 * mainBookFuncations() to allow the user to perform another process.
	 * 
	 */
	void addNewBook() {

		System.out.println("\n ==== Add a Book ==== \n" + "\n Please enter the following information:\n");

		System.out.print("Title: ");
		scanner.nextLine();
		String title = scanner.nextLine();

		System.out.print("Author: ");
		String author = scanner.nextLine();

		System.out.print("Category: ");
		String category = scanner.nextLine();

		System.out.print("Description: ");
		String description = scanner.nextLine();

		Book book = new Book();

		book.setId(getNewBookId());
		book.setTitle(title);
		book.setAuthor(author);
		book.setCategory(category);
		book.setDescription(description);

		allBooks.add(book);

		System.out.println(" \nBook [" + book.getId() + "] Saved \n");

		mainBookFuncations();
	}

	/**
	 * Allow the user, through a command line interface, to find book, by typing in
	 * one or more keywords to search for then allow the user to perform another
	 * search process or back to main book functions to execute another process.
	 * 
	 */
	void mainFindBook() {

		System.out.print("\n Search for a book  \n"
				+ "\n Type in one or more keywords to search for, or <Enter> to return \n\n " + "Search ");

		String keyword = new Scanner(System.in).nextLine();

		if (keyword.length() == 0) {

			mainBookFuncations();

		} else {

			findBookFunction(keyword);

		}

	}

	void findBookFunction(String keyword) {

		boolean isFounded = false;

		for (Book book : allBooks) {

			if (book.toString().toLowerCase().contains(keyword)) {

				isFounded = true;

				System.out.println(
						"\n The following books matched your query. Enter the book ID to see more details, or <Enter> to return.\n");

				System.out.println("	[" + book.getId() + "] " + book.getTitle());

				break;
			}

		}

		if (isFounded) {

			repeatBookFuncation("", FIND_BOOK);

		} else {

			System.out.println("\n Not Founded");

			mainFindBook();
		}

	}

	/**
	 * Allow the user, through a command line interface, Display a list of available
	 * books then then Allow the user to select a book to edit then Display each
	 * field, one at a time, and allow them to change the value of the field or
	 * leave the value unchanged.
	 * 
	 * then allow the user to perform another edit process or back to main book
	 * functions to execute another process.
	 * 
	 * @param bookId
	 */
	void mainEditBook() {
		
		System.out.println();
		displayAllBooks();

		repeatBookFuncation("\n Enter the book ID of the book you want to edit; to return press <Enter>. \n",
				EDIT_BOOK);
	}

	void editBookProcess(int bookId) {

		Book book = allBooks.get(bookId - 1);

		System.out.println("\n Input the following information. To leave a field unchanged, hit <Enter> \n");

		System.out.print("Title [" + book.getTitle() + " ] : ");

		Scanner editBookScanner = new Scanner(System.in);
		String title = editBookScanner.nextLine();

		if (title.length() != 0) {
			book.setTitle(title);
		}

		System.out.print("Author [" + book.getAuthor() + " ] : ");

		String author = editBookScanner.nextLine();

		if (author.length() != 0) {
			book.setAuthor(author);
		}

		System.out.print("Category [" + book.getCategory() + " ] : ");

		String category = editBookScanner.nextLine();

		if (category.length() != 0) {
			book.setCategory(category);
		}

		System.out.print("Description [" + book.getDescription() + " ] : ");

		String description = editBookScanner.nextLine();

		if (description.length() != 0) {
			book.setDescription(description);
		}

		System.out.println("Book saved.\n");

	}

	/**
	 * Allow the user, through a command line interface, to save all changes and
	 * exit from the application, and Write the list of books to disk.
	 */
	void saveAndExit() {

		System.out.println("\n Library saved.");

		CSVBookUtil.writeAllBooksToCSV(allBooks);

		scanner.close();
	}

	/**
	 * It's a common function to repeat the book process likes Edit, View or Find
	 * book or allow the user to perform another edit process or back to main book
	 * functions to execute another process.
	 * 
	 * @param displayedMessage
	 * @param funcationType
	 * @throws java.lang.NumberFormatException
	 */
	void repeatBookFuncation(String displayedMessage, String funcationType) {

		System.out.println(displayedMessage);

		try {
			System.out.print("\n Book ID: ");
			Scanner repeatBookScanner = new Scanner(System.in);
			String newChoice = repeatBookScanner.nextLine();

			if (newChoice.length() != 0) {

				int bookId = Integer.parseInt(newChoice);

				if (funcationType.equals(EDIT_BOOK)) {

					editBookProcess(bookId);

					repeatBookFuncation("\n Enter the book ID of the book you want to edit; to return press <Enter>.",
							EDIT_BOOK);

				} else if (funcationType.equals(VIEW_BOOK_DETAILS)) {

					viewBookDetails(allBooks.get(bookId - 1));

					repeatBookFuncation("\n To view details enter the book ID, to return press <Enter>.",
							VIEW_BOOK_DETAILS);

				} else if (funcationType.equals(FIND_BOOK)) {

					viewBookDetails(allBooks.get(bookId - 1));

					mainFindBook();

				}

			} else {
				mainBookFuncations();
			}
		} catch (NumberFormatException e) {

			System.out.println("\n Your Input not valid, Please try your process again.");
			mainBookFuncations();

		} catch (IndexOutOfBoundsException ex) {

			System.out.println("\n The Book ID not vaild, Please try your process again.");
			mainBookFuncations();
		}
	}

	/**
	 * View details of specific book
	 * 
	 * @param book
	 */
	void viewBookDetails(Book book) {

		System.out.println("\n" + "	ID: " + book.getId() + "\n" + "	Title: " + book.getTitle() + "\n" + "	Author: "
				+ book.getAuthor() + "\n" + "	Category: " + book.getCategory() + "\n" + "	Description: "
				+ book.getDescription() + "\n");
	}

	/**
	 * Display ID and title of each book
	 * 
	 */
	void displayAllBooks() {

		for (Book book : allBooks) {
			System.out.println("[" + book.getId() + "] " + book.getTitle());
		}
	}

	/**
	 * prepare the new book Id by getting the Id of last book in the list and
	 * incremented it by 1
	 * 
	 */
	int getNewBookId() {

		if (allBooks != null && !allBooks.isEmpty()) {
			return allBooks.get(allBooks.size() - 1).getId() + 1;
		}

		return 1;
	}

}
