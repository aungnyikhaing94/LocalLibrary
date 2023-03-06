package toppan.library.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import toppan.library.model.Book;
import toppan.library.repository.BookRepository;
import toppan.library.service.BookService;
@Service
public class BookServiceImpl implements BookService {
	@Autowired
	BookRepository bookRepository;

	@Override
	public List<Book> getTopThreeAuthorBooks(String countryCode) {
		List<Book> books = bookRepository.findTop3RentedBooksByCountryCode(countryCode);
		return books;
	}

}
