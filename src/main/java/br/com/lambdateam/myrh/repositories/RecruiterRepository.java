package br.com.lambdateam.myrh.repositories;

import br.com.lambdateam.myrh.models.RecruiterModel;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RecruiterRepository extends JpaRepository<RecruiterModel, Long> {
	
	@Query(value = "SELECT r FROM RecruiterModel r WHERE upper(trim(r.company)) LIKE %?1%")
	List<RecruiterModel> dynamicSearchByCompany(String company);
}