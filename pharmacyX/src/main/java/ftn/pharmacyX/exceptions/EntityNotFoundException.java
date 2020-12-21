package ftn.pharmacyX.exceptions;

import org.springframework.http.HttpStatus;

public class EntityNotFoundException extends RuntimeException {

	private HttpStatus httpStatus;
	
	public EntityNotFoundException(String msg, HttpStatus status) {
		super(msg);
		this.httpStatus = status;
	}
	
	public EntityNotFoundException(String msg) {
		super(msg);
		this.httpStatus = HttpStatus.NO_CONTENT;
	}
	
	public HttpStatus getHttpStatus() {
		return this.httpStatus;
	}
}
