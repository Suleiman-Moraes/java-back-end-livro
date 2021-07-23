package br.com.moraes.productapi.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.moraes.productapi.api.data.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

}
