package br.com.moraes.userapi.api.service;

import java.util.List;
import java.util.Optional;

import br.com.moraes.interfaces.CrudPadraoService;
import br.com.moraes.userapi.api.data.model.User;

public interface UserService extends CrudPadraoService<User>{

	List<User> queryByName(String name);

	Optional<User> findTopByCpf(String cpf);
}
