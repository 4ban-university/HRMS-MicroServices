package ca.concordia.soen6841.job.controller;

import ca.concordia.soen6841.job.pojo.JobApplicants;
import ca.concordia.soen6841.job.pojo.JobApplicantsPostings;
import ca.concordia.soen6841.job.pojo.JobPostings;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/applications")
public class JobApplicantsPostingsController {
    // Get all job applications
    @GetMapping("/")
    public List<JobApplicants> getJobApplicantsPostingsList() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<JobApplicants>> response = restTemplate.exchange(
                "http://localhost:8700/jobapplicantspostings/",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<JobApplicants>>(){});
        return response.getBody();
    }

    // Get job applicant posting by applicant id
    @GetMapping("/applicant{id}")
    public JobPostings getJobApplicantsPostingsByApplicantId(@PathVariable Integer id) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<JobPostings> response = restTemplate.exchange(
                "http://localhost:8700/jobapplicantspostings/applicant/" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<JobPostings>(){});
        return response.getBody();
    }

    // Get job applicant posting by job id
    @GetMapping("/job/{id}")
    public JobPostings getJobApplicantsPostingsByJobPostingId(@PathVariable Integer id) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<JobPostings> response = restTemplate.exchange(
                "http://localhost:8700/jobapplicantspostings/job/" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<JobPostings>(){});
        return response.getBody();
    }

    // Delete a job applicant posting
    @DeleteMapping("/")
    public String deleteJobApplicantsPostings(@RequestBody JobApplicantsPostings jobApplicantsPostings) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.exchange( "http://localhost:8700/jobapplicantspostings/" + jobApplicantsPostings,
                HttpMethod.DELETE,
                null,
                String.class);
        return "Job Applicant Posting deleted successfully";   // return a message to postman as notification
    }

    // Post a job
    @PostMapping("/")
    public String postJobApplicantPosting(@Valid @RequestBody JobApplicantsPostings jobApplicantsPostings) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.exchange(
                "http://localhost:8700/jobapplicantspostings/",
                HttpMethod.POST,
                new HttpEntity<>(jobApplicantsPostings),
                new ParameterizedTypeReference<JobPostings>(){});
        return "Job Applicant Posting posted successfully";
    }

    // Edit a job
    @PutMapping("/")
    public String editJobApplicantPosting(@Valid @RequestBody JobApplicantsPostings jobApplicantsPostings) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.exchange(
                "http://localhost:8700/jobapplicantspostings/",
                HttpMethod.PUT,
                new HttpEntity<>(jobApplicantsPostings),
                new ParameterizedTypeReference<JobPostings>(){});
        return "Job Applicant Posting posted successfully";
    }
}
