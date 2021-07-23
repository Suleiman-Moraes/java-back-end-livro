package br.com.moraes.shoppingapi.api.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.ValidationException;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.moraes.dto.ShopReportDto;
import br.com.moraes.shoppingapi.api.data.model.Shop;
import br.com.moraes.shoppingapi.api.repository.ShopRepository;
import br.com.moraes.shoppingapi.api.service.ProductService;
import br.com.moraes.shoppingapi.api.service.ShopService;
import br.com.moraes.shoppingapi.api.service.UserService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ShopServiceImpl implements ShopService {

	@Getter
	@Autowired
	private ShopRepository repository;

	@Autowired
	private ProductService productService;

	@Autowired
	private UserService userService;

	@Override
	public Logger getLogger() {
		return log;
	}

	@Override
	public List<Shop> findAllByUserIdentifier(String userIdentifier) {
		return repository.findAllByUserIdentifier(userIdentifier);
	}

	@Override
	public List<Shop> findAllByTotalGreaterThan(Float total) {
		return repository.findAllByTotalGreaterThan(total);
	}

	@Override
	public List<Shop> findAllByDateGreaterThan(Date date) {
		return repository.findAllByDateGreaterThan(date);
	}

	@Override
	public void preSave(Shop objeto) {
		if (userService.findTopByCpf(objeto.getUserIdentifier()) == null) {
			throw new ValidationException("Usuário não identificado.");
		}
		if (!productService.validateProducts(objeto.getItems())) {
			throw new ValidationException("Produto(s) inválido(s).");
		}

		if (objeto.getId() == null) {
			objeto.setTotal(new Float(objeto.getItems().stream().mapToDouble(i -> i.getPrice()).sum()));
			objeto.setDate(new Date());
		}
	}

	@Override
	public List<Shop> getShopsByFilters(Date dataInicio, Date dataFim, Float valorMinimo) {
		return repository.getShopsByFilters(dataInicio, dataFim, valorMinimo);
	}

	@Override
	public Optional<ShopReportDto> getReportByDate(Date dataInicio, Date dataFim) {
		return repository.getReportByDate(dataInicio, dataFim);
	}
}
