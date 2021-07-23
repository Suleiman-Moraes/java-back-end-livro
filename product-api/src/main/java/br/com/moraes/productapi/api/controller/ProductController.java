package br.com.moraes.productapi.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.moraes.dto.ProductDto;
import br.com.moraes.exception.ResourceNotFoundException;
import br.com.moraes.productapi.api.converter.DtoConverter;
import br.com.moraes.productapi.api.data.model.Product;
import br.com.moraes.productapi.api.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService service;
	
	@GetMapping
	public ResponseEntity<List<ProductDto>> findAll() {
		final List<Product> objetos = service.findAll();
		if (CollectionUtils.isEmpty(objetos)) {
			throw new ResourceNotFoundException();
		}
		return ResponseEntity.ok(objetos.stream().map(o -> DtoConverter.convert(o)).collect(Collectors.toList()));
	}
	
	@GetMapping("/category/{categoryId}")
	public ResponseEntity<List<ProductDto>> findByCategoryId(@PathVariable long categoryId) {
		final List<Product> objetos = service.findByCategoryId(categoryId);
		if (CollectionUtils.isEmpty(objetos)) {
			throw new ResourceNotFoundException();
		}
		return ResponseEntity.ok(objetos.stream().map(o -> DtoConverter.convert(o)).collect(Collectors.toList()));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProductDto> findById(@PathVariable long id) {
		return ResponseEntity
				.ok(DtoConverter.convert(service.findById(id).orElseThrow(() -> new ResourceNotFoundException())));
	}

	@PostMapping
	public ResponseEntity<ProductDto> create(@Valid @RequestBody ProductDto dto) {
		final Product objeto = service.create(Product.convert(dto));
		return ResponseEntity.status(HttpStatus.CREATED).body(DtoConverter.convert(objeto));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
