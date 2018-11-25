package ca.concordia.soen6841.dbservice.repository;

import ca.concordia.soen6841.dbservice.model.JobPostings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobPostingsRepository extends JpaRepository<JobPostings, Integer> {
}
