package toppan.library.model;

import java.time.ZonedDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "author_books")
public class AuthorBook {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "author_id")
	private Author author;

	@ManyToOne
	@JoinColumn(name = "book_id")
	private Book book;	
	
	@Column(name = "createdAt")
	private ZonedDateTime createdAt;

	@Column(name = "updatedAt")
	private ZonedDateTime updatedAt;
	
	public AuthorBook(Author author, Book book, ZonedDateTime createdAt, ZonedDateTime updatedAt){
		this.author = author;
		this.book = book;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
}
