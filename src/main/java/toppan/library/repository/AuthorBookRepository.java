package toppan.library.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import toppan.library.model.AuthorBook;

public interface AuthorBookRepository extends JpaRepository<AuthorBook, Integer> {

}
