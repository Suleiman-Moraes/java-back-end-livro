package br.com.moraes.shoppingapi.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.moraes.dto.ProductDto;
import br.com.moraes.shoppingapi.api.data.model.Item;
import br.com.moraes.shoppingapi.api.service.ProductService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {
	
	@Value("${PRODUCT_API_URL:http://localhost:8081}")
	private String apiURL;

	@Override
	public ProductDto findTopByProductIdentifier(String productIdentifier) {
		RestTemplate restTemplate = new RestTemplate();
		final String url = apiURL + "/product/" + productIdentifier;

		ResponseEntity<ProductDto> response = restTemplate.getForEntity(url, ProductDto.class);
		if (response.getStatusCodeValue() >= 300) {
			log.warn("Erro no findTopByProductIdentifier com status == {}.", response.getStatusCodeValue());
			return null;
		}
		return response.getBody();
	}

	@Override
	public Boolean validateProducts(List<Item> items) {
		for (Item item : items) {
			ProductDto productDTO = findTopByProductIdentifier(item.getProductIdentifier());
			if (productDTO == null) {
				return Boolean.FALSE;
			}
			item.setPrice(productDTO.getPreco());
		}
		return Boolean.TRUE;
	}
}
