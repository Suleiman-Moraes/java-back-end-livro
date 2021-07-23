package br.com.moraes.interfaces;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICrudPadraoService<MODEL extends IModel> {
	
	Optional<MODEL> findById(Long id);

	List<MODEL> findAll();

	MODEL create(MODEL objeto);

	MODEL update(MODEL objeto, Long id) throws Exception;
	
	MODEL save(MODEL objeto);

	void delete(Long id);

	Page<MODEL> findAll(Pageable pageable);
}
