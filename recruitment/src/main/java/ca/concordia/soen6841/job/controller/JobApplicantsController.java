package ca.concordia.soen6841.job.controller;

import ca.concordia.soen6841.job.pojo.JobApplicants;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/applicant")
public class JobApplicantsController {
    // Get all job applicants
    @GetMapping("/")
    public List<JobApplicants> getJobApplicantsList() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<JobApplicants>> response = restTemplate.exchange(
                "http://localhost:8700/jobapplicants/",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<JobApplicants>>(){});
        return response.getBody();
    }

    // Get job applicant by id
    @GetMapping("/{id}")
    public JobApplicants getJobApplicantsById(@PathVariable Integer id) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<JobApplicants> response = restTemplate.exchange(
                "http://localhost:8700/jobapplicants/" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<JobApplicants>(){});
        return response.getBody();
    }

    // Create a job applicant
    @PostMapping("/")
    public String postJob(@Valid @RequestBody JobApplicants jobPosting) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.exchange(
                "http://localhost:8700/jobapplicants/postjob/",
                HttpMethod.POST,
                new HttpEntity<>(jobPosting),
                new ParameterizedTypeReference<JobApplicants>(){});
        return "Job posted successfully";

    }

    // Delete a job applicant
    @DeleteMapping("/{id}")
    public String deleteJobApplicant(@PathVariable Integer id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.exchange( "http://localhost:8700/jobapplicants/" + id,
                HttpMethod.DELETE,
                null,
                String.class);
        return "Job deleted successfully";   // return a message to postman as notification
    }

    // Edit the job post
    @PutMapping("/{id}")  // pass in the entire object inorder to edit based of the Id
    public JobApplicants editJobApplicant(@RequestBody JobApplicants jobPost, @PathVariable Integer id) {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<JobApplicants> response = restTemplate.exchange(
                "http://localhost:8700/jobapplicants/" + id,
                HttpMethod.PUT,
                new HttpEntity<>(jobPost),  // response type
                new ParameterizedTypeReference<JobApplicants>(){}); // object type POJO

        return response.getBody();
    }
}
