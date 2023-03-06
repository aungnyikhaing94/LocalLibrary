package toppan.library.model;

import java.time.ZonedDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "books")
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "createdAt")
	private ZonedDateTime createdAt;
	
	@Column(name = "updatedAt")
	private ZonedDateTime updatedAt;
	
	@ManyToMany(mappedBy = "books")
	private List<Author> authors;
		
	@JsonIgnore
	@OneToMany(mappedBy="book")
	private List<BookRent> bookRents;
	
	public Book(String name, ZonedDateTime createdAt, ZonedDateTime updatedAt){
		this.name = name;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
}
