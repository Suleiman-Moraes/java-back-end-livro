package br.com.moraes.productapi.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.moraes.productapi.api.data.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	@Query(value = "SELECT new Product(p.nome, p.preco, p.productIdentifier, p.descricao) "
			+ "FROM Product p JOIN Category c ON p.category.id = c.id WHERE c.id = ?1")
	List<Product> findByCategoryId(Long categoryId);

	Optional<Product> findTopByProductIdentifier(String productIdentifier);
}
