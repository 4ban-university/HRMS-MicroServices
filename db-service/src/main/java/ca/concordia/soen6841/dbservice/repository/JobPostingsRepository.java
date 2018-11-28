package ca.concordia.soen6841.dbservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ca.concordia.soen6841.dbservice.model.JobPostings;

@Repository
public interface JobPostingsRepository extends JpaRepository<JobPostings, Integer> {
	
}
