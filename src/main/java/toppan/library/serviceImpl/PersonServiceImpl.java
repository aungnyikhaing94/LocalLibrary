package toppan.library.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import toppan.library.model.Person;
import toppan.library.repository.PersonRepository;
import toppan.library.service.PersonService;

@Service
public class PersonServiceImpl implements PersonService {
	
	@Autowired
	PersonRepository personRepository;

	@Override
	public List<Person> getTopThreeBorrowers(String countryCode) {
		List<Person> borrowers = personRepository.getTopThreeBorrowers(countryCode);
		return borrowers;
	}

}
