package br.com.moraes.shoppingapi.api.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import br.com.moraes.dto.ShopReportDto;
import br.com.moraes.shoppingapi.api.data.model.Shop;

public interface ReportRepository {

	List<Shop> getShopsByFilters(Date dataInicio, Date dataFim, Float valorMinimo);

	Optional<ShopReportDto> getReportByDate(Date dataInicio, Date dataFim);
}
