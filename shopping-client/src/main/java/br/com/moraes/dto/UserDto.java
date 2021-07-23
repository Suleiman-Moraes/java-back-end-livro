package br.com.moraes.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class UserDto {

	private String nome;
	private String cpf;
	private String endereco;
	private String email;
	private String telefone;
	private String key;
	private Date dataCadastro;
}
