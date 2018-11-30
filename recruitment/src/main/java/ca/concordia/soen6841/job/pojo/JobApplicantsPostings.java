package ca.concordia.soen6841.job.pojo;

public class JobApplicantsPostings {

    private JobApplicants applicant;

    private Number jobPostingId;

    public JobApplicantsPostings() {

    }

    public JobApplicants getApplicant() {
        return applicant;
    }

    public void setApplicant(JobApplicants applicant) {
        this.applicant = applicant;
    }

    public Number getJobPostingId() {
        return jobPostingId;
    }

    public void setJobPostingId(Number jobPostingId) {
        this.jobPostingId = jobPostingId;
    }
}
