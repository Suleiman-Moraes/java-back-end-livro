package br.com.moraes.shoppingapi.api.repository.impl;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.moraes.dto.ShopReportDto;
import br.com.moraes.shoppingapi.api.data.model.Shop;
import br.com.moraes.shoppingapi.api.repository.ReportRepository;

public class ReportRepositoryImpl implements ReportRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Shop> getShopsByFilters(Date dataInicio, Date dataFim, Float valorMinimo) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT s ");
		sb.append("FROM shop s ");
		sb.append("WHERE s.date >= :dataInicio ");
		if (dataFim != null) {
			sb.append("AND s.date <= :dataFim ");
		}
		if (valorMinimo != null) {
			sb.append("AND s.total <= :valorMinimo ");
		}
		TypedQuery<Shop> query = entityManager.createQuery(sb.toString(), Shop.class);
		query.setParameter("dataInicio", dataInicio);
		if (dataFim != null) {
			query.setParameter("dataFim", dataFim);
		}
		if (valorMinimo != null) {
			query.setParameter("valorMinimo", valorMinimo);
		}
		return query.getResultList();
	}

	@Override
	public Optional<ShopReportDto> getReportByDate(Date dataInicio, Date dataFim) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT COUNT(sp.id), SUM(sp.total), AVG(sp.total) ");
		sb.append("FROM shopping.shop sp ");
		sb.append("WHERE  sp.date >= :dataInicio ");
		sb.append("AND sp.date <= :dataFim ");
		Query query = entityManager.createNativeQuery(sb.toString());
		query.setParameter("dataInicio", dataInicio);
		query.setParameter("dataFim", dataFim);
		Object[] result = (Object[]) query.getSingleResult();
		ShopReportDto shopReportDTO = new ShopReportDto();
		shopReportDTO.setCount(((BigInteger) result[0]).intValue());
		shopReportDTO.setTotal((Double) result[1]);
		shopReportDTO.setMean((Double) result[2]);
		return Optional.of(shopReportDTO);
	}

}
