package ca.concordia.soen6841.dbservice.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ca.concordia.soen6841.dbservice.model.JobPostings;

@Repository
@Transactional(readOnly = true)
public class JobPostingsCustomRepositoryImpl implements JobPostingsCustomRepository {

	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public List<JobPostings> findJobByStatus(String status) {
		
		Query query = entityManager.createNativeQuery("SELECT jp.* FROM hrms.job_postings as jp " +
                "WHERE jp.application_status = ?", JobPostings.class);
        query.setParameter(1, status);
        return query.getResultList();
	}

}
