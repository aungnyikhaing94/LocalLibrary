package toppan.library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import toppan.library.model.Person;

public interface PersonRepository extends JpaRepository<Person, Integer> {
	@Query("SELECT br.person FROM BookRent br "
//			+ "INNER JOIN Book b ON br.book = b "
//			+ "INNER JOIN AuthorBook ab ON b = ab.book "
//			+ "INNER JOIN Author a ON ab.author = a "
			+ "INNER JOIN br.person p "
			+ "INNER JOIN p.country c "
			+ "WHERE c.countryCode = :countryCode "
			+ "GROUP BY p "
			+ "ORDER BY COUNT(*) DESC LIMIT 3")
	public List<Person> getTopThreeBorrowers(@Param("countryCode") String countryCode);
}
