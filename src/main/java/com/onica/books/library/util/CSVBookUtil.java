package com.onica.books.library.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.onica.books.library.model.Book;

/**
 * Represents util for the CSV file.
 *
 * @author Shady.Salah
 * @version 1.0
 */

public class CSVBookUtil {

	/**
	 * Represents the CSV file path.
	 */
	final static String csvFilePath = System.getProperty("user.dir") + File.separator + "book_file" + File.separator
			+ "books.csv";

	/**
	 * Validate on the CSV file path, if not found then create a new CSV file.
	 * 
	 * @throws java.io.IOException
	 */
	public static boolean isFileFound() {

		try {
			File file = new File(csvFilePath);

			if (file.exists()) {

				return true;
			} else {

				file.getParentFile().mkdirs();

				return file.createNewFile();

			}

		} catch (IOException ex) {
			System.out.println("Faile to find or creat the book file desk");
		}

		return false;
	}

	/**
	 * Reading all the books from the CSV file.
	 * 
	 * @throws java.io.IOException
	 */
	public static List<Book> readAllBooksFromCSV() {

		if (!isFileFound()) {
			return null;
		}

		List<Book> allbooks = new ArrayList<Book>();

		try {

			BufferedReader csvReader = new BufferedReader(new FileReader(csvFilePath));

			String row;
			int rowCounter = 0;
			while ((row = csvReader.readLine()) != null) {

				if (rowCounter > 0) {
					allbooks.add(transformRowToBook(row));
				}
				rowCounter++;
			}

			csvReader.close();

		} catch (IOException ex) {
			System.out.println(ex.getMessage());

		}
		return allbooks;

	}

	/**
	 * Writing all the books into the CSV file.
	 * 
	 * @throws java.io.IOException
	 */
	public static boolean writeAllBooksToCSV(List<Book> books) {

		// to avoid the problem that may be happened if the book CSV file is deleted during application running.
		if (!isFileFound()) {

			return false;
		}

		FileWriter csvWriter;

		try {
			csvWriter = new FileWriter(csvFilePath);

			csvWriter.write("id");
			csvWriter.write(",");
			csvWriter.write("title");
			csvWriter.write(",");
			csvWriter.write("author");
			csvWriter.write(",");
			csvWriter.write("category");
			csvWriter.write(",");
			csvWriter.write("description");
			csvWriter.write("\n");

			for (Book book : books) {

				csvWriter.append(book.getId() + "");
				csvWriter.append(",");
				csvWriter.append(book.getTitle());
				csvWriter.append(",");
				csvWriter.append(book.getAuthor());
				csvWriter.append(",");
				csvWriter.append(book.getCategory());
				csvWriter.append(",");
				csvWriter.append(book.getDescription());
				csvWriter.append("\n");

			}

			csvWriter.flush();
			csvWriter.close();

		} catch (IOException ex) {

			System.out.println(ex.getMessage());
		}

		return true;
	}

	/**
	 * transform row items to book during the reading process from the CSV file.
	 * 
	 * @param row
	 */
	static Book transformRowToBook(String row) {

		String[] data = row.split(",");

		Book book = new Book();

		book.setId(Integer.parseInt(data[0]));
		book.setTitle(data[1]);
		book.setAuthor(data[2]);
		book.setCategory(data[3]);
		book.setDescription(data[4]);

		return book;

	}

}
