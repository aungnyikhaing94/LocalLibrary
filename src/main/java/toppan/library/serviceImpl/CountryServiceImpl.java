package toppan.library.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import toppan.library.model.Country;
import toppan.library.repository.CountryRepository;
import toppan.library.service.CountryService;

@Service
public class CountryServiceImpl implements CountryService {
	
	@Autowired
	CountryRepository countryRepository;

	@Override
	public List<Country> getAllCountries() {		
		return countryRepository.findAll();
	}

}
