package ca.concordia.soen6841.dbservice.payloads;

import javax.validation.constraints.NotNull;

public class JobApplicantsPostings {
    @NotNull
    private Number applicantId;

    @NotNull
    private Number jobPostingId;

    public JobApplicantsPostings() {

    }

    public Number getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(Number applicantId) {
        this.applicantId = applicantId;
    }

    public Number getJobPostingId() {
        return jobPostingId;
    }

    public void setJobPostingId(Number jobPostingId) {
        this.jobPostingId = jobPostingId;
    }
}
