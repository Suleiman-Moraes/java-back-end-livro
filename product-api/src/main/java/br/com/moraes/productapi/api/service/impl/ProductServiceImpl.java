package br.com.moraes.productapi.api.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.moraes.productapi.api.data.model.Product;
import br.com.moraes.productapi.api.repository.ProductRepository;
import br.com.moraes.productapi.api.service.ProductService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService{

	@Getter
	@Autowired
	private ProductRepository repository;

	@Override
	public Logger getLogger() {
		return log;
	}

	@Override
	public List<Product> findByCategoryId(Long categoryId) {
		return repository.findByCategoryId(categoryId);
	}

	@Override
	public Optional<Product> findTopByProductIdentifier(String productIdentifier) {
		return repository.findTopByProductIdentifier(productIdentifier);
	}
}
