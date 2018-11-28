package com.hrms.soen6841.recruitment.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.hrms.soen6841.recruitment.pojo.JobPostings;

@RestController
@RequestMapping("/")
public class JobPostingsController {
	
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


}
