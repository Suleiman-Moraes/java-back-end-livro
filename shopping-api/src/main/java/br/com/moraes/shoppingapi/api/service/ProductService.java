package br.com.moraes.shoppingapi.api.service;

import java.util.List;

import br.com.moraes.dto.ProductDto;
import br.com.moraes.shoppingapi.api.data.model.Item;

public interface ProductService {

	ProductDto findTopByProductIdentifier(String productIdentifier);

	Boolean validateProducts(List<Item> items);
}
