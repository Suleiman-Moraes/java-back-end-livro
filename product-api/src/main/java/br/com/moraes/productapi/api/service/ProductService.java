package br.com.moraes.productapi.api.service;

import java.util.List;
import java.util.Optional;

import br.com.moraes.interfaces.CrudPadraoService;
import br.com.moraes.productapi.api.data.model.Product;

public interface ProductService extends CrudPadraoService<Product>{

	List<Product> findByCategoryId(Long categoryId);

	Optional<Product> findTopByProductIdentifier(String productIdentifier);
}
