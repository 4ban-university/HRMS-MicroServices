package ca.concordia.soen6841.job.controller;

import java.util.List;

import javax.validation.Valid;

import ca.concordia.soen6841.job.pojo.JobPostings;
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

@RestController
@RequestMapping("/")
public class JobPostingsController {

	// Get all posted jobs
	@GetMapping("/")
	public List<JobPostings> getJobPostingsList() {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<List<JobPostings>> response = restTemplate.exchange(
				"http://localhost:8700/jobpostings/",
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<List<JobPostings>>(){});
		return response.getBody();
	}

	// Get job by id
	@GetMapping("/{id}")
	public JobPostings getJobPostingsById(@PathVariable Integer id) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<JobPostings> response = restTemplate.exchange(
				"http://localhost:8700/jobpostings/" + id,
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<JobPostings>(){});
		return response.getBody();
	}

	// Get job by status
	@GetMapping("/status/{status}")
	public List<JobPostings> getJobPostingsByStatus(@PathVariable String status) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<List<JobPostings>> response = restTemplate.exchange(
				"http://localhost:8700/jobpostings/status/" + status,
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<List<JobPostings>>(){});
		return response.getBody();
	}

	// Get job by description
	@GetMapping("/description/{description}")
	public List<JobPostings> getJobPostingsByDesc(@PathVariable String description) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<List<JobPostings>> response = restTemplate.exchange(
				"http://localhost:8700/jobpostings/description/" + description,
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<List<JobPostings>>(){});
		return response.getBody();
	}

	// Post a job
	@PostMapping("/")
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
	@DeleteMapping("/{id}")
	public String deleteJob(@PathVariable Integer id) {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.exchange( "http://localhost:8700/jobpostings/" + id,
				HttpMethod.DELETE,
				null,
				String.class);
		return "Job post deleted successfully";   // return a message to postman as notification
	}

	// Edit the job post
	@PutMapping("/{id}")  // pass in the entire object inorder to edit based of the Id
	public JobPostings editJob(@RequestBody JobPostings jobPost, @PathVariable Integer id) {
		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<JobPostings> response = restTemplate.exchange(
				"http://localhost:8700/jobpostings/" + id,
				HttpMethod.PUT,
				new HttpEntity<>(jobPost),  // response type 
				new ParameterizedTypeReference<JobPostings>(){}); // object type POJO

		return response.getBody();
	}
}
