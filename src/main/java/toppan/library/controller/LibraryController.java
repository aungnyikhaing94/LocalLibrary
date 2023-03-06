package toppan.library.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import toppan.library.DTOs.BookDTO;
import toppan.library.DTOs.CountryDTO;
import toppan.library.DTOs.ErrorResponseDTO;
import toppan.library.model.Author;
import toppan.library.model.Book;
import toppan.library.model.Country;
import toppan.library.model.Person;
import toppan.library.service.BookService;
import toppan.library.service.CountryService;
import toppan.library.service.PersonService;
import toppan.library.exception.InvalidParameterException;
import toppan.library.exception.ItemNotFoundException;

@CrossOrigin(origins= "http://localhost:3000/")
@RestController
@RequestMapping("/")
public class LibraryController {
	@Autowired
	BookService bookService;

	@Autowired
	CountryService countryService;

	@Autowired
	PersonService personService;

	@GetMapping("/getRandomCountry")
	public ResponseEntity<?> getRandomCountryCode() {
		List<Country> countries = countryService.getAllCountries();
		
//		System.out.println("Countries");
//		for(Country c : countries) {
//			System.out.println(c);	
//		}
		
		Random random = new Random();
		int index = random.nextInt(countries.size());
		
//		System.out.println("current index" + index);
		
		CountryDTO countryDto = new CountryDTO();
		countryDto.setCountry_code(countries.get(index).getCountryCode());
		countryDto.setFull_name(countries.get(index).getCountryFullName());
		return ResponseEntity.ok(countryDto);
	}

	@GetMapping("/getTop3ReadBooks")
	public ResponseEntity<?> getTopThreeBooks(@RequestParam("country_code") String countryCode) {
		try {
			if (countryCode == null || countryCode.isEmpty()) {
				throw new InvalidParameterException("invalid parameter");
			}

			List<Book> books = bookService.getTopThreeAuthorBooks(countryCode);
			if (books.isEmpty()) {
				throw new ItemNotFoundException("no results");
			}

			List<BookDTO> result = new ArrayList<>();
			List<String> borrowerNames = personService.getTopThreeBorrowers(countryCode).stream().map(Person::getName)
					.collect(Collectors.toList());
			
			for (Book b : books) {
				BookDTO bookDto = new BookDTO();
				//convert list of authors of a book to a string separated by comma
				String authors = b.getAuthors().stream().map(Author::getName).collect(Collectors.joining(", "));

				bookDto.setAuthor(authors);
				bookDto.setName(b.getName());
				bookDto.setBorrowers(borrowerNames);
				result.add(bookDto);
			}

			return ResponseEntity.ok(result);
		} catch (InvalidParameterException ex) {
			// Return a 400 Bad Request status code with error message
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseDTO(ex.getMessage()));
		} catch (ItemNotFoundException ex) {
			// Return a 404 Not Found status code with error message
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDTO(ex.getMessage()));
		} catch (Exception ex) {
			ex.printStackTrace();
			// Return a 503 Service Unavailable status code
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
		}
	}

}
