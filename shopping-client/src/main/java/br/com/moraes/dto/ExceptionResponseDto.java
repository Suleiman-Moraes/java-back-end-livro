package br.com.moraes.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class ExceptionResponseDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Date timestamp;
	
	private List<String> messages;

	private String messageDevelop;

	private String details;

	@JsonIgnore
	private HttpStatus httpStatus;

	public String getHttpErro() {
		if (httpStatus != null) {
			return String.format("%s - %s", httpStatus.value(), httpStatus.getReasonPhrase());
		}
		return String.format("%s - %s", HttpStatus.INTERNAL_SERVER_ERROR.value(),
				HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
	}
}
