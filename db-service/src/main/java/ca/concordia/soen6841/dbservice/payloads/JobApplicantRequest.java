package ca.concordia.soen6841.dbservice.payloads;

import ca.concordia.soen6841.dbservice.model.ApplicantStatus;

import javax.validation.constraints.NotBlank;

public class JobApplicantRequest {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    private ApplicantStatus status;

    @NotBlank
    private String email;

    public JobApplicantRequest() {

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public ApplicantStatus getStatus() {
        return status;
    }

    public void setStatus(ApplicantStatus status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
