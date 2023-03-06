package toppan.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import toppan.library.model.Country;

public interface CountryRepository extends JpaRepository<Country, Long> {

}
