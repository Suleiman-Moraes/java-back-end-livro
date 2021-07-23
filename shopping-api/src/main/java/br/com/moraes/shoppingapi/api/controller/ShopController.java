package br.com.moraes.shoppingapi.api.controller;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.moraes.dto.ShopDto;
import br.com.moraes.dto.ShopReportDto;
import br.com.moraes.exception.ResourceNotFoundException;
import br.com.moraes.shoppingapi.api.converter.DtoConverter;
import br.com.moraes.shoppingapi.api.data.model.Shop;
import br.com.moraes.shoppingapi.api.service.ShopService;

@RestController
@RequestMapping("/shopping")
public class ShopController {

	@Autowired
	private ShopService service;

	@GetMapping
	public ResponseEntity<List<ShopDto>> findAll() {
		final List<Shop> objetos = service.findAll();
		if (CollectionUtils.isEmpty(objetos)) {
			throw new ResourceNotFoundException();
		}
		return ResponseEntity.ok(objetos.stream().map(DtoConverter::convert).collect(Collectors.toList()));
	}

	@GetMapping("/user/{userIdentifier}")
	public ResponseEntity<List<ShopDto>> findAllByUserIdentifier(@PathVariable String userIdentifier) {
		final List<Shop> objetos = service.findAllByUserIdentifier(userIdentifier);
		if (CollectionUtils.isEmpty(objetos)) {
			throw new ResourceNotFoundException();
		}
		return ResponseEntity.ok(objetos.stream().map(DtoConverter::convert).collect(Collectors.toList()));
	}

	@GetMapping("/shopByDate")
	public ResponseEntity<List<ShopDto>> getShops(@RequestBody ShopDto shopDTO) {
		final List<Shop> objetos = service.findAllByDateGreaterThan(shopDTO.getDate());
		if (CollectionUtils.isEmpty(objetos)) {
			throw new ResourceNotFoundException();
		}
		return ResponseEntity.ok(objetos.stream().map(DtoConverter::convert).collect(Collectors.toList()));
	}

	@GetMapping("/{id}")
	public ResponseEntity<ShopDto> findById(@PathVariable long id) {
		return ResponseEntity
				.ok(DtoConverter.convert(service.findById(id).orElseThrow(() -> new ResourceNotFoundException())));
	}

	@PostMapping
	public ResponseEntity<ShopDto> create(@Valid @RequestBody ShopDto dto) {
		final Shop objeto = service.create(Shop.convert(dto));
		return ResponseEntity.status(HttpStatus.CREATED).body(DtoConverter.convert(objeto));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/search")
	public ResponseEntity<List<ShopDto>> getShopsByFilter(
			@RequestParam(name = "dataInicio", required = true) @DateTimeFormat(pattern = "dd/MM/yyyy") Date dataInicio,
			@RequestParam(name = "dataFim", required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") Date dataFim,
			@RequestParam(name = "valorMinimo", required = false) Float valorMinimo) {
		final List<Shop> objetos = service.getShopsByFilters(dataInicio, dataFim, valorMinimo);
		if (CollectionUtils.isEmpty(objetos)) {
			throw new ResourceNotFoundException();
		}
		return ResponseEntity.ok(objetos.stream().map(DtoConverter::convert).collect(Collectors.toList()));
	}

	@GetMapping("/report")
	public ResponseEntity<ShopReportDto> getReportByDate(
			@RequestParam(name = "dataInicio", required = true) @DateTimeFormat(pattern = "dd/MM/yyyy") Date dataInicio,
			@RequestParam(name = "dataFim", required = true) @DateTimeFormat(pattern = "dd/MM/yyyy") Date dataFim) {
		return ResponseEntity
				.ok(service.getReportByDate(dataInicio, dataFim).orElseThrow(() -> new ResourceNotFoundException()));
	}
}
