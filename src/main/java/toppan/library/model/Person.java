package toppan.library.model;

import java.time.ZonedDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "people")
public class Person {

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

	@ManyToOne
    @JoinColumn(name = "country_id")
	private Country country;

	@JsonIgnore
	@OneToMany(mappedBy = "person")
	private List<BookRent> bookRents;

	public Person(String name, ZonedDateTime createdAt, ZonedDateTime updatedAt, Country country) {
		this.name = name;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.country = country;
	}
}