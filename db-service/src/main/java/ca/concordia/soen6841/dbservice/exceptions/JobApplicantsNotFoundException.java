package ca.concordia.soen6841.dbservice.exceptions;

public class JobApplicantsNotFoundException extends RuntimeException{
	public JobApplicantsNotFoundException(Integer id) {
		super("could not find job applicant with Id" + id);
	}
}
