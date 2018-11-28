package ca.concordia.soen6841.dbservice.repository;

import java.util.List;

import ca.concordia.soen6841.dbservice.model.JobPostings;

public interface JobPostingsCustomRepository {
	List<JobPostings> findJobByStatus(String status);
}
