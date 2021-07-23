package br.com.moraes.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private static final String MENSAGEM_GENERICA = "Registro não encontrado.";
	
	private HttpStatus httpStatus = HttpStatus.NOT_FOUND;

	public ResourceNotFoundException(HttpStatus httpStatus) {
		this();
		this.httpStatus = httpStatus;
	}
	
	public ResourceNotFoundException() {
		this(MENSAGEM_GENERICA);
	}

	public ResourceNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public ResourceNotFoundException(String message) {
		super(message);
	}
}
