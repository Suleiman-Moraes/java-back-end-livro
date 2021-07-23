package br.com.moraes.productapi.api.data.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.moraes.dto.ProductDto;
import br.com.moraes.interfaces.IModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product", schema = "products")
public class Product implements IModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;

	private Float preco;

	private String descricao;

	@Column(name = "product_identifier")
	private String productIdentifier;

	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;
	
	public Product(String nome, Float preco, String productIdentifier, String descricao) {
		this.nome = nome;
		this.preco = preco;
		this.descricao = descricao;
		this.productIdentifier = productIdentifier;
	}

	public static Product convert(ProductDto productDTO) {
		Product product = new Product();
		product.setNome(productDTO.getNome());
		product.setPreco(productDTO.getPreco());
		product.setDescricao(productDTO.getDescricao());
		product.setProductIdentifier(productDTO.getProductIdentifier());
		if (productDTO.getCategory() != null) {
			product.setCategory(Category.convert(productDTO.getCategory()));
		}
		return product;
	}
}
