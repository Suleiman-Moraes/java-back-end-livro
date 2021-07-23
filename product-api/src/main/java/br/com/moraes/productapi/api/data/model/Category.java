package br.com.moraes.productapi.api.data.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.moraes.dto.CategoryDto;
import br.com.moraes.interfaces.IModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "category", schema = "products")
public class Category implements IModel{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;

	public static Category convert(CategoryDto categoryDTO) {
		Category category = new Category();
		category.setId(categoryDTO.getId());
		category.setNome(categoryDTO.getNome());
		return category;
	}
}
