package br.com.lambdateam.myrh.services;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.lambdateam.myrh.exceptions.NotFoundException;
import br.com.lambdateam.myrh.models.RecruiterModel;
import br.com.lambdateam.myrh.repositories.RecruiterRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class RecruiterService {
	
	private final RecruiterRepository repository;
	
//    public RecruiterService(RecruiterRepository repository) {
//        this.repository = repository;
//    }
	
	private RecruiterModel findOrThrow(final Long id) throws NotFoundException {
		return repository
				.findById(id)
				.orElseThrow(
						() -> new NotFoundException("The recruiter id " + id + " was not found.")
						);
	}
	
	public Iterable<RecruiterModel> findAllRecruiters() {
		try {
			return repository.findAll();
		} catch (NotFoundException ex) {
			throw new NotFoundException("No results.");
		}
	}
	
	public RecruiterModel findRecruiterById(Long id) throws NotFoundException {
		if(!repository.existsById(id)) throw new NotFoundException("The recruiter id " + id + " was not found.");
		return findOrThrow(id);
	}
	
	public List<RecruiterModel> dynamycSearchByCompany(String company) throws NotFoundException {
		try {
			return repository.dynamicSearchByCompany(company);
		} catch (NotFoundException ex) {
			throw new NotFoundException("No results.");
		}
	}
	
	public void deleteRecruiterById(Long id) throws NotFoundException {
		if(!repository.existsById(id)) throw new NotFoundException("The recruiter id " + id + " was not found.");
		repository.deleteById(id);
	}
	
	public RecruiterModel createRecruiter(RecruiterModel recruiter) {
		return repository.save(recruiter);
	}
	
	public void updateRecruiter(Long id, RecruiterModel recruiter) throws NotFoundException {
		if(!repository.existsById(id)) throw new NotFoundException("The recruiter id " + id + " was not found.");
		findOrThrow(id);
		repository.save(recruiter);
	}
}