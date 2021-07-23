package br.com.moraes.interfaces;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;

import br.com.moraes.exception.IncorrectDataEnteredException;
import br.com.moraes.exception.ResourceNotFoundException;

public interface CrudPadraoService<MODEL extends IModel> extends ICrudPadraoService<MODEL> {

	JpaRepository<MODEL, Long> getRepository();

	Logger getLogger();

	@Override
	@Transactional(readOnly = true)
	default Optional<MODEL> findById(Long id) {
		return getRepository().findById(Long.valueOf(id));
	}

	@Override
	@Transactional(readOnly = true)
	default List<MODEL> findAll() {
		return getRepository().findAll();
	}

	@Override
	@Transactional(readOnly = true)
	default Page<MODEL> findAll(Pageable pageable) {
		final Page<MODEL> page = getRepository().findAll(pageable);
		return page;
	}

	@Override
	@Transactional
	default MODEL create(MODEL objeto) {
		return save(objeto);
	}

	@Override
	@Transactional
	default MODEL update(MODEL objeto, Long id) throws Exception {
		if (!getRepository().existsById(id)) {
			throw new ResourceNotFoundException();
		}
		objeto.setId(id);
		return save(objeto);
	}

	@Override
	@Transactional
	default void delete(Long id) {
		if(!getRepository().existsById(id)) {
			throw new ResourceNotFoundException(HttpStatus.GONE);
		}
		getRepository().deleteById(id);
	}

	@Override
	@Transactional
	default MODEL save(MODEL model) {
		try {
			preSave(model);
			model = getRepository().save(model);
			posSave(model);
			return model;
		} catch (DataIntegrityViolationException e) {
			getLogger().warn("save " + ExceptionUtils.getRootCauseMessage(e));
			throw new IncorrectDataEnteredException("Dados inseridos de forma incorreta.", e);
		} catch (Exception e) {
			getLogger().warn("save " + e.getMessage());
			throw e;
		}
	}

	default void preSave(MODEL objeto) {
	}

	default void posSave(MODEL objeto) {
	}
}
