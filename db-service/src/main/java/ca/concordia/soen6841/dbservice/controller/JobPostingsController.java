package ca.concordia.soen6841.dbservice.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.concordia.soen6841.dbservice.exceptions.JobPostingsNotFoundException;
import ca.concordia.soen6841.dbservice.model.JobPostings;
import ca.concordia.soen6841.dbservice.payloads.JobPostingRequest;
import ca.concordia.soen6841.dbservice.repository.JobPostingsRepository;

@RestController
@RequestMapping("/jobpostings")
public class JobPostingsController {

	@Autowired
	private JobPostingsRepository jobPostingsRepository;

	// Get all posted jobs
	@GetMapping("/")
	public List<JobPostings> getJobsPosting() {
		return jobPostingsRepository.findAll();
	}
	
	// Get job by id
	@GetMapping("/{id}")
	public JobPostings getJobsPostingById(@PathVariable Integer id) {
		return jobPostingsRepository.findById(id)
				.orElseThrow(() -> new JobPostingsNotFoundException(id));
	}
	
	// Get jobs by status
	@GetMapping("/status/{status}")
	public List<JobPostings> getJobsPostingBystatus(@PathVariable String status) {
		return jobPostingsRepository.findJobByStatus(status);
	}
	
	//Get jobs by description
	@GetMapping("/description/{description}")
	public List<JobPostings>getJobPostingByDesc(@PathVariable String description){
		return jobPostingsRepository.findJobByDesc(description);
		
	}

	// Delete Job Post
	@DeleteMapping("/{id}")
	public String deleteEmployee(@PathVariable Integer id) {
		jobPostingsRepository.deleteById(id);
		return "Job post deleted successfully";
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
