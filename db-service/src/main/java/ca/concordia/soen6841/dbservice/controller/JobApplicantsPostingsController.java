package ca.concordia.soen6841.dbservice.controller;

import ca.concordia.soen6841.dbservice.exceptions.JobApplicantsNotFoundException;
import ca.concordia.soen6841.dbservice.model.JobApplicants;
import ca.concordia.soen6841.dbservice.model.JobPostings;
import ca.concordia.soen6841.dbservice.payloads.JobApplicantsPostings;
import ca.concordia.soen6841.dbservice.repository.JobApplicantsPostingsCustomRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/jobapplicantspostings")
public class JobApplicantsPostingsController {
    @Autowired
    private JobApplicantsPostingsCustomRepositoryImpl JobApplicantsPostingsRepository;

    // Get all posted jobs
    @GetMapping("/")
    public List<ca.concordia.soen6841.dbservice.payloads.JobApplicantsPostings> getJobsApplicantsPosting() {
        return JobApplicantsPostingsRepository.findAll().orElseThrow(() -> new JobApplicantsNotFoundException(0));
    }

    // Get a list of job applicants per job posting id
    @GetMapping("/applicant/{id}")
    public List<JobPostings> getJobPostingsByApplicantId(@PathVariable Integer id) {
        return JobApplicantsPostingsRepository.findJobPostingsByApplicantId(id)
                .orElseThrow(() -> new JobApplicantsNotFoundException(id));
    }

    // Get job by id
    @GetMapping("/job/{id}")
    public List<JobApplicants> getJobsApplicantsByJobPostingId(@PathVariable Integer id) {
        return JobApplicantsPostingsRepository.findJobApplicantsByJobPostingId(id)
                .orElseThrow(() -> new JobApplicantsNotFoundException(id));
    }

    @DeleteMapping("/")
    public ResponseEntity<?> deleteJob(@Valid @RequestBody JobApplicantsPostings jobPostingRequest) {

        boolean result = JobApplicantsPostingsRepository.deleteByIds(jobPostingRequest.getJobPostingId(), jobPostingRequest.getApplicantId());

        if (result) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body("job applicant deleted successfully");
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("error");
    }


    @PostMapping("/")
    public ResponseEntity<?> postJob(@Valid @RequestBody JobApplicantsPostings jobPostingRequest) {

        boolean result = JobApplicantsPostingsRepository.insertByIds(jobPostingRequest.getJobPostingId(), jobPostingRequest.getApplicantId());

        if (result) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body("job applicant inserted successfully");
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("error");
    }
}
