package toppan.library.service;

import java.util.List;

import toppan.library.model.Person;

public interface PersonService {
	public List<Person> getTopThreeBorrowers(String countryCode);
}
