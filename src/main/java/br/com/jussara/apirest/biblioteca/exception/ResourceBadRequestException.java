package br.com.jussara.apirest.biblioteca.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ResourceBadRequestException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public ResourceBadRequestException(String message) {
		super(message);
	}
}