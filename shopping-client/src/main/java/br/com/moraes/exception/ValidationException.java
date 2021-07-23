package br.com.moraes.exception;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import lombok.Getter;

@Getter
public class ValidationException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	private List<String> erros = new LinkedList<>();
	
	public ValidationException(String ...erros) {
		this.erros.addAll(Arrays.asList(erros));
	}
	
	public ValidationException(List<String> erros) {
		this.erros.addAll(erros);
	}
}
