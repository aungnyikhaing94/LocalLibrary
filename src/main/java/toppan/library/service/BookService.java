package toppan.library.service;

import java.util.List;

import toppan.library.model.Book;

public interface BookService {
	public List<Book> getTopThreeAuthorBooks(String countryCode);
}
