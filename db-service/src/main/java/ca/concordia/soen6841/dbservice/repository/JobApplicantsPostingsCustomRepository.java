package ca.concordia.soen6841.dbservice.repository;

import ca.concordia.soen6841.dbservice.model.JobApplicants;
import ca.concordia.soen6841.dbservice.model.JobPostings;
import ca.concordia.soen6841.dbservice.payloads.JobApplicantsPostings;

import java.util.List;
import java.util.Optional;

public interface JobApplicantsPostingsCustomRepository  {
	Optional<List<JobApplicantsPostings>> findAll();
	Optional<List<JobPostings>> findJobPostingsByApplicantId(Number jobPostingId);
	Optional<List<JobApplicants>> findJobApplicantsByJobPostingId(Number jobApplicantId);
	boolean deleteByIds(Number jobPostingId, Number jobApplicantId);
	boolean insertByIds(Number jobPostingId, Number jobApplicantId);
}
