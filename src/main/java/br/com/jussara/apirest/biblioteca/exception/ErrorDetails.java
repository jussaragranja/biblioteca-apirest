package br.com.jussara.apirest.biblioteca.exception;

import org.apache.http.HttpStatus;

import java.util.Date;

public class ErrorDetails {
	private Date timestamp;
	private String message;
	private HttpStatus status;
	private String details;
	private String error;
	
	public ErrorDetails(Date timestamp, String message, String details) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}

	public ErrorDetails(HttpStatus status, String message, String error) {
		super();
		this.status = status;
		this.message = message;
		this.error = error;
	}
	
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
}
