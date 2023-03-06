package toppan.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import toppan.library.model.Author;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
	
}
