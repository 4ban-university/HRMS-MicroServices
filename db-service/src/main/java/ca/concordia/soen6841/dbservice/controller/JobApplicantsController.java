package ca.concordia.soen6841.dbservice.controller;

import ca.concordia.soen6841.dbservice.exceptions.JobApplicantsNotFoundException;
import ca.concordia.soen6841.dbservice.model.JobApplicants;
import ca.concordia.soen6841.dbservice.payloads.JobApplicantRequest;
import ca.concordia.soen6841.dbservice.repository.JobApplicantsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/jobapplicants")
public class JobApplicantsController {

    @Autowired
    private JobApplicantsRepository JobApplicantsRepository;

    // Get all posted jobs
    @GetMapping("/")
    public List<JobApplicants> getJobsApplicants() {
        return JobApplicantsRepository.findAll();
    }

    // Get job by id
    @GetMapping("/{id}")
    public JobApplicants getJobsApplicantsById(@PathVariable Integer id) {
        return JobApplicantsRepository.findById(id)
                .orElseThrow(() -> new JobApplicantsNotFoundException(id));
    }

    // Delete Job Post
    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable Integer id) {
        JobApplicantsRepository.deleteById(id);
        return "Job applicant post deleted successfully";
    }


    @PostMapping("/postjobapplicant")
    public ResponseEntity<?> postJob(@Valid @RequestBody JobApplicantRequest jobPostingRequest) {

        JobApplicants jobApplicant = new JobApplicants();
        jobApplicant.setEmail(jobPostingRequest.getEmail());
        jobApplicant.setFirstName(jobPostingRequest.getFirstName());
        jobApplicant.setLastName(jobPostingRequest.getLastName());

        JobApplicantsRepository.save(jobApplicant);

        return ResponseEntity.status(HttpStatus.OK)
                .body("job applicant posted successfully");
    }


    // edit the job post

    @PutMapping("/{id}")
    public JobApplicants editEmployee(@RequestBody JobApplicants newJobPost, @PathVariable Integer id) {
        return JobApplicantsRepository.findById(id)
                .map(changedJobPost -> {

                    changedJobPost.setFirstName(newJobPost.getFirstName());
                    changedJobPost.setLastName(newJobPost.getLastName());
                    changedJobPost.setEmail(newJobPost.getEmail());
                    changedJobPost.setUpdatedAt(newJobPost.getUpdatedAt());

                    return JobApplicantsRepository.save(changedJobPost);

                }).orElseGet(() -> {

                    newJobPost.setId(id);

                    return JobApplicantsRepository.save(newJobPost);

                });

    }
}
