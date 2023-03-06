package toppan.library;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import toppan.library.model.Author;
import toppan.library.model.AuthorBook;
import toppan.library.model.Book;
import toppan.library.model.BookRent;
import toppan.library.model.Country;
import toppan.library.model.Person;
import toppan.library.repository.AuthorBookRepository;
import toppan.library.repository.AuthorRepository;
import toppan.library.repository.BookRentRepository;
import toppan.library.repository.BookRepository;
import toppan.library.repository.CountryRepository;
import toppan.library.repository.PersonRepository;

@Component
public class SampleDataInitializer implements CommandLineRunner {
	
	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	AuthorRepository authorRepository;
	
	@Autowired
	AuthorBookRepository authorBookRepository;
	
	@Autowired
	CountryRepository countryRepository;
	
	@Autowired
	PersonRepository personRepository;
	
	@Autowired
	BookRentRepository bookRentRepository;

	@Override
	public void run(String... args) throws Exception {		
		ZonedDateTime timestamp = ZonedDateTime.now(); 
		
		// create five authors
		Author author1 = new Author("Author "+1, timestamp, timestamp);
    	Author author2 = new Author("Author "+2, timestamp, timestamp);
    	Author author3 = new Author("Author "+3, timestamp, timestamp);
    	Author author4 = new Author("Author "+4, timestamp, timestamp);
    	Author author5 = new Author("Author "+5, timestamp, timestamp);
    	authorRepository.save(author1);
    	authorRepository.save(author2);
    	authorRepository.save(author3);
    	authorRepository.save(author4);
    	authorRepository.save(author5);
    	
    	Country country1 = new Country("SG", "Singapore");
    	Country country2 = new Country("MY", "Malaysia");
    	Country country3 = new Country("US", "United States");
    	countryRepository.save(country1);
    	countryRepository.save(country2);
    	countryRepository.save(country3);
    	
    	
        for(int i=1;i<11;i++) {
        	Book book = new Book("Book "+i, timestamp, timestamp);        	
        	bookRepository.save(book);
        	AuthorBook authorBook = new AuthorBook();
        	if(i <= 5) {
        		authorBook = new AuthorBook(author2, book, timestamp, timestamp);
        	} else if(i > 5 && i <= 6){
        		authorBook = new AuthorBook(author1, book, timestamp, timestamp);
        	} else if(i > 6 && i <= 7) {
        		authorBook = new AuthorBook(author4, book, timestamp, timestamp);
        	} else {
        		authorBook = new AuthorBook(author3, book, timestamp, timestamp);
        	}
        	authorBookRepository.save(authorBook);        	
        }
        
        List<Person> people = new ArrayList<>();
        for(int j=1;j<=15;j++) {
        	Person person = new Person();
        	if(j <= 3) {
        		person = new Person("Person "+j, timestamp, timestamp, country1); 
        	} else if(j > 3 && j <= 8) {
        		person = new Person("Person "+j, timestamp, timestamp, country2);
        	} else {
        		person = new Person("Person "+j, timestamp, timestamp, country3);
        	}        	        	
        	people.add(person);
        }
        personRepository.saveAll(people);       
        
        List<Book> bookList = bookRepository.findAll();
        List<Person> personList = personRepository.findAll();
        Random rand = new Random();
        List<BookRent> bookRents = new ArrayList<>();
        for(int k=0;k<30;k++) {
        	int randomNum1 = rand.nextInt(9);
        	int randomNum2 = rand.nextInt(9);
        	BookRent bookRent = new BookRent(personList.get(randomNum1),bookList.get(randomNum2), timestamp, timestamp);
        	bookRents.add(bookRent);
        }
        bookRentRepository.saveAll(bookRents);
		
	}

}
