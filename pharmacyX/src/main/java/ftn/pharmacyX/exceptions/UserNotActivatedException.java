package ftn.pharmacyX.exceptions;

import org.springframework.http.HttpStatus;

public class UserNotActivatedException extends RuntimeException {

	private HttpStatus httpStatus;
	
	public UserNotActivatedException(String msg, HttpStatus status) {
		super(msg);
		this.httpStatus = status;
	}
	
	public UserNotActivatedException(String msg) {
		super(msg);
		this.httpStatus = HttpStatus.BAD_REQUEST;
	}
	
	public HttpStatus getHttpStatus() {
		return this.httpStatus;
	}
}
