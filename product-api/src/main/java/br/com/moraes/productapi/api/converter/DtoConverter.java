package br.com.moraes.productapi.api.converter;

import br.com.moraes.dto.CategoryDto;
import br.com.moraes.dto.ProductDto;
import br.com.moraes.productapi.api.data.model.Category;
import br.com.moraes.productapi.api.data.model.Product;

public class DtoConverter {
	
	public static CategoryDto convert(Category category) {
		CategoryDto categoryDTO = new CategoryDto();
		categoryDTO.setId(category.getId());
		categoryDTO.setNome(category.getNome());
		return categoryDTO;
	}

	public static ProductDto convert(Product product) {
		ProductDto productDTO = new ProductDto();
		productDTO.setProductIdentifier(product.getProductIdentifier());
		productDTO.setNome(product.getNome());
		productDTO.setPreco(product.getPreco());
		if (product.getCategory() != null) {
			productDTO.setCategory(convert(product.getCategory()));
		}
		return productDTO;
	}
}
