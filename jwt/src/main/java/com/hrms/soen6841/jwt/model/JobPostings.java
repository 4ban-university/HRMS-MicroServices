package com.hrms.soen6841.jwt.model;

import javax.persistence.*;

@Entity
@Table(name = "JobPostings")
public class JobPostings extends AuditModel {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "min_salary")
    private Number minSalary;

    @Column(name = "max_salary")
    private Number maxSalary;

    @Column(name = "application_status")
    private String applicationStatus;

    @Column(name = "job_description")
    private String jobDescription;

    @Column(name = "no_of_openings")
    private String noOfOpenings;

    @Column(name = "contract_type")
    private String contractType;

    @Column(name = "contract_period")
    private Number contractPeriod;

    public JobPostings() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Number getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(Number minSalary) {
        this.minSalary = minSalary;
    }

    public Number getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(Number maxSalary) {
        this.maxSalary = maxSalary;
    }

    public String getApplicationStatus() {
        return applicationStatus;
    }

    public void setApplicationStatus(String applicationStatus) {
        this.applicationStatus = applicationStatus;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public String getNoOfOpenings() {
        return noOfOpenings;
    }

    public void setNoOfOpenings(String noOfOpenings) {
        this.noOfOpenings = noOfOpenings;
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public Number getContractPeriod() {
        return contractPeriod;
    }

    public void setContractPeriod(Number contractPeriod) {
        this.contractPeriod = contractPeriod;
    }
}
