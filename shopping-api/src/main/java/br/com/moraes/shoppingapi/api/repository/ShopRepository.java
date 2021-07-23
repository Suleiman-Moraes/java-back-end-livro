package br.com.moraes.shoppingapi.api.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.moraes.shoppingapi.api.data.model.Shop;

public interface ShopRepository extends JpaRepository<Shop, Long>, ReportRepository{
	
	List<Shop> findAllByUserIdentifier(String userIdentifier);

	List<Shop> findAllByTotalGreaterThan(Float total);
	
	List<Shop> findAllByDateGreaterThan(Date date);
}
