package br.com.moraes.productapi.api.service.impl;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.moraes.productapi.api.repository.CategoryRepository;
import br.com.moraes.productapi.api.service.CategoryService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService{

	@Getter
	@Autowired
	private CategoryRepository repository;

	@Override
	public Logger getLogger() {
		return log;
	}
}
