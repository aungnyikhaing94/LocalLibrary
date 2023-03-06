package toppan.library.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "countries")
public class Country {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "country_code")
    private String countryCode;
    
    @Column(name = "full_name")
    private String countryFullName;
    
    @JsonIgnore
	@OneToMany(mappedBy = "id")
	private List<Person> people;
    
    public Country(String countryCode, String countryFullName) {
    	this.countryCode = countryCode;
    	this.countryFullName = countryFullName;
    }
}
