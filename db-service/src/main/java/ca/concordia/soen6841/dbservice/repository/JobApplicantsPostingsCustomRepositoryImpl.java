package ca.concordia.soen6841.dbservice.repository;

import ca.concordia.soen6841.dbservice.model.JobApplicants;
import ca.concordia.soen6841.dbservice.model.JobPostings;
import ca.concordia.soen6841.dbservice.payloads.JobApplicantsPostings;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class JobApplicantsPostingsCustomRepositoryImpl implements JobApplicantsPostingsCustomRepository {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public Optional<List<JobApplicantsPostings>> findAll() {
		List<Object[]> results = entityManager.createNativeQuery("SELECT jobposting_id, applicant_id FROM job_applicants_postings").getResultList();

		List<JobApplicantsPostings> list = new ArrayList<>();
		results.stream().forEach((record) -> {
			JobApplicantsPostings jobApplicantsPostings = new JobApplicantsPostings();
			jobApplicantsPostings.setJobPostingId((Number) record[0]);
			jobApplicantsPostings.setApplicantId((Number) record[1]);
			list.add(jobApplicantsPostings);
		});

		return Optional.of(list);
	}

	@Override
	public Optional<List<JobPostings>> findJobPostingsByApplicantId(Number jobPostingId) {
		Query query = entityManager.createNativeQuery("SELECT * FROM hrms.job_postings " +
				"WHERE id IN (SELECT jobposting_id FROM job_applicants_postings WHERE applicant_id=?)", JobPostings.class);
		query.setParameter(1, jobPostingId);
		return Optional.of(query.getResultList());
	}

	@Override
	public Optional<List<JobApplicants>> findJobApplicantsByJobPostingId(Number jobApplicantId) {
		Query query = entityManager.createNativeQuery("SELECT * FROM hrms.job_applicants " +
				"WHERE id IN (SELECT applicant_id FROM job_applicants_postings WHERE jobposting_id=?)", JobApplicants.class);
		query.setParameter(1, jobApplicantId);
		return Optional.of(query.getResultList());
	}

	@Override
	public boolean deleteByIds(Number jobPostingId, Number jobApplicantId) {
		Query query = entityManager.createNativeQuery("DELETE FROM hrms.job_applicants_postings " +
				"WHERE applicant_id = ? AND jobposting_id = ?");
		query.setParameter(1, jobApplicantId);
		query.setParameter(2, jobPostingId);
		int rows = query.executeUpdate();

		return rows > 0;
	}

	@Override
	public boolean insertByIds(Number jobPostingId, Number jobApplicantId) {
		Query query = entityManager.createNativeQuery("INSERT INTO hrms.job_applicants_postings (applicant_id, jobposting_id) " +
				"VALUES (?, ?)");
		query.setParameter(1, jobApplicantId);
		query.setParameter(2, jobPostingId);
		int rows = query.executeUpdate();

		return rows > 0;
	}
}
