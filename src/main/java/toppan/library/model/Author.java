package toppan.library.model;

import java.time.ZonedDateTime;
import java.util.List;


import jakarta.persistence.JoinColumn;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "authors")
public class Author {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "name")
	private String name;

	@Column(name = "createdAt")
	private ZonedDateTime createdAt;

	@Column(name = "updatedAt")
	private ZonedDateTime updatedAt;

	@ManyToMany
	@JoinTable(
		name = "author_books", 
		joinColumns = @JoinColumn(name = "author_id"), 
		inverseJoinColumns = @JoinColumn(name = "book_id")
		)
	private List<Book> books;

	public Author(String name, ZonedDateTime createdAt, ZonedDateTime updatedAt) {
		this.name = name;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
}
