package br.com.moraes.shoppingapi.api.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import br.com.moraes.dto.ShopReportDto;
import br.com.moraes.interfaces.CrudPadraoService;
import br.com.moraes.shoppingapi.api.data.model.Shop;

public interface ShopService extends CrudPadraoService<Shop>{

	List<Shop> findAllByUserIdentifier(String userIdentifier);

	List<Shop> findAllByTotalGreaterThan(Float total);
	
	List<Shop> findAllByDateGreaterThan(Date date);
	
	List<Shop> getShopsByFilters(Date dataInicio, Date dataFim, Float valorMinimo);

	Optional<ShopReportDto> getReportByDate(Date dataInicio, Date dataFim);
}
