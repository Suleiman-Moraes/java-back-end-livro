package br.com.moraes.shoppingapi.api.service;

import br.com.moraes.dto.UserDto;

public interface UserService {

	UserDto findTopByCpf(String cpf);
}
