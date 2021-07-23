package br.com.moraes.handler;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import br.com.moraes.dto.ExceptionResponseDto;
import br.com.moraes.exception.IncorrectDataEnteredException;
import br.com.moraes.exception.ResourceNotFoundException;
import br.com.moraes.exception.ValidationException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class CustomizedResponseEntityExceptionHandlerGeneric {

	@Autowired
	protected MessageSource messageSource;

	@ExceptionHandler({ Exception.class })
	public final ResponseEntity<ExceptionResponseDto> handleAllExceptions(Exception exception, WebRequest webRequest) {
		final HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		return tratar(exception, webRequest, httpStatus);
	}

	@ExceptionHandler({ ResourceNotFoundException.class })
	public final ResponseEntity<ExceptionResponseDto> handleNotFoundException(ResourceNotFoundException exception,
			WebRequest webRequest) {
		final HttpStatus httpStatus = exception.getHttpStatus();
		return tratar(exception, webRequest, httpStatus);
	}

	@ExceptionHandler({ EmptyResultDataAccessException.class })
	public final ResponseEntity<ExceptionResponseDto> handleEmptyResultDataAccessException(Exception exception,
			WebRequest webRequest) {
		final HttpStatus httpStatus = HttpStatus.GONE;
		return tratar(exception, webRequest, httpStatus);
	}

	@ExceptionHandler(ValidationException.class)
	public final ResponseEntity<ExceptionResponseDto> handleValidationException(ValidationException exception,
			WebRequest webRequest) {
		return handleBadRequestExceptions(exception, webRequest, exception.getErros());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public final ResponseEntity<ExceptionResponseDto> handleMethodArgumentNotValidException(
			MethodArgumentNotValidException exception, WebRequest webRequest) {
		final BindingResult bindingResult = exception.getBindingResult();
		final List<String> erros = new LinkedList<>();
		if (bindingResult.hasErrors()) {
			bindingResult.getFieldErrors().parallelStream()
					.forEach(fieldError -> erros.add(fieldError.getField() + " - " + fieldError.getDefaultMessage()));
		}
		return handleBadRequestExceptions(exception, webRequest, erros);
	}

	@ExceptionHandler({ DataIntegrityViolationException.class, IncorrectDataEnteredException.class })
	public final ResponseEntity<ExceptionResponseDto> handleBadRequestExceptions(Exception exception,
			WebRequest webRequest) {
		List<String> erros = new LinkedList<>();
		erros.add(exception.getMessage());
		return handleBadRequestExceptions(exception, webRequest, erros);
	}

	protected final ResponseEntity<ExceptionResponseDto> handleBadRequestExceptions(Exception exception,
			WebRequest webRequest, List<String> erros) {
		final HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		return tratar(exception, webRequest, httpStatus, erros);
	}

	protected ResponseEntity<ExceptionResponseDto> tratar(Exception exception, WebRequest webRequest,
			HttpStatus httpStatus) {
		List<String> erros = new LinkedList<>();
		erros.add(exception.getMessage());
		return tratar(exception, webRequest, httpStatus, erros);
	}

	protected ResponseEntity<ExceptionResponseDto> tratar(Exception exception, WebRequest webRequest,
			HttpStatus httpStatus, List<String> erros) {
		final ExceptionResponseDto exceptionResponse = new ExceptionResponseDto(new Date(), erros,
				ExceptionUtils.getRootCauseMessage(exception), webRequest.getDescription(Boolean.FALSE), httpStatus);
		StringBuilder ers = new StringBuilder();
		erros.parallelStream().forEach(e -> ers.append(e).append(" && "));
		log.error("Erro durante requisição - |{}| ## Detalhe do Erro: |{}|", ers.toString().replaceAll(" && $", ""),
				exceptionResponse.getMessageDevelop());
		return ResponseEntity.status(httpStatus).body(exceptionResponse);
	}

	protected Logger getLogger() {
		return log;
	}
}
