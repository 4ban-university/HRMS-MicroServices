package ca.concordia.soen6841.dbservice.exceptions;

public class JobPostingsNotFoundException extends RuntimeException{
	public JobPostingsNotFoundException(Integer id) {
		super("could not find job posting" + id);
	}

}
