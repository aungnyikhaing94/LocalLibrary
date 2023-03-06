package toppan.library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import toppan.library.model.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {
	@Query("SELECT b, COUNT(br) AS rentals FROM Book b " 
			+ "JOIN b.bookRents br " 
			+ "JOIN br.person p "
			+ "JOIN p.country c " 
			+ "WHERE c.countryCode = :countryCode " 
			+ "GROUP BY b " 
			+ "ORDER BY rentals DESC LIMIT 3")
	List<Book> findTop3RentedBooksByCountryCode(@Param("countryCode") String countryCode);
}
