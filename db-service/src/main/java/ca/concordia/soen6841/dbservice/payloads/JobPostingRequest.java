package ca.concordia.soen6841.dbservice.payloads;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

public class JobPostingRequest {

	@NotEmpty
	private Number minSalary;

	@NotEmpty
	private Number maxSalary;

	@NotBlank
	private String applicationStatus;

	@NotBlank
	private String jobDescription;

	@NotBlank
	private String noOfOpenings;
	
	@NotBlank
	private String contractType;

	@NotEmpty
	private Number contractPeriod;

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
