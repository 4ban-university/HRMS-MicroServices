package ca.concordia.soen6841.dbservice.model;

import javax.persistence.*;

@Embeddable
@Table(name = "JobApplicants_Postings",
        uniqueConstraints=
        @UniqueConstraint(columnNames={"applicant_id", "jobposting_id"}))
public class JobApplicantsPostings extends AuditModel {
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "applicant_id", nullable = false)
    private JobApplicants applicant;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "jobposting_id", nullable = false)
    private JobPostings jobPosting;

    public JobApplicantsPostings() {

    }

    public JobApplicants getApplicant() {
        return applicant;
    }

    public void setApplicant(JobApplicants applicant) {
        this.applicant = applicant;
    }

    public JobPostings getJobPosting() {
        return jobPosting;
    }

    public void setJobPosting(JobPostings jobPosting) {
        this.jobPosting = jobPosting;
    }
}
