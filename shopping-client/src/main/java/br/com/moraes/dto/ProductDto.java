package br.com.moraes.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class ProductDto {

	@NotBlank
	private String productIdentifier;

	@NotBlank
	private String nome;

	private String descricao;

	@NotNull
	private Float preco;

	@NotNull
	private CategoryDto category;
}