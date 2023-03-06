package toppan.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toppan.library.model.BookRent;

public interface BookRentRepository extends JpaRepository<BookRent, Integer> {

}
