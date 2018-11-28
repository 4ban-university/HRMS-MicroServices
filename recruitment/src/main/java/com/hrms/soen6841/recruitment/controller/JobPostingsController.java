package com.hrms.soen6841.recruitment.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.hrms.soen6841.recruitment.pojo.JobPostings;

@RestController
@RequestMapping("/")
public class JobPostingsController {

	// Get all posted jobs
	@GetMapping("/getjobs")
	public List<JobPostings> getJobPostingsList() {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<List<JobPostings>> response = restTemplate.exchange(
				"http://localhost:8700/jobpostings/",
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<List<JobPostings>>(){});
		List<JobPostings> jobPostings = response.getBody();
		return jobPostings;
	}

	// Get job by id
	@GetMapping("/getjobs/id/{id}")
	public JobPostings getJobPostingsByStatus(@PathVariable Integer id) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<JobPostings> response = restTemplate.exchange(
				"http://localhost:8700/jobpostings/" + id,
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<JobPostings>(){});
		JobPostings jobPostings = response.getBody();
		return jobPostings;
	}

	// Get job by status
	@GetMapping("/getjobs/status/{status}")
	public List<JobPostings> getJobPostingsByStatus(@PathVariable String status) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<List<JobPostings>> response = restTemplate.exchange(
				"http://localhost:8700/jobpostings/status/" + status,
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<List<JobPostings>>(){});
		List<JobPostings> jobPostings = response.getBody();
		return jobPostings;
	}

	// Get job by description
	@GetMapping("/getjobs/description/{description}")
	public List<JobPostings> getJobPostingsByDesc(@PathVariable String description) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<List<JobPostings>> response = restTemplate.exchange(
				"http://localhost:8700/jobpostings/description/" + description,
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<List<JobPostings>>(){});
		List<JobPostings> jobPostings = response.getBody();
		return jobPostings;
	}

	// Post a job
	@PostMapping("/postjob")
	public String postJob(@Valid @RequestBody JobPostings jobPosting) {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.exchange(
				"http://localhost:8700/jobpostings/postjob/",
				HttpMethod.POST,
				new HttpEntity<>(jobPosting),
				new ParameterizedTypeReference<JobPostings>(){});
		return "Job posted successfully";

	}

	// Delete a job
	@DeleteMapping("deletejob/{id}")
	public String deleteJobPost(@PathVariable Integer id) {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.exchange( "http://localhost:8700/jobpostings/" + id,
				HttpMethod.DELETE,
				null,
				String.class);
		return "Job post deleted successfully";   // return a message to postman as notification
	}

	// Edit the job post
	@PutMapping("editjob/{id}")  // pass in the entire object inorder to edit based of the Id
	public JobPostings editEmployee(@RequestBody JobPostings jobPost, @PathVariable Integer id) {
		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<JobPostings> response = restTemplate.exchange(
				"http://localhost:8700/jobpostings/" + id,
				HttpMethod.PUT,
				new HttpEntity<>(jobPost),  // response type 
				new ParameterizedTypeReference<JobPostings>(){}); // object type POJO 

		JobPostings employee = response.getBody();  // convert it back to the job "JSON"

		return employee;
	}


}
