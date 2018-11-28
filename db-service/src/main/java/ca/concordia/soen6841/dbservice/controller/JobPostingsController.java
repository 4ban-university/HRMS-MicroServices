package ca.concordia.soen6841.dbservice.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.concordia.soen6841.dbservice.model.JobPostings;
import ca.concordia.soen6841.dbservice.payloads.JobPostingRequest;
import ca.concordia.soen6841.dbservice.repository.JobPostingsRepository;

@RestController
@RequestMapping("/jobpostings")
public class JobPostingsController {
	
	@Autowired
	private JobPostingsRepository jobPostingsRepository;
	
	@GetMapping("/")
	public List<JobPostings> getJobsPosting() {
		return jobPostingsRepository.findAll();
	}

	@PostMapping("/postjob")
	public ResponseEntity<?> postJob(@Valid @RequestBody JobPostingRequest jobPostingRequest) {
	
		JobPostings jobPosting = new JobPostings();
		jobPosting.setMinSalary(jobPostingRequest.getMinSalary());
		jobPosting.setMaxSalary(jobPostingRequest.getMaxSalary());
		jobPosting.setApplicationStatus(jobPostingRequest.getApplicationStatus());
		jobPosting.setJobDescription(jobPostingRequest.getJobDescription());
		jobPosting.setNoOfOpenings(jobPostingRequest.getNoOfOpenings());
		jobPosting.setContractType(jobPostingRequest.getContractType());
		jobPosting.setContractPeriod(jobPostingRequest.getContractPeriod());
		
		jobPostingsRepository.save(jobPosting);
	
		return ResponseEntity.status(HttpStatus.OK)
                .body("job posted successfully");
	}
	
}
